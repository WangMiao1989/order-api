package com.wm.responseDto;

import java.util.List;

import com.wm.entity.CategoryEntity;

import lombok.Data;

@Data
public class CategoryRetrieveResponse {
	private List<CategoryEntity> categoryList;
}
