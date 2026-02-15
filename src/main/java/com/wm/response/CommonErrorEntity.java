package com.wm.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonErrorEntity {
	private String code;
	private String message;
}
