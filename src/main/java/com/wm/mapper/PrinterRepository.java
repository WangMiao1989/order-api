package com.wm.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrinterRepository {
	public String selectPrinterSn(String tenantId);
}
