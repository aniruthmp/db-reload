package io.pivotal.dbreload.api;

import io.pivotal.dbreload.domain.User;
import io.pivotal.dbreload.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
@RequestMapping(path = "/demo")
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PutMapping(path = "/add")
    public @ResponseBody User addNewUser(@RequestParam String name, @RequestParam String email) {
        log.info("Came inside addNewUser");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        log.info("Saved : " + user.toString());
        return user;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        log.info("Came inside getAllUsers");
        return userRepository.findAll();
    }

    @DeleteMapping(path = "/remove")
    public @ResponseBody String removeUser(@RequestParam Integer id) {
        log.info("Came inside removeUser");
        try {
            userRepository.deleteById(id);
            return "Success";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Failure";
        }
    }
}
