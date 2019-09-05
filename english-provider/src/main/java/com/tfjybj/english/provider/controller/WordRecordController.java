package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.WordRecordEntity;
import com.tfjybj.english.model.WordRecordModel;
import com.tfjybj.english.provider.service.WordRecordService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * WordRecordController
 * wordRecord表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since  2019-08-16 08:47:57
 */
@Api(tags = {"wordRecord表接口"})
@RequestMapping(value = "/wordRecord")
@RestController
public class WordRecordController {

    @Resource
    private WordRecordService wordRecordService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model WordRecordModel
     * @return 添加的结果
     * @author 张凯超
	 * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody WordRecordModel model) {
		if (StringUtils.isEmpty(model.getUserId())){
            return ItooResult.build(ItooResult.FAIL, "userId为空");
        }
        WordRecordEntity wordRecordEntity = new WordRecordEntity();
        BeanUtils.copyProperties(model, wordRecordEntity);
        wordRecordService.save(wordRecordEntity);
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
        wordRecordService.removeById(id);
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
        wordRecordService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model WordRecordModel
     * @return 修改后的结果
     * @author 张凯超
	 * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "根据id修改wordRecord")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody WordRecordModel model) {
		if (StringUtils.isEmpty(model.getUserId())){
            return ItooResult.build(ItooResult.FAIL, "userId为空");
        }
        WordRecordEntity wordRecordEntity = new WordRecordEntity();
        BeanUtils.copyProperties(model, wordRecordEntity);
        wordRecordService.updateById(wordRecordEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找WordRecord
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
        WordRecordEntity wordRecordEntity = wordRecordService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordRecordEntity);
    }

    /**
     * 分页查询所有WordRecord
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 张凯超
	 * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "分页查询所有WordRecord")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo",value = "页码",required = true,example = "1")@PathVariable Integer pageNo, 
								   @ApiParam(name = "pageSize",value = "页数",required = true,example = "10")@PathVariable Integer pageSize) {
        PageInfo<WordRecordEntity> wordRecords = wordRecordService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordRecords);
    }
	
    //endregion




}    
