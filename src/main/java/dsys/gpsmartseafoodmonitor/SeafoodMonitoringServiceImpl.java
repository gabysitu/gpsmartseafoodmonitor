/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsys.gpsmartseafoodmonitor;

/**
 *
 * @author gport
 */

// Import generated classes from the proto file
import generated.grpc.seafoodmonitoring.BatchSummary;
import generated.grpc.seafoodmonitoring.SeafoodEvaluation;
import generated.grpc.seafoodmonitoring.SeafoodMonitoringServiceGrpc;
import generated.grpc.seafoodmonitoring.SeafoodRequest;


// gRPC
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

//Implement the service
public class SeafoodMonitoringServiceImpl extends SeafoodMonitoringServiceGrpc.SeafoodMonitoringServiceImplBase {
    
  
//In this step the client will send one request and the server will return a responce(evaluation)

    @Override
    public void evaluateSeafood(SeafoodRequest request, StreamObserver<SeafoodEvaluation> responseObserver) {

        // Read request values
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

        // Evaluate seafood using simple rules
        SeafoodEvaluation evaluation = buildEvaluation(species, location);

        // Send result to client
        responseObserver.onNext(evaluation);
        responseObserver.onCompleted();
    }
    
    //Client sends several request and the server will return a response (Summary)
    @Override
    public StreamObserver<SeafoodRequest> evaluateSeafoodBatch(StreamObserver<BatchSummary> responseObserver) {

        return new StreamObserver<SeafoodRequest>() {

            int totalItems = 0;
            int unsafeItems = 0;
            int unsustainableItems = 0;

            @Override
            public void onNext(SeafoodRequest request) {
                // Called every time client sends one seafood item
                totalItems++;

                SeafoodEvaluation evaluation = buildEvaluation(
                        request.getSpecies().trim().toLowerCase(),
                        request.getLocation().trim().toLowerCase()
                );

                // Count unsafe items
                if (!evaluation.getSafeToEat()) {
                    unsafeItems++;
                }

                // Count unsustainable items
                if (!evaluation.getSustainable()) {
                    unsustainableItems++;
                }
            }

            @Override
            public void onError(Throwable t) {
                // If client stream fails, print error on server side
                System.err.println("Error in client stream: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                // Create the summary with all the input send by the client
                BatchSummary summary = BatchSummary.newBuilder()
                        .setTotalItems(totalItems)
                        .setUnsafeItems(unsafeItems)
                        .setUnsustainableItems(unsustainableItems)
                        .setOverallMessage(
                                unsafeItems + " unsafe item(s), " +
                                unsustainableItems + " unsustainable item(s) detected."
                        )
                        .build();

                // Send final summary back to client
                responseObserver.onNext(summary);
                responseObserver.onCompleted();
            }
        };
    }

    // Helper method with simple evaluation rules
    private SeafoodEvaluation buildEvaluation(String species, String location) {

        boolean safeToEat = true;
        boolean sustainable = true;
        String alarm = "Safe";

        // Example unsafe rule
        if (location.equals("dublin") && species.equals("prawn")) {
            safeToEat = false;
            alarm = "Contaminated";
        }

        // Example sustainability rule
        if (species.equals("tuna")) {
            sustainable = false;
            alarm = "Unsustainable fishing practices";
        }

        // Another species considered more sustainable
        if (species.equals("mussels") || species.equals("sardines")) {
            sustainable = true;
        }

        return SeafoodEvaluation.newBuilder()
                .setSpecies(species)
                .setSafeToEat(safeToEat)
                .setSustainable(sustainable)
                .setAlarm(alarm)
                .build();
    }
}  
    
    
