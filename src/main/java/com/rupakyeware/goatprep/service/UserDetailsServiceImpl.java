package com.rupakyeware.goatprep.service;

import com.rupakyeware.goatprep.model.UserPrincipal;
import com.rupakyeware.goatprep.model.Users;
import com.rupakyeware.goatprep.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepo userRepo;

    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepo.findByUsername(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("User could not be found");
        return new UserPrincipal(user.orElse(null));
    }
}
