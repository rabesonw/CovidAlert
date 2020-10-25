package alert.covid.controllers;

import alert.covid.models.CovidTest;
import alert.covid.repositories.CovidTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/covid_test")
public class CovidTestController {
    @Autowired
    private CovidTestRepository covidTestRepository;

    @GetMapping
    public List<CovidTest> getAllCovidTests() {
        return covidTestRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public CovidTest getCovidTest(@PathVariable long covid_test_id) {
        return covidTestRepository.getOne(covid_test_id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CovidTest addCovidTest(@RequestBody final CovidTest covid_test) {
        return covidTestRepository.saveAndFlush(covid_test);
    }
}
