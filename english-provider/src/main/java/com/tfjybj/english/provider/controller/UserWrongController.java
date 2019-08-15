package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.UserWrongEntity;
import com.tfjybj.english.model.UserWrongModel;
import com.tfjybj.english.provider.service.UserWrongService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * UserWrongController
 * userWrong表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Api(tags = {"userWrong表接口"})
@RequestMapping(value = "/userWrong")
@RestController
public class UserWrongController {

    @Resource
    private UserWrongService userWrongService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model UserWrongModel
     * @return 添加的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody UserWrongModel model) {
        if (StringUtils.isEmpty(model.getUserId())) {
            return ItooResult.build(ItooResult.FAIL, "userId为空");
        }
        if (StringUtils.isEmpty(model.getType())) {
            return ItooResult.build(ItooResult.FAIL, "type为空");
        }
        UserWrongEntity userWrongEntity = new UserWrongEntity();
        BeanUtils.copyProperties(model, userWrongEntity);
        userWrongService.save(userWrongEntity);
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
        userWrongService.removeById(id);
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
        userWrongService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model UserWrongModel
     * @return 修改后的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id修改userWrong")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody UserWrongModel model) {
        if (StringUtils.isEmpty(model.getUserId())) {
            return ItooResult.build(ItooResult.FAIL, "userId为空");
        }
        if (StringUtils.isEmpty(model.getPhoneficId())) {
            return ItooResult.build(ItooResult.FAIL, "phoneficId为空");
        }
        if (StringUtils.isEmpty(model.getWordId())) {
            return ItooResult.build(ItooResult.FAIL, "wordId为空");
        }
        if (StringUtils.isEmpty(model.getType())) {
            return ItooResult.build(ItooResult.FAIL, "type为空");
        }
        UserWrongEntity userWrongEntity = new UserWrongEntity();
        BeanUtils.copyProperties(model, userWrongEntity);
        userWrongService.updateById(userWrongEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找UserWrong
     *
     * @param id 主键id
     * @return 根据id查找的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        UserWrongEntity userWrongEntity = userWrongService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", userWrongEntity);
    }

    /**
     * 分页查询所有UserWrong
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "分页查询所有UserWrong")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo", value = "页码", required = true, example = "1") @PathVariable Integer pageNo,
                                   @ApiParam(name = "pageSize", value = "页数", required = true, example = "10") @PathVariable Integer pageSize) {
        PageInfo<UserWrongEntity> userWrongs = userWrongService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", userWrongs);
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 根据用户id查询错误单词
     *
     * @param userId 用户ID
     * @return 该用户的错误单词
     * @author 陈广晗
     * @since 2019-06-11 16:30:24
     */
    @ApiOperation(value = "根据用户id查询错误单词")
    @GetMapping(value = "/queryWrongWordId/{userId}")
    public ItooResult queryWrongWordId(@PathVariable String userId) {
        List<UserWrongModel> userWrongs = userWrongService.queryWrongWordId(userId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", userWrongs);
    }


    /**
     * 根据用户id查询错误音标
     *
     * @return 该用户的错误音标
     * @author 陈广晗
     * @since 2019-06-11 16:30:24
     */
    @ApiOperation(value = "根据用户id查询错误音标")
    @GetMapping(value = "/queryWrongPhoneficId/{userId}")
    public ItooResult queryWrongPhoneficId(@PathVariable String userId) {
        List<UserWrongModel> userWrongs = userWrongService.queryWrongPhoneficId(userId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", userWrongs);
    }
}    
