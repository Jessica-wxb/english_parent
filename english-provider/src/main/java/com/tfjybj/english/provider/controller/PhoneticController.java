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
            return ItooResult.build(ItooResult.FAIL, "侧边点击音标进行查询失败");
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

    /**
     * 将48个音标插入redis
     * @author 闫伟强
     * @since 2019年10月3日15:58:18
     */
    @ApiOperation(value = "将48个音标插入redis")
    @GetMapping(value = "/PhoneticInsertRedis")
    public ItooResult PhoneticInsertRedis() {
        phoneticService.PhoneticInsertRedis();
        return ItooResult.build(ItooResult.SUCCESS, "插入成功");

    }
}

