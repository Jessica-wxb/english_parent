package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.PhoneficWordEntity;
import com.tfjybj.english.model.PhoneficModel;
import com.tfjybj.english.model.PhoneficWordModel;
import com.tfjybj.english.entity.PhoneficEntity;
import com.tfjybj.english.provider.service.PhoneficWordService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
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
@Api(tags = {"phoneficWord表接口"})
@RequestMapping(value = "/phoneficTest")
@RestController
@Slf4j
public class PhoneficWordController {

    @Resource
    private PhoneficWordService phoneficWordService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model PhoneficWordModel
     * @return 添加的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody PhoneficWordModel model) {
        if (StringUtils.isEmpty(model.getPhoneficId())) {
            return ItooResult.build(ItooResult.FAIL, "phoneficId为空");
        }
        if (StringUtils.isEmpty(model.getState())) {
            return ItooResult.build(ItooResult.FAIL, "state为空");
        }
        PhoneficWordEntity phoneficWordEntity = new PhoneficWordEntity();
        BeanUtils.copyProperties(model, phoneficWordEntity);
        phoneficWordService.save(phoneficWordEntity);
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
        phoneficWordService.removeById(id);
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
        phoneficWordService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model PhoneficWordModel
     * @return 修改后的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id修改phoneficTest")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody PhoneficWordModel model) {
        if (StringUtils.isEmpty(model.getPhoneficId())) {
            return ItooResult.build(ItooResult.FAIL, "phoneficId为空");
        }
        if (StringUtils.isEmpty(model.getState())) {
            return ItooResult.build(ItooResult.FAIL, "state为空");
        }
        PhoneficWordEntity phoneficWordEntity = new PhoneficWordEntity();
        BeanUtils.copyProperties(model, phoneficWordEntity);
        phoneficWordService.updateById(phoneficWordEntity);
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
        PhoneficWordEntity phoneficWordEntity = phoneficWordService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficWordEntity);
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
        PageInfo<PhoneficWordEntity> phoneficTests = phoneficWordService.queryPageAll(pageNo, pageSize);
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
    public ItooResult queryAudioByPhoneficId(@ApiParam(name = "phoneficId", value = "音标Id", required = true) @PathVariable String phoneficId) {
        List<PhoneficWordModel> phoneficWordModelList = phoneficWordService.queryAudioByPhoneficId(phoneficId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficWordModelList);
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
    public ItooResult selectWordByPhoneficId(@ApiParam(name = "phoneficId", value = "音标Id", required = true) @PathVariable String phoneficId) {
        List<PhoneficWordModel> phoneficWordModelList = phoneficWordService.selectWordByPhoneficId(phoneficId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficWordModelList);
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
    public ItooResult queryStateByWord(@ApiParam(name = "word", value = "单词", required = true) @PathVariable String word) {
        PhoneficWordEntity phoneficWordEntity = phoneficWordService.queryStateByWord(word);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficWordEntity);
    }

    /**
     * 根据id查找PhoneficTest
     *
     * @param phoneficid 音标id
     * @return 音标练习(听)根据音标id查询对应正确单词
     * @author 邢美玲
     * @since ${version} 2019年6月11日14:39:46
     */
    @ApiOperation(value = "音标练习(听)根据音标id查询对应正确单词")
    @GetMapping(value = {"/findPhoneficById/{phoneficid}"})
    public ItooResult findPhoneficTestById(@ApiParam(value = "音标id", required = true, example = "0") @PathVariable Integer phoneficid) {
        List<PhoneficWordModel> phoneficTestList = phoneficWordService.getPhoneficTestByIdById(phoneficid);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficTestList);
    }


    /**
     * 根据用户Id查询音标Id、音频
     *
     * @param userId 用户Id
     * @return 音标Id、音频
     * @since 2019年6月13日22:31:07
     */
    @ApiOperation(value = "根据用户Id查询音标Id、音频")
    @GetMapping(value = {"/queryAudioByUserId/{userId}"})
    public ItooResult queryAudioByUserId(@ApiParam(value = "用户Id", name = "userId", required = true) @PathVariable String userId) {
        List<PhoneficEntity> phoneficTestEntityList = phoneficWordService.queryAudioByUserId(userId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficTestEntityList);
    }


    /**
     *根据音标的ID查询的tn_phonefic_word中所有的字段信息
     * @param phoneficId 音标Id
     * @return tn_phonefic_word中所有的字段
     * @since 2019年6月15日10:27:04
     * @author 冯佳兴
     */
    @ApiOperation(value = "根据音标查找tn_phonefic_word中所有的信息")
    @GetMapping(value = "selectAllById/{phoneficId}")
    public ItooResult selectAllById(@ApiParam(value = "音标phoneficId", name = "phoneficId", required = true) @PathVariable String phoneficId) {
        List<PhoneficWordModel> phoneficWordModelList = phoneficWordService.selectAllById(phoneficId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficWordModelList);
    }

    /**
     * 根据路径插入根据音频选单词的文件路径
     *
     * @param phoneWordPath 根据音频选择单词的文件路径
     * @return true/false
     * @author 马莹
     * @since 2019-6-15 18:05:07
     */
    @ApiOperation(value = "根据路径插入根据音频选单词的文件路径")
    @GetMapping(value = "phoneWordPath")
    public ItooResult phoneWordPath(@RequestParam String phoneWordPath) {
        try {
            boolean flag = phoneficWordService.insertPhoneWordTable(phoneWordPath);
            return ItooResult.build(ItooResult.SUCCESS, "上传成功!", flag);

        } catch (Exception e) {
            log.error("错误" + e);
            return ItooResult.build(ItooResult.FAIL, "文件插入失败!");
        }
    }
}
