package kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.UUID;

public class KafkaProducerApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 1; i <= 5; i++) {
            String key = UUID.randomUUID().toString();
            String value = "Заявка " + UUID.randomUUID().toString();
            producer.send(new ProducerRecord<>("requests", key, value));
            System.out.println("Отправлено: " + value);
        }

        producer.close();
    }
}

// mvn clean install
//docker-compose down --volumes --remove-orphans
//docker-compose up -d
// mvn exec:java "-Dexec.mainClass=kafka.KafkaConsumerApp"
// mvn exec:java "-Dexec.mainClass=kafka.KafkaProducerApp"
