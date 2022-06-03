package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ty.zenxl.entity.HsCode;
/**
 * Interface to interact with db for {@code HsCode} entity class.
 * Extends the {@code JpaRepository} interface.
 * 
 * @author Indrajit
 * @verion 1.0
 */
public interface HsCodeRepository extends JpaRepository<HsCode, Integer> {

	boolean existsByHsCodeType(String hsCodeType);

	Optional<HsCode> findByHsCodeType(String hsCodeType);

	Optional<HsCode> findByHsCodeId(int hsCodeType);
	
	@Modifying
	@Query("delete from HsCode c where c.hsCodeId=:hsCodeId")
	void deleteHsCode(int hsCodeId);
}
