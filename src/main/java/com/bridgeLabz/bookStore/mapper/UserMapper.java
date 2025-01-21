package com.bridgeLabz.bookStore.mapper;

import com.bridgeLabz.bookStore.requestdto.RegistrationRequest;
import com.bridgeLabz.bookStore.requestdto.UserRequest;
import com.bridgeLabz.bookStore.responsedto.UserResponse;
import com.bridgeLabz.bookStore.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class UserMapper {

    private BCryptPasswordEncoder encoder;

    public User mapToUser(UserRequest userRequest, User user) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setDob(userRequest.getDob());
        user.setRole(userRequest.getRole());
        return user;
    }

    public User mapToUser(RegistrationRequest registrationRequest) {
        User user = new User();
        user.setFirstName(registrationRequest.getFname());
        user.setLastName(registrationRequest.getLname());
        user.setEmail(registrationRequest.getEmail());
        user.setDob(registrationRequest.getDob());
        user.setRole(registrationRequest.getRole());
        user.setPassword(encoder.encode(registrationRequest.getPassword()));
        user.setRegisteredDate(LocalDate.now());
        return user;
    }
    public UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setDob(user.getDob());
        userResponse.setRole(user.getRole());
        userResponse.setRegisteredDate(user.getRegisteredDate());
        userResponse.setUpdatedDate(user.getUpdatedDate());
        return userResponse;

    }
}

