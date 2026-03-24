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

public class SeafoodMonitoringServer {
    public static void main(String[] args) {
        
        //Create the server in a different port 
        try {
            Server server = ServerBuilder.forPort(50052)
                    .addService(new SeafoodMonitoringServiceImpl())
                    .build();
            
            //Start server
            server.start();
            System.out.println("SeafoodMonitoringService is running on port 50052");
            
            // Register service using JmDNS
            SmartSeafoodServiceRegistration.registerService(
                    "_seafoodmonitoring._tcp.local.",
                    "SeafoodMonitoringService",
                    50052,
                    "Seafood Monitoring gRPC Service"
            );
            
            //Keep server 
            server.awaitTermination();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    
   
