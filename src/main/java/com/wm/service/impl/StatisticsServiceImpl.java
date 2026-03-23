package com.wm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wm.entity.RevenueInfoEntity;
import com.wm.mapper.StatisticsRepository;
import com.wm.requestDto.RevenueRetrieveRequestForm;
import com.wm.service.StatisticsService;

@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService{
	
	@Autowired
	private StatisticsRepository statisticsRepository;
	
	public List<RevenueInfoEntity> revenueRetrieve(RevenueRetrieveRequestForm request){
		return statisticsRepository.selectRevenueByMonth(request.getStartMonth(), request.getEndMonth());
	}
}
