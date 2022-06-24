package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ty.zenxl.entity.InspectionType;

/**
 * Interface to interact with db for {@code InspectionType} entity class.
 * Extends the {@code JpaRepository} interface.
 * 
 * @author Indrajit
 * @verion 1.0
 */

public interface InspectionTypeRepository extends JpaRepository<InspectionType, Integer> {
	
	Boolean existsByInspectionType(String inspectionType);

	Optional<InspectionType> findByInspectionType(String inspectionType);

	Optional<InspectionType> findByInspectionTypeId(int inspectionTypeId);
	
	@Modifying
	@Query("delete from InspectionType c where c.inspectionTypeId=:inspectionTypeId")
	void deleteInspectionType(int inspectionTypeId);
}
