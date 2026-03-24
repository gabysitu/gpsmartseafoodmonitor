/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsys.gpsmartseafoodmonitor;

/**
 *
 * @author gport
 */

// Import generated classes from SeafoodMonitoringService.proto file
import generated.grpc.seafoodmonitoring.BatchSummary;
import generated.grpc.seafoodmonitoring.SeafoodEvaluation;
import generated.grpc.seafoodmonitoring.SeafoodMonitoringServiceGrpc;
import generated.grpc.seafoodmonitoring.SeafoodRequest;

//And Import generated classes from OceanMonitoringService.proto file
import generated.grpc.oceanmonitoring.Location;
import generated.grpc.oceanmonitoring.OceanData;
import generated.grpc.oceanmonitoring.OceanMonitoringServiceGrpc;

// Import gRPC
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

//Implement the Seafood Monitor Service
public class SeafoodMonitoringServiceImpl extends SeafoodMonitoringServiceGrpc.SeafoodMonitoringServiceImplBase {
    
  
//In this step the client will send one request and the server will return a responce(evaluation)

    @Override
    public void evaluateSeafood(SeafoodRequest request, StreamObserver<SeafoodEvaluation> responseObserver) {

        // In this step I aim to get the species and location send by the user
        String species = request.getSpecies().trim().toLowerCase();
        String location = request.getLocation().trim().toLowerCase();

        // Check if the input is correct, if not send back to the user that those to camps cannot be empty
        if (species.isEmpty() || location.isEmpty()) {
            responseObserver.onError(
                Status.INVALID_ARGUMENT
                    .withDescription("Species and location cannot be empty")
                    .asRuntimeException()
            );
            return;
        }
        
        //Now in this step let's call OceanMonitoringService, so we can get Ocean conditions
        try {
           
            OceanData oceanData = getOceanData(location);

            // Lets create the evaluation using both species and ocean conditions
            SeafoodEvaluation evaluation = buildEvaluation(species, location, oceanData);

            // Send evaluation back to client
            responseObserver.onNext(evaluation);
            responseObserver.onCompleted();

        } catch (Exception e) {
            // If Ocean service fails, Seafood service returns an error
            responseObserver.onError(
                Status.INTERNAL
                    .withDescription("Failed to evaluate seafood using ocean data: " + e.getMessage())
                    .withCause(e)
                    .asRuntimeException()
            );
        }
    }


    //Comment for Gaby(me): Client send different inputs and the server will send back a several responses or summary?
    @Override
    public StreamObserver<SeafoodRequest> evaluateSeafoodBatch(StreamObserver<BatchSummary> responseObserver) {

        return new StreamObserver<SeafoodRequest>() {

            int totalItems = 0;
            int unsafeItems = 0;
            int unsustainableItems = 0;

            @Override
            public void onNext(SeafoodRequest request) {
                totalItems++;

                try {
                    // Get ocean conditions for each seafood item
                    OceanData oceanData = getOceanData(request.getLocation().trim().toLowerCase());

                    // Evaluate each item
                    SeafoodEvaluation evaluation = buildEvaluation(
                            request.getSpecies().trim().toLowerCase(),
                            request.getLocation().trim().toLowerCase(),
                            oceanData
                    );

                    // Count unsafe items
                    if (!evaluation.getSafeToEat()) {
                        unsafeItems++;
                    }

                    // Count unsustainable items
                    if (!evaluation.getSustainable()) {
                        unsustainableItems++;
                    }

                } catch (Exception e) {
                    System.err.println("Error evaluating batch item: " + e.getMessage());
                }
            }
            
            //If there is an error send back this message

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in client stream: " + t.getMessage());
            }
            
            //After the client sends all the items lets build a summary

            @Override
            public void onCompleted() {
                BatchSummary summary = BatchSummary.newBuilder()
                        .setTotalItems(totalItems)
                        .setUnsafeItems(unsafeItems)
                        .setUnsustainableItems(unsustainableItems)
                        .setOverallMessage(
                                unsafeItems + " unsafe item(s), " +
                                unsustainableItems + " unsustainable item(s) detected."
                        )
                        .build();

                // Send summary back to client
                responseObserver.onNext(summary);
                responseObserver.onCompleted();
            }
        };
    }

    //Lets try to connect the seafood server and ocean server
    private OceanData getOceanData(String location) {

        // Create channel to Ocean service
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        try {
            OceanMonitoringServiceGrpc.OceanMonitoringServiceBlockingStub stub =
                    OceanMonitoringServiceGrpc.newBlockingStub(channel);

            // location request
            Location request = Location.newBuilder()
                    .setLocation(location)
                    .build();

            // Call Ocean Monitor and get current ocean conditions
            return stub.currentOceanConditions(request);

        } finally {
            // Close channel after request
            channel.shutdown();
        }
    }

    // This method evaluates seafood according to ocean conditions and species rules
    private SeafoodEvaluation buildEvaluation(String species, String location, OceanData oceanData) {
        
        //First: if everything is perfect send this
        boolean safeToEat = true;
        boolean sustainable = true;
        String alarm = "Safe";

        // Second: Unsafe if pollution level is high
        if (oceanData.getPollutionLevel() > 0.5) {
            safeToEat = false;
            alarm = "Contaminated due to high pollution";
        }

        // Third: Unsafe if oxygen level is too low (poor conditions)
        if (oceanData.getOxygenLevel() < 6.5) {
            safeToEat = false;
            alarm = "Unsafe due to low oxygen levels";
        }

        // Four: Unsustainable species example - overfishing
        if (species.equals("tuna")) {
            sustainable = false;
            alarm = "Unsustainable fishing practices";
        }

        // Five: Send sustainable options
        if (species.equals("mussels") || species.equals("sardines")) {
            sustainable = true;
        }

        // Test: Location and seafood item
        if (location.equals("dublin") && species.equals("prawn")) {
            safeToEat = false;
            alarm = "Contaminated";
        }

        // Build response
        return SeafoodEvaluation.newBuilder()
                .setSpecies(species)
                .setSafeToEat(safeToEat)
                .setSustainable(sustainable)
                .setAlarm(alarm)
                .build();
    }
}
        
        
        
