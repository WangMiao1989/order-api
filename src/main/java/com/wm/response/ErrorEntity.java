package com.wm.response;

import java.util.List;
import lombok.Data;

@Data
public class ErrorEntity {
	private CommonErrorEntity globalError;
	private List<CommonErrorEntity> fieldsError;
}
