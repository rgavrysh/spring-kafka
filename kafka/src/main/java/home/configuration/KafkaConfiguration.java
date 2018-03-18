package home.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfiguration {

    @Value("${input.topic}")
    private String inputTopic;

    @Value("${output.topic}")
    private String outputTopic;

    @Value("${bootstrap.server}")
    private String bootstrapServer;

    public void setInputTopic(String inputTopic) {
        this.inputTopic = inputTopic;
    }

    public void setOutputTopic(String outputTopic) {
        this.outputTopic = outputTopic;
    }

    public String getInputTopic() {
        return inputTopic;
    }

    public String getOutputTopic() {
        return outputTopic;
    }

    public String getBootstrapServer() {
        return bootstrapServer;
    }

    public void setBootstrapServer(String bootstrapServer) {
        this.bootstrapServer = bootstrapServer;
    }
}
