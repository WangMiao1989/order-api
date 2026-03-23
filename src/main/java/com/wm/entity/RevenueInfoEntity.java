package com.wm.entity;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class RevenueInfoEntity{
	private String yearMonth;
	private BigDecimal totalRevenue;
	private List<DishRevenueEntity> dishSummary;
}
