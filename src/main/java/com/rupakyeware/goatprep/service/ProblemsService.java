package com.rupakyeware.goatprep.service;

import com.rupakyeware.goatprep.dto.company.CompanyDTO;
import com.rupakyeware.goatprep.dto.companyProblem.InterviewExperienceRequest;
import com.rupakyeware.goatprep.dto.problem.ProblemDTO;
import com.rupakyeware.goatprep.model.Companies;
import com.rupakyeware.goatprep.model.CompanyProblems;
import com.rupakyeware.goatprep.model.Problems;
import com.rupakyeware.goatprep.repo.CompaniesRepo;
import com.rupakyeware.goatprep.repo.CompanyProblemsRepo;
import com.rupakyeware.goatprep.repo.ProblemsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProblemsService {
    private final CompanyProblemsRepo companyProblemsRepo;
    private final CompaniesRepo companiesRepo;
    private ProblemsRepo problemsRepo;

    @Autowired
    public ProblemsService(ProblemsRepo problemsRepo, CompanyProblemsRepo companyProblemsRepo, CompaniesRepo companiesRepo) {
        this.problemsRepo = problemsRepo;
        this.companyProblemsRepo = companyProblemsRepo;
        this.companiesRepo = companiesRepo;
    }

    private List<ProblemDTO> convertToDTO(List<Problems> problems) {
        return problems.stream()
                .map(p -> new ProblemDTO(
                        p.getProblemId(),
                        p.getProblemName(),
                        p.getProblemDifficulty(),
                        p.getProblemLookups(),
                        p.getProblemUrl()
                )).collect(Collectors.toList());
    }


    public List<ProblemDTO> getFilteredProblems(Integer difficulty, Integer minLookups, String sortBy, String order, Integer page) {
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, 50, sort);
        List<Problems> problems = problemsRepo.findFilteredProblems(difficulty, minLookups, pageable).getContent();
        return convertToDTO(problems);
    }

    public List<ProblemDTO> getProblemsByName(String name) {
        PageRequest pageRequest = PageRequest.of(0, 5);
        List<Problems> problems = problemsRepo.findProblemsByProblemNameIgnoreCase(name, pageRequest);
        return convertToDTO(problems);
    }

    public List<ProblemDTO> getProblemsByCompanyId(Integer companyId, Integer difficulty, Integer minLookups, String sortBy, String order, Integer page) {
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, 50, sort);
        return companyProblemsRepo.findFilteredCompanyProblems(companyId, difficulty, minLookups, pageable).getContent();
    }

    @Transactional
    public void postCompanyInterview(InterviewExperienceRequest experience) {
        Companies company = companiesRepo.findById(experience.getCompanyId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));

        for (Integer problemId : experience.getProblemIds()) {
            Problems problem = problemsRepo.findById(problemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Problem not found"));
            Optional<CompanyProblems> existing = companyProblemsRepo.findByCompanyAndProblem(company, problem);

            if (existing.isPresent()) {
                CompanyProblems companyProblem = existing.get();
                companyProblem.setProblemLookups(companyProblem.getProblemLookups() + 1);
                companyProblemsRepo.save(companyProblem);
            } else {
                CompanyProblems newProblem = new CompanyProblems();
                newProblem.setCompany(company);
                newProblem.setProblem(problem);
                newProblem.setProblemLookups(1);
                companyProblemsRepo.save(newProblem);
            }
        }
    }
}