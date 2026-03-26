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

//Import JmDNS
import javax.jmdns.ServiceInfo;

//Implement the Seafood Monitor Service
public class SeafoodMonitoringServiceImpl extends SeafoodMonitoringServiceGrpc.SeafoodMonitoringServiceImplBase {
    
  
    //Client seds one seafood item
    //Seafood service - Ocean Service
    
    @Override
    public void evaluateSeafood(SeafoodRequest request, StreamObserver<SeafoodEvaluation> responseObserver) {

        // Extract the input send by client
        String species = request.getSpecies().trim().toLowerCase();
        String location = request.getLocation().trim().toLowerCase();

        // Validate if there is not errors
        if (species.isEmpty() || location.isEmpty()) {
            responseObserver.onError(
                Status.INVALID_ARGUMENT
                        .withDescription("Species and location cannot be empty")
                        .asRuntimeException()
            );
            return;
        }
        
        //Start of Handling Exceptions - I saw this in Algorithm class
        try {
            // Discover and call Ocean service to get current ocean conditions
            OceanData oceanData = getOceanData(location);

            // Build seafood evaluation using ocean conditions
            SeafoodEvaluation evaluation = buildEvaluation(species, location, oceanData);

            // Send result back to client
            responseObserver.onNext(evaluation);
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(
                Status.INTERNAL
                        .withDescription("Failed to evaluate seafood using discovered Ocean service: " + e.getMessage())
                        .withCause(e)
                        .asRuntimeException()
            );
        }
    }

    //Comment for future Gaby(me) - lest solve this for GUI
    //Client sends multiple seafood items
    //Sends back a summary
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
                    // Discover Ocean service and get conditions for each item
                    OceanData oceanData = getOceanData(request.getLocation().trim().toLowerCase());

                    // Evaluate the seafood item
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

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in client stream: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                // Build final summary after receiving all items
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

   
    // Discover OceanMonitoringService using JmDNS
    // Then call it using gRPC  
    private OceanData getOceanData(String location) {

        // Discover the Ocean service dynamically
        ServiceInfo serviceInfo = SmartSeafoodServiceDiscovery.discoverService("_oceanmonitoring._tcp.local.");

        // If service not found, throw error
        if (serviceInfo == null) {
            throw new RuntimeException("OceanMonitoringService not found via JmDNS");
        }

        // Get host and port from discovered service
        String host = serviceInfo.getHostAddresses()[0];
        int port = serviceInfo.getPort();

        System.out.println("Seafood service discovered Ocean service at " + host + ":" + port);

        // Create gRPC channel to Ocean service
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();

        try {
           
            OceanMonitoringServiceGrpc.OceanMonitoringServiceBlockingStub stub =
                    OceanMonitoringServiceGrpc.newBlockingStub(channel);

            Location request = Location.newBuilder()
                    .setLocation(location)
                    .build();

            // Call Ocean unary RPC
            return stub.currentOceanConditions(request);
            
//This part of the catch/Try exception is very important beacause it will finish even if an exception is thrown or not
        } finally {
            channel.shutdown();
        }
    }

   
    // Evaluate seafood using species and ocean conditions
    private SeafoodEvaluation buildEvaluation(String species, String location, OceanData oceanData) {

        //If everything is okay lets send this values
        boolean safeToEat = true;
        boolean sustainable = true;
        String alarm = "Safe";

        // If pollution is high, seafood becomes unsafe
        if (oceanData.getPollutionLevel() > 0.5) {
            safeToEat = false;
            alarm = "Contaminated due to high pollution";
        }

        // If oxygen is too low, ecosystem is unhealthy
        if (oceanData.getOxygenLevel() < 6.5) {
            safeToEat = false;
            alarm = "Unsafe due to low oxygen levels";
        }

        // For testing purposes Tuna is treated as unsustainable
        if (species.equals("tuna")) {
            sustainable = false;
            alarm = "Unsustainable fishing practices";
        }

        // For testing purposes Mussels and sardines are treated as sustainable
        if (species.equals("mussels") || species.equals("sardines")) {
            sustainable = true;
        }

        // Example location-specific rule
        if (location.equals("dublin") && species.equals("prawn")) {
            safeToEat = false;
            alarm = "Contaminated";
        }

        // Build final response
        return SeafoodEvaluation.newBuilder()
                .setSpecies(species)
                .setSafeToEat(safeToEat)
                .setSustainable(sustainable)
                .setAlarm(alarm)
                .build();
    }
} 
        
        
