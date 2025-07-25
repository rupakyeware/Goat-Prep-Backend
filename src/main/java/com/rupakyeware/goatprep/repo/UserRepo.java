package com.rupakyeware.goatprep.repo;

import com.rupakyeware.goatprep.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
}
