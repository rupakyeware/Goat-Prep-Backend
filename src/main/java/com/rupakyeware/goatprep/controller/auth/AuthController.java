package com.rupakyeware.goatprep.controller.auth;

import com.rupakyeware.goatprep.dto.auth.AuthRequest;
import com.rupakyeware.goatprep.model.Users;
import com.rupakyeware.goatprep.service.JWTService;
import com.rupakyeware.goatprep.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("api/public/auth")
public class AuthController {

    private final JWTService jWTService;
    UserService userService;

    @Autowired
    public AuthController(UserService userService, JWTService jWTService) {
        this.userService = userService;
        this.jWTService = jWTService;
    }

    private BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid AuthRequest request) {
        request.setPassword(bCrypt.encode(request.getPassword()));
        try {
            userService.registerUser(request);
            return new ResponseEntity<>(userService.generateJWTToken(request), HttpStatus.CREATED);
        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthRequest request) {
        if(userService.verifyUser(request)) return new ResponseEntity<>(userService.generateJWTToken(request), HttpStatus.OK);
        else return new ResponseEntity<> ("Incorrect credentials", HttpStatus.UNAUTHORIZED);
    }
}
