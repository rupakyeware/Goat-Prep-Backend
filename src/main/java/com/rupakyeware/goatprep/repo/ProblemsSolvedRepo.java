package com.rupakyeware.goatprep.repo;

import com.rupakyeware.goatprep.model.ProblemsSolved;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemsSolvedRepo extends JpaRepository<ProblemsSolved, Integer> {
}
