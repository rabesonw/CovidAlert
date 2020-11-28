package alert.covid.controllers;

import alert.covid.models.Location;
import alert.covid.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class LocationController to fetch and post info the table locations of the database
 */
@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    /**
     * GET mapping to fetch a location from the database
     * @param location_id the ID of the location to fetch
     * @return the Location fetched
     */
    @GetMapping
    @RequestMapping("{id}")
    public Location getLocation(@PathVariable long location_id) {
        return locationRepository.getOne(location_id);
    }

    /**
     * POST mapping to add a location to the database
     * @param location the Location to add
     * @return the Location created
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location addLocation(@RequestBody final Location location) {
        return locationRepository.saveAndFlush(location);
    }
}
