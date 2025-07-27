package com.rupakyeware.goatprep.service;

import com.rupakyeware.goatprep.dto.auth.AuthRequest;
import com.rupakyeware.goatprep.dto.problem.ProblemDTO;
import com.rupakyeware.goatprep.model.Problems;
import com.rupakyeware.goatprep.model.ProblemsSolved;
import com.rupakyeware.goatprep.model.Users;
import com.rupakyeware.goatprep.repo.ProblemsRepo;
import com.rupakyeware.goatprep.repo.ProblemsSolvedRepo;
import com.rupakyeware.goatprep.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final ProblemsSolvedRepo problemsSolvedRepo;
    private final ProblemsRepo problemsRepo;
    UserRepo userRepo;
    AuthenticationManager authManager;
    JWTService jwtService;

    @Autowired
    public UserService(UserRepo userRepo, AuthenticationManager authManager, JWTService jwtService, ProblemsSolvedRepo problemsSolvedRepo, ProblemsRepo problemsRepo) {
        this.userRepo = userRepo;
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.problemsSolvedRepo = problemsSolvedRepo;
        this.problemsRepo = problemsRepo;
    }

    public void registerUser(AuthRequest request) {
        Optional<Users> optionalUser = userRepo.findByUsername(request.getUsername());

        if(optionalUser.isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is taken");

        Users user = new Users(request.getUsername(), request.getPassword());
        userRepo.save(user);
    }

    public Boolean verifyUser(AuthRequest request) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        return (authentication.isAuthenticated());
    }

    public String handleUserOAuth(String username) {
        Optional<Users> optionalUser = userRepo.findByUsername(username); // check if user exists

        // if user doesn't exist, create
        if(optionalUser.isEmpty()) {
            AuthRequest request = new AuthRequest();
            request.setUsername(username);
            request.setPassword(UUID.randomUUID().toString());
            registerUser(request);
        }

        // generate jwt for user
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername(username);
        authRequest.setPassword("");
        String jwt = generateJWTToken(authRequest);

        return jwt;
    }

    public String generateJWTToken(AuthRequest request) {
        return jwtService.generateToken(request.getUsername());
    }

    public List<ProblemDTO> getProblemsSolvedByUser(int userId) {
        return problemsSolvedRepo.findProblemsSolvedByUser(userId);
    }

    @Transactional
    public void markSolved(int userId, int problemId) {
        Users user = userRepo.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User could not be found"));
        Problems problem = problemsRepo.findById(problemId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Problem could not be found"));

        Optional<ProblemsSolved> existing = problemsSolvedRepo.findByUserAndProblem(user, problem);
        if(existing.isPresent()) {
            problemsSolvedRepo.delete(existing.get());
        }
        else {
            ProblemsSolved solved = new ProblemsSolved();
            solved.setUser(user);
            solved.setProblem(problem);
            problemsSolvedRepo.save(solved);
        }
    }
}
