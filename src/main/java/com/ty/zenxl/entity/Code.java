package com.ty.zenxl.entity;

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
@Table(name = "code_details")
public class Code {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code_id", unique = true, nullable = false, precision = 10)
	private Integer codeId;
	@Column(name = "code_value", length = 45)
	private String codeValue;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "code_codeType", joinColumns = @JoinColumn(name = "cId", referencedColumnName = "code_id"), inverseJoinColumns = @JoinColumn(name = "tId", referencedColumnName = "code_type_id"))
	private CodeType codeType;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ItemDetails item;

}
