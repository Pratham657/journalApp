package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class publicController {

    @Autowired
    UserService userService;

    @GetMapping
    String Checkup(){
        return "All Ok";
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            userService.createNewUser(user);
            return new ResponseEntity<>( user, HttpStatus.CREATED);
        }
        catch (Exception e){
            return  new ResponseEntity<>(e.getMessage() , HttpStatus.NO_CONTENT);
        }
    }

}
