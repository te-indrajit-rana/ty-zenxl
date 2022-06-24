package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ty.zenxl.entity.IncotermType;

/**
 * Interface to interact with db for {@code IncotermType} entity class.
 * Extends the {@code JpaRepository} interface.
 * 
 * @author Indrajit
 * @verion 1.0
 */

public interface IncotermTypeRepository extends JpaRepository<IncotermType, Integer> {
	
	Boolean existsByIncotermType(String incotermType);

	Optional<IncotermType> findByIncotermType(String incotermType);

	Optional<IncotermType> findByIncotermTypeId(int incotermTypeId);
	
	@Modifying
	@Query("delete from IncotermType c where c.incotermTypeId=:incotermTypeId")
	void deleteIncotermType(int incotermTypeId);
}
