package com.wm.service;

import java.util.List;

import com.wm.entity.DailyRevenueInfoEntity;
import com.wm.entity.PeriodRevenueInfoEntity;
import com.wm.requestDto.DailyRevenueRetrieveRequestForm;
import com.wm.requestDto.PeriodRevenueRetrieveRequestForm;

public interface StatisticsService {
	public List<PeriodRevenueInfoEntity> periodRevenueRetrieve(PeriodRevenueRetrieveRequestForm request);
	
	public List<DailyRevenueInfoEntity> dailyRevenueRetrieve(DailyRevenueRetrieveRequestForm request);
}
