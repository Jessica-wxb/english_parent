package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.PhoneticWrongEntity;
import com.tfjybj.english.model.PhoneticWrongModel;
import com.tfjybj.english.provider.service.PhoneticWrongService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * PhoneticWrongController
 * phoneticWrong表
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@Api(tags = {"phoneticWrong表接口"})
@RequestMapping(value = "/phoneticWrong")
@RestController
public class PhoneticWrongController {

    @Resource
    private PhoneticWrongService phoneticWrongService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model PhoneticWrongModel
     * @return 添加的结果
     * @author 闫伟强
     * @since 1.0.0 2019-09-06 11:10:13
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody PhoneticWrongModel model) {
        PhoneticWrongEntity phoneticWrongEntity = new PhoneticWrongEntity();
        BeanUtils.copyProperties(model, phoneticWrongEntity);
        phoneticWrongService.save(phoneticWrongEntity);
        return ItooResult.build(ItooResult.SUCCESS, "添加成功");
    }

    /**
     * 删除
     *
     * @param id 主键id
     * @return ItooResult 是否删除成功          
     * @author 闫伟强
     * @since 1.0.0 2019-09-06 11:10:13
     */
    @ApiOperation(value = "根据id删除（单个）")
    @DeleteMapping(value = {"/delete/{id}"})
    public ItooResult delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        phoneticWrongService.removeById(id);
        return ItooResult.build(ItooResult.SUCCESS, "删除成功");
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return ItooResult 批量删除是否成功结果
     * @author 闫伟强
     * @since 1.0.0 2019-09-06 11:10:13
     */
    @ApiOperation(value = "根据id批量删除")
    @DeleteMapping(value = {"/deleteByIds"})
    @ApiImplicitParam(name = "ids", value = "ids", dataType = "List<String>", required = true)
    public ItooResult deleteByIds(@RequestBody List<String> ids) {
        phoneticWrongService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model PhoneticWrongModel
     * @return 修改后的结果
     * @author 闫伟强
     * @since 1.0.0 2019-09-06 11:10:13
     */
    @ApiOperation(value = "根据id修改phoneticWrong")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody PhoneticWrongModel model) {
        PhoneticWrongEntity phoneticWrongEntity = new PhoneticWrongEntity();
        BeanUtils.copyProperties(model, phoneticWrongEntity);
        phoneticWrongService.updateById(phoneticWrongEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找PhoneticWrong
     *
     * @param id 主键id
     * @return 根据id查找的结果
     * @author 闫伟强
     * @since 1.0.0 2019-09-06 11:10:13
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        PhoneticWrongEntity phoneticWrongEntity = phoneticWrongService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneticWrongEntity);
    }

    /**
     * 分页查询所有PhoneticWrong
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 闫伟强
     * @since 1.0.0 2019-09-06 11:10:13
     */
    @ApiOperation(value = "分页查询所有PhoneticWrong")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo",value = "页码",required = true,example = "1")@PathVariable Integer pageNo, 
								   @ApiParam(name = "pageSize",value = "页数",required = true,example = "10")@PathVariable Integer pageSize) {
        PageInfo<PhoneticWrongEntity> phoneticWrongs = phoneticWrongService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneticWrongs);
    }
	
    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

   
}    
