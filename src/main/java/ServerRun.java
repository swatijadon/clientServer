import com.entities.Example.request;
import com.entities.Example.response;
import com.entities.GreeterGrpc.GreeterImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.Executors;


public class ServerRun extends GreeterImplBase{
    private Server server;
    private int port =10001;


    public static void main(String[] args)throws IOException,InterruptedException{
        new ServerRun().start(args);
    }

    private void start(String args[]) throws IOException,InterruptedException {
        server =
                ServerBuilder.forPort(port).addService(this).executor(Executors.newFixedThreadPool(1)).build();
        server.start();
        server.awaitTermination();
    }





    public void hello(request req, StreamObserver<response> Observer) {

        response replay= response.newBuilder().setName("hello"+req.getName()).build();
        Observer.onNext(replay);
        Observer.onCompleted();
    }
/* public void hello(request     amObserver<response> observer){


    }*/


}
