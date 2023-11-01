package com.example.BookMyShow.trasnformer;

import com.example.BookMyShow.dto.requestdtos.AddUserRequest;
import com.example.BookMyShow.dto.responsedtos.UserResponse;
import com.example.BookMyShow.model.User;

public class UserTransformers {
    public static User convertAddUserReqToUserEntity(AddUserRequest addUserRequest){


        User userObj = User.builder()
                .age(addUserRequest.getAge())
                .emailId(addUserRequest.getEmailId())
                .mobNo(addUserRequest.getMobNo())
                .name(addUserRequest.getName())
                .username(addUserRequest.getUsername())
                .password(addUserRequest.getPassword())
                .build();


        return userObj;

    }

    public static UserResponse UserToUserResponse(User user){
        String mobileno= user.getMobNo();
        String process=mobileno.substring(6);
        String op="XXXXXX"+process;
        UserResponse userResponse=UserResponse.builder()
                .mobNo(op)
                .name(user.getName())
                .build();
        return userResponse;
    }
}
