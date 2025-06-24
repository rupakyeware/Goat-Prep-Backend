package com.rupakyeware.goatprep.controller.companies;

import com.rupakyeware.goatprep.dto.InterviewExperienceRequest;
import com.rupakyeware.goatprep.model.Problems;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/company")
public class CompanyProblemsController {
    @GetMapping("/{companyId}/user/{userId}/problems")
    public ResponseEntity<List<Problems>> getCompanyProblemsByUserId(@PathVariable int companyId, @PathVariable int userId) {
        return new ResponseEntity<>(new ArrayList<>(List.of(new Problems())), HttpStatus.OK); // placeholder
    }

    @PostMapping("/experience")
    public ResponseEntity<?> uploadCompanyProblem(@RequestBody InterviewExperienceRequest experienceRequest) {
        return new ResponseEntity<>("Success", HttpStatus.CREATED); // placeholder
    }
}
