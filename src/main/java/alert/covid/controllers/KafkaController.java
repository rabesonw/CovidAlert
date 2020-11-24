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

            @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude
            /*@RequestParam("location_date") String location_date*/ ){

        System.out.println("AFISEAZAAAAAA");
        Location location = new Location();
        location.setLatitude(Double.parseDouble(latitude));
        location.setLongitude(Double.parseDouble(longitude));
        //location.setLocation_date(location_date);

        this.kafkaProducer.sendLocation(location, "my_topic");
    }
}
