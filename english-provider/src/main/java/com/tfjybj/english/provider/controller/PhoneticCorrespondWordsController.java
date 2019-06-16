package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.PhoneticCorrespondWordsEntity;
import com.tfjybj.english.model.PhoneficModel;
import com.tfjybj.english.model.PhoneticCorrespondWordsModel;
import com.tfjybj.english.provider.service.PhoneticCorrespondWordsService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * PhoneticCorrespondWordsController
 * phoneticCorrespondWords表
 *
 * @author 马莹
 * @version 1.0.0
 * @since  2019-06-14 16:48:08
 */
@Api(tags = {"phoneticCorrespondWords表接口"})
@RequestMapping(value = "/phoneticCorrespondWords")
@RestController
public class PhoneticCorrespondWordsController {

    @Resource
    private PhoneticCorrespondWordsService phoneticCorrespondWordsService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model PhoneticCorrespondWordsModel
     * @return 添加的结果
     * @author 马莹
	 * @version 1.0.0
     * @since 2019-06-14 16:48:08
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody PhoneticCorrespondWordsModel model) {
        PhoneticCorrespondWordsEntity phoneticCorrespondWordsEntity = new PhoneticCorrespondWordsEntity();
        BeanUtils.copyProperties(model, phoneticCorrespondWordsEntity);
        phoneticCorrespondWordsService.save(phoneticCorrespondWordsEntity);
        return ItooResult.build(ItooResult.SUCCESS, "添加成功");
    }

    /**
     * 删除
     *
     * @param id 主键id
     * @return ItooResult 是否删除成功          
     * @author 马莹
	 * @version 1.0.0
     * @since 2019-06-14 16:48:08
     */
    @ApiOperation(value = "根据id删除（单个）")
    @DeleteMapping(value = {"/delete/{id}"})
    public ItooResult delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        phoneticCorrespondWordsService.removeById(id);
        return ItooResult.build(ItooResult.SUCCESS, "删除成功");
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return ItooResult 批量删除是否成功结果
     * @author 马莹
	 * @version 1.0.0
     * @since  2019-06-14 16:48:08
     */
    @ApiOperation(value = "根据id批量删除")
    @DeleteMapping(value = {"/deleteByIds"})
    @ApiImplicitParam(name = "ids", value = "ids", dataType = "List<String>", required = true)
    public ItooResult deleteByIds(@RequestBody List<String> ids) {
        phoneticCorrespondWordsService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model PhoneticCorrespondWordsModel
     * @return 修改后的结果
     * @author 马莹
	 * @version 1.0.0
     * @since 2019-06-14 16:48:08
     */
    @ApiOperation(value = "根据id修改phoneticCorrespondWords")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody PhoneticCorrespondWordsModel model) {
        PhoneticCorrespondWordsEntity phoneticCorrespondWordsEntity = new PhoneticCorrespondWordsEntity();
        BeanUtils.copyProperties(model, phoneticCorrespondWordsEntity);
        phoneticCorrespondWordsService.updateById(phoneticCorrespondWordsEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找PhoneticCorrespondWords
     *
     * @param id 主键id
     * @return 根据id查找的结果
     * @author 马莹
	 * @version 1.0.0
     * @since 2019-06-14 16:48:08
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        PhoneticCorrespondWordsEntity phoneticCorrespondWordsEntity = phoneticCorrespondWordsService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneticCorrespondWordsEntity);
    }

    /**
     * 分页查询所有PhoneticCorrespondWords
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 马莹
	 * @version 1.0.0
     * @since 2019-06-14 16:48:08
     */
    @ApiOperation(value = "分页查询所有PhoneticCorrespondWords")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo",value = "页码",required = true,example = "1")@PathVariable Integer pageNo, 
								   @ApiParam(name = "pageSize",value = "页数",required = true,example = "10")@PathVariable Integer pageSize) {
        PageInfo<PhoneticCorrespondWordsEntity> phoneticCorrespondWordss = phoneticCorrespondWordsService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneticCorrespondWordss);
    }
	
    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 根据音标ID查询对应的单词
     * @return 音标所对应的单词
     * @param id 音标id
     * @author 闫伟强
     * @since ${version} 2019年6月15日19:17:28
     */
    @ApiOperation(value = "根据音标ID查询对应的单词")
    @GetMapping(value = {"/findWordById/[id]"})
    public ItooResult findWordById(@ApiParam(value = "音标id", required = true) @PathVariable String id) {
        List<PhoneticCorrespondWordsModel> PhoneticCorrespondList = phoneticCorrespondWordsService.findWordById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功",PhoneticCorrespondList);
    }
}    
