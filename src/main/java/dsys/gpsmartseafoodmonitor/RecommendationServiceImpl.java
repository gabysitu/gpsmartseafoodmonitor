/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsys.gpsmartseafoodmonitor;

/**
 *
 * @author gport
 */

//Import generated class from RecommendationService.proto
import generated.grpc.recommendation.RecommendationRequest;
import generated.grpc.recommendation.RecommendationResponse;
import generated.grpc.recommendation.RecommendationServiceGrpc;

//Import grpc
import io.grpc.stub.StreamObserver;

//Import Array
import java.util.ArrayList;
import java.util.List;


public class RecommendationServiceImpl extends RecommendationServiceGrpc.RecommendationServiceImplBase {
   @Override
    public StreamObserver<RecommendationRequest> liveRecommendations(
            StreamObserver<RecommendationResponse> responseObserver) {

        // Return an object per client requests
        return new StreamObserver<RecommendationRequest>() {
            
             // Called every time the client sends one recommendation request
            @Override
            public void onNext(RecommendationRequest request) {

                // Get request 
                String location = request.getLocation().trim().toLowerCase();
                String selectedSpecies = request.getSelectedSpecies().trim().toLowerCase();
                String reason = request.getReason().trim().toLowerCase();

                // Arrays to store recommendations 
                List<String> recommended = new ArrayList<>();
                List<String> avoid = new ArrayList<>();
                
                //Now lets set up the following rules:
                // Rule 1: If tuna gets selected, suggest more sustainable alternatives
                if (selectedSpecies.equals("tuna")) {
                    recommended.add("mussels");
                    recommended.add("sardines");
                    recommended.add("haddock");
                    avoid.add("tuna"); //do not give back tuna as option
                }

                // Rule 2: If prawn is unsafe in Dublin, suggest safer species
                else if (selectedSpecies.equals("prawn") && location.equals("dublin")) {
                    recommended.add("mussels");
                    recommended.add("cod");
                    recommended.add("sardines");
                    avoid.add("prawn"); //do not give back prawn as option
                }

                // Rule 3: If contamination/unsafe reason is given suggest safer options
                else if (reason.contains("contaminated") || reason.contains("unsafe")) {
                    recommended.add("mussels");
                    recommended.add("sardines");
                    avoid.add(selectedSpecies);
                }

                // Default rule: suggest sustainable species
                else {
                    recommended.add("mussels");
                    recommended.add("sardines");
                    recommended.add("hake");
                    avoid.add("shark");
                    avoid.add("Turtle");
                }

                // Build response message
                RecommendationResponse response = RecommendationResponse.newBuilder()
                        .addAllRecommendedSpecies(recommended)
                        .addAllAvoidSpecies(avoid)
                        .build();

                // Send response back to client immediately
                responseObserver.onNext(response);
            }
            
            // If something goes wrong send back an error message
            @Override
            public void onError(Throwable t) {
                System.err.println("Error in recommendation stream: " + t.getMessage());
            }

            //when the requests are completed
            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
} 

