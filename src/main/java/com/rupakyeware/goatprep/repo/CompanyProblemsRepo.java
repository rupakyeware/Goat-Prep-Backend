package com.rupakyeware.goatprep.repo;

import com.rupakyeware.goatprep.dto.problem.ProblemDTO;
import com.rupakyeware.goatprep.model.Companies;
import com.rupakyeware.goatprep.model.CompanyProblems;
import com.rupakyeware.goatprep.model.Problems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyProblemsRepo extends JpaRepository<CompanyProblems, Integer> {
//    @Query("SELECT cp.problem FROM CompanyProblems cp WHERE " +
//            "cp.company.companyId = :companyId AND" +
//            ":difficulty IS NULL OR cp.problem.problemDifficulty = :difficulty AND " +
//            ":minLookups IS NULL OR cp.problemLookups >= :minLookups ")
@Query("SELECT new com.rupakyeware.goatprep.dto.problem.ProblemDTO(" +
        "cp.problem.problemId, cp.problem.problemName, cp.problem.problemDifficulty, cp.problemLookups, cp.problem.problemUrl) " +
        "FROM CompanyProblems cp " +
        "WHERE cp.company.companyId = :companyId " +
        "AND (:difficulty IS NULL OR cp.problem.problemDifficulty = :difficulty) " +
        "AND (:minLookups IS NULL OR cp.problemLookups >= :minLookups)")
    Page<ProblemDTO> findFilteredCompanyProblems(
            @Param("companyId") Integer companyId,
            @Param("difficulty") Integer difficulty,
            @Param("minLookups") Integer minLookups,
            Pageable pageable
    );

    Optional<CompanyProblems> findByCompanyAndProblem(Companies company, Problems problem);

}
