package com.rupakyeware.goatprep.controller.users;

import com.rupakyeware.goatprep.model.Problems;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserProblemsController {
    @GetMapping("/{userId}/problems")
    public ResponseEntity<List<Problems>> getProblemsByUserId(@PathVariable int userId) {
        return new ResponseEntity<>(new ArrayList<>(List.of(new Problems())), HttpStatus.OK); // placeholder
    }
}
