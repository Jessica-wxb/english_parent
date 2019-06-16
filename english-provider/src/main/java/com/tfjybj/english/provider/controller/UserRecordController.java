package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.UserRecordEntity;
import com.tfjybj.english.model.UserRecordModel;
import com.tfjybj.english.provider.service.UserRecordService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * UserRecordController
 * userRecord表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Api(tags = {"userRecord表接口"})
@RequestMapping(value = "/userRecord")
@RestController
public class UserRecordController {

    @Resource
    private UserRecordService userRecordService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model UserRecordModel
     * @return 添加的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody UserRecordModel model) {
		if (StringUtils.isEmpty(model.getUserId())){
            return ItooResult.build(ItooResult.FAIL, "userId为空");
        }
		if (StringUtils.isEmpty(model.getWordId())){
            return ItooResult.build(ItooResult.FAIL, "wordId为空");
        }
		if (StringUtils.isEmpty(model.getPhoneficId())){
            return ItooResult.build(ItooResult.FAIL, "phoneficId为空");
        }
		if (StringUtils.isEmpty(model.getType())){
            return ItooResult.build(ItooResult.FAIL, "type为空");
        }
		if (StringUtils.isEmpty(model.getStatus())){
            return ItooResult.build(ItooResult.FAIL, "status为空");
        }
        UserRecordEntity userRecordEntity = new UserRecordEntity();
        BeanUtils.copyProperties(model, userRecordEntity);
        userRecordService.save(userRecordEntity);
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
        userRecordService.removeById(id);
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
        userRecordService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model UserRecordModel
     * @return 修改后的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id修改userRecord")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody UserRecordModel model) {
		if (StringUtils.isEmpty(model.getUserId())){
            return ItooResult.build(ItooResult.FAIL, "userId为空");
        }
		if (StringUtils.isEmpty(model.getWordId())){
            return ItooResult.build(ItooResult.FAIL, "wordId为空");
        }
		if (StringUtils.isEmpty(model.getPhoneficId())){
            return ItooResult.build(ItooResult.FAIL, "phoneficId为空");
        }
		if (StringUtils.isEmpty(model.getType())){
            return ItooResult.build(ItooResult.FAIL, "type为空");
        }
		if (StringUtils.isEmpty(model.getStatus())){
            return ItooResult.build(ItooResult.FAIL, "status为空");
        }
        UserRecordEntity userRecordEntity = new UserRecordEntity();
        BeanUtils.copyProperties(model, userRecordEntity);
        userRecordService.updateById(userRecordEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找UserRecord
     *
     * @param id 主键id
     * @return 根据id查找的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        UserRecordEntity userRecordEntity = userRecordService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", userRecordEntity);
    }

    /**
     * 分页查询所有UserRecord
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "分页查询所有UserRecord")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo",value = "页码",required = true,example = "1")@PathVariable Integer pageNo, 
								   @ApiParam(name = "pageSize",value = "页数",required = true,example = "10")@PathVariable Integer pageSize) {
        PageInfo<UserRecordEntity> userRecords = userRecordService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", userRecords);
    }
	
    //endregion

    /* **********************************以下为非模板生成的内容********************************* */
    /**
     * 查询所有Word
     * *@param userid 用户id
     * @return 查询的已经学习单词总数
     * @author 邢美玲
     * @since ${version} 2019年6月9日14:52:28
     */
    @ApiOperation(value = "根据id查询已学单词数")
    @GetMapping(value = {"/findStudyWordById/{userid}"})
    public ItooResult findById(@ApiParam(value = "用户id", required = true) @PathVariable int userid) {
        int studywords;
        studywords = userRecordService.findStudyWordById(userid);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", studywords);
    }

    @ApiOperation(value = "根据用户id查询未检测的单词")
    @GetMapping(value = {"/queryNoDetected/{userId}"})
    public ItooResult queryNoDetectedByUId(@ApiParam(value = "用户id", required = true) @PathVariable String userId) {
        List<UserRecordModel> userRecordModels = userRecordService.queryNoDetectedByUId(userId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", userRecordModels);
    }

    /**
     * 根据用户Id和当天时间,查询音标的ID
     * @param userId 用户ID
     * @return 当天学习过的音标ID(phonefic_id)
     * @since 2019年6月15日11:55:03
     * @autor 冯佳兴
     */
    @ApiOperation(value="根据用户id(user_id)和当天时间,获取当天学习的音标id(phonefic_id)")
    @GetMapping(value={"/selectPhonefic_idByUserIdAndcreatetime/{userId}"})
    public ItooResult selectPhonefic_idByUserIdAndcreatetime(@ApiParam(value = "用户id", required = true) @PathVariable String userId){
        List<UserRecordModel> userRecordModels = userRecordService.selectPhonefic_idByUserIdAndcreatetime(userId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", userRecordModels);
    }
}
