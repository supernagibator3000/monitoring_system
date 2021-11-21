package com.superngb.monitoring_system.UseCases.UserAuthorization;

import com.superngb.monitoring_system.Entities.person.User;

public interface UserAuthorizationUserDataAccess {
    void save(User user);
    User findByUsername(String username);
}
