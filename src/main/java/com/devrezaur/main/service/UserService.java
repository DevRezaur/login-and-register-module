package com.devrezaur.main.service;

import com.devrezaur.main.model.Authority;
import com.devrezaur.main.model.User;
import com.devrezaur.main.repository.AuthorityRepository;
import com.devrezaur.main.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username.toLowerCase());
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Authority authority = authorityRepository.findByAuthority("ROLE_USER");
        user.setAuthorities(Set.of(authority));
        userRepository.save(user);
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Authority authority = authorityRepository.findByAuthority("ROLE_ADMIN");
        user.setAuthorities(Set.of(authority));
        userRepository.save(user);
    }

}
