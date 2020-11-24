package alert.covid.services;

//import alert.covid.models.KafkaListenerExample;
import alert.covid.models.Location;
import alert.covid.repositories.LocationRepository;
import alert.covid.repositories.UserRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class KafkaConsumer {
    Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);
    @Autowired
    private LocationRepository locationRepository;

    @KafkaListener(topics = "my_topic", groupId = "my−topic")
    public void consume(Location location) {
        //locationRepository.saveAndFlush(location);
        LOG.info(String.format("Location -> %s", location));
    }



}
