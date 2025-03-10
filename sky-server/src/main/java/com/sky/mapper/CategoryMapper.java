package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import com.sky.entity.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
    void update(Category category);

    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void insert(Category category);

    void deleteById(Long id);

    List<Category> list(Integer type);
}
