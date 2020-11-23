package alert.covid.services;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaTopicConfig {
    @Bean
    public NewTopic topic1 () {
        return TopicBuilder.name("my_topic").build () ; }
    @Bean
    public NewTopic topic2 () {
        return TopicBuilder.name("mytopic−2").config(TopicConfig.RETENTION_MS_CONFIG, "1680000").build(); }
}