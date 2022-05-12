package com.superngb.monitoring_system.UseCases.UserAuthorization;

import com.superngb.monitoring_system.Configs.JWTUtil;
import com.superngb.monitoring_system.Entities.Role;
import com.superngb.monitoring_system.Entities.person.User;
import com.superngb.monitoring_system.Enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Optional;

public class UserAuthorizationInteractor implements UserAuthorizationInputBoundary {

    @Autowired
    private JWTUtil jwtUtil;

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
            return userAuthorizationOutputBoundary.prepareFailRegistrationView(new RegistrationResponseModel(usernameError,passwordError));

        User user = new User();
        user.setRoles(Collections.singletonList(new Role(RoleEnum.ROLE_USER.getId(), RoleEnum.ROLE_USER.name())));
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
        userAuthorizationUserDataAccess.save(user);
        return userAuthorizationOutputBoundary.prepareSuccessRegistrationView();
    }

    @Override
    public ResponseEntity<String> login(LoginRequest loginRequest) {
        Optional<User> user = Optional.ofNullable(userAuthorizationUserDataAccess.findByUsername(loginRequest.getUsername()));
        if(user.isPresent()){
            if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
                String token = jwtUtil.generateToken(loginRequest.getUsername());
                return new ResponseEntity(token, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Неправильный ввод данных", HttpStatus.NOT_FOUND);
            }
        }
        else{
            return new ResponseEntity("Неправильный ввод данных", HttpStatus.NOT_FOUND);
        }
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
