package org.spring.springkafka.config;

import org.spring.springkafka.stream.ErrorStream;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(ErrorStream.class)
public class StreamsConfig {
}
