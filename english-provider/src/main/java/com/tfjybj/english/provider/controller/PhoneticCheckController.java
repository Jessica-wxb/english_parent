package com.tfjybj.english.provider.controller;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.english.model.PhoneticCheckModel;
import com.tfjybj.english.provider.service.PhoneticCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"音标检测接口"})
@RequestMapping(value = "/phoneticCheck")
@RestController
public class PhoneticCheckController {

    @Autowired
    PhoneticCheckService phoneticCheckService;

    /**
     * @param
     * @return 进入音标检测
     * @author 闫伟强
     * @since 2019年9月6日08:18:57
     */
    @ApiOperation(value = "进入音标检测")
    @GetMapping(value = {"/findPhoneticCheck"})
    public ItooResult findPhoneticCheck() {
        PhoneticCheckModel phoneticCheckModel = phoneticCheckService.findPhoneticCheck();
        if (phoneticCheckModel == null) {
            return ItooResult.build("2222", "无数据", phoneticCheckModel);
        }
        return ItooResult.build("0000", "查询成功", phoneticCheckModel);
    }

    /**
     * @param phonetic 音标
     * @param correct 对错
     * @param falseType 错误类型
     * @return 点击正确或者错误
     * @author 闫伟强
     * @since 2019年9月6日08:18:57
     */
    @ApiOperation(value = "点击正确或者错误")
    @GetMapping(value = {"/nextPhonetic"})
    public ItooResult nextPhonetic(String phonetic, Integer correct, Integer falseType) {
        PhoneticCheckModel phoneticCheckModel = phoneticCheckService.nextPhonetic(phonetic,correct,falseType);
        if (phoneticCheckModel==null){
            return ItooResult.build("2222", "无数据");
        }
        return ItooResult.build("0000", "查询成功", phoneticCheckModel);
    }
}
