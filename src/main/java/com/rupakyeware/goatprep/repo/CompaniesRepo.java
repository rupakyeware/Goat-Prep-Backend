package com.rupakyeware.goatprep.repo;

import com.rupakyeware.goatprep.model.Companies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompaniesRepo extends JpaRepository<Companies, Integer> {
    @Query("SELECT c FROM Companies c WHERE LOWER(c.companyName) LIKE LOWER(CONCAT('%',:name,'%'))")
    Page<Companies> findAllByCompanyName(@Param("name") String name, Pageable pageable);
}
