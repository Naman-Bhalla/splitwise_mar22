package com.scaler.splitwise_mar22.services;

import com.scaler.splitwise_mar22.models.User;
import com.scaler.splitwise_mar22.repositories.UserRepository;
import com.scaler.splitwise_mar22.services.passwordhashing.PasswordHashingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

// if multiple classes implenting an interface:
// via configuration: Which data type to use
@Service
public class UserService {

    @Autowired
    private PasswordHashingStrategy passwordHashingStrategy;

//    @Qualifier("userRepoHelo")
    @Autowired
    private UserRepository userRepository;

    public User registerUser(String name,
                             String password,
                             String phoneNumber) {

        // hash the password
        String hashedPassword = passwordHashingStrategy.hash(password);

        // create the user object -> no ID
        User user = new User();
        user.setName(name);
        user.setHashedPassword(hashedPassword);
        user.setPhoneNumber(phoneNumber);

        // save the user object ->  ID exists
        User savedUser = userRepository.save(user);

        // return the saved object
        return savedUser;
    }
}

// don't tightly couple a service to a request
// same service can be used by multiple requests
