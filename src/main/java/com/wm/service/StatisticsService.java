package com.wm.service;

import java.util.List;

import com.wm.entity.RevenueInfoEntity;
import com.wm.requestDto.RevenueRetrieveRequestForm;

public interface StatisticsService {
	public List<RevenueInfoEntity> revenueRetrieve(RevenueRetrieveRequestForm request);
}
