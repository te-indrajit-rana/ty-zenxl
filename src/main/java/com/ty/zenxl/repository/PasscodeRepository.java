package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.zenxl.entity.Passcode;

/**
 * Interface to interact with db for {@code Passcode} entity class.
 * Extends the {@code JpaRepository} interface.
 * 
 * @author Indrajit
 * @verion 1.0
 */

public interface PasscodeRepository extends JpaRepository<Passcode, Integer> {

	Optional<Passcode> findByEmail(String email);

}
