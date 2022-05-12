package com.superngb.monitoring_system.UseCases.UserAuthorization;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public interface UserAuthorizationInputBoundary extends UserDetailsService {
    RegistrationResponseModel save(RegistrationRequest registrationRequest);
    ResponseEntity<String> login(LoginRequest loginRequest);
}
