package com.superngb.monitoring_system.UseCases.UserAuthorization;

import com.superngb.monitoring_system.Entities.Role;
import com.superngb.monitoring_system.Entities.person.User;
import com.superngb.monitoring_system.Enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

public class UserAuthorizationInteractor implements UserAuthorizationInputBoundary {

    private UserAuthorizationUserDataAccess userAuthorizationUserDataAccess;

    private UserAuthorizationOutputBoundary userAuthorizationOutputBoundary;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserAuthorizationInteractor(UserAuthorizationUserDataAccess userAuthorizationUserDataAccess, UserAuthorizationOutputBoundary userAuthorizationOutputBoundary) {
        this.userAuthorizationUserDataAccess = userAuthorizationUserDataAccess;
        this.userAuthorizationOutputBoundary = userAuthorizationOutputBoundary;
    }

    @Override
    public RegistrationResponseModel save(RegistrationRequest registrationRequest) {
        boolean usernameError = userAuthorizationUserDataAccess.findByUsername(registrationRequest.getUsername())!=null;
        boolean passwordError = !registrationRequest.getPassword().equals(registrationRequest.getPasswordConfirm());
        if(usernameError || passwordError)
            return userAuthorizationOutputBoundary.prepareFailView(new RegistrationResponseModel(usernameError,passwordError));

        User user = new User();
        user.setRoles(Collections.singletonList(new Role(RoleEnum.ROLE_USER.getId(), RoleEnum.ROLE_USER.name())));
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
        userAuthorizationUserDataAccess.save(user);
        return userAuthorizationOutputBoundary.prepareSuccessView();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userAuthorizationUserDataAccess.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
