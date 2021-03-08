package com.springboot.todoApp.springboottodoApp.utils;

import com.springboot.todoApp.springboottodoApp.security.UserService;
import com.springboot.todoApp.springboottodoApp.security.AppUser;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstTimeInitializer implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(FirstTimeInitializer.class);

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // Check if we have user
        if(userService.findAll().isEmpty()){
          logger.info("No users accounts found, creating some users");
            AppUser user = new AppUser("abdo@gmail.com","password","abdo");

            userService.save(user);
        }
        // if no user exist ,create some user
    }
}
