package com.prices.api.exception.rest;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestResponse implements Serializable {
	private HttpStatus status;
	private String body;
	private Map<String, List<String>> errorAttributes;
	private List<String> errors;
}
