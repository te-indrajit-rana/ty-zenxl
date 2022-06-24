package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.zenxl.entity.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Integer> {

	Boolean existsByCertificateNumber(String number);

	Optional<Certificate> findByCertificateNumber(String number);

	void deleteByCertificateNumber(String certificateNumber);

}
