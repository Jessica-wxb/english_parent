package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.UserSetEntity;
import com.tfjybj.english.model.UserSetModel;
import com.tfjybj.english.provider.service.UserSetService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * UserSetController
 * userSet表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Api(tags = {"用户设置接口"})
@RequestMapping(value = "/userSet")
@RestController
public class UserSetController {

    @Resource
    private UserSetService userSetService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model UserSetModel
     * @return 添加的结果
     * @author 张凯超
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody UserSetModel model) {
        if (StringUtils.isEmpty(model.getUserId())) {
            return ItooResult.build(ItooResult.FAIL, "userId为空");
        }
        if (StringUtils.isEmpty(model.getPlayNums())) {
            return ItooResult.build(ItooResult.FAIL, "playNums为空");
        }
        if (StringUtils.isEmpty(model.getIsTurnAuto())) {
            return ItooResult.build(ItooResult.FAIL, "isTurnAuto为空");
        }
        if (StringUtils.isEmpty(model.getTurnDelayTime())) {
            return ItooResult.build(ItooResult.FAIL, "turnDelayTime为空");
        }
        if (StringUtils.isEmpty(model.getStudyNumber())) {
            return ItooResult.build(ItooResult.FAIL, "studyNumber为空");
        }
        UserSetEntity userSetEntity = new UserSetEntity();
        BeanUtils.copyProperties(model, userSetEntity);
        userSetService.save(userSetEntity);
        return ItooResult.build(ItooResult.SUCCESS, "添加成功");
    }

    /**
     * 删除
     *
     * @param id 主键id
     * @return ItooResult 是否删除成功
     * @author 张凯超
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "根据id删除（单个）")
    @DeleteMapping(value = {"/delete/{id}"})
    public ItooResult delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        userSetService.removeById(id);
        return ItooResult.build(ItooResult.SUCCESS, "删除成功");
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return ItooResult 批量删除是否成功结果
     * @author 张凯超
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "根据id批量删除")
    @DeleteMapping(value = {"/deleteByIds"})
    @ApiImplicitParam(name = "ids", value = "ids", dataType = "List<String>", required = true)
    public ItooResult deleteByIds(@RequestBody List<String> ids) {
        userSetService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model UserSetModel
     * @return 修改后的结果
     * @author 张凯超
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "根据id修改userSet")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody UserSetModel model) {
        if (StringUtils.isEmpty(model.getUserId())) {
            return ItooResult.build(ItooResult.FAIL, "userId为空");
        }
        if (StringUtils.isEmpty(model.getPlayNums())) {
            return ItooResult.build(ItooResult.FAIL, "playNums为空");
        }
        if (StringUtils.isEmpty(model.getIsTurnAuto())) {
            return ItooResult.build(ItooResult.FAIL, "isTurnAuto为空");
        }
        if (StringUtils.isEmpty(model.getTurnDelayTime())) {
            return ItooResult.build(ItooResult.FAIL, "turnDelayTime为空");
        }
        if (StringUtils.isEmpty(model.getStudyNumber())) {
            return ItooResult.build(ItooResult.FAIL, "studyNumber为空");
        }
        UserSetEntity userSetEntity = new UserSetEntity();
        BeanUtils.copyProperties(model, userSetEntity);
        userSetService.updateById(userSetEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找UserSet
     *
     * @param id 主键id
     * @return 根据id查找的结果
     * @author 张凯超
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        UserSetEntity userSetEntity = userSetService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", userSetEntity);
    }

    /**
     * 分页查询所有UserSet
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 张凯超
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "分页查询所有UserSet")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo", value = "页码", required = true, example = "1") @PathVariable Integer pageNo,
                                   @ApiParam(name = "pageSize", value = "页数", required = true, example = "10") @PathVariable Integer pageSize) {
        PageInfo<UserSetEntity> userSets = userSetService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", userSets);
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */


    /**
     * 查询用户设置信息
     *
     * @return
     * @author 闫伟强
     */
    @ApiOperation(value = "查询用户设置信息")
    @GetMapping(value = {"/findUserSetById"})
    public ItooResult findUserSetById() {
        UserSetEntity userSets = userSetService.findUserSetById();
        userSets.setUserId(null);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", userSets);
    }

    /**
     * 用户更新设置信息
     *
     * @return
     * @author 闫伟强
     */
    @ApiOperation(value = "用户更新设置信息")
    @PostMapping(value = {"/modifyById"})
    public ItooResult modifyById(@RequestBody UserSetModel userSetModel) {
        Integer palyNums = userSetModel.getPlayNums();
        Integer isTurnAuto = userSetModel.getIsTurnAuto();
        Integer tuenDelayTime = userSetModel.getTurnDelayTime();
        Integer studyNumbe = userSetModel.getStudyNumber();
        userSetService.modifyById(palyNums,isTurnAuto ,tuenDelayTime ,studyNumbe );
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }
}
