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


// Import JmDNS 
import javax.jmdns.ServiceInfo;

public class RecommendationTestClient {
     public static void main(String[] args) throws InterruptedException {

        // Discover Recommendation service
        ServiceInfo serviceInfo = SmartSeafoodServiceDiscovery.discoverService("_recommendation._tcp.local.");

        // Check if service was found
        if (serviceInfo == null) {
            System.out.println("RecommendationService not found.");
            return;
        }

        // Get discovered host and port
        String host = serviceInfo.getHostAddresses()[0];
        int port = serviceInfo.getPort();

        System.out.println("Discovered RecommendationService at " + host + ":" + port);

        // Create channel to discovered service
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();

        try {
          
            RecommendationServiceGrpc.RecommendationServiceStub stub =
                    RecommendationServiceGrpc.newStub(channel);

       
            StreamObserver<RecommendationResponse> responseObserver = new StreamObserver<RecommendationResponse>() {

                @Override
                public void onNext(RecommendationResponse response) {
                    // Expected: print recommendation response from server
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

            // Complete client stream
            requestObserver.onCompleted();

            // Wait for async responses
            Thread.sleep(3000);

        } finally {
            channel.shutdown();
        }
    }
}
    
    
    