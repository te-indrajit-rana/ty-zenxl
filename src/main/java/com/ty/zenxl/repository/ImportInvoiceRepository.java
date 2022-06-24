package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.zenxl.entity.ImportInvoice;

public interface ImportInvoiceRepository extends JpaRepository<ImportInvoice, Integer> {

	Optional<ImportInvoice> findByInvoiceNumber(Long invoiceNumber);

	Boolean existsByInvoiceNumber(Long invoiceNumber);
}
