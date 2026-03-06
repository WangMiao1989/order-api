package com.wm.service;

import com.wm.requestDto.TableNoRequestForm;
import com.wm.responseDto.TableDetailRetrieveResponse;

public interface TableService {
	public TableDetailRetrieveResponse tableDetailRetrieve(TableNoRequestForm request);
}
