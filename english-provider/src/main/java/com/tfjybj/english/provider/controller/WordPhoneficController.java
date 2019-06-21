package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.entity.WordPhoneficEntity;
import com.tfjybj.english.model.PhoneficModel;
import com.tfjybj.english.model.WordPhoneficModel;
import com.tfjybj.english.provider.service.WordTestService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * WordTestController
 * wordTest表
 *
 * @author 马莹
 * @version 1.0.0
 * @since 2019-06-08 14:26:23
 */
@Api(tags = {"WordPhonefic表接口"})
@RequestMapping(value = "/wordPhonefic")
@RestController
@Slf4j
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
        if (StringUtils.isEmpty(model.getWordId())) {
            return ItooResult.build(ItooResult.FAIL, "wordId为空");
        }
        if (StringUtils.isEmpty(model.getState())) {
            return ItooResult.build(ItooResult.FAIL, "state为空");
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
        if (StringUtils.isEmpty(model.getWordId())) {
            return ItooResult.build(ItooResult.FAIL, "wordId为空");
        }
        if (StringUtils.isEmpty(model.getState())) {
            return ItooResult.build(ItooResult.FAIL, "state为空");
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
    public ItooResult queryPageAll(@ApiParam(name = "pageNo", value = "页码", required = true, example = "1") @PathVariable Integer pageNo,
                                   @ApiParam(name = "pageSize", value = "页数", required = true, example = "10") @PathVariable Integer pageSize) {
        PageInfo<WordPhoneficEntity> wordTests = wordTestService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordTests);
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */


    /**
     * 根据单词Id获取对应单词audio
     *
     * @param wordId 单词Id
     * @return 单词audio
     * @author 张凯超
     */
    @ApiOperation(value = "根据单词Id获取对应单词audio")
    @GetMapping(value = {"/queryAudioByWordId/{wordId}"})
    public ItooResult queryAudioByWordId(@ApiParam(value = "单词Id", name = "wordId") @PathVariable String wordId) {
        WordEntity wordEntity = wordTestService.queryAudioByWordId(wordId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordEntity.getAudio());
    }

    /**
     * 根据单词id匹配对应两个音标
     *
     * @param wordId 单词Id
     * @return 单词Id对应音标
     * @author 张凯超
     */
    @ApiOperation(value = "根据单词id匹配对应两个音标")
    @GetMapping(value = {"/queryPhoneticByWordId/{wordId}"})
    public ItooResult queryPhoneticByWordId(@ApiParam(value = "单词Id", name = "wordId", required = true) @PathVariable String wordId) {
        List<WordPhoneficEntity> wordPhoneficEntity = wordTestService.queryPhoneticByWordId(wordId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordPhoneficEntity);
    }

    /**
     * 根据音标Id查找状态
     *
     * @param phoneficId 音标Id
     * @return state 0 正确 1 错误
     * @author 张凯超
     */
    @ApiOperation(value = "根据音标Id拼写查找状态")
    @GetMapping(value = "/queryWordStateByphoneficId/{phoneficId}")
    public ItooResult queryWordStateByphoneficId(@ApiParam(value = "phoneficId", name = "音标Id", required = true) @PathVariable String phoneficId) {
        WordPhoneficEntity wordPhoneficEntity = wordTestService.queryWordStateByphoneficId(phoneficId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordPhoneficEntity);
    }

    /**
     * 根据音标Id获取相关对应Id所有信息
     * @param PhoneficTrueId 正确音标Id
     * @return  音标信息集合
     * @author 张凯超
     * @since 2019年6月14日22点35分
     *
     */
    @ApiOperation(value = "根据音标Id获取相关对应Id所有信息")
    @GetMapping(value = "/queryPhoneficAboutByPhoneficTrueId/{PhoneficTrueId}")
    public ItooResult queryPhoneficAboutByPhoneficTrueId(@PathVariable String[] PhoneficTrueId) {
        List<WordPhoneficModel> wordPhoneficModelList = new ArrayList<>();
        for (String id : PhoneficTrueId) {
            List<WordPhoneficModel> wordPhoneficModel = wordTestService.queryPhoneficAboutByPhoneficTrueId(id);
            wordPhoneficModelList.addAll(wordPhoneficModel);
        }
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordPhoneficModelList);

    }

    /**
     * 根据音标Id查询所有信息
     *
     * @param phoneficid
     * @return
     * @author 张凯超
     * @since 2019年6月16日-21点14分
     */
    @ApiOperation(value = "根据音标Id查询所有信息")
    @GetMapping(value = "/queryAllByPhoneficId/{phoneficid}")
    public ItooResult queryAllById(@ApiParam(name = "phoneficid", value = "音标Id", required = true) @PathVariable(name = "phoneficid") String phoneficid) {
        List<WordPhoneficModel> wordPhoneficModelList = wordTestService.queryAllById(phoneficid);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordPhoneficModelList);
    }



    /**
     * 根据路径插入根据单词选音标的文件路径
     *
     * @param phoneWordPath 根据单词选音标的文件路径
     * @return true/false
     * @author 马莹
     * @since 2019-6-17 21:13:58
     */
    @ApiOperation(value = "根据路径插入根据单词选音标的文件路径")
    @GetMapping(value = "phoneWordPath")
    public ItooResult phoneWordPath(@RequestParam String phoneWordPath, Map<String, PhoneficModel> phoneficWordMap) {
        try {
            boolean flag = wordTestService.insertPhoneWordTable(phoneWordPath, phoneficWordMap);
            return ItooResult.build(ItooResult.SUCCESS, "上传成功!", flag);

        } catch (Exception e) {
            log.error("错误" + e);
            return ItooResult.build(ItooResult.FAIL, "文件插入失败!");
        }
    }

}
