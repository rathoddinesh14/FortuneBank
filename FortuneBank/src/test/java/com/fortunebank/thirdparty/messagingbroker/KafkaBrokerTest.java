import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;
import java.util.Collections;

public class KafkaBrokerTest {
    private KafkaProducer<String, String> producer;
    private KafkaConsumer<String, String> consumer;
    private KafkaBroker kafkaBroker;

    @BeforeEach
    public void setUp() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "test-group");

        producer = mock(KafkaProducer.class);
        consumer = mock(KafkaConsumer.class);

        kafkaBroker = new KafkaBroker(properties) {
            @Override
            protected KafkaProducer<String, String> createProducer(Properties properties) {
                return producer;
            }

            @Override
            protected KafkaConsumer<String, String> createConsumer(Properties properties) {
                return consumer;
            }
        };
    }

    @Test
    public void testSendMessage() {
        kafkaBroker.sendMessage("topic1", "Hello, World!");
        verify(producer).send(any(ProducerRecord.class));
    }

    @Test
    public void testReceiveMessage() {
        ConsumerRecords<String, String> records = mock(ConsumerRecords.class);
        when(records.iterator()).thenReturn(Collections.singletonList(new ConsumerRecord<>("topic1", 0, 0L, "key", "value")).iterator());
        when(consumer.poll(anyLong())).thenReturn(records);

        String message = kafkaBroker.receiveMessage("topic1");
        assertEquals("value", message);
    }
}
