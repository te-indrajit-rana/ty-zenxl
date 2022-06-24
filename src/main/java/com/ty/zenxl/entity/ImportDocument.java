package com.ty.zenxl.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "import_document")
public class ImportDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "document_id", unique = true, nullable = false, precision = 10)
	private Integer documentId;
	@Column(name = "document_name", length = 45)
	private String documentName;
	@Column(name = "document_format", length = 45)
	private String documentFormat;
	@Column(name = "document_url")
	private String documentUrl;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ImportInvoice invoice;

}
