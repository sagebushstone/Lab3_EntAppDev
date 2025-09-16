package com.elkabani.userregistration;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private NotificationService notificationService;
    private UserRepository userRepository;

    public UserService(NotificationService notificationService, UserRepository userRepository) {
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }

    public void registerUser(User user){
        System.out.println(); // to make spacing look nicer
        // put user in memory
        if(userRepository.findByEmail(user.getEmail())==null) {
            userRepository.save(user);
        }
        else{
            // throws an error if you try to register a user with an email that already exists.
            throw new RuntimeException("A user with the email " + user.getEmail() + " already exists.");
        }
        // send a notification to the user
        notificationService.send("The user has been successfully added with the data: " + user.toString(), user.getEmail());
    }
}
