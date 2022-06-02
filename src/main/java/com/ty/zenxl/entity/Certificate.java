package com.ty.zenxl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Defines the Certificate table created in the database with the mentioned
 * fields.
 * 
 * @author Indrajit
 * @version 1.0
 *
 */

@Table
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "certificate_id", unique = true, nullable = false, precision = 10)
	private int certificateId;
	@Column(name = "certificate_type", length = 45)
	private String certificateType;
}
