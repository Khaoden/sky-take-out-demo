package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "分类相关接口")
@Slf4j
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    CategoryService categoryservice;

    @PutMapping
    @ApiOperation("修改分类")
    public Result updateCategory(@RequestBody CategoryDTO categorydto) {
        log.info("修改分类:{}", categorydto);
        categoryservice.updateCategory(categorydto);
        return Result.success("修改成功");
    }

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("分页查询:{}", categoryPageQueryDTO);
        PageResult pageResult = categoryservice.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("status/{status}")
    @ApiOperation("启用禁用分类")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        log.info("启用禁用分类:{},{}", status, id);
        categoryservice.startOrStop(status, id);
        return Result.success("修改成功");
    }

    @PostMapping
    @ApiOperation("新增分类")
    public Result save(@RequestBody CategoryDTO categorydto) {
        log.info("新增分类:{}", categorydto);
        categoryservice.save(categorydto);
        return Result.success("新增成功");
    }

    @DeleteMapping
    @ApiOperation("删除分类")
    public Result deleteById(Long id) {
        log.info("删除分类:{}", id);
        categoryservice.deleteById(id);
        return Result.success("删除成功");
    }

    @GetMapping("/list")
    @ApiOperation("根据类型查询分类")
    public Result<List<CategoryDTO>> list(Integer type) {
        log.info("根据类型查询分类:{}", type);
        List<CategoryDTO> res = categoryservice.list(type);
        return Result.success(res);
    }
}
