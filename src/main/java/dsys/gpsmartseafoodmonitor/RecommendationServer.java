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
        
        //Start of Handling Exceptions - I saw this in Algorithm class
        try {
            // Create new server port different from the Monitor and Seafood
            Server server = ServerBuilder.forPort(50053)
                    .addService(new RecommendationServiceImpl())
                    .build();

            // Start server
            server.start();
            System.out.println("RecommendationService is running on port 50053");
            
            // JmDNS
            SmartSeafoodServiceRegistration.registerService(
                    "_recommendation._tcp.local.",
                    "RecommendationService",
                    50053,
                    "Recommendation gRPC Service"
            );

            // Keep server RUNNING
            server.awaitTermination();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    

