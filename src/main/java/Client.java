import com.entities.GreeterGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.entities.Example.request;
import static com.entities.Example.response;

public final class Client {
        public static void main(String[] args) throws InterruptedException {
            new Client().run();
     }

    private void run() throws InterruptedException {

        ExecutorService executor  = Executors.newFixedThreadPool(1);
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 10001)
                .executor(executor)
                .usePlaintext()
                .build();

        try {
            GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
            response resp = stub.hello(request.newBuilder().setName("1").build());


            System.out.println(resp);
        } finally {
            channel.shutdown();
            channel.awaitTermination(1, TimeUnit.SECONDS);
            // Wait until the channel has terminated, since tasks can be queued after the channel is
            // shutdown.
            executor.shutdown();
        }
    }

}
