package com.superngb.monitoring_system.DTOs.person;

import com.superngb.monitoring_system.Entities.Role;
import com.superngb.monitoring_system.Entities.person.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoModel implements Serializable {

    private Long id;
    private Long personality;
    private String username;
    private List<String> roles;

    static public UserDtoModel userMapper(User user){
        List<String> roles = new ArrayList<>();

        for (Role role: user.getRoles())
            roles.add(role.getName());

        return new UserDtoModel(user.getId(), user.getPersonality().getId(), user.getUsername(), roles);
    }

    static public List<UserDtoModel> listUserMapper(List<User> users){
        List<UserDtoModel> userDtoModels = new ArrayList<>();
        for (User user: users)
            userDtoModels.add(userMapper(user));
        return userDtoModels;
    }
}
