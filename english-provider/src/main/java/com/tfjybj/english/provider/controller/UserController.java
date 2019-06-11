package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.UserEntity;
import com.tfjybj.english.model.UserModel;
import com.tfjybj.english.provider.service.UserService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * UserController
 * user表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Api(tags = {"user表接口"})
@RequestMapping(value = "/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model UserModel
     * @return 添加的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody UserModel model) {
		if (StringUtils.isEmpty(model.getUserId())){
            return ItooResult.build(ItooResult.FAIL, "userId为空");
        }
		if (StringUtils.isEmpty(model.getUserCode())){
            return ItooResult.build(ItooResult.FAIL, "userCode为空");
        }
		if (StringUtils.isEmpty(model.getUserName())){
            return ItooResult.build(ItooResult.FAIL, "userName为空");
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(model, userEntity);
        userService.save(userEntity);
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
        userService.removeById(id);
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
        userService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model UserModel
     * @return 修改后的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id修改user")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody UserModel model) {
		if (StringUtils.isEmpty(model.getUserId())){
            return ItooResult.build(ItooResult.FAIL, "userId为空");
        }
		if (StringUtils.isEmpty(model.getUserCode())){
            return ItooResult.build(ItooResult.FAIL, "userCode为空");
        }
		if (StringUtils.isEmpty(model.getUserName())){
            return ItooResult.build(ItooResult.FAIL, "userName为空");
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(model, userEntity);
        userService.updateById(userEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找User
     *
     * @param id 主键id
     * @return 根据id查找的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        UserEntity userEntity = userService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", userEntity);
    }

    /**
     * 分页查询所有User
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "分页查询所有User")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo",value = "页码",required = true,example = "1")@PathVariable Integer pageNo, 
								   @ApiParam(name = "pageSize",value = "页数",required = true,example = "10")@PathVariable Integer pageSize) {
        PageInfo<UserEntity> users = userService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", users);
    }
	
    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

}    
