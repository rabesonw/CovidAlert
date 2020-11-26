package alert.covid.services;

import alert.covid.enums.StateCovid;
import alert.covid.models.Location;
import alert.covid.models.User;
import alert.covid.repositories.LocationRepository;
import alert.covid.repositories.UserRepository;
import alert.covid.controllers.SecurityController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class KafkaConsumer {
    Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Bean
    public String username() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }

    void sendEmail(String adress,String object,  String message) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(adress);
        msg.setSubject(object);
        msg.setText(message);
        javaMailSender.send(msg);

    }

    String username= username();
    HashMap<String, Location> usernameList = new HashMap<String, Location>();
    //private List <Location> locationList = new ArrayList<>();
   // private List <String> usernameList = new ArrayList<>();

    public <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }


    @KafkaListener(topics = "my_topic", groupId = "my−topic")
    public void consume(Location location) {
        //locationRepository.saveAndFlush(location);
        LOG.info(String.format("Location -> %s", location));

        LocalDateTime data = LocalDateTime.now();
        location.setLocation_date(data);

        //verify if user already exists in the array and the location/time of him
        for (String i : usernameList.keySet()) {
            if(usernameList.containsKey(username) ){
                if((usernameList.get(i).getLatitude() != location.getLatitude()) || (usernameList.get(i).getLongitude() != location.getLongitude()) || (usernameList.get(i).getLocation_date() != location.getLocation_date())) {
                    usernameList.replace(username, location);
                }
            }else {
                usernameList.put(username,location);
                locationRepository.saveAndFlush(location);
            }

            double distanceMeter = DistanceCalculator.distance(location.getLatitude(),location.getLongitude(),usernameList.get(i).getLatitude(),usernameList.get(i).getLongitude(), "K")*1000;

            if ((distanceMeter<=2) && (getKey(usernameList,location)!= username) && (usernameList.get(i).getLocation_date() == location.getLocation_date())){
                    User user1 = userRepository.findByUsername(username);
                    User user2 = userRepository.findByUsername(getKey(usernameList,usernameList.get(i)));
                    if(user1.getState_user()== StateCovid.malade){
                        String object="Interaction malade covid";
                        String message="Vous avez eu contact avec une personne confirme avec le virus...";
                        sendEmail(user2.getEmail(), object, message);
                    } else if (user1.getState_user()==StateCovid.cas_contact){
                        String object="Interaction cas contact covid";
                        String message="Vous avez eu contact avec une personne cas contact qui est possible d'avoir le virus...";
                        sendEmail(user2.getEmail(), object, message);
                    } else if ((user2.getState_user() == StateCovid.malade)) {
                        String object="Interaction malade covid";
                        String message="Vous avez eu contact avec une personne confirme avec le virus...";
                        sendEmail(user1.getEmail(), object, message);
                    }else if(user2.getState_user() == StateCovid.cas_contact){
                        String object="Interaction cas contact covid";
                        String message="Vous avez eu contact avec une personne cas contact qui est possible d'avoir le virus...";
                        sendEmail(user1.getEmail(), object, message);
                    }

            }
        }
    }



}
