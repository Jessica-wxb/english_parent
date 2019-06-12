package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.provider.service.WordService;
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
 * WordController
 * word表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Api(tags = {"word表接口"})
@RequestMapping(value = "/word")
@Slf4j
@RestController
public class WordController {

    @Resource
    private WordService wordService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model WordModel
     * @return 添加的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody WordModel model) {
        if (StringUtils.isEmpty(model.getWord())) {
            return ItooResult.build(ItooResult.FAIL, "word为空");
        }
        if (StringUtils.isEmpty(model.getWordPicture1())) {
            return ItooResult.build(ItooResult.FAIL, "wordPicture1为空");
        }
        if (StringUtils.isEmpty(model.getWordPicture2())) {
            return ItooResult.build(ItooResult.FAIL, "wordPicture2为空");
        }
        if (StringUtils.isEmpty(model.getWordPicture3())) {
            return ItooResult.build(ItooResult.FAIL, "wordPicture3为空");
        }
        if (StringUtils.isEmpty(model.getWordPicture4())) {
            return ItooResult.build(ItooResult.FAIL, "wordPicture4为空");
        }
        if (StringUtils.isEmpty(model.getWordPicture5())) {
            return ItooResult.build(ItooResult.FAIL, "wordPicture5为空");
        }
        if (StringUtils.isEmpty(model.getAudio())) {
            return ItooResult.build(ItooResult.FAIL, "audio为空");
        }
        if (StringUtils.isEmpty(model.getPhonefic())) {
            return ItooResult.build(ItooResult.FAIL, "phonefic为空");
        }
        WordEntity wordEntity = new WordEntity();
        BeanUtils.copyProperties(model, wordEntity);
        wordService.save(wordEntity);
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
        wordService.removeById(id);
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
        wordService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model WordModel
     * @return 修改后的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id修改word")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody WordModel model) {
        if (StringUtils.isEmpty(model.getWord())) {
            return ItooResult.build(ItooResult.FAIL, "word为空");
        }
        if (StringUtils.isEmpty(model.getWordPicture1())) {
            return ItooResult.build(ItooResult.FAIL, "wordPicture1为空");
        }
        if (StringUtils.isEmpty(model.getWordPicture2())) {
            return ItooResult.build(ItooResult.FAIL, "wordPicture2为空");
        }
        if (StringUtils.isEmpty(model.getWordPicture3())) {
            return ItooResult.build(ItooResult.FAIL, "wordPicture3为空");
        }
        if (StringUtils.isEmpty(model.getWordPicture4())) {
            return ItooResult.build(ItooResult.FAIL, "wordPicture4为空");
        }
        if (StringUtils.isEmpty(model.getWordPicture5())) {
            return ItooResult.build(ItooResult.FAIL, "wordPicture5为空");
        }
        if (StringUtils.isEmpty(model.getAudio())) {
            return ItooResult.build(ItooResult.FAIL, "audio为空");
        }
        if (StringUtils.isEmpty(model.getPhonefic())) {
            return ItooResult.build(ItooResult.FAIL, "phonefic为空");
        }
        WordEntity wordEntity = new WordEntity();
        BeanUtils.copyProperties(model, wordEntity);
        wordService.updateById(wordEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找Word
     *
     * @param id 主键id
     * @return 根据id查找的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        WordEntity wordEntity = wordService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordEntity);
    }

    /**
     * 分页查询所有Word
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "分页查询所有Word")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo", value = "页码", required = true, example = "1") @PathVariable Integer pageNo,
                                   @ApiParam(name = "pageSize", value = "页数", required = true, example = "10") @PathVariable Integer pageSize) {
        PageInfo<WordEntity> words = wordService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", words);
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 根据设定学习量查询数据条数
     *
     * @param setNumber 设定当天学习任务量
     * @return 查询任务量条数
     * @author 马莹
     * @since 2019-6-9 19:41:00
     */
    @ApiOperation(value = "根据设定学习量查询数据条数")
    @GetMapping(value = "/selDataNum/{setNumber}")
    public ItooResult selDataNum(@PathVariable Integer setNumber) {
        return ItooResult.build(ItooResult.SUCCESS, "查询成功!", wordService.selDataNum(setNumber));
    }

    /**
     * 查询所有Word
     *
     * @return 查询的单词总数
     * @author 邢美玲
     * @since ${version} 2019年6月9日14:52:28
     */
    @ApiOperation(value = "分页查询所有Word")
    @GetMapping(value = {"/selectAll"})
    public ItooResult queryPageAll() {
        //int allwords;
        Integer allwords = wordService.selectAll();
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", allwords);
    }

    /**
     * 根据当天学习任务量查询
     *
     * @param studSum 当天学习任务量
     * @return Id 返回一个Id值
     * @author 任嘉颖
     * @since 2019年6月10日15:13:41
     */
    @ApiOperation(value = "根据当天学习任务量查询")
    @GetMapping(value = "/selectPhoneficPictureById/{studSum}")
    public ItooResult selectPhoneficPictureById(@PathVariable Integer studSum) {
        return ItooResult.build(ItooResult.SUCCESS, "查询成功!", wordService.selectPhoneficPictureById(studSum));
    }

    /**
     * 根据目录结构插入数据
     *
     * @param path 文件路径
     * @return true/false
     * @author 马莹
     * @since 2019-6-11 19:31:50
     */
    @ApiOperation(value = "根据目录结构插入数据")
    @GetMapping(value = "/batchInsert/{path}")
    public ItooResult batchInsertion(String path) {
        try {
            boolean flag = wordService.batchInsert(path);
            return ItooResult.build(ItooResult.SUCCESS, "上传成功!", flag);

        } catch (Exception e) {
            log.error("错误" + e);
            return ItooResult.build(ItooResult.FAIL, "文件插入失败!");
        }
    }

}
