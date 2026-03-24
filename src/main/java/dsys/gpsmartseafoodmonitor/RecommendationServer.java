/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsys.gpsmartseafoodmonitor;

/**
 *
 * @author gport
 */

//Import grpc
import io.grpc.Server;
import io.grpc.ServerBuilder;


public class RecommendationServer {
    public static void main(String[] args) {
        try {
            // Create new server port different from the Monitor and Seafood
            Server server = ServerBuilder.forPort(50053)
                    .addService(new RecommendationServiceImpl())
                    .build();

            // Start server
            server.start();
            System.out.println("RecommendationService is running on port 50053");

            // Keep server RUNNING
            server.awaitTermination();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    

