package com.wm.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wm.entity.TableSessionEntity;
import com.wm.entity.TenantEntity;
import com.wm.exception.BusinessException;
import com.wm.entity.AllTableEntity;
import com.wm.mapper.TableRepository;
import com.wm.requestDto.TableChangeRequestForm;
import com.wm.requestDto.TableNoRequestForm;
import com.wm.responseDto.TableDetailRetrieveResponse;
import com.wm.service.TableService;
import com.wm.utils.ContextHolder;

@Service
@Transactional
public class TableServiceImpl implements TableService{
	
	@Autowired
	private TableRepository tableRepository;
	
	public TableDetailRetrieveResponse tableDetailRetrieve(TableNoRequestForm request) {
		TableDetailRetrieveResponse response = new TableDetailRetrieveResponse();
		
		// table信息取得
		TableSessionEntity tableInfo =  tableRepository.selectTableInfo(request.getTableNo());
		if(!Objects.isNull(tableInfo)) {
			BeanUtils.copyProperties(tableInfo, response);
		}
		
		// 店铺名称取得
		TenantEntity  tenantInfo = ContextHolder.getContextHolder("tenantInfo", TenantEntity.class);
		if(!Objects.isNull(tenantInfo)) {
			// 店铺名称设定
			response.setTenantName(tenantInfo.getTenantName());
		}

		return response;
	}
	
	// 全部桌台信息取得
	public List<AllTableEntity> allTableRetrieve(){
		return tableRepository.selectAllTable();
	}
	
	// 结束订单
	public void tableFinish(TableNoRequestForm request) {
		tableRepository.updateTableEndtime(request.getTableNo());
	}
	
	// 换桌
	public void tableChange(TableChangeRequestForm request) {
		// 如果当前桌位空桌的情况，抛业务异常
		if(!tableRepository.isBusy(request.getCurTableNo())) {
			throw new BusinessException("E01", "当前桌台为空桌，无需更换");
		}
		
		// 如果变更后桌不为空桌的情况，抛业务异常
		if(tableRepository.isBusy(request.getChangeTableNo())) {
			throw new BusinessException("E02", "变更后的桌台已经被使用，无法换桌");
		}
		
		tableRepository.tableChange(request.getCurTableNo(), request.getChangeTableNo());
	}
}
