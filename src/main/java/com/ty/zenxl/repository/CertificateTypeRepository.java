package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ty.zenxl.entity.CertificateType;

/**
 * Interface to interact with db for {@code CertificateType} entity class.
 * Extends the {@code JpaRepository} interface.
 * 
 * @author Indrajit
 * @verion 1.0
 */

public interface CertificateTypeRepository extends JpaRepository<CertificateType, Integer> {

	Boolean existsByCertificateType(String certificateType);

	Optional<CertificateType> findByCertificateType(String certificateType);

	Optional<CertificateType> findByCertificateTypeId(int certificateTypeId);
	
	@Modifying
	@Query("delete from CertificateType c where c.certificateTypeId=:certificateTypeId")
	void deleteCertificateType(int certificateTypeId);

}
