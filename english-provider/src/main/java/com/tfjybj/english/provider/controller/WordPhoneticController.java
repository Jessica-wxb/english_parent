package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.WordPhoneticEntity;
import com.tfjybj.english.model.WordPhoneticModel;
import com.tfjybj.english.provider.service.WordPhoneticService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * WordPhoneticController
 * wordPhonetic表
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@Api(tags = {"wordPhonetic表接口"})
@RequestMapping(value = "/wordPhonetic")
@RestController
public class WordPhoneticController {

    @Resource
    private WordPhoneticService wordPhoneticService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model WordPhoneticModel
     * @return 添加的结果
     * @author 闫伟强
     * @since 1.0.0 2019-09-06 11:10:13
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody WordPhoneticModel model) {
        WordPhoneticEntity wordPhoneticEntity = new WordPhoneticEntity();
        BeanUtils.copyProperties(model, wordPhoneticEntity);
        wordPhoneticService.save(wordPhoneticEntity);
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
        wordPhoneticService.removeById(id);
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
        wordPhoneticService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model WordPhoneticModel
     * @return 修改后的结果
     * @author 闫伟强
     * @since 1.0.0 2019-09-06 11:10:13
     */
    @ApiOperation(value = "根据id修改wordPhonetic")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody WordPhoneticModel model) {
        WordPhoneticEntity wordPhoneticEntity = new WordPhoneticEntity();
        BeanUtils.copyProperties(model, wordPhoneticEntity);
        wordPhoneticService.updateById(wordPhoneticEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找WordPhonetic
     *
     * @param id 主键id
     * @return 根据id查找的结果
     * @author 闫伟强
     * @since 1.0.0 2019-09-06 11:10:13
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        WordPhoneticEntity wordPhoneticEntity = wordPhoneticService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordPhoneticEntity);
    }

    /**
     * 分页查询所有WordPhonetic
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 闫伟强
     * @since 1.0.0 2019-09-06 11:10:13
     */
    @ApiOperation(value = "分页查询所有WordPhonetic")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo",value = "页码",required = true,example = "1")@PathVariable Integer pageNo, 
								   @ApiParam(name = "pageSize",value = "页数",required = true,example = "10")@PathVariable Integer pageSize) {
        PageInfo<WordPhoneticEntity> wordPhonetics = wordPhoneticService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordPhonetics);
    }
	
    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 根据目录结构插入单词
     *
     * @param path 文件路径
     * @return true/false
     * @author 陈广晗
     * @since 2019-08-30 10:05:18
     */
    @ApiOperation(value = "根据目录结构插入单词")
    @GetMapping(value = "/batchInsert")
    public ItooResult batchInsertion(@RequestParam String path) {
        try {
            boolean flag = wordPhoneticService.insertWordPhoneticTable(path);
            return ItooResult.build(ItooResult.SUCCESS, "上传成功!", flag);
        } catch (Exception e) {
            return ItooResult.build(ItooResult.FAIL, "文件插入失败!");
        }
    }
    /**
     * 将单词选音标插入redis
     * @author 闫伟强
     * @since 2019年10月3日15:58:18
     */
    @ApiOperation(value = "将单词选音标插入redis")
    @GetMapping(value = "/WordPhoneticInsertRedis")
    public ItooResult WordPhoneticInsertRedis() {
        wordPhoneticService.WordPhoneticInsertRedis();
        return ItooResult.build(ItooResult.SUCCESS, "插入成功");
    }
}    
