package com.superngb.monitoring_system.Controllers.UserAuthorization;

import com.superngb.monitoring_system.UseCases.UserAuthorization.LoginRequest;
import com.superngb.monitoring_system.UseCases.UserAuthorization.RegistrationRequest;
import com.superngb.monitoring_system.UseCases.UserAuthorization.RegistrationResponseModel;
import com.superngb.monitoring_system.UseCases.UserAuthorization.UserAuthorizationInputBoundary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthorizationController {

    private final UserAuthorizationInputBoundary userAuthorizationInputBoundary;

    public UserAuthorizationController(UserAuthorizationInputBoundary userAuthorizationInputBoundary) {
        this.userAuthorizationInputBoundary = userAuthorizationInputBoundary;
    }

    @PostMapping("/addUser")
    public RegistrationResponseModel addUser(@RequestBody RegistrationRequest registrationRequest) {
        return userAuthorizationInputBoundary.save(registrationRequest);
    }

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return userAuthorizationInputBoundary.login(loginRequest);
    }
}
