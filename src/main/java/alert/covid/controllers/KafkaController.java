package alert.covid.controllers;

import alert.covid.models.Location;
import alert.covid.services.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * KafkaController to manage the kafka pipeline message production
 */

@RestController
public class KafkaController {

    KafkaController(KafkaProducer kafkaProducer){
        kafkaProducer=kafkaProducer;
    }
    @Autowired
    private KafkaProducer kafkaProducer;

    /**
     * POST mapping to send a kafka message
     * @param latitude the latitude of the location to send
     * @param longitude the longitude of the location to send
     */
    @PostMapping({"/createLocation"})
    public void sendMessageToKafkaTopic2(
            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude)
    {
        Location location = new Location();
        location.setLatitude(Double.parseDouble(latitude));
        location.setLongitude(Double.parseDouble(longitude));

        this.kafkaProducer.sendLocation(location, "my_topic");
    }
}
