package com.rupakyeware.goatprep.repo;

import com.rupakyeware.goatprep.model.CompanyProblems;
import com.rupakyeware.goatprep.model.Problems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyProblemsRepo extends JpaRepository<CompanyProblems, Integer> {
    @Query("SELECT p FROM Problems p JOIN CompanyProblems cp ON p.problemId = cp.problem.problemId " +
            "WHERE cp.company.companyId = :companyId AND " +
            "(:difficulty IS NULL OR p.problemDifficulty = :difficulty) AND " +
            "(:minLookups IS NULL OR p.problemLookups >= :minLookups)")
    Page<Problems> findFilteredCompanyProblems(
            @Param("companyId") Integer companyId,
            @Param("difficulty") Integer difficulty,
            @Param("minLookups") Integer minLookups,
            Pageable pageable
    );

}
