package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.EExpensedRecordEntity;
import com.tfjybj.english.model.EExpensedRecordModel;
import com.tfjybj.english.provider.service.EExpensedRecordService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * EExpensedRecordController
 * eExpensedRecord表
 *
 * @author 王小波
 * @version ${version}
 * @since ${version} 2019-09-20 16:05:03
 */
@Api(tags = {"eExpensedRecord表接口"})
@RequestMapping(value = "/eExpensedRecord")
@RestController
public class EExpensedRecordController {

    @Resource
    private EExpensedRecordService eExpensedRecordService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model EExpensedRecordModel
     * @return 添加的结果
     * @author 王小波
     * @since ${version} 2019-09-20 16:05:03
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody EExpensedRecordModel model) {
		if (StringUtils.isEmpty(model.getUserId())){
            return ItooResult.build(ItooResult.FAIL, "userId为空");
        }
		if (StringUtils.isEmpty(model.getDescription())){
            return ItooResult.build(ItooResult.FAIL, "description为空");
        }
		if (StringUtils.isEmpty(model.getExpensedENum())){
            return ItooResult.build(ItooResult.FAIL, "expensedENum为空");
        }
        EExpensedRecordEntity eExpensedRecordEntity = new EExpensedRecordEntity();
        BeanUtils.copyProperties(model, eExpensedRecordEntity);
        eExpensedRecordService.save(eExpensedRecordEntity);
        return ItooResult.build(ItooResult.SUCCESS, "添加成功");
    }

    /**
     * 删除
     *
     * @param id 主键id
     * @return ItooResult 是否删除成功          
     * @author 王小波
     * @since ${version} 2019-09-20 16:05:03
     */
    @ApiOperation(value = "根据id删除（单个）")
    @DeleteMapping(value = {"/delete/{id}"})
    public ItooResult delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        eExpensedRecordService.removeById(id);
        return ItooResult.build(ItooResult.SUCCESS, "删除成功");
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return ItooResult 批量删除是否成功结果
     * @author 王小波
     * @since ${version} 2019-09-20 16:05:03
     */
    @ApiOperation(value = "根据id批量删除")
    @DeleteMapping(value = {"/deleteByIds"})
    @ApiImplicitParam(name = "ids", value = "ids", dataType = "List<String>", required = true)
    public ItooResult deleteByIds(@RequestBody List<String> ids) {
        eExpensedRecordService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model EExpensedRecordModel
     * @return 修改后的结果
     * @author 王小波
     * @since ${version} 2019-09-20 16:05:03
     */
    @ApiOperation(value = "根据id修改eExpensedRecord")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody EExpensedRecordModel model) {
		if (StringUtils.isEmpty(model.getUserId())){
            return ItooResult.build(ItooResult.FAIL, "userId为空");
        }
		if (StringUtils.isEmpty(model.getDescription())){
            return ItooResult.build(ItooResult.FAIL, "description为空");
        }
		if (StringUtils.isEmpty(model.getExpensedENum())){
            return ItooResult.build(ItooResult.FAIL, "expensedENum为空");
        }
        EExpensedRecordEntity eExpensedRecordEntity = new EExpensedRecordEntity();
        BeanUtils.copyProperties(model, eExpensedRecordEntity);
        eExpensedRecordService.updateById(eExpensedRecordEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找EExpensedRecord
     *
     * @param id 主键id
     * @return 根据id查找的结果
     * @author 王小波
     * @since ${version} 2019-09-20 16:05:03
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        EExpensedRecordEntity eExpensedRecordEntity = eExpensedRecordService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", eExpensedRecordEntity);
    }

    /**
     * 分页查询所有EExpensedRecord
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 王小波
     * @since ${version} 2019-09-20 16:05:03
     */
    @ApiOperation(value = "分页查询所有EExpensedRecord")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo",value = "页码",required = true,example = "1")@PathVariable Integer pageNo, 
								   @ApiParam(name = "pageSize",value = "页数",required = true,example = "10")@PathVariable Integer pageSize) {
        PageInfo<EExpensedRecordEntity> eExpensedRecords = eExpensedRecordService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", eExpensedRecords);
    }
	
    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

   
}    
