package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.PhoneficRecordEntity;
import com.tfjybj.english.model.PhoneficRecordModel;
import com.tfjybj.english.provider.service.PhoneficRecordService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * PhoneficRecordController
 * phoneficRecord表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since  2019-08-16 08:47:57
 */
@Api(tags = {"phoneficRecord表接口"})
@RequestMapping(value = "/phoneficRecord")
@RestController
public class PhoneficRecordController {

    @Resource
    private PhoneficRecordService phoneficRecordService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model PhoneficRecordModel
     * @return 添加的结果
     * @author 张凯超
	 * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody PhoneficRecordModel model) {
        PhoneficRecordEntity phoneficRecordEntity = new PhoneficRecordEntity();
        BeanUtils.copyProperties(model, phoneficRecordEntity);
        phoneficRecordService.save(phoneficRecordEntity);
        return ItooResult.build(ItooResult.SUCCESS, "添加成功");
    }

    /**
     * 删除
     *
     * @param id 主键id
     * @return ItooResult 是否删除成功          
     * @author 张凯超
	 * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "根据id删除（单个）")
    @DeleteMapping(value = {"/delete/{id}"})
    public ItooResult delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        phoneficRecordService.removeById(id);
        return ItooResult.build(ItooResult.SUCCESS, "删除成功");
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return ItooResult 批量删除是否成功结果
     * @author 张凯超
	 * @version 1.0.0
     * @since  2019-08-16 08:47:57
     */
    @ApiOperation(value = "根据id批量删除")
    @DeleteMapping(value = {"/deleteByIds"})
    @ApiImplicitParam(name = "ids", value = "ids", dataType = "List<String>", required = true)
    public ItooResult deleteByIds(@RequestBody List<String> ids) {
        phoneficRecordService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model PhoneficRecordModel
     * @return 修改后的结果
     * @author 张凯超
	 * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "根据id修改phoneficRecord")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody PhoneficRecordModel model) {
        PhoneficRecordEntity phoneficRecordEntity = new PhoneficRecordEntity();
        BeanUtils.copyProperties(model, phoneficRecordEntity);
        phoneficRecordService.updateById(phoneficRecordEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找PhoneficRecord
     *
     * @param id 主键id
     * @return 根据id查找的结果
     * @author 张凯超
	 * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        PhoneficRecordEntity phoneficRecordEntity = phoneficRecordService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficRecordEntity);
    }

    /**
     * 分页查询所有PhoneficRecord
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 张凯超
	 * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "分页查询所有PhoneficRecord")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo",value = "页码",required = true,example = "1")@PathVariable Integer pageNo, 
								   @ApiParam(name = "pageSize",value = "页数",required = true,example = "10")@PathVariable Integer pageSize) {
        PageInfo<PhoneficRecordEntity> phoneficRecords = phoneficRecordService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficRecords);
    }
	
    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

   
}    
