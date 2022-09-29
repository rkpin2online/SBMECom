package com.rk.dto;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author RK
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	
	@Id
	private Integer id;
	
	@NotNull
	private String name;

	@NotNull
	private String brand;
	
	
}
