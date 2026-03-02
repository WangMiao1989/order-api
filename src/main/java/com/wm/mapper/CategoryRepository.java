package com.wm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.CategoryEntity;

@Mapper
public interface CategoryRepository {
	public List<CategoryEntity> findAllCategorys();
}
