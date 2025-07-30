package net.engineeringdigest.journalApp.Services;

import net.engineeringdigest.journalApp.Interface.UserRepo;
import net.engineeringdigest.journalApp.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class UserService {

    @Autowired
    UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final Logger logger =  LoggerFactory.getLogger(UserService.class);

//    public List<User> getAllUser() {
//        return userRepo.findAll();
//    }

    public void deleteUser(String name){
        userRepo.deleteByusername(name);
    }

    public void createNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("User"));
            userRepo.save(user);
        } catch (Exception e) {
            logger.info("Layi motha game Zala rey...");
            logger.warn("Layi motha game Zala rey...");
            logger.error("Layi motha game Zala rey...");
            logger.debug("Layi motha game Zala rey...");
            logger.trace("Layi motha game Zala rey...");

            throw e; // rethrow so controller can handle it
        }
    }


    public void createAdmin(User user) {


                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRoles(Arrays.asList("User", "ADMIN"));
                userRepo.save(user);

    }

    public void createUser(User user){
        userRepo.save(user);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public List<User> getAllUser() {
        return userRepo.findAll();
    }


}
