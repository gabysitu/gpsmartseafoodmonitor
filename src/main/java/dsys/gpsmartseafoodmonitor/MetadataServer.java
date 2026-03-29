/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsys.gpsmartseafoodmonitor;

/**
 *
 * @author gport
 */


//Import gRPC
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

public class MetadataServer implements ServerInterceptor{
    
    // Metadata key expected from the client
    private static final Metadata.Key<String> CLIENT_NAME_KEY =
            Metadata.Key.of("client-name", Metadata.ASCII_STRING_MARSHALLER);

    //This instruction will help to know from where the request came from
    private static final Metadata.Key<String> REQUEST_SOURCE_KEY =
            Metadata.Key.of("request-source", Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> io.grpc.ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next) {

        // Read metadata sent by the client
        String clientName = headers.get(CLIENT_NAME_KEY);
        String requestSource = headers.get(REQUEST_SOURCE_KEY);

        // Print 
        System.out.println("Data");
        System.out.println("Client Name: " + clientName);
        System.out.println("Request Source: " + requestSource);
        System.out.println("=========================");

        // Continue normal gRPC call
        return next.startCall(call, headers);
    }
}
    
