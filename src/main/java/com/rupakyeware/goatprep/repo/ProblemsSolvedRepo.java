package com.rupakyeware.goatprep.repo;

import com.rupakyeware.goatprep.dto.problem.ProblemDTO;
import com.rupakyeware.goatprep.model.Problems;
import com.rupakyeware.goatprep.model.ProblemsSolved;
import com.rupakyeware.goatprep.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProblemsSolvedRepo extends JpaRepository<ProblemsSolved, Integer> {
    Optional<ProblemsSolved> findByUserAndProblem(Users user, Problems problem);

    @Query("SELECT NEW com.rupakyeware.goatprep.dto.problem.ProblemDTO(ps.problem.problemId, ps.problem.problemName, ps.problem.problemDifficulty, ps.problem.problemLookups, ps.problem.problemUrl) " +
            " from ProblemsSolved ps " +
            " WHERE ps.user.userId = :userId")
    List<ProblemDTO> findProblemsSolvedByUser(@Param("userId") int userId);
}
