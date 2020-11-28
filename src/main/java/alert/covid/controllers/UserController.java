package alert.covid.controllers;

import alert.covid.models.User;
import alert.covid.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * Class UserController to fetch and post info to the database for the table users
 */

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /***
     * GET mapping to fetch all users of the database
     * @return a List of the Users of the database
     */
    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<User> list(){
        return userRepository.findAll();
    }

    /**
     * POST mapping to add a user to the database
     * @param user the User to add
     * @return the User created
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User user){
        return userRepository.saveAndFlush(user);
    }

    /***
     * GET mapping to fetch a user from the database
     * @param id the ID of the User to fetch
     * @return the User fetched from the database
     */
    @GetMapping
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User get(@PathVariable Long id){
        return userRepository.getOne(id);
    }

    /**
     * GET mapping to fetch a user from the database
     * @param username the Username of the User to fetch
     * @return the User fetched from the database
     */
    @GetMapping
    @RequestMapping(value="{username}", method=RequestMethod.GET)
    public User getByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * DELETE mapping to remove a user from the database
     * @param id the ID of the User to remove
     */
    @DeleteMapping
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    /**
     * PUT mapping to update a user from the database
     * @param id the ID of the User to update
     * @param user the User with updated data
     * @return the updated User
     */
    @PutMapping
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public User update(@PathVariable Long id, @RequestBody User user){
        User existingUser = userRepository.getOne(id);
        BeanUtils.copyProperties(user, existingUser, "user_id");
        return userRepository.saveAndFlush(existingUser);
    }

}
