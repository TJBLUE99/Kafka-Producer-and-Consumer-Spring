package com.notifications.streaming.dto;

import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    @Id
    private int userID;
    private String userName;
    private String userEmail;
    private String userPassword;
}
