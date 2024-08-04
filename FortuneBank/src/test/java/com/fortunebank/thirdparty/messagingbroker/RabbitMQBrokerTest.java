import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.rabbitmq.client.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQBrokerTest {
    private Connection connection;
    private Channel channel;
    private RabbitMQBroker rabbitMQBroker;

    @BeforeEach
    public void setUp() throws IOException, TimeoutException {
        connection = mock(Connection.class);
        channel = mock(Channel.class);
        rabbitMQBroker = new RabbitMQBroker("testQueue") {
            @Override
            protected Connection createConnection() throws IOException, TimeoutException {
                return connection;
            }

            @Override
            protected Channel createChannel(Connection connection) throws IOException {
                return channel;
            }
        };
    }

    @Test
    public void testSendMessage() throws IOException {
        rabbitMQBroker.connect();
        rabbitMQBroker.sendMessage("testQueue", "Hello, World!");
        verify(channel).basicPublish(eq(""), eq("testQueue"), isNull(), eq("Hello, World!".getBytes()));
        rabbitMQBroker.disconnect();
    }

    @Test
    public void testReceiveMessage() throws IOException {
        GetResponse response = mock(GetResponse.class);
        when(response.getBody()).thenReturn("Hello, World!".getBytes());
        when(channel.basicGet("testQueue", true)).thenReturn(response);

        rabbitMQBroker.connect();
        String message = rabbitMQBroker.receiveMessage("testQueue");
        assertEquals("Hello, World!", message);
        rabbitMQBroker.disconnect();
    }
}
