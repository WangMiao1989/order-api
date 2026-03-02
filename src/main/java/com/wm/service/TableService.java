package com.wm.service;

import com.wm.requestDto.TableIdRetrieveRequestForm;
import com.wm.responseDto.TableDetailRetrieveResponse;

public interface TableService {
	public TableDetailRetrieveResponse tableDetailRetrieve(TableIdRetrieveRequestForm request);
}
