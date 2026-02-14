package com.wm.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wm.entity.CategoryEntity;

@Repository
public interface CategoryRepository {
	public List<CategoryEntity> findAllCategorys();
}
