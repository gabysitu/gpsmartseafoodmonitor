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

// Import gRPC communication classes
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class TestClient {
     public static void main(String[] args) {

        // Create communication channel to server (localhost:50051)
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext() // No encryption (fine for testing)
                .build();

        try {
            // Create a blocking stub (client that waits for response)
            OceanMonitoringServiceGrpc.OceanMonitoringServiceBlockingStub stub =
                    OceanMonitoringServiceGrpc.newBlockingStub(channel);

            // Build request with location
            Location request = Location.newBuilder()
                    .setLocation("Dublin")
                    .build();

            // Call the server (UNARY RPC)
            OceanData response = stub.currentOceanConditions(request);

            // Expected: print returned ocean data
            System.out.println("Ocean Conditions for Dublin:");
            System.out.println("Temperature: " + response.getTemperatureC());
            System.out.println("Oxygen Level: " + response.getOxygenLevel());
            System.out.println("pH: " + response.getAcidityPH());
            System.out.println("Pollution Level: " + response.getPollutionLevel());

        } catch (Exception e) {
            // If call fails → print error
            e.printStackTrace();

        } finally {
            // Always close channel after use
            channel.shutdown();
        }
    }
}

