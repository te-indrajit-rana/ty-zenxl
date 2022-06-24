package com.ty.zenxl.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "item_details")
public class ItemDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id", unique = true, nullable = false, precision = 10)
	private Integer itemId;
	@Column(name = "serial_num", unique = true)
	private Long serialNumber;
	@Column(name = "manufacturer_name", length = 45)
	private String manufacturerName;
	@Column(name = "part_num")
	private Long partNumber;
	@Column(name = "item_qty")
	private Integer quantity;
	@Column(length = 255)
	private String description;
	@Column(name = "uom", length = 45)
	private String unitOfMeasure;
	@Column(name = "unit_price")
	private Double unitPrice;
	@Column(name = "country", length = 45)
	private String countryOfOrigin;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "item")
	private Set<Code> codeList;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "itemDetails")
	private Set<Certificate> certificateList;


}
