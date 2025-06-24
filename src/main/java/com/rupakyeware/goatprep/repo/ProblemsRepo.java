package com.rupakyeware.goatprep.repo;

import com.rupakyeware.goatprep.model.Problems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemsRepo extends JpaRepository<Problems, Integer> {
}
