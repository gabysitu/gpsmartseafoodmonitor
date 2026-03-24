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


// Import gRPC
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

        // Check the rules that I am applying for the server
        SeafoodEvaluation evaluation = buildEvaluation(species, location);

        // Send result to client
        responseObserver.onNext(evaluation);
        responseObserver.onCompleted();
    }
    
//Comment for me: Right now I can test one specie now for the GUI how can the client send multiple sepecies?    
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
                
                //Evaluation according to the rules
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
            
            //If something goes wrong send a message to the client
            @Override
            public void onError(Throwable t) {
                System.err.println("Error in client stream: " + t.getMessage());
            }
            
            //Call this method when the client sends all the request and build a summary with all the information
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

                // Send final summary back to client
                responseObserver.onNext(summary);
                responseObserver.onCompleted();
            }
        };
    }

    // In this step I am setting the rules: The seafood is safe? its sustainable?
    private SeafoodEvaluation buildEvaluation(String species, String location) {
        
        //First: Everything is safe
        boolean safeToEat = true;
        boolean sustainable = true;
        String alarm = "Safe";

        // Second: Contamination example
        if (location.equals("dublin") && species.equals("prawn")) {
            safeToEat = false;
            alarm = "Contaminated";
        }

        // Thirs: Sustainability example
        if (species.equals("tuna")) {
            sustainable = false;
            alarm = "Unsustainable fishing practices";
        }

        // Fourth: If the specie is not sustainable send another specie
        if (species.equals("mussels") || species.equals("sardines")) {
            sustainable = true;
        }
        
        //Fith: Lastly send the summary back
        return SeafoodEvaluation.newBuilder()
                .setSpecies(species)
                .setSafeToEat(safeToEat)
                .setSustainable(sustainable)
                .setAlarm(alarm)
                .build();
    }
}  
    
    
