package com.rupakyeware.goatprep.controller.companies;

import com.rupakyeware.goatprep.dto.company.CompanyDTO;
import com.rupakyeware.goatprep.dto.companyProblem.InterviewExperienceRequest;
import com.rupakyeware.goatprep.dto.problem.ProblemDTO;
import com.rupakyeware.goatprep.model.Companies;
import com.rupakyeware.goatprep.model.Problems;
import com.rupakyeware.goatprep.service.CompaniesService;
import com.rupakyeware.goatprep.service.ProblemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/company")
public class CompanyProblemsController {
    private final ProblemsService problemsService;
    CompaniesService companiesService;

    @Autowired
    public CompanyProblemsController(CompaniesService companiesService, ProblemsService problemsService) {
        this.companiesService = companiesService;
        this.problemsService = problemsService;
    }

    @GetMapping(value = "/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Integer companyId) {
        CompanyDTO company = companiesService.getCompanyById(companyId);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/problems")
    public ResponseEntity<List<ProblemDTO>> getCompanyProblemsByCompanyId(
            @RequestParam(required = true) Integer companyId,
            @RequestParam(required = false) Integer difficulty,
            @RequestParam(required = false) Integer minLookups,
            @RequestParam(required = false, defaultValue = "problemLookups") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String order,
            @RequestParam(required = false, defaultValue = "0") Integer page
    ) {
        return new ResponseEntity<>(problemsService.getProblemsByCompanyId(companyId, difficulty, minLookups, sortBy, order, page), HttpStatus.OK);
    }

    @PostMapping("/experience")
    public ResponseEntity<?> uploadCompanyProblem(@RequestBody InterviewExperienceRequest experienceRequest) {
        return new ResponseEntity<>("Success", HttpStatus.CREATED); // placeholder
    }
}
