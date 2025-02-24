package com.notifications.streaming.dto;

import com.notifications.streaming.entity.Roles;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private int userID;
    private String userName;
    private String userEmail;
    private String userPassword;
    private Set<Roles> roles;

}
