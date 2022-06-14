package com.ty.zenxl.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Defines the BillingDetails table created in the database with the mentioned
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
public class BillingDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "billing_details_id", unique = true, nullable = false, precision = 10)
	private Integer billingDetailsId;
	@Column(name = "billing_name", length = 45)
	private String billingName;
	@Column(name = "billing_contact_number", precision = 19)
	private Long billingContactNumber;
	@Column(name = "billing_email", length = 45)
	private String billingEmail;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "billingDetails")
	private Customer customer;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", nullable = false)
	private Address address;
}
