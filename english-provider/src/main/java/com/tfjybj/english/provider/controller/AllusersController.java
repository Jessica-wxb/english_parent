package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.AllusersEntity;
import com.tfjybj.english.model.AllusersModel;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import com.tfjybj.english.provider.service.AllusersService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * AllusersController
 * allusers表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 17:38:43
 */
@Api(tags = {"allusers表接口"})
@Slf4j
@RequestMapping(value = "/allusers")
@RestController
public class AllusersController {

    @Resource
    private AllusersService allusersService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model AllusersModel
     * @return 添加的结果
     * @author 马莹
     * @since ${version} 2019-06-08 17:38:43
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody AllusersModel model) {
        AllusersEntity allusersEntity = new AllusersEntity();
        BeanUtils.copyProperties(model, allusersEntity);
        allusersService.save(allusersEntity);
        return ItooResult.build(ItooResult.SUCCESS, "添加成功");
    }

    /**
     * 删除
     *
     * @param id 主键id
     * @return ItooResult 是否删除成功
     * @author 马莹
     * @since ${version} 2019-06-08 17:38:43
     */
    @ApiOperation(value = "根据id删除（单个）")
    @DeleteMapping(value = {"/delete/{id}"})
    public ItooResult delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        allusersService.removeById(id);
        return ItooResult.build(ItooResult.SUCCESS, "删除成功");
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return ItooResult 批量删除是否成功结果
     * @author 马莹
     * @since ${version} 2019-06-08 17:38:43
     */
    @ApiOperation(value = "根据id批量删除")
    @DeleteMapping(value = {"/deleteByIds"})
    @ApiImplicitParam(name = "ids", value = "ids", dataType = "List<String>", required = true)
    public ItooResult deleteByIds(@RequestBody List<String> ids) {
        allusersService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model AllusersModel
     * @return 修改后的结果
     * @author 马莹
     * @since ${version} 2019-06-08 17:38:43
     */
    @ApiOperation(value = "根据id修改allusers")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody AllusersModel model) {
        AllusersEntity allusersEntity = new AllusersEntity();
        BeanUtils.copyProperties(model, allusersEntity);
        allusersService.updateById(allusersEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找Allusers
     *
     * @param id 主键id
     * @return 根据id查找的结果
     * @author 马莹
     * @since ${version} 2019-06-08 17:38:43
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        AllusersEntity allusersEntity = allusersService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", allusersEntity);
    }

    /**
     * 分页查询所有Allusers
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 马莹
     * @since ${version} 2019-06-08 17:38:43
     */
    @ApiOperation(value = "分页查询所有Allusers")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo", value = "页码", required = true, example = "1") @PathVariable Integer pageNo,
                                   @ApiParam(name = "pageSize", value = "页数", required = true, example = "10") @PathVariable Integer pageSize) {
        PageInfo<AllusersEntity> alluserss = allusersService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", alluserss);
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    @ApiOperation(value = "上传图片到fastdfs")
    @PostMapping(value = "/upLoad")
    public ItooResult upLoad(@RequestParam MultipartFile file) {
        try {
            return ItooResult.build("", allusersService.upLoadPicture(file));
        } catch (Exception e) {
            log.error("" + e);
            return ItooResult.build("", null);
        }
    }


}

