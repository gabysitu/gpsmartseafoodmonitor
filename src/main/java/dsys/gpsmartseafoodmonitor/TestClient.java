/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsys.gpsmartseafoodmonitor;

/**
 *
 * @author gport
 */


// Import generated gRPC classes
import generated.grpc.oceanmonitoring.Location;
import generated.grpc.oceanmonitoring.OceanData;
import generated.grpc.oceanmonitoring.OceanMonitoringServiceGrpc;

// Import gRPC 
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

//Import JmDNS
import javax.jmdns.ServiceInfo;

public class TestClient {
     public static void main(String[] args) {
         
         // Discover Ocean Monitoring service
        ServiceInfo serviceInfo = SmartSeafoodServiceDiscovery.discoverService("_oceanmonitoring._tcp.local.");

        // Check if service was found
        if (serviceInfo == null) {
            System.out.println("OceanMonitoringService not found.");
            return;
        }

        // Get host and port from discovered service
        String host = serviceInfo.getHostAddresses()[0];
        int port = serviceInfo.getPort();

        System.out.println("Discovered OceanMonitoringService at " + host + ":" + port);

        // Create gRPC channel using discovered host and port
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        //Start of Handling Exceptions - I saw this in Algorithm class
        try {
            // Create blocking stub
            OceanMonitoringServiceGrpc.OceanMonitoringServiceBlockingStub stub =
                    OceanMonitoringServiceGrpc.newBlockingStub(channel);

            // Build request
            Location request = Location.newBuilder()
                    .setLocation("Dublin")
                    .build();

            // Call unary RPC
            OceanData response = stub.currentOceanConditions(request);

            // Expected: print ocean values returned by server
            System.out.println("Ocean Conditions for Dublin:");
            System.out.println("Temperature: " + response.getTemperatureC());
            System.out.println("Oxygen Level: " + response.getOxygenLevel());
            System.out.println("pH: " + response.getAcidityPH());
            System.out.println("Pollution Level: " + response.getPollutionLevel());

        } catch (Exception e) {
            e.printStackTrace();
            //This part of the catch/Try exception is very important beacause it will finish even if an exception is thrown or not
        } finally {
            channel.shutdown();
        }
    }
}
        

        