package com.tfjybj.english.provider.controller;

import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.english.model.CheckWord;
import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.provider.service.common.WordDetectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Api(tags = {"单词检测"})
@RequestMapping(value = "/wordcheck")
@RestController
public class WordCheckController {
    @Resource
    private WordDetectionService wordDetectionService;


    /**
     * 单词检测初始化
     * @return 单词信息
     */
    @ApiOperation(value="单词检测初始化")
    @GetMapping(value={"/initialization"})
    public ItooResult JoinWordDetection(){
        String userId=UserUtil.getCurrentUser().getUserId();
        WordModel wordModel = wordDetectionService.JoinWordDetection(userId);
        if (wordModel.getId()==null){
            return ItooResult.build("2222", "记录为空",wordModel);
        }
        return ItooResult.build(ItooResult.SUCCESS, "查询成功",wordModel);
    }

    /**
     * 修改单词状态,返回下一条数据
     * @param model 单词检测信息
     * @return 单词信息
     */
    @ApiOperation(value = "修改单词状态,返回下一条数据")
    @PutMapping ( value = {"/modify"})
    public ItooResult modify(@RequestBody CheckWord model,String userCode) {
        if (StringUtils.isEmpty(model.getId())){
            return ItooResult.build(ItooResult.FAIL, "单词Id为空");
        }
        if (StringUtils.isEmpty(model.getStatus())){
            return ItooResult.build(ItooResult.FAIL, "单词检测状态为空");
        }
        String userId=UserUtil.getCurrentUser().getUserId();
        WordModel wordModel=wordDetectionService.DetectNext(model,userId,userCode);
        if (wordModel.getId()==null){
            return ItooResult.build("2222", "记录为空",wordModel);
        }
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordModel);
    }

    /**
     * 导入单词检测所需信息
     * @return 单词信息
     */
    @ApiOperation(value="将单词检测的数据导入")
    @GetMapping(value={"/toWordTemplate"})
    public ItooResult toWordTemplate(String english){

        if (english.equals("english")){
            wordDetectionService.toWordTemplate();
            return ItooResult.build(ItooResult.SUCCESS, "操作成功");
        }
        return ItooResult.build(ItooResult.SUCCESS, "操作失败");
    }
}
