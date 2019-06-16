package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.entity.WordPhoneficEntity;
import com.tfjybj.english.model.WordPhoneficModel;
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
@Api(tags = {"WordPhonefic表接口"})
@RequestMapping(value = "/wordPhonefic")
@RestController
public class WordPhoneficController {

    @Resource
    private WordTestService wordTestService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model WordPhoneficModel
     * @return 添加的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody WordPhoneficModel model) {
		if (StringUtils.isEmpty(model.getWordId())){
            return ItooResult.build(ItooResult.FAIL, "wordId为空");
        }
		if (StringUtils.isEmpty(model.getState())){
            return ItooResult.build(ItooResult.FAIL, "state为空");
        }
		if (StringUtils.isEmpty(model.getCreatTime())){
            return ItooResult.build(ItooResult.FAIL, "creatTime为空");
        }
        WordPhoneficEntity wordPhoneficEntity = new WordPhoneficEntity();
        BeanUtils.copyProperties(model, wordPhoneficEntity);
        wordTestService.save(wordPhoneficEntity);
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
     * @param model WordPhoneficModel
     * @return 修改后的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id修改wordTest")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody WordPhoneficModel model) {
		if (StringUtils.isEmpty(model.getWordId())){
            return ItooResult.build(ItooResult.FAIL, "wordId为空");
        }
		if (StringUtils.isEmpty(model.getState())){
            return ItooResult.build(ItooResult.FAIL, "state为空");
        }
		if (StringUtils.isEmpty(model.getCreatTime())){
            return ItooResult.build(ItooResult.FAIL, "creatTime为空");
        }
        WordPhoneficEntity wordPhoneficEntity = new WordPhoneficEntity();
        BeanUtils.copyProperties(model, wordPhoneficEntity);
        wordTestService.updateById(wordPhoneficEntity);
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
        WordPhoneficEntity wordPhoneficEntity = wordTestService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordPhoneficEntity);
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
        PageInfo<WordPhoneficEntity> wordTests = wordTestService.queryPageAll(pageNo, pageSize);
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
        List<WordPhoneficEntity> wordPhoneficEntity = wordTestService.queryPhoneticByWordId(wordId);
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",wordPhoneficEntity);
    }

    /**
     * 根据音标Id查找状态
     * @author 张凯超
     * @param phoneficId  音标Id
     * @return state 0 正确 1 错误
     *
     */
    @ApiOperation(value = "根据音标Id拼写查找状态")
    @GetMapping(value = "/queryWordStateByphoneficId/{phoneficId}")
    public ItooResult queryWordStateByphoneficId(@ApiParam(value = "phoneficId",name = "音标Id",required = true)@PathVariable String phoneficId){
        WordPhoneficEntity wordPhoneficEntity = wordTestService.queryWordStateByphoneficId(phoneficId);
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",wordPhoneficEntity);
    }

    /**
     * 根据单词Id获取相关音标信息
     * @author 张凯超
     * @param wordId 单词Id
     * @return 音标信息
     * @since 2019年6月14日22点35分
     *
     */
    @ApiOperation(value = "根据单词Id获取相关音标信息")
//    @GetMapping(value = "/queryPhoficByWordId/{wordId}")
    @GetMapping(value = "/queryPhoneficAboutByWordId/{wordId}")
    public ItooResult queryPhoneficAboutByWordId(@PathVariable String wordId){
       List<WordPhoneficModel> wordPhoneficModelList = wordTestService.queryPhoneficAboutByWordId(wordId);
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",wordPhoneficModelList);

    }

}
