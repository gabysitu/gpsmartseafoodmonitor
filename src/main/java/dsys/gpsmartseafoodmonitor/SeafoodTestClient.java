/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsys.gpsmartseafoodmonitor;

/**
 *
 * @author gport
 */

//Import the generated classes by the proto file
import generated.grpc.seafoodmonitoring.SeafoodEvaluation;
import generated.grpc.seafoodmonitoring.SeafoodMonitoringServiceGrpc;
import generated.grpc.seafoodmonitoring.SeafoodRequest;

//import grpc 
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

//Import JmDNS
import javax.jmdns.ServiceInfo;

//Import grpc classes
public class SeafoodTestClient {
     public static void main(String[] args) {

        // Discover Seafood Monitoring service
        ServiceInfo serviceInfo = SmartSeafoodServiceDiscovery.discoverService("_seafoodmonitoring._tcp.local.");

        // Check if service was found
        if (serviceInfo == null) {
            System.out.println("SeafoodMonitoringService not found.");
            return;
        }

        // Get discovered host and port
        String host = serviceInfo.getHostAddresses()[0];
        int port = serviceInfo.getPort();

        System.out.println("Discovered SeafoodMonitoringService at " + host + ":" + port);

        // Create channel to discovered service
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        
        //Start of Handling Exceptions - I saw this in Algorithm class
        try {
           
            SeafoodMonitoringServiceGrpc.SeafoodMonitoringServiceBlockingStub stub =
                    SeafoodMonitoringServiceGrpc.newBlockingStub(channel);


            SeafoodRequest request = SeafoodRequest.newBuilder()
                    .setSpecies("Tuna")
                    .setLocation("Dublin")
                    .build();

            
            SeafoodEvaluation response = stub.evaluateSeafood(request);

            // seafood evaluation
            System.out.println("Seafood Evaluation:");
            System.out.println("Species: " + response.getSpecies());
            System.out.println("Safe to eat: " + response.getSafeToEat());
            System.out.println("Sustainable: " + response.getSustainable());
            System.out.println("Alarm: " + response.getAlarm());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channel.shutdown();
        }
    }
}
    
    
   