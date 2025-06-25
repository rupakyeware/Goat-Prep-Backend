package com.rupakyeware.goatprep.service;

import com.rupakyeware.goatprep.dto.auth.AuthRequest;
import com.rupakyeware.goatprep.model.Users;
import com.rupakyeware.goatprep.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepo userRepo;
    AuthenticationManager authManager;
    JWTService jwtService;

    @Autowired
    public UserService(UserRepo userRepo, AuthenticationManager authManager, JWTService jwtService) {
        this.userRepo = userRepo;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public Users registerUser(AuthRequest request) {
        Users user = new Users(request.getUsername(), request.getPassword());
        userRepo.save(user);
        return user;
    }

    public Boolean verifyUser(AuthRequest request) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        return (authentication.isAuthenticated());
    }

    public String generateJWTToken(AuthRequest request) {
        Users user = new Users(request.getUsername(), request.getPassword());
        return jwtService.generateToken(user);
    }
}
