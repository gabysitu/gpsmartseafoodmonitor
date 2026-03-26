/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsys.gpsmartseafoodmonitor;

/**
 *
 * @author gport
 */

// Import gRPC server classes
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class OceanMonitoringServer {
    
    public static void main(String[] args) {
        
        //Start of Handling Exceptions - I saw this in Algorithm class
        try {
            // Create server on port 50051
            Server server = ServerBuilder.forPort(50051)
                    .addService(new OceanMonitoringServiceImpl())
                    .build();

            // Start the server
            server.start();
            System.out.println("OceanMonitoringService is running on port 50051");
            
            //Modify this for JmDNS
             SmartSeafoodServiceRegistration.registerService(
                    "_oceanmonitoring._tcp.local.",
                    "OceanMonitoringService",
                    50051,
                    "Ocean Monitoring gRPC Service"
            );

            // Keep server running 
            server.awaitTermination();

            // If server fails 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    

