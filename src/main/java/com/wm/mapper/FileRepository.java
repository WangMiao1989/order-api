package com.wm.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.FileEntity;

@Mapper
public interface FileRepository {
	public FileEntity selectContentById(Long fileId);
	
	public int insertFile(FileEntity file);
	
	public void deleteFile(Long fileId);
}
