package com.ty.zenxl.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request DTO for {@code Status}
 * 
 * Contains bean validation properties, to validate the request object using {@code @Valid} 
 * in {@code ZenxlUtilityController}
 * 
 * @author Indrajit
 * @version 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusRequest {
	
	@NotNull
	private String statusName;
	@NotNull
	private String statusCategory;
	@NotNull
	private String description;

}
