package com.example.foine.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.foine.entity.User;
import com.example.foine.repository.UserRepository;

@Service
public class FoineUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public FoineUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        
        if (user == null) {
            throw new UsernameNotFoundException("Email does not exist.");
        }
        
        return new UserPrincipal(user);
    }
    
}
