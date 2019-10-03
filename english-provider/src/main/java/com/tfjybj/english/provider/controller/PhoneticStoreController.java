package com.tfjybj.english.provider.controller;

import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.english.model.PhoneticStoreModel;
import com.tfjybj.english.model.PhoneticWrongModel;
import com.tfjybj.english.provider.service.PhoneticCheckService;
import com.tfjybj.english.provider.service.PhoneticWrongService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"音标归仓接口"})
@RequestMapping(value = "/phoneticstore")
@RestController
public class PhoneticStoreController {

    @Autowired
    PhoneticCheckService phoneticCheckService;
    @Autowired
    PhoneticWrongService phoneticWrongService;

    @ApiOperation(value = "进入音标检测")
    @GetMapping(value = {"/initialization"})
    public ItooResult initialization() {
        String userId = UserUtil.getCurrentUser().getUserId();
        PhoneticStoreModel phoneticStoreModel = phoneticWrongService.Initialisation(userId);
        if (phoneticStoreModel == null) {
            return ItooResult.build("2222", "记录为空", phoneticStoreModel);
        }
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneticStoreModel);

    }

    @ApiOperation(value = "修改检测数据")
    @PutMapping(value = {"/modify"})
    public ItooResult modify(@RequestBody PhoneticWrongModel phoneticWrongModel) {
        String userId = UserUtil.getCurrentUser().getUserId();
        phoneticWrongModel.setUserId(userId);
        PhoneticStoreModel phoneticStoreModel = phoneticWrongService.Modity(phoneticWrongModel);
        if (phoneticStoreModel == null) {
            return ItooResult.build("2222", "记录为空", phoneticStoreModel);
        }
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", phoneticStoreModel);

    }
}
