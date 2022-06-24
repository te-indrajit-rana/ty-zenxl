package com.ty.zenxl.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "certificate_details")
public class Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "certificate_id", unique = true, nullable = false, precision = 10)
	private Integer certificateId;
	@Column(name = "certificate_name", length = 45)
	private String certificateName;
	@Column(name = "certificate_format", length = 45)
	private String certificateFormat;
	@Column(name = "certificate_url")
	private String certificateUrl;
	@Column(name = "certificate_num", unique = true)
	private String certificateNumber;
	@Column(name = "exp_date")
	private Date expiryDate;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "certificate_certificateType", joinColumns = @JoinColumn(name = "cId", referencedColumnName = "certificate_id"), inverseJoinColumns = @JoinColumn(name = "tId", referencedColumnName = "certificate_type_id"))
	private CertificateType certificateType;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ItemDetails itemDetails;

}
