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
        // put user in memory
        if(userRepository.findByEmail(user.getEmail())==null) {
            userRepository.save(user);
        }
        else{
            throw new RuntimeException("A user with that email already exists.");
        }
        // send a notification to the user
        notificationService.send("The user has been successfully added with the data: " + user.toString(), user.getEmail());
    }
}
