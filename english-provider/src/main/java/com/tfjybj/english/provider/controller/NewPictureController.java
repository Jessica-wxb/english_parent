package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.NewPictureEntity;
import com.tfjybj.english.model.NewPictureModel;
import com.tfjybj.english.model.UserNewpictureModel;
import com.tfjybj.english.provider.service.NewPictureService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * NewPictureController
 * newPicture表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since  2019-08-16 08:47:57
 */
@Api(tags = {"newPicture表接口"})
@RequestMapping(value = "/newPicture")
@RestController
public class NewPictureController {

    @Resource
    private NewPictureService newPictureService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model NewPictureModel
     * @return 添加的结果
     * @author 张凯超
	 * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody NewPictureModel model) {
		if (StringUtils.isEmpty(model.getUserId())){
            return ItooResult.build(ItooResult.FAIL, "userId为空");
        }
        NewPictureEntity newPictureEntity = new NewPictureEntity();
        BeanUtils.copyProperties(model, newPictureEntity);
        newPictureService.save(newPictureEntity);
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
        newPictureService.removeById(id);
        return ItooResult.build(ItooResult.SUCCESS, "删除成功");
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return ItooResult 批量删除是否成功结果
     * @author 张凯超
	 * @version 1.0.0
     * @since  2019-08-16 08:47:57
     */
    @ApiOperation(value = "根据id批量删除")
    @DeleteMapping(value = {"/deleteByIds"})
    @ApiImplicitParam(name = "ids", value = "ids", dataType = "List<String>", required = true)
    public ItooResult deleteByIds(@RequestBody List<String> ids) {
        newPictureService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model NewPictureModel
     * @return 修改后的结果
     * @author 张凯超
	 * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "根据id修改newPicture")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody NewPictureModel model) {
		if (StringUtils.isEmpty(model.getUserId())){
            return ItooResult.build(ItooResult.FAIL, "userId为空");
        }
        NewPictureEntity newPictureEntity = new NewPictureEntity();
        BeanUtils.copyProperties(model, newPictureEntity);
        newPictureService.updateById(newPictureEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找NewPicture
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
        NewPictureEntity newPictureEntity = newPictureService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", newPictureEntity);
    }

    /**
     * 分页查询所有NewPicture
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 张凯超
	 * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "分页查询所有NewPicture")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo",value = "页码",required = true,example = "1")@PathVariable Integer pageNo, 
								   @ApiParam(name = "pageSize",value = "页数",required = true,example = "10")@PathVariable Integer pageSize) {
        PageInfo<NewPictureEntity> newPictures = newPictureService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", newPictures);
    }
	
    //endregion

    /* **********************************以下为非模板生成的内容********************************* */


    /**
     * 将用户新图插入Redis
     *
     * @author 闫伟强
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "将用户新图插入Redis")
    @GetMapping(value = {"/newPictureInsertRedis"})
    public ItooResult newPictureInsertRedis() {
        List<UserNewpictureModel> userNewpictureModel = newPictureService.newPictureInsertRedis();
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", userNewpictureModel);
    }

}    
