package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.PhoneficEntity;
import com.tfjybj.english.entity.PhoneficWordEntity;
import com.tfjybj.english.model.PhoneficModel;
import com.tfjybj.english.model.PhoneficWordModel;
import com.tfjybj.english.provider.service.PhoneficService;
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
 * PhoneficController
 * phonefic表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Api(tags = {"phonefic表接口"})
@RequestMapping(value = "/phonefic")
@RestController
@Slf4j
public class PhoneficController {

    @Resource
    private PhoneficService phoneficService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model PhoneficModel
     * @return 添加的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody PhoneficModel model) {
        if (StringUtils.isEmpty(model.getPhonefic())) {
            return ItooResult.build(ItooResult.FAIL, "phonefic为空");
        }
        if (StringUtils.isEmpty(model.getPhoneficPicture())) {
            return ItooResult.build(ItooResult.FAIL, "phoneficPicture为空");
        }
        if (StringUtils.isEmpty(model.getKnackPicture())) {
            return ItooResult.build(ItooResult.FAIL, "knackPicture为空");
        }
        if (StringUtils.isEmpty(model.getAudio())) {
            return ItooResult.build(ItooResult.FAIL, "audio为空");
        }
        if (StringUtils.isEmpty(model.getVideo())) {
            return ItooResult.build(ItooResult.FAIL, "video为空");
        }
        PhoneficEntity phoneficEntity = new PhoneficEntity();
        BeanUtils.copyProperties(model, phoneficEntity);
        phoneficService.save(phoneficEntity);
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
        phoneficService.removeById(id);
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
        phoneficService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model PhoneficModel
     * @return 修改后的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id修改phonefic")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody PhoneficModel model) {
        if (StringUtils.isEmpty(model.getPhonefic())) {
            return ItooResult.build(ItooResult.FAIL, "phonefic为空");
        }
        if (StringUtils.isEmpty(model.getPhoneficPicture())) {
            return ItooResult.build(ItooResult.FAIL, "phoneficPicture为空");
        }
        if (StringUtils.isEmpty(model.getKnackPicture())) {
            return ItooResult.build(ItooResult.FAIL, "knackPicture为空");
        }
        if (StringUtils.isEmpty(model.getAudio())) {
            return ItooResult.build(ItooResult.FAIL, "audio为空");
        }
        if (StringUtils.isEmpty(model.getVideo())) {
            return ItooResult.build(ItooResult.FAIL, "video为空");
        }
        PhoneficEntity phoneficEntity = new PhoneficEntity();
        BeanUtils.copyProperties(model, phoneficEntity);
        phoneficService.updateById(phoneficEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找Phonefic
     *
     * @param id 主键id
     * @return 根据id查找的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        PhoneficEntity phoneficEntity = phoneficService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficEntity);
    }

    /**
     * 分页查询所有Phonefic
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "分页查询所有Phonefic")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo", value = "页码", required = true, example = "1") @PathVariable Integer pageNo,
                                   @ApiParam(name = "pageSize", value = "页数", required = true, example = "10") @PathVariable Integer pageSize) {
        PageInfo<PhoneficEntity> phonefics = phoneficService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phonefics);
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */


    /**
     * 通过音标phonefic选择音频audio
     *
     * @param phonefic 音标
     * @return 查询结果
     * @author 薛帅行
     * @since 2019年6月12日11:45:42
     */
    @ApiOperation(value = "通过音标phonefic选择音频audio")
    @GetMapping(value = {"/selectAudioByPhonefic/{phonefic}"})
    public ItooResult selectAudioByPhonefic(@ApiParam(name = "phonefic", value = "音标", required = true) @PathVariable String phonefic) {
        PhoneficEntity phoneficEntity = phoneficService.selectAudioByPhonefic(phonefic);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficEntity);
    }


    /**
     * 通过音标Id查找对应图片
     *
     * @param phoneficId 音标ID
     * @return 音标对应图片
     * @Author 张凯超
     */
    @ApiOperation("通过音标Id查找对应图片")
    @GetMapping(value = {"/queryPictureByPhonetic/{phoneficId}"})
    public ItooResult queryPictureByPhoneficId(@ApiParam(value = "音标", name = "phoneficId", required = true) @PathVariable String phoneficId) {
        List<PhoneficEntity> phoneficEntityList = phoneficService.queryPictureByPhonefic(phoneficId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficEntityList);
    }

    /**
     * 根据id查找Phonefic
     *
     * @param id 主键id
     * @return 根据id查找的音标结果
     * @author xml
     * @since ${version} 2019年6月11日08:33:00
     */
    @ApiOperation(value = "音标练习(看)_根据音标id查询音标信息")
    @GetMapping(value = {"/findPhoneficById/{id}"})
    public ItooResult findPhoneficById(@ApiParam(value = "主键id", required = true) @PathVariable Integer id) {
        List<PhoneficModel> phoneficList = phoneficService.getPhoneficById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneficList);
    }

    /**
     * 根据目录结构插入音标
     *
     * @param phonePath 音标所在路径
     * @return true/false
     * @author 马莹
     * @since 2019-6-14 10:02:48
     */
    @ApiOperation(value = "根据目录结构插入音标")
    @GetMapping(value = "/phonePathInstert")
    public ItooResult phonePathInstert(@RequestParam String phonePath) {
        try {
            boolean flag = phoneficService.phonePathInstert(phonePath);
            return ItooResult.build(ItooResult.SUCCESS, "上传成功!", flag);

        } catch (Exception e) {
            log.error("错误" + e);
            return ItooResult.build(ItooResult.FAIL, "文件插入失败!");
        }
    }
}
