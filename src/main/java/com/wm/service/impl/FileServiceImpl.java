package com.wm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wm.entity.FileEntity;
import com.wm.mapper.FileRepository;
import com.wm.service.FileService;

@Service
@Transactional
public class FileServiceImpl implements FileService{
	
	@Autowired
	private FileRepository fileRepository;
	
	public FileEntity getFile(Long tenantId) {
		return fileRepository.selectContentById(tenantId);
	}
}
