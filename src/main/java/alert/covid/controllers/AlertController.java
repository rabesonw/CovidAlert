package alert.covid.controllers;

import alert.covid.models.Alert;
import alert.covid.repositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/alerts")
@RestController
public class AlertController {
    @Autowired
    private AlertRepository alertRepository;


    @GetMapping
    public List<Alert> list(){
        return alertRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Alert get(@PathVariable Long id){
        return alertRepository.getOne(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Alert create(@RequestBody final Alert alert){
        return alertRepository.saveAndFlush(alert);
    }
}
