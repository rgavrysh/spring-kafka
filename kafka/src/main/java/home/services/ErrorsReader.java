package home.services;

import home.configuration.KafkaConfiguration;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collections;
import java.util.Properties;

@Service
public class ErrorsReader {
    @Autowired
    private KafkaConfiguration kafkaConfiguration;
    private KafkaConsumer<String, String> kafkaConsumer;

    @PostConstruct
    public void initConsumer() {
        Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfiguration.getBootstrapServer());
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "restConsumer");
//        consumerProperties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 5);
//        consumerProperties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 2000);
        consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "errors");

        kafkaConsumer = new KafkaConsumer<String, String>(consumerProperties);
        kafkaConsumer.subscribe(Collections.singletonList(kafkaConfiguration.getOutputTopic()));
        kafkaConsumer.poll(1000);
    }

    @PreDestroy
    public void closeConsumer() {
        this.kafkaConsumer.close();
    }


    public KafkaConsumer<String, String> getKafkaConsumer() {
        return kafkaConsumer;
    }

    public ConsumerRecords<String, String> getConsumerRecords() {
//        kafkaConsumer.seekToBeginning(Collections.emptyList());
        kafkaConsumer.seek(new TopicPartition(kafkaConfiguration.getOutputTopic(), 0), 0);
        kafkaConsumer.position(new TopicPartition(kafkaConfiguration.getOutputTopic(), 0));
        return kafkaConsumer.poll(1000);
    }
}
