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
        
        try {
            Server server = ServerBuilder.forPort(50052)
                    .addService(new SeafoodMonitoringServiceImpl())
                    .build();

            server.start();
            System.out.println("SeafoodMonitoringService is running on port 50052");

            server.awaitTermination();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    
   
