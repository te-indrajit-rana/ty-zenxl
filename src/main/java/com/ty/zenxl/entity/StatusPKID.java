package com.ty.zenxl.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Defines the columns that forms a composite key for the {@code Status} table
 * created in the database.
 * 
 * @author Indrajit
 * @version 1.0
 *
 */

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StatusPKID implements Serializable {

	private String statusName;
	private String statusCategory;

}
