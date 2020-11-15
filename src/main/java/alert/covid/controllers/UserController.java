package alert.covid.controllers;

import alert.covid.models.User;
import alert.covid.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> list(){
        return userRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User user){
        return userRepository.saveAndFlush(user);
    }


    @GetMapping
    @RequestMapping("{id}")
    public User get(@PathVariable Long id){
        return userRepository.getOne(id);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User update(@RequestBody final User user){
        return userRepository.saveAndFlush(user);
    }

}
