/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsys.gpsmartseafoodmonitor;

/**
 *
 * @author gport
 */

// First I have to import generated classes from the proto 
import generated.grpc.oceanmonitoring.Location;
import generated.grpc.oceanmonitoring.OceanData;
import generated.grpc.oceanmonitoring.OceanMonitoringServiceGrpc;

//gRPC
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

//Extend the service that defined in the proto file
public class OceanMonitoringServiceImpl extends OceanMonitoringServiceGrpc.OceanMonitoringServiceImplBase {
   
    // Unary rpc method - Client sends one request and the server will send back a response
    @Override
    public void currentOceanConditions(Location request, StreamObserver<OceanData> responseObserver) {

        // Get the location type by the client 
        String location = request.getLocation().trim().toLowerCase();

        // Send error if the client did not send a locatio 
        if (location.isEmpty()) {
            responseObserver.onError(
                Status.INVALID_ARGUMENT
                    .withDescription("Location cannot be empty")
                    .asRuntimeException()
            );
            return; 
        }

        // Generate simulated ocean data based on location
        OceanData data = buildOceanData(location);

        // Send response back to client
        responseObserver.onNext(data);

        // Tell client we are done sending data
        responseObserver.onCompleted();
    }


    // Client sends one requests and the server will sends back multiple responses
    @Override
    public void monitorOceanConditions(Location request, StreamObserver<OceanData> responseObserver) {

        // Get location send by the client
        String location = request.getLocation().trim().toLowerCase();

        
        if (location.isEmpty()) {
            responseObserver.onError(
                Status.INVALID_ARGUMENT
                    .withDescription("Location cannot be empty")
                    .asRuntimeException() // Exception
            );
            return;
        }

        try {
            // Simulate real-time monitoring (sending multiple updates)
            for (int i = 0; i < 5; i++) {

                // Generate new data each time
                OceanData data = buildOceanData(location);

                // Send update to client
                responseObserver.onNext(data);

                // Wait 2 seconds before sending next update
                Thread.sleep(2000);
            }

            // Tell client streaming is finished
            responseObserver.onCompleted();

        } catch (InterruptedException e) {
            // If something goes wrong → send error
            responseObserver.onError(
                Status.INTERNAL
                    .withDescription("Streaming interrupted")
                    .withCause(e)
                    .asRuntimeException()
            );
        }
    }

    private OceanData buildOceanData(String location) {

        // Simulate data for different loctions - in this case I will use Dublin and Galway
        switch (location) {

            case "dublin":
                // Expected: moderate pollution, normal pH, average temp
                return OceanData.newBuilder()
                        .setTemperatureC(11.5)
                        .setOxygenLevel(7.8)
                        .setAcidityPH(8.1)
                        .setPollutionLevel(0.3)
                        .build();

            case "galway":
                // Expected: cleaner water, slightly better oxygen
                return OceanData.newBuilder()
                        .setTemperatureC(10.9)
                        .setOxygenLevel(8.2)
                        .setAcidityPH(8.0)
                        .setPollutionLevel(0.2)
                        .build();

            default:
                // Default values for unknown locations
                return OceanData.newBuilder()
                        .setTemperatureC(12.0)
                        .setOxygenLevel(7.0)
                        .setAcidityPH(7.9)
                        .setPollutionLevel(0.4)
                        .build();
        }
    }
}
  
