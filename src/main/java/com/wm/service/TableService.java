package com.wm.service;

import java.util.List;

import com.wm.entity.AllTableEntity;
import com.wm.requestDto.TableNoRequestForm;
import com.wm.responseDto.TableDetailRetrieveResponse;

public interface TableService {
	public TableDetailRetrieveResponse tableDetailRetrieve(TableNoRequestForm request);
	
	public List<AllTableEntity> allTableRetrieve();
	
	public void tableFinish(TableNoRequestForm request);
}
