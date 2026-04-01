package com.wm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wm.entity.DailyRevenueInfoEntity;
import com.wm.entity.PeriodRevenueInfoEntity;
import com.wm.mapper.StatisticsRepository;
import com.wm.requestDto.DailyRevenueRetrieveRequestForm;
import com.wm.requestDto.PeriodRevenueRetrieveRequestForm;
import com.wm.service.StatisticsService;

@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService{
	
	@Autowired
	private StatisticsRepository statisticsRepository;
	
	public List<PeriodRevenueInfoEntity> periodRevenueRetrieve(PeriodRevenueRetrieveRequestForm request){
		return statisticsRepository.selectRevenueByPeriod(request.getStartDate(), request.getEndDate());
	}
	
	public List<DailyRevenueInfoEntity> dailyRevenueRetrieve(DailyRevenueRetrieveRequestForm request){
		return statisticsRepository.selectRevenueByDate(request.getSearchDate());
	}
}
