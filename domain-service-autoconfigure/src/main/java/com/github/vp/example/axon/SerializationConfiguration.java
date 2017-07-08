package com.github.vp.example.axon;

import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vimalpar on 08/07/17.
 */
@Configuration
public class SerializationConfiguration {
    @Bean
    public XStreamSerializer xStreamSerializer() {
        return new XStreamSerializer();
    }
}
