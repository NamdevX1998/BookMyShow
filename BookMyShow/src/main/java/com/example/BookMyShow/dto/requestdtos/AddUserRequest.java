package com.example.BookMyShow.dto.requestdtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    private String name;

    private String emailId;

    private String mobNo;

    private Integer age;

    private String username;

    private String password;
}
