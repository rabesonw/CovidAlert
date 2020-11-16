package alert.covid.controllers;

import alert.covid.models.User;
import alert.covid.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<User> list(){
        return userRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User user){
        return userRepository.saveAndFlush(user);
    }


    @GetMapping
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User get(@PathVariable Long id){
        return userRepository.getOne(id);
    }

    @DeleteMapping
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    @PutMapping
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public User update(@RequestBody final User user){
        return userRepository.saveAndFlush(user);
    }

}
