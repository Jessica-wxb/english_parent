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
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Api(tags = {"userSet表接口"})
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
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody UserSetModel model) {
		if (StringUtils.isEmpty(model.getUserId())){
            return ItooResult.build(ItooResult.FAIL, "userId为空");
        }
		if (StringUtils.isEmpty(model.getPhoneficNumber())){
            return ItooResult.build(ItooResult.FAIL, "phoneficNumber为空");
        }
		if (StringUtils.isEmpty(model.getWordNumber())){
            return ItooResult.build(ItooResult.FAIL, "wordNumber为空");
        }
		if (StringUtils.isEmpty(model.getIsTurnAuto())){
            return ItooResult.build(ItooResult.FAIL, "isTurnAuto为空");
        }
		if (StringUtils.isEmpty(model.getTurnDelayTime())){
            return ItooResult.build(ItooResult.FAIL, "turnDelayTime为空");
        }
		if (StringUtils.isEmpty(model.getStudyNumber())){
            return ItooResult.build(ItooResult.FAIL, "studyNumber为空");
        }
		if (StringUtils.isEmpty(model.getIsRandom())){
            return ItooResult.build(ItooResult.FAIL, "isRandom为空");
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
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
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
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
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
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "根据id修改userSet")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody UserSetModel model) {
//		if (StringUtils.isEmpty(model.getUserId())){
//            return ItooResult.build(ItooResult.FAIL, "userId为空");
//        }
//		if (StringUtils.isEmpty(model.getPhoneficNumber())){
//            return ItooResult.build(ItooResult.FAIL, "phoneficNumber为空");
//        }
//		if (StringUtils.isEmpty(model.getWordNumber())){
//            return ItooResult.build(ItooResult.FAIL, "wordNumber为空");
//        }
//		if (StringUtils.isEmpty(model.getIsTurnAuto())){
//            return ItooResult.build(ItooResult.FAIL, "isTurnAuto为空");
//        }
//		if (StringUtils.isEmpty(model.getTurnDelayTime())){
//            return ItooResult.build(ItooResult.FAIL, "turnDelayTime为空");
//        }
//		if (StringUtils.isEmpty(model.getStudyNumber())){
//            return ItooResult.build(ItooResult.FAIL, "studyNumber为空");
//        }
//		if (StringUtils.isEmpty(model.getIsRandom())){
//            return ItooResult.build(ItooResult.FAIL, "isRandom为空");
//        }
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
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
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
     * @author 马莹
     * @since ${version} 2019-06-08 14:26:23
     */
    @ApiOperation(value = "分页查询所有UserSet")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo",value = "页码",required = true,example = "1")@PathVariable Integer pageNo, 
								   @ApiParam(name = "pageSize",value = "页数",required = true,example = "10")@PathVariable Integer pageSize) {
        PageInfo<UserSetEntity> userSets = userSetService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", userSets);
    }
	
    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 修改是否为自动跳转
     *
     * @param model UserSetModel
     * @return 修改后的结果
     * @author 张伟杰
     * @since 2019-6-11 15:34:58
     */
    @ApiOperation(value = "根据id修改是否自动跳转")
    @PutMapping(value = {"/modifyIsTurnAuto"})
    public ItooResult modifyIsTurnAuto(@RequestBody UserSetModel model){
        if (StringUtils.isEmpty(model.getIsTurnAuto())){
            return ItooResult.build(ItooResult.FAIL,"isTurnAuto为空");
        }
        UserSetEntity userSetEntity = new UserSetEntity();
        BeanUtils.copyProperties(model,userSetEntity);
        userSetService.updateById(userSetEntity);
        return ItooResult.build(ItooResult.SUCCESS,"修改成功");
    }

    /**
     * 根据用户id查询用户设置
     *
     * @param userid 用户id
     * @return 返回查询用户设置实体集合
     * @author 白爱民
     * @since 2019-6-11 17:04:31
     */
    @ApiOperation(value = "根据用户id，查询用户设置")
    @GetMapping(value = "/selStudyNumber/{userid}")
    public ItooResult selStudyNumber(@PathVariable Integer userid) {
        List<UserSetModel> StudyNumberList = userSetService.getStudyNumberService(userid);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功！", StudyNumberList);
    }

    /**
     * 根据用户id查询联播次数
     *
     * @param userId 用户id
     * @return 查询结果
     * @author 薛帅行
     * @since 2019年6月10日09:27:14
     */
    @ApiOperation(value = "根据用户id查询联播次数")
    @GetMapping(value = {"/queryTimes/{userId}"})
    public ItooResult queryTimes(@ApiParam(name = "userId",value = "用户id",required = true)@PathVariable String userId) {
        UserSetEntity userSetEntity = userSetService.getByUserId(userId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", userSetEntity);
    }

    /**
     * 根据用户id更新联播次数
     *
     * @param userId 用户id
     * @param phoneficNumber 联播次数
     * @author 薛帅行
     * @since 2019年6月10日18:00:38
     */
    @ApiOperation(value = "根据用户id更新联播次数")
    @PutMapping(value = "/updateTimesById/{userId}/{phoneficNumber}")
    public ItooResult updateTimesById(@ApiParam(name = "userId",value = "用户id",required = true)@PathVariable String userId,
                                      @ApiParam(name = "phoneficNumber",value = "音标次数",required = true)@PathVariable String phoneficNumber){
        UserSetEntity userSetEntity = new UserSetEntity();
        userSetEntity = userSetService.updateTimesById(userId, phoneficNumber);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }
}
