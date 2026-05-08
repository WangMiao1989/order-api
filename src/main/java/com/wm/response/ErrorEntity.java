package com.wm.response;

import java.util.List;
import lombok.Data;

@Data
public class ErrorEntity {
	private GlobalErrorEntity globalError;
	private List<FieldErrorEntity> fieldsError;
}
