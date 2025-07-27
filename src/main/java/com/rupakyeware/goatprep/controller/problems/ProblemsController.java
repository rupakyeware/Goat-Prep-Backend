package com.rupakyeware.goatprep.controller.problems;

import com.rupakyeware.goatprep.dto.problem.ProblemDTO;
import com.rupakyeware.goatprep.service.ProblemsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/problems")
public class ProblemsController {
    ProblemsService problemsService;

    public ProblemsController(ProblemsService problemsService) {
        this.problemsService = problemsService;
    }

    @GetMapping
    public ResponseEntity<List<ProblemDTO>> getProblems(
            @RequestParam(required = false) Integer difficulty,
            @RequestParam(required = false) Integer minLookups,
            @RequestParam(required = false, defaultValue = "problemLookups") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String order,
            @RequestParam(required = false, defaultValue = "0") Integer page) {

        return new ResponseEntity<>(problemsService.getFilteredProblems(difficulty, minLookups, sortBy, order, page), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProblemDTO>> search(@RequestParam String name) {
        return new ResponseEntity<>(problemsService.getProblemsByName(name), HttpStatus.OK);
    }
}
