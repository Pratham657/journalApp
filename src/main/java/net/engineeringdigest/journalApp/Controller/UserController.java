package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

//    @GetMapping
//    public List<User> getAllUser(){
//        return userService.getAllUser();
//    }

    @GetMapping("/userName/{username}")
    public User getUser(@PathVariable String username){
        return userService.findByUsername(username);
    }



    @PutMapping
    public User updateUser(@RequestBody User user ){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User usr = userService.findByUsername(username);
        usr.setUsername(user.getUsername());
        usr.setPassword(user.getPassword());

        userService.createNewUser(usr);
        return usr;
    }

    @DeleteMapping
    public void deleteUser(){
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        userService.deleteUser(name);
    }
}
