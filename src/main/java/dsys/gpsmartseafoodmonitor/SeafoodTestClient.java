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
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


//Import grpc classes
public class SeafoodTestClient {
    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        try {
            SeafoodMonitoringServiceGrpc.SeafoodMonitoringServiceBlockingStub stub =
                    SeafoodMonitoringServiceGrpc.newBlockingStub(channel);

            SeafoodRequest request = SeafoodRequest.newBuilder()
                    .setSpecies("Prawn")
                    .setLocation("Dublin")
                    .build();

            SeafoodEvaluation response = stub.evaluateSeafood(request);

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
    

