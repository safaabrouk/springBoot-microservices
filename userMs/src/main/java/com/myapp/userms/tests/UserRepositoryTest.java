package com.myapp.userms.tests;

import com.myapp.userms.entities.User;
import com.myapp.userms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UserRepositoryTest implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {

        // Test adding
        User u = new User();
        u.setFirstName("John");
        u.setLastName("Smith");
        u.setEmail("john.smith@gmail.com");
        u.setPassword("password");
        // -----------------------------------------------------------------------
        String dateStr = "25-12-2020"; // Format dd-MM-yyyy
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
             Date date = formatter.parse(dateStr);
             u.setBirthDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // -----------------------------------------------------------------------

        //System.out.println("User 1 : "+userRepository.save(u));
        //System.out.println("Get User 1 : "+userRepository.findById(1).orElse(null));

    }
}
