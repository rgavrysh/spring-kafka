package home.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ErrorService {
    @Autowired
    private ErrorsReader errorsReader;

    private List<String> errorLogs = new ArrayList<>();

    public Collection<String> getErrors() {
        for (ConsumerRecord<String, String> consumerRecord : errorsReader.getConsumerRecords()) {
            errorLogs.add(consumerRecord.value());
        }
        return errorLogs;
    }
}
