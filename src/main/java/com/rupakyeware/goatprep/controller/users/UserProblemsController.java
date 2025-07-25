package com.rupakyeware.goatprep.controller.users;

import com.rupakyeware.goatprep.dto.problem.ProblemDTO;
import com.rupakyeware.goatprep.model.Problems;
import com.rupakyeware.goatprep.service.JWTService;
import com.rupakyeware.goatprep.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserProblemsController {
    private final UserService userService;
    private final JWTService jWTService;

    public UserProblemsController(UserService userService, JWTService jWTService) {
        this.userService = userService;
        this.jWTService = jWTService;
    }

    @GetMapping("/problems")
    public ResponseEntity<List<ProblemDTO>> getProblemsByUserId(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        Integer userId = jWTService.extractUserId(token);
        return new ResponseEntity<>(userService.getProblemsSolvedByUser(userId), HttpStatus.OK);
    }

    @PostMapping("/problems/{problemId}/solved")
    public ResponseEntity<?> handleProblemSolved(@RequestHeader("Authorization") String authHeader, @PathVariable int problemId) {
        String token = authHeader.substring(7);
        Integer userId = jWTService.extractUserId(token);
        userService.markSolved(userId, problemId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
