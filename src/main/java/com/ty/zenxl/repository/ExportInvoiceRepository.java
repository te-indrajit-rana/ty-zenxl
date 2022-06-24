package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.zenxl.entity.ExportInvoice;

public interface ExportInvoiceRepository extends JpaRepository<ExportInvoice, Integer> {

	Optional<ExportInvoice> findByInvoiceNumber(Long invoiceNumber);

	Boolean existsByInvoiceNumber(Long invoiceNumber);
}
