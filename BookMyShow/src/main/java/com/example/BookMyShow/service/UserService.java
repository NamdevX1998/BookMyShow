package com.example.BookMyShow.service;

import com.example.BookMyShow.dto.requestdtos.AddUserRequest;
import com.example.BookMyShow.dto.responsedtos.UserResponse;
import com.example.BookMyShow.exception.UserRelatedException;
import com.example.BookMyShow.model.User;
import com.example.BookMyShow.repository.UserRepository;
import com.example.BookMyShow.trasnformer.UserTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponse addUser(AddUserRequest addUserRequest) {

        User userObj = UserTransformers.convertAddUserReqToUserEntity(addUserRequest);
        User savedUser=userRepository.save(userObj);

        return UserTransformers.UserToUserResponse(savedUser);
    }

    public String changePassword(String email, String oldPassword, String newPassword) {
        User user=userRepository.findByEmailId(email);
        if(user==null){
            throw new UserRelatedException("Email is invalid");
        }
        String old= user.getPassword();
        if(!(old.equalsIgnoreCase(oldPassword))){
            throw new UserRelatedException("Invalid password");
        }
        else
            user.setPassword(newPassword);
        userRepository.save(user);
        return "password change successfully";
    }

}
