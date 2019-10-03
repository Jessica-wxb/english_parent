package com.tfjybj.english.provider.controller;

import com.tfjybj.english.entity.PhoneticEntity;
import com.tfjybj.english.model.PhoneticModel;
import com.tfjybj.english.model.WordAndTypeModel;
import com.tfjybj.english.provider.service.PhoneticService;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * PhoneticController
 * phonetic表
 *
 * @author 白靖
 * @version ${version}
 * @since ${version} 2019-09-05 17:36:14
 */
@Api(tags = {"phonetic表接口"})
@RequestMapping(value = "/phonetic")
@RestController
public class PhoneticController {

    @Resource
    private PhoneticService phoneticService;

    //region 模板生成：基本增删改

    /**
     * 添加
     *
     * @param model PhoneticModel
     * @return 添加的结果
     * @author 白靖
     * @since ${version} 2019-09-05 17:36:14
     */
    @ApiOperation(value = "添加")
    @PostMapping(value = {"/create"})
    public ItooResult create(@RequestBody PhoneticModel model) {
        PhoneticEntity phoneticEntity = new PhoneticEntity();
        BeanUtils.copyProperties(model, phoneticEntity);
        phoneticService.save(phoneticEntity);
        return ItooResult.build(ItooResult.SUCCESS, "添加成功");
    }

    /**
     * 删除
     *
     * @param id 主键id
     * @return ItooResult 是否删除成功          
     * @author 白靖
     * @since ${version} 2019-09-05 17:36:14
     */
    @ApiOperation(value = "根据id删除（单个）")
    @DeleteMapping(value = {"/delete/{id}"})
    public ItooResult delete(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        phoneticService.removeById(id);
        return ItooResult.build(ItooResult.SUCCESS, "删除成功");
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return ItooResult 批量删除是否成功结果
     * @author 白靖
     * @since ${version} 2019-09-05 17:36:14
     */
    @ApiOperation(value = "根据id批量删除")
    @DeleteMapping(value = {"/deleteByIds"})
    @ApiImplicitParam(name = "ids", value = "ids", dataType = "List<String>", required = true)
    public ItooResult deleteByIds(@RequestBody List<String> ids) {
        phoneticService.removeByIds(ids);
        return ItooResult.build(ItooResult.SUCCESS, "批量删除成功");
    }

    /**
     * 修改
     *
     * @param model PhoneticModel
     * @return 修改后的结果
     * @author 白靖
     * @since ${version} 2019-09-05 17:36:14
     */
    @ApiOperation(value = "根据id修改phonetic")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody PhoneticModel model) {
        PhoneticEntity phoneticEntity = new PhoneticEntity();
        BeanUtils.copyProperties(model, phoneticEntity);
        phoneticService.updateById(phoneticEntity);
        return ItooResult.build(ItooResult.SUCCESS, "修改成功");
    }

    /**
     * 根据id查找Phonetic
     *
     * @param id 主键id
     * @return 根据id查找的结果
     * @author 白靖
     * @since ${version} 2019-09-05 17:36:14
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = {"/findById/{id}"})
    public ItooResult findById(@ApiParam(value = "主键id", required = true) @PathVariable String id) {
        PhoneticEntity phoneticEntity = phoneticService.getById(id);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneticEntity);
    }

    /**
     * 分页查询所有Phonetic
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 白靖
     * @since ${version} 2019-09-05 17:36:14
     */
    @ApiOperation(value = "分页查询所有Phonetic")
    @GetMapping(value = {"/queryPageAll/{pageNo}/{pageSize}"})
    public ItooResult queryPageAll(@ApiParam(name = "pageNo",value = "页码",required = true,example = "1")@PathVariable Integer pageNo, 
								   @ApiParam(name = "pageSize",value = "页数",required = true,example = "10")@PathVariable Integer pageSize) {
        PageInfo<PhoneticEntity> phonetics = phoneticService.queryPageAll(pageNo, pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phonetics);
    }
	
    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 查询要学习的音标
     * @return 音标实体
     * @author 白靖
     * @since ${version} 2019年9月5日19:47:57
     */
    @ApiOperation(value = "查询要学习的音标")
    @GetMapping(value = {"/PhoneticToDo"})
    public ItooResult PhoneticToDo() {
        try{
            PhoneticEntity PhoneticToDo = phoneticService.PhoneticToDo();
            return ItooResult.build(ItooResult.SUCCESS, "查询成功", PhoneticToDo);
        }
        catch (Exception e){
            return ItooResult.build(ItooResult.FAIL, "查询要学习的音标失败");
        }
    }

    /**
     * 左滑下一个音标
     * @return 音标实体
     * @author 白靖
     * @since ${version} 2019年9月5日19:47:57
     */
    @ApiOperation(value = "左滑下一个音标")
    @GetMapping(value = {"/NextPhonetic"})
    public ItooResult NextPhonetic(String phonetic) {
        try{
            PhoneticEntity NextPhonetic = phoneticService.NextPhonetic(phonetic);
            if (NextPhonetic==null){
                return ItooResult.build("2222", "数据为空");
            }
            return ItooResult.build(ItooResult.SUCCESS, "查询成功", NextPhonetic);
        }
        catch (Exception e){
            return ItooResult.build(ItooResult.FAIL, "左滑下一个音标失败");
        }
    }
    /**
     * 侧边点击音标进行查询
     * @return 音标实体
     * @author 白靖
     * @since ${version} 2019年9月5日19:47:57
     */
    @ApiOperation(value = "侧边点击音标进行查询")
    @GetMapping(value = {"/queryPhonetic"})
    public ItooResult queryPhonetic(String phonetic) {
        try{
            PhoneticEntity queryPhonetic = phoneticService.queryPhonetic(phonetic);
            return ItooResult.build(ItooResult.SUCCESS, "查询成功", queryPhonetic);
        }
        catch (Exception e){
            return ItooResult.build(ItooResult.FAIL, "侧边点击音标进行查询");
        }
    }

    /**
     * 根据目录结构插入音标
     *
     * @param phonePath 音标所在路径
     * @return true/false
     * @author 陈广晗
     * @since 2019-09-07 08:08:15
     */
    @ApiOperation(value = "根据目录结构插入音标")
    @GetMapping(value = "/phonePathInstert")
    public ItooResult phonePathInstert(@RequestParam String phonePath) {
        try {
            boolean flag = phoneticService.phonePathInstert(phonePath);
            return ItooResult.build(ItooResult.SUCCESS, "上传成功!", flag);

        } catch (Exception e) {
//            log.error("错误" + e);
            return ItooResult.build(ItooResult.FAIL, "文件插入失败!");
        }
    }
}

