package com.rupakyeware.goatprep.controller.companies;

import com.rupakyeware.goatprep.dto.company.CompanyDTO;
import com.rupakyeware.goatprep.dto.companyProblem.InterviewExperienceRequest;
import com.rupakyeware.goatprep.dto.problem.ProblemDTO;
import com.rupakyeware.goatprep.service.CompaniesService;
import com.rupakyeware.goatprep.service.ProblemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/company")
public class CompanyController {
    private final ProblemsService problemsService;
    CompaniesService companiesService;

    @Autowired
    public CompanyController(CompaniesService companiesService, ProblemsService problemsService) {
        this.companiesService = companiesService;
        this.problemsService = problemsService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getCompanies(@RequestParam(required = false, defaultValue = "0") Integer page) {
        return new ResponseEntity<>(companiesService.getCompanies(page), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CompanyDTO>> getCompaniesByName(@RequestParam() String name, @RequestParam(required = false, defaultValue = "0") Integer page) {
        List<CompanyDTO> companies = companiesService.getCompaniesByName(name, page);
        if(companies.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping(value = "/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Integer companyId) {
        CompanyDTO company = companiesService.getCompanyById(companyId);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/problems")
    public ResponseEntity<List<ProblemDTO>> getCompanyProblemsByCompanyId(
            @RequestParam() Integer companyId,
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
        problemsService.postCompanyInterview(experienceRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}