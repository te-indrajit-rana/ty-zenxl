package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ty.zenxl.entity.CodeType;

/**
 * Interface to interact with db for {@code CodeType} entity class.
 * Extends the {@code JpaRepository} interface.
 * 
 * @author Indrajit
 * @verion 1.0
 */

public interface CodeTypeRepository extends JpaRepository<CodeType, Integer> {

	Boolean existsByCodeType(String codeType);

	Optional<CodeType> findByCodeType(String codeType);

	Optional<CodeType> findByCodeTypeId(int codeTypeId);
	
	@Modifying
	@Query("delete from CodeType c where c.codeTypeId=:codeTypeId")
	void deleteCodeType(int codeTypeId);
}
