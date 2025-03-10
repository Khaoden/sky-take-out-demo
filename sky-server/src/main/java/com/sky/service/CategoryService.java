package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {
    void updateCategory(CategoryDTO categorydto);

    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void startOrStop(Integer status, Long id);

    void save(CategoryDTO categoryDTO);

    void deleteById(Long id);

    List<CategoryDTO> list(Integer type);
}
