package com.example.BookMyShow.controller;

import com.example.BookMyShow.dto.requestdtos.AddUserRequest;
import com.example.BookMyShow.dto.responsedtos.UserResponse;
import com.example.BookMyShow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody AddUserRequest addUserRequest) {
        UserResponse userResponse = userService.addUser(addUserRequest);
        return new ResponseEntity(userResponse, HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    public ResponseEntity changePassword(@RequestParam("email") String email,@RequestParam("old") String oldPassword,
                                         @RequestParam("new")String NewPassword){
        try{
            String ans=userService.changePassword(email,oldPassword,NewPassword);
            return new ResponseEntity<>(ans,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
    }
}
