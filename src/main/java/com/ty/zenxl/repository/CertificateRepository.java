package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ty.zenxl.entity.Certificate;

/**
 * Interface to interact with db for {@code Certificate} entity class.
 * Extends the {@code JpaRepository} interface.
 * 
 * @author Indrajit
 * @verion 1.0
 */

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {

	boolean existsByCertificateType(String certificateType);

	Optional<Certificate> findByCertificateType(String certificateType);

	Optional<Certificate> findByCertificateId(int certificateId);
	
	@Modifying
	@Query("delete from Certificate c where c.certificateId=:certificateId")
	void deleteCertificate(int certificateId);

}
