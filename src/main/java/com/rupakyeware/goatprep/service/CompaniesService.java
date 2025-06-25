package com.rupakyeware.goatprep.service;

import com.rupakyeware.goatprep.dto.company.CompanyDTO;
import com.rupakyeware.goatprep.model.Companies;
import com.rupakyeware.goatprep.repo.CompaniesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CompaniesService {
    CompaniesRepo companiesRepo;

    @Autowired
    public CompaniesService(CompaniesRepo companiesRepo) {
        this.companiesRepo = companiesRepo;
    }

    public CompanyDTO getCompanyById(Integer companyId) {
        Companies company = companiesRepo.findById(companyId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new CompanyDTO(company.getCompanyId(), company.getCompanyName(), company.getCompanyLogoUrl());
    }
}
