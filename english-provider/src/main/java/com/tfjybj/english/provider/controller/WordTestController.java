package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.entity.WordTestEntity;
import com.tfjybj.english.model.WordTestModel;
import com.tfjybj.english.provider.service.WordTestService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.ejb.PostActivate;
import java.util.List;



/**
 * WordTestController
 * wordTest表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Api(tags = {"wordTest表接口"})
@RequestMapping(value = "/wordTest")
@RestController
public class WordTestController {

    @Resource
    private WordTestService wordTestService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model WordTestModel
     * @return 添加的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody WordTestModel model) {
		if (StringUtils.isEmpty(model.getWordId())){
            return ItooResult.build(ItooResult.FAIL, "wordId为空");
        }
		if (StringUtils.isEmpty(model.getPhoneficId())){
            return ItooResult.build(ItooResult.FAIL, "phoneficId为空");
        }
		if (StringUtils.isEmpty(model.getState())){
            return ItooResult.build(ItooResult.FAIL, "state为空");
        }
		if (StringUtils.isEmpty(model.getCreatTime())){
            return ItooResult.build(ItooResult.FAIL, "creatTime为空");
        }
        WordTestEntity wordTestEntity = new WordTestEntity();
        BeanUtils.copyProperties(model, wordTestEntity);
        wordTestService.save(wordTestEntity);
        return ItooResult.build(ItooResult.SUCCESS, "添加成功");
    }

    /**
     * 删除
     *
     * @param id 主键id
     * @return ItooResult 是否删除成功          
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id删除（单个）")
    @DeleteMapping(value = {"/delete/{id}"})
    public ItooResult delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        wordTestService.removeById(id);
        return ItooResult.build(ItooResult.SUCCESS, "删除成功");
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return ItooResult 批量删除是否成功结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id批量删除")
    @DeleteMapping(value = {"/deleteByIds"})
    @ApiImplicitParam(name = "ids", value = "ids", dataType = "List<String>", required = true)
    public ItooResult deleteByIds(@RequestBody List<String> ids) {
        wordTestService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model WordTestModel
     * @return 修改后的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id修改wordTest")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody WordTestModel model) {
		if (StringUtils.isEmpty(model.getWordId())){
            return ItooResult.build(ItooResult.FAIL, "wordId为空");
        }
		if (StringUtils.isEmpty(model.getPhoneficId())){
            return ItooResult.build(ItooResult.FAIL, "phoneficId为空");
        }
		if (StringUtils.isEmpty(model.getState())){
            return ItooResult.build(ItooResult.FAIL, "state为空");
        }
		if (StringUtils.isEmpty(model.getCreatTime())){
            return ItooResult.build(ItooResult.FAIL, "creatTime为空");
        }
        WordTestEntity wordTestEntity = new WordTestEntity();
        BeanUtils.copyProperties(model, wordTestEntity);
        wordTestService.updateById(wordTestEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找WordTest
     *
     * @param id 主键id
     * @return 根据id查找的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        WordTestEntity wordTestEntity = wordTestService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordTestEntity);
    }

    /**
     * 分页查询所有WordTest
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "分页查询所有WordTest")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo",value = "页码",required = true,example = "1")@PathVariable Integer pageNo, 
								   @ApiParam(name = "pageSize",value = "页数",required = true,example = "10")@PathVariable Integer pageSize) {
        PageInfo<WordTestEntity> wordTests = wordTestService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordTests);
    }
	
    //endregion

    /* **********************************以下为非模板生成的内容********************************* */


    /**
     * 根据单词Id获取对应单词audio
     * @param wordId 单词Id
     * @return 单词audio
     * @author 张凯超
     */
    @ApiOperation(value ="根据单词Id获取对应单词audio" )
    @GetMapping(value = {"/queryAudioByWordId/{wordId}"})
    public ItooResult queryAudioByWordId(@ApiParam(value = "单词Id",name = "wordId") @PathVariable String wordId){
       WordEntity wordEntity = wordTestService.queryAudioByWordId(wordId);
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",wordEntity.getAudio());
    }

    /**
     *根据单词id匹配对应两个音标
     * @param wordId 单词Id
     * @return 单词Id对应音标
     * @author 张凯超
     */
    @ApiOperation(value = "根据单词id匹配对应两个音标")
    @GetMapping(value = {"/queryPhoneticByWordId/{wordId}"})
    public ItooResult queryPhoneticByWordId(@ApiParam(value = "单词Id",name = "wordId",required = true) @PathVariable String wordId){
        List<WordTestEntity> wordTestEntityList = wordTestService.queryPhoneticByWordId(wordId);
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",wordTestEntityList);
    }

    /**
     * 根据单词拼写查找状态
     * @author
     * @param word 单词
     * @return state 0 正确 1 错误
     */
    public ItooResult queryWordStateByWord(@ApiParam(value = "word",name = "单词",required = true)@PathVariable String word){
        WordTestModel wordTestModel = wordTestService.queryWordStateByWord(word);
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",wordTestModel);
    }

}
