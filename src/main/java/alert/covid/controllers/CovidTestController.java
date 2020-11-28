package alert.covid.controllers;

import alert.covid.models.CovidTest;
import alert.covid.repositories.CovidTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class CovidTestController to fetch and post to the table covid_test of the database
 */

@RestController
@RequestMapping("/covid_test")
public class CovidTestController {

    @Autowired
    private CovidTestRepository covidTestRepository;

    @GetMapping
    public List<CovidTest> getAllCovidTests() {
        return covidTestRepository.findAll();
    }

    /**
     * GET mapping to fetch a covid_test from the database
     * @param covid_test_id the ID of the covid_test to fetch
     * @return the CovidTest fetched
     */
    @GetMapping
    @RequestMapping("{id}")
    public CovidTest getCovidTest(@PathVariable long covid_test_id) {
        return covidTestRepository.getOne(covid_test_id);
    }

    /***
     * POST mapping to add a covid_test to the database
     * @param covid_test the CovidTest to add
     * @return the CovidTest added
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CovidTest addCovidTest(@RequestBody final CovidTest covid_test) {
        return covidTestRepository.saveAndFlush(covid_test);
    }
}
