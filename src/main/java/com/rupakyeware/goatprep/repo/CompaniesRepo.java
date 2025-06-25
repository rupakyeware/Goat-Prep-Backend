package com.rupakyeware.goatprep.repo;

import com.rupakyeware.goatprep.model.Companies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompaniesRepo extends JpaRepository<Companies, Integer> {
}
