package alert.covid.controllers;

import alert.covid.models.Location;
import alert.covid.services.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@RestController
public class KafkaController {

    KafkaController(KafkaProducer kafkaProducer){
        kafkaProducer=kafkaProducer;
    }
    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping({"/createLocation"})
    public void sendMessageToKafkaTopic2(

            @RequestParam("latitude") BigDecimal latitude,
            @RequestParam("longitude") BigDecimal longitude,
            @RequestParam("location_date") LocalDateTime location_date ){

        System.out.println("AFISEAZAAAAAA");
        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setLocation_date(location_date);

        this.kafkaProducer.sendLocation(location, "my_topic");
    }
}
