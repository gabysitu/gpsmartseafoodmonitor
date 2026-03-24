/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsys.gpsmartseafoodmonitor;

/**
 *
 * @author gport
 */

//Import classes
import generated.grpc.recommendation.RecommendationRequest;
import generated.grpc.recommendation.RecommendationResponse;
import generated.grpc.recommendation.RecommendationServiceGrpc;

//Import grpc
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class RecommendationTestClient {
    public static void main(String[] args) throws InterruptedException {

        // call port
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50053)
                .usePlaintext()
                .build();

        try {
 
            RecommendationServiceGrpc.RecommendationServiceStub stub =
                    RecommendationServiceGrpc.newStub(channel);

            
            StreamObserver<RecommendationResponse> responseObserver = new StreamObserver<RecommendationResponse>() {

                @Override
                public void onNext(RecommendationResponse response) {
                    // Expected: print recommended species and species to avoid
                    System.out.println("Recommendation Response:");
                    System.out.println("Recommended Species: " + response.getRecommendedSpeciesList());
                    System.out.println("Avoid Species: " + response.getAvoidSpeciesList());
                    System.out.println("--------------------------------");
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Error from server: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("Recommendation stream completed.");
                }
            };

            // Open request stream to server
            StreamObserver<RecommendationRequest> requestObserver =
                    stub.liveRecommendations(responseObserver);

            // Send first request
            requestObserver.onNext(
                    RecommendationRequest.newBuilder()
                            .setLocation("Dublin")
                            .setSelectedSpecies("Tuna")
                            .setReason("Unsustainable")
                            .build()
            );

            // Send second request
            requestObserver.onNext(
                    RecommendationRequest.newBuilder()
                            .setLocation("Dublin")
                            .setSelectedSpecies("Prawn")
                            .setReason("Contaminated")
                            .build()
            );

            // Tell server client has finished sending messages
            requestObserver.onCompleted();

            // Tell the server to Wait a little for the responses
            Thread.sleep(3000);

        } finally {
            channel.shutdown();
        }
    }
}
    

