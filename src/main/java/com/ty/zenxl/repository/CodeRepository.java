package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ty.zenxl.entity.Code;

/**
 * Interface to interact with db for {@code Code} entity class.
 * Extends the {@code JpaRepository} interface.
 * 
 * @author Indrajit
 * @verion 1.0
 */

public interface CodeRepository extends JpaRepository<Code, Integer> {

	boolean existsByCodeType(String codeType);

	Optional<Code> findByCodeType(String codeType);

	Optional<Code> findByCodeId(int codeId);
	
	@Modifying
	@Query("delete from Code c where c.codeId=:codeId")
	void deleteCode(int codeId);
}
