package com.rupakyeware.goatprep.repo;

import com.rupakyeware.goatprep.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends JpaRepository<Users, Integer> {
}
