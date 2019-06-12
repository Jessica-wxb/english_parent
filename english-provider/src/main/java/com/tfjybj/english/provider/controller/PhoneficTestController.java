package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.PhoneficTestEntity;
import com.tfjybj.english.model.PhoneficTestModel;
import com.tfjybj.english.provider.service.PhoneficTestService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * PhoneficTestController
 * phoneficTest表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Api(tags = {"phoneficTest表接口"})
@RequestMapping(value = "/phoneficTest")
@RestController
public class PhoneficTestController {

    @Resource
    private PhoneficTestService phoneficTestService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model PhoneficTestModel
     * @return 添加的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody PhoneficTestModel model) {
        if (StringUtils.isEmpty(model.getPhoneficId())) {
            return ItooResult.build(ItooResult.FAIL, "phoneficId为空");
        }
        if (StringUtils.isEmpty(model.getWordId())) {
            return ItooResult.build(ItooResult.FAIL, "wordId为空");
        }
        if (StringUtils.isEmpty(model.getState())) {
            return ItooResult.build(ItooResult.FAIL, "state为空");
        }
        if (StringUtils.isEmpty(model.getUpdteTime())) {
            return ItooResult.build(ItooResult.FAIL, "updteTime为空");
        }
        PhoneficTestEntity phoneficTestEntity = new PhoneficTestEntity();
        BeanUtils.copyProperties(model, phoneficTestEntity);
        phoneficTestService.save(phoneficTestEntity);
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
        phoneficTestService.removeById(id);
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
        phoneficTestService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model PhoneficTestModel
     * @return 修改后的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id修改phoneficTest")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody PhoneficTestModel model) {
        if (StringUtils.isEmpty(model.getPhoneficId())) {
            return ItooResult.build(ItooResult.FAIL, "phoneficId为空");
        }
        if (StringUtils.isEmpty(model.getWordId())) {
            return ItooResult.build(ItooResult.FAIL, "wordId为空");
        }
        if (StringUtils.isEmpty(model.getState())) {
            return ItooResult.build(ItooResult.FAIL, "state为空");
        }
        if (StringUtils.isEmpty(model.getUpdteTime())) {
            return ItooResult.build(ItooResult.FAIL, "updteTime为空");
        }
        PhoneficTestEntity phoneficTestEntity = new PhoneficTestEntity();
        BeanUtils.copyProperties(model, phoneficTestEntity);
        phoneficTestService.updateById(phoneficTestEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找PhoneficTest
     *
     * @param id 主键id
     * @return 根据id查找的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        PhoneficTestEntity phoneficTestEntity = phoneficTestService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficTestEntity);
    }

    /**
     * 分页查询所有PhoneficTest
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "分页查询所有PhoneficTest")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo", value = "页码", required = true, example = "1") @PathVariable Integer pageNo,
                                   @ApiParam(name = "pageSize", value = "页数", required = true, example = "10") @PathVariable Integer pageSize) {
        PageInfo<PhoneficTestEntity> phoneficTests = phoneficTestService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficTests);
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 根据音标id找到对应音频audio
     *
     * @param phoneficId 音标Id
     * @return 查询结果
     * @author 薛帅行
     * @since 2019年6月11日10:29:26
     */
    @ApiOperation(value = "根据音标id找到对应音频audio")
    @GetMapping(value = "queryAudioByPhoneficId/{phoneficId}")
    public ItooResult queryAudioByPhoneficId(@ApiParam(name = "phoneficId",value = "音标Id",required = true)@PathVariable String phoneficId) {
        List<PhoneficTestModel> phoneficTestModelList = phoneficTestService.queryAudioByPhoneficId(phoneficId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficTestModelList);
    }

    /**
     * 根据音标id匹配对应两个单词word
     *
     * @param phoneficId
     * @return 查询结果
     * @author 薛帅行
     * @since 2019年6月11日15:40:32
     */
    @ApiOperation(value = "根据音标id匹配对应两个单词word")
    @GetMapping(value = "selectWordByPhoneficId/{phoneficId}")
    public ItooResult selectWordByPhoneficId(@ApiParam(name = "phoneficId",value = "音标Id",required = true)@PathVariable String phoneficId) {
        List<PhoneficTestModel> phoneficTestModelList = phoneficTestService.selectWordByPhoneficId(phoneficId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficTestModelList);
    }

    /**
     * 根据单词拼写查找状态
     *
     * @param word 单词拼写
     * @return 查询结果
     * @author 薛帅行
     * @since 2019年6月11日19:11:05
     */
    @ApiOperation(value = "根据单词拼写查找单词音频")
    @GetMapping(value = {"/queryStateByWord/{word}"})
    public ItooResult queryStateByWord(@ApiParam(name = "word",value = "单词",required = true)@PathVariable String word) {
        PhoneficTestEntity phoneficTestEntity = phoneficTestService.queryStateByWord(word);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficTestEntity);
    }
    /**
     * 根据id查找PhoneficTest
     * @param phoneficid 音标id
     * @return 音标练习(听)根据音标id查询对应正确单词
     * @author 邢美玲
     * @since ${version} 2019年6月11日14:39:46
     */
    @ApiOperation(value = "音标练习(听)根据音标id查询对应正确单词")
    @GetMapping(value = {"/findPhoneficById/{phoneficid}"})
    public ItooResult findPhoneficTestById(@ApiParam(value = "音标id", required = true) @PathVariable Integer phoneficid) {
        List<PhoneficTestModel> phoneficTestList = phoneficTestService.getPhoneficTestByIdById(phoneficid);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficTestList);
    }


}    
