package generated.grpc.seafoodmonitoring;

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
    comments = "Source: SeafoodMonitoringService.proto")
public final class SeafoodMonitoringServiceGrpc {

  private SeafoodMonitoringServiceGrpc() {}

  public static final String SERVICE_NAME = "seafoodmonitoring.SeafoodMonitoringService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.grpc.seafoodmonitoring.SeafoodRequest,
      generated.grpc.seafoodmonitoring.SeafoodEvaluation> getEvaluateSeafoodMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EvaluateSeafood",
      requestType = generated.grpc.seafoodmonitoring.SeafoodRequest.class,
      responseType = generated.grpc.seafoodmonitoring.SeafoodEvaluation.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.grpc.seafoodmonitoring.SeafoodRequest,
      generated.grpc.seafoodmonitoring.SeafoodEvaluation> getEvaluateSeafoodMethod() {
    io.grpc.MethodDescriptor<generated.grpc.seafoodmonitoring.SeafoodRequest, generated.grpc.seafoodmonitoring.SeafoodEvaluation> getEvaluateSeafoodMethod;
    if ((getEvaluateSeafoodMethod = SeafoodMonitoringServiceGrpc.getEvaluateSeafoodMethod) == null) {
      synchronized (SeafoodMonitoringServiceGrpc.class) {
        if ((getEvaluateSeafoodMethod = SeafoodMonitoringServiceGrpc.getEvaluateSeafoodMethod) == null) {
          SeafoodMonitoringServiceGrpc.getEvaluateSeafoodMethod = getEvaluateSeafoodMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.seafoodmonitoring.SeafoodRequest, generated.grpc.seafoodmonitoring.SeafoodEvaluation>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "seafoodmonitoring.SeafoodMonitoringService", "EvaluateSeafood"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.seafoodmonitoring.SeafoodRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.seafoodmonitoring.SeafoodEvaluation.getDefaultInstance()))
                  .setSchemaDescriptor(new SeafoodMonitoringServiceMethodDescriptorSupplier("EvaluateSeafood"))
                  .build();
          }
        }
     }
     return getEvaluateSeafoodMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.grpc.seafoodmonitoring.SeafoodRequest,
      generated.grpc.seafoodmonitoring.BatchSummary> getEvaluateSeafoodBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EvaluateSeafoodBatch",
      requestType = generated.grpc.seafoodmonitoring.SeafoodRequest.class,
      responseType = generated.grpc.seafoodmonitoring.BatchSummary.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.seafoodmonitoring.SeafoodRequest,
      generated.grpc.seafoodmonitoring.BatchSummary> getEvaluateSeafoodBatchMethod() {
    io.grpc.MethodDescriptor<generated.grpc.seafoodmonitoring.SeafoodRequest, generated.grpc.seafoodmonitoring.BatchSummary> getEvaluateSeafoodBatchMethod;
    if ((getEvaluateSeafoodBatchMethod = SeafoodMonitoringServiceGrpc.getEvaluateSeafoodBatchMethod) == null) {
      synchronized (SeafoodMonitoringServiceGrpc.class) {
        if ((getEvaluateSeafoodBatchMethod = SeafoodMonitoringServiceGrpc.getEvaluateSeafoodBatchMethod) == null) {
          SeafoodMonitoringServiceGrpc.getEvaluateSeafoodBatchMethod = getEvaluateSeafoodBatchMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.seafoodmonitoring.SeafoodRequest, generated.grpc.seafoodmonitoring.BatchSummary>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "seafoodmonitoring.SeafoodMonitoringService", "EvaluateSeafoodBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.seafoodmonitoring.SeafoodRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.seafoodmonitoring.BatchSummary.getDefaultInstance()))
                  .setSchemaDescriptor(new SeafoodMonitoringServiceMethodDescriptorSupplier("EvaluateSeafoodBatch"))
                  .build();
          }
        }
     }
     return getEvaluateSeafoodBatchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SeafoodMonitoringServiceStub newStub(io.grpc.Channel channel) {
    return new SeafoodMonitoringServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SeafoodMonitoringServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SeafoodMonitoringServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SeafoodMonitoringServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SeafoodMonitoringServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class SeafoodMonitoringServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void evaluateSeafood(generated.grpc.seafoodmonitoring.SeafoodRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.seafoodmonitoring.SeafoodEvaluation> responseObserver) {
      asyncUnimplementedUnaryCall(getEvaluateSeafoodMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.grpc.seafoodmonitoring.SeafoodRequest> evaluateSeafoodBatch(
        io.grpc.stub.StreamObserver<generated.grpc.seafoodmonitoring.BatchSummary> responseObserver) {
      return asyncUnimplementedStreamingCall(getEvaluateSeafoodBatchMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getEvaluateSeafoodMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.grpc.seafoodmonitoring.SeafoodRequest,
                generated.grpc.seafoodmonitoring.SeafoodEvaluation>(
                  this, METHODID_EVALUATE_SEAFOOD)))
          .addMethod(
            getEvaluateSeafoodBatchMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                generated.grpc.seafoodmonitoring.SeafoodRequest,
                generated.grpc.seafoodmonitoring.BatchSummary>(
                  this, METHODID_EVALUATE_SEAFOOD_BATCH)))
          .build();
    }
  }

  /**
   */
  public static final class SeafoodMonitoringServiceStub extends io.grpc.stub.AbstractStub<SeafoodMonitoringServiceStub> {
    private SeafoodMonitoringServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SeafoodMonitoringServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SeafoodMonitoringServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SeafoodMonitoringServiceStub(channel, callOptions);
    }

    /**
     */
    public void evaluateSeafood(generated.grpc.seafoodmonitoring.SeafoodRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.seafoodmonitoring.SeafoodEvaluation> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEvaluateSeafoodMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.grpc.seafoodmonitoring.SeafoodRequest> evaluateSeafoodBatch(
        io.grpc.stub.StreamObserver<generated.grpc.seafoodmonitoring.BatchSummary> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getEvaluateSeafoodBatchMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class SeafoodMonitoringServiceBlockingStub extends io.grpc.stub.AbstractStub<SeafoodMonitoringServiceBlockingStub> {
    private SeafoodMonitoringServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SeafoodMonitoringServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SeafoodMonitoringServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SeafoodMonitoringServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public generated.grpc.seafoodmonitoring.SeafoodEvaluation evaluateSeafood(generated.grpc.seafoodmonitoring.SeafoodRequest request) {
      return blockingUnaryCall(
          getChannel(), getEvaluateSeafoodMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SeafoodMonitoringServiceFutureStub extends io.grpc.stub.AbstractStub<SeafoodMonitoringServiceFutureStub> {
    private SeafoodMonitoringServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SeafoodMonitoringServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SeafoodMonitoringServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SeafoodMonitoringServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.grpc.seafoodmonitoring.SeafoodEvaluation> evaluateSeafood(
        generated.grpc.seafoodmonitoring.SeafoodRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEvaluateSeafoodMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EVALUATE_SEAFOOD = 0;
  private static final int METHODID_EVALUATE_SEAFOOD_BATCH = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SeafoodMonitoringServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SeafoodMonitoringServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EVALUATE_SEAFOOD:
          serviceImpl.evaluateSeafood((generated.grpc.seafoodmonitoring.SeafoodRequest) request,
              (io.grpc.stub.StreamObserver<generated.grpc.seafoodmonitoring.SeafoodEvaluation>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EVALUATE_SEAFOOD_BATCH:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.evaluateSeafoodBatch(
              (io.grpc.stub.StreamObserver<generated.grpc.seafoodmonitoring.BatchSummary>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SeafoodMonitoringServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SeafoodMonitoringServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.grpc.seafoodmonitoring.SeafoodMonitoringProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SeafoodMonitoringService");
    }
  }

  private static final class SeafoodMonitoringServiceFileDescriptorSupplier
      extends SeafoodMonitoringServiceBaseDescriptorSupplier {
    SeafoodMonitoringServiceFileDescriptorSupplier() {}
  }

  private static final class SeafoodMonitoringServiceMethodDescriptorSupplier
      extends SeafoodMonitoringServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SeafoodMonitoringServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SeafoodMonitoringServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SeafoodMonitoringServiceFileDescriptorSupplier())
              .addMethod(getEvaluateSeafoodMethod())
              .addMethod(getEvaluateSeafoodBatchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
