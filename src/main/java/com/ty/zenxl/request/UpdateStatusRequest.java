package com.ty.zenxl.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request DTO for {@code Status} for update purpose.
 * 
 * Contains bean validation properties, to validate the request object using
 * {@code @Valid} in {@code ZenxlUtilityController}
 * 
 * @author Indrajit
 * @version 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusRequest {

	@NotNull(message = "Status must not be null.")
	@NotBlank(message = "Status must not be blank.")
	private String statusName;
	@NotNull(message = "Status catagory must not be null.")
	@NotBlank(message = "Status catagory must not be blank.")
	private String statusCategory;
	@NotNull(message = "Status description must not be null.")
	@NotBlank(message = "Status description must not be blank.")
	private String description;

}
