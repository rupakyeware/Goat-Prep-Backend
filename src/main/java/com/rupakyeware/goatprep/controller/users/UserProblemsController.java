package com.rupakyeware.goatprep.controller.users;

import com.rupakyeware.goatprep.dto.problem.ProblemDTO;
import com.rupakyeware.goatprep.model.Problems;
import com.rupakyeware.goatprep.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserProblemsController {
    private final UserService userService;

    public UserProblemsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/problems")
    public ResponseEntity<List<ProblemDTO>> getProblemsByUserId(@PathVariable int userId) {
        return new ResponseEntity<>(userService.getProblemsSolvedByUser(userId), HttpStatus.OK);
    }

    @PostMapping("/{userId}/problems/{problemId}/solved")
    public ResponseEntity<?> handleProblemSolved(@PathVariable int userId, @PathVariable int problemId) {
        userService.markSolved(userId, problemId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
