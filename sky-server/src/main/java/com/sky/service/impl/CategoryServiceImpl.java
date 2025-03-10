package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.exception.CategoryException;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void updateCategory(CategoryDTO categorydto) {
        Category category = new Category();
        BeanUtils.copyProperties(categorydto,category);
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());
        log.info("更新分类信息:{}",category);
        categoryMapper.update(category);
    }

    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categorypagequerydto) {
        PageHelper.startPage(categorypagequerydto.getPage(), categorypagequerydto.getPageSize());

        Page<Category> page = categoryMapper.pageQuery(categorypagequerydto);

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        log.info("启用禁用分类:{},{}", status, id);
        Category category = Category.builder()
                .id(id)
                .status(status)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();
        categoryMapper.update(category);
    }

    @Override
    public void save(CategoryDTO categoryDTO) {
        log.info("新增分类:{}",categoryDTO);
//        if (categoryDTO.getId() != null) {
//            throw new CategoryException(MessageConstant.CATEGORY_EXISTS);
//        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        category.setStatus(1);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.insert(category);
    }

    @Override
    public void deleteById(Long id) {
        log.info("删除分类:{}",id);
        categoryMapper.deleteById(id);
    }

    @Override
    public List<CategoryDTO> list(Integer type) {
        log.info("根据类型查询分类:{}", type);
        List<Category> list = categoryMapper.list(type);
        log.info("查询结果:{}", list);
        List<CategoryDTO> res = new ArrayList<>();
        for (Category category : list) {
            CategoryDTO dto = new CategoryDTO();
            BeanUtils.copyProperties(category, dto); // 单个对象转换
            res.add(dto);
        }
        return res;
    }
}
