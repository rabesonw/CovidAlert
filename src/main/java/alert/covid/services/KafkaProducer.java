package alert.covid.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import alert.covid.models.Location;

/**
 * KafkaProducer to send the location of the connected user
 */
@Service
public class KafkaProducer {
    Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    @Autowired
    private KafkaTemplate <String , Object > kafkaTemplate;
    public void sendLocation(Location location, String topic) {
        /** For displaying the created location in the console*/
        logger.info(String.format("[Producer] Location created -> %s", location));
        /**Sending the location through the specified topic*/
        this.kafkaTemplate.send("my_topic", location);
    }
}