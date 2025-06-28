package com.rupakyeware.goatprep.service;

import com.rupakyeware.goatprep.dto.company.CompanyDTO;
import com.rupakyeware.goatprep.model.Companies;
import com.rupakyeware.goatprep.repo.CompaniesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CompanyDTO> getCompaniesByName(String name, Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        List<Companies> companies = companiesRepo.findAllByCompanyName(name, pageable).getContent();
        return companies.stream().map(c-> new CompanyDTO(
                c.getCompanyId(),
                c.getCompanyName(),
                c.getCompanyLogoUrl()
        )).collect(Collectors.toList());
    }

}
