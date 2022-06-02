package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ty.zenxl.entity.Inspection;

/**
 * Interface to interact with db for {@code Inspection} entity class.
 * Extends the {@code JpaRepository} interface.
 * 
 * @author Indrajit
 * @verion 1.0
 */

public interface InspectionRepository extends JpaRepository<Inspection, Integer> {
	
	boolean existsByInspectionType(String inspectionType);

	Optional<Inspection> findByInspectionType(String inspectionType);

	Optional<Inspection> findByInspectionId(int inspectionId);
	
	@Modifying
	@Query("delete from Inspection c where c.inspectionId=:inspectionId")
	void deleteInspection(int inspectionId);
}
