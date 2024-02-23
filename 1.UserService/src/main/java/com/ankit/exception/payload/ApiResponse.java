package com.ankit.exception.payload;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder//to help to create object of this class
public class ApiResponse {

	private String message;
	private boolean sucess;
	private HttpStatus status;
}
