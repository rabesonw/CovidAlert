package alert.covid.controllers;

import alert.covid.models.Alert;
import alert.covid.repositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class AlertController to fetch and post to the table alerts of the database
 */

@RequestMapping("/alerts")
@RestController
public class AlertController {

    @Autowired
    private AlertRepository alertRepository;

    /**
     * GET mapping to fetch all alerts from the database
     * @return a List of all the alerts of the database
     */
    @GetMapping
    public List<Alert> list(){
        return alertRepository.findAll();
    }

    /**
     * GET mapping to fetch one alert from the database
     * @param id the ID of the alert to fetch
     * @return the Alert fetched
     */
    @GetMapping
    @RequestMapping("{id}")
    public Alert get(@PathVariable Long id){
        return alertRepository.getOne(id);
    }

    /**
     * POST mapping to add an alert to the database
     * @param alert the Alert to add
     * @return the Alert added
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Alert create(@RequestBody final Alert alert){
        return alertRepository.saveAndFlush(alert);
    }
}
