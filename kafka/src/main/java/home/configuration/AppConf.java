package home.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

@Configuration
@PropertySource("kafka.properties")
public class AppConf {

    @Autowired
    private KafkaConfiguration kafkaConfiguration;

    @Bean
    public PropertyPlaceholderAutoConfiguration propertyPlaceholderAutoConfiguration() {
        return new PropertyPlaceholderAutoConfiguration();
    }

    @Bean
    public KafkaStreams kafkaStream() {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "log-filter-app");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfiguration.getBootstrapServer());
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 3000);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KStream<String, String> stream = streamBuilder().stream(kafkaConfiguration.getInputTopic());
        stream.filter((key, line) -> line.contains("ERROR"))
                .to(kafkaConfiguration.getOutputTopic());
        return new KafkaStreams(streamBuilder(), properties);
    }

    @Bean
    public KStreamBuilder streamBuilder() {
        return new KStreamBuilder();
    }


}
