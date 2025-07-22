package com.rupakyeware.goatprep.repo;

import com.rupakyeware.goatprep.model.Problems;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemsRepo extends JpaRepository<Problems, Integer> {

    @Query(
            "SELECT p FROM Problems p WHERE " +
                    "(:difficulty IS NULL OR p.problemDifficulty = :difficulty) AND " +
                    "(:minLookups IS NULL OR p.problemLookups >= :minLookups)"
    )
    Page<Problems> findFilteredProblems(
            @Param("difficulty") Integer difficulty,
            @Param("minLookups") Integer minLookups,
            Pageable pageable
    );

    @Query(
            "SELECT p FROM Problems p WHERE LOWER(p.problemName) LIKE LOWER(CONCAT('%',:name,'%'))"
    )
    List<Problems> findProblemsByProblemNameIgnoreCase(String name, Pageable pageable);
}
