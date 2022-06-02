package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ty.zenxl.entity.Incoterm;

/**
 * Interface to interact with db for {@code Incoterm} entity class.
 * Extends the {@code JpaRepository} interface.
 * 
 * @author Indrajit
 * @verion 1.0
 */

public interface IncotermRepository extends JpaRepository<Incoterm, Integer> {
	
	boolean existsByIncotermType(String incotermType);

	Optional<Incoterm> findByIncotermType(String incotermType);

	Optional<Incoterm> findByIncotermId(int incotermId);
	
	@Modifying
	@Query("delete from Incoterm c where c.incotermId=:incotermId")
	void deleteIncoterm(int incotermId);
}
