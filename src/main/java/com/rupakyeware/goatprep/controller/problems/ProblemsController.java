package com.rupakyeware.goatprep.controller.problems;

import com.rupakyeware.goatprep.model.Problems;
import com.rupakyeware.goatprep.service.ProblemsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/problems")
public class ProblemsController {
    ProblemsService problemsService;

    public ProblemsController(ProblemsService problemsService) {
        this.problemsService = problemsService;
    }

    @GetMapping
    public ResponseEntity<List<Problems>> getProblems(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer difficulty,
            @RequestParam(required = false) Integer minLookups,
            @RequestParam(required = false, defaultValue = "problemLookups") String sortBy) {
        return new ResponseEntity<>(new ArrayList<>(List.of(new Problems())), HttpStatus.OK); // placeholder
    }
}
