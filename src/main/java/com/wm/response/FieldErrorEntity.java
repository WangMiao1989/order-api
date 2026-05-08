package com.wm.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldErrorEntity {
	private String fieldName;
	private String message;
}
