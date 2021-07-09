package com.devrezaur.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.devrezaur.main.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Boolean existsByUsernameIgnoreCase(String username);
	
	User findByUsernameIgnoreCase(String username);
}
