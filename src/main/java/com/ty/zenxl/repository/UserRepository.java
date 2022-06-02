package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ty.zenxl.entity.User;

/**
 * Interface to interact with db for {@code User} entity class.
 * Extends the {@code JpaRepository} interface.
 * 
 * @author Indrajit
 * @verion 1.0
 */

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserId(int userId);
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);
	
	@Modifying
	@Query("delete from User u where u.userId=:userId")
	void deleteUser(int userId);

}
