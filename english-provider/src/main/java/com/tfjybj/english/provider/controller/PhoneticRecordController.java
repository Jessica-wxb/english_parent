package com.tfjybj.english.provider.controller;

import com.tfjybj.english.model.PhoneticCheckModel;
import com.tfjybj.english.provider.service.PhoneticRecordService;
import com.tfjybj.english.entity.PhoneticRecordEntity;
import com.tfjybj.english.model.PhoneticRecordModel;
import com.tfjybj.english.provider.service.PhoneticRecordService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * PhoneticRecordController
 * phoneticRecord表
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@Api(tags = {"phoneticRecord表接口"})
@RequestMapping(value = "/phoneticRecord")
@RestController
public class PhoneticRecordController {

    @Resource
    private PhoneticRecordService phoneticRecordService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model PhoneticRecordModel
     * @return 添加的结果
     * @author 闫伟强
     * @since 1.0.0 2019-09-06 11:10:13
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody PhoneticRecordModel model) {
        PhoneticRecordEntity phoneticRecordEntity = new PhoneticRecordEntity();
        BeanUtils.copyProperties(model, phoneticRecordEntity);
        phoneticRecordService.save(phoneticRecordEntity);
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
        phoneticRecordService.removeById(id);
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
        phoneticRecordService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model PhoneticRecordModel
     * @return 修改后的结果
     * @author 闫伟强
     * @since 1.0.0 2019-09-06 11:10:13
     */
    @ApiOperation(value = "根据id修改phoneticRecord")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody PhoneticRecordModel model) {
        PhoneticRecordEntity phoneticRecordEntity = new PhoneticRecordEntity();
        BeanUtils.copyProperties(model, phoneticRecordEntity);
        phoneticRecordService.updateById(phoneticRecordEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找PhoneticRecord
     *
     * @param id 主键id
     * @return 根据id查找的结果
     * @author 闫伟强
     * @since 1.0.0 2019-09-06 11:10:13
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        PhoneticRecordEntity phoneticRecordEntity = phoneticRecordService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneticRecordEntity);
    }

    /**
     * 分页查询所有PhoneticRecord
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 闫伟强
     * @since 1.0.0 2019-09-06 11:10:13
     */
    @ApiOperation(value = "分页查询所有PhoneticRecord")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo",value = "页码",required = true,example = "1")@PathVariable Integer pageNo,
								   @ApiParam(name = "pageSize",value = "页数",required = true,example = "10")@PathVariable Integer pageSize) {
        PageInfo<PhoneticRecordEntity> phoneticRecords = phoneticRecordService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneticRecords);
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */
    /**
     * @param
     * @return 进入音标检测
     * @author 闫伟强
     * @since 2019年9月6日08:18:57
     */
    @ApiOperation(value = "进入音标检测")
    @GetMapping(value = {"/findPhoneticCheck"})
    public ItooResult findPhoneticCheck() {
        PhoneticCheckModel phoneticCheckModel = phoneticRecordService.findPhoneticCheck();
        if (phoneticCheckModel == null) {
            return ItooResult.build("2222", "无数据", phoneticCheckModel);
        }
        return ItooResult.build("0000", "查询成功", phoneticCheckModel);
    }

    /**
     * @param phonetic 音标
     * @param correct 对错
     * @param falseType 错误类型
     * @return 点击正确或者错误
     * @author 闫伟强
     * @since 2019年9月6日08:18:57
     */
    @ApiOperation(value = "点击正确或者错误")
    @GetMapping(value = {"/nextPhonetic"})
    public ItooResult nextPhonetic(String phonetic, Integer correct, Integer falseType) {
        PhoneticCheckModel phoneticCheckModel = phoneticRecordService.nextPhonetic(phonetic,correct,falseType);
        if (phoneticCheckModel==null){
            return ItooResult.build("2222", "无数据");
        }
        return ItooResult.build("0000", "查询成功", phoneticCheckModel);
    }
   
}    
