package generated.grpc.recommendation;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: RecommendationService.proto")
public final class RecommendationServiceGrpc {

  private RecommendationServiceGrpc() {}

  public static final String SERVICE_NAME = "recommendation.RecommendationService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.grpc.recommendation.RecommendationRequest,
      generated.grpc.recommendation.RecommendationResponse> getLiveRecommendationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiveRecommendations",
      requestType = generated.grpc.recommendation.RecommendationRequest.class,
      responseType = generated.grpc.recommendation.RecommendationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.recommendation.RecommendationRequest,
      generated.grpc.recommendation.RecommendationResponse> getLiveRecommendationsMethod() {
    io.grpc.MethodDescriptor<generated.grpc.recommendation.RecommendationRequest, generated.grpc.recommendation.RecommendationResponse> getLiveRecommendationsMethod;
    if ((getLiveRecommendationsMethod = RecommendationServiceGrpc.getLiveRecommendationsMethod) == null) {
      synchronized (RecommendationServiceGrpc.class) {
        if ((getLiveRecommendationsMethod = RecommendationServiceGrpc.getLiveRecommendationsMethod) == null) {
          RecommendationServiceGrpc.getLiveRecommendationsMethod = getLiveRecommendationsMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.recommendation.RecommendationRequest, generated.grpc.recommendation.RecommendationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "recommendation.RecommendationService", "LiveRecommendations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.recommendation.RecommendationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.recommendation.RecommendationResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new RecommendationServiceMethodDescriptorSupplier("LiveRecommendations"))
                  .build();
          }
        }
     }
     return getLiveRecommendationsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RecommendationServiceStub newStub(io.grpc.Channel channel) {
    return new RecommendationServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RecommendationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RecommendationServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RecommendationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RecommendationServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class RecommendationServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<generated.grpc.recommendation.RecommendationRequest> liveRecommendations(
        io.grpc.stub.StreamObserver<generated.grpc.recommendation.RecommendationResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getLiveRecommendationsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLiveRecommendationsMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                generated.grpc.recommendation.RecommendationRequest,
                generated.grpc.recommendation.RecommendationResponse>(
                  this, METHODID_LIVE_RECOMMENDATIONS)))
          .build();
    }
  }

  /**
   */
  public static final class RecommendationServiceStub extends io.grpc.stub.AbstractStub<RecommendationServiceStub> {
    private RecommendationServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RecommendationServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RecommendationServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RecommendationServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.grpc.recommendation.RecommendationRequest> liveRecommendations(
        io.grpc.stub.StreamObserver<generated.grpc.recommendation.RecommendationResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getLiveRecommendationsMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class RecommendationServiceBlockingStub extends io.grpc.stub.AbstractStub<RecommendationServiceBlockingStub> {
    private RecommendationServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RecommendationServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RecommendationServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RecommendationServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class RecommendationServiceFutureStub extends io.grpc.stub.AbstractStub<RecommendationServiceFutureStub> {
    private RecommendationServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RecommendationServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RecommendationServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RecommendationServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_LIVE_RECOMMENDATIONS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RecommendationServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RecommendationServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LIVE_RECOMMENDATIONS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.liveRecommendations(
              (io.grpc.stub.StreamObserver<generated.grpc.recommendation.RecommendationResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RecommendationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RecommendationServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.grpc.recommendation.RecommendationProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RecommendationService");
    }
  }

  private static final class RecommendationServiceFileDescriptorSupplier
      extends RecommendationServiceBaseDescriptorSupplier {
    RecommendationServiceFileDescriptorSupplier() {}
  }

  private static final class RecommendationServiceMethodDescriptorSupplier
      extends RecommendationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RecommendationServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RecommendationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RecommendationServiceFileDescriptorSupplier())
              .addMethod(getLiveRecommendationsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
