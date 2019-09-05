package com.tfjybj.english.provider.controller;

import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.english.entity.UserInfoEntity;
import com.tfjybj.english.model.HomePageNumsModel;
import com.tfjybj.english.provider.service.common.HomePageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"首页接口"})
@RequestMapping(value = "/homePage")
@RestController
public class HomePageController {

    @Autowired
    HomePageService homePageService;

    protected static Logger logger = LoggerFactory.getLogger(HomePageController.class);

    /**
     * 首页初始化--董可
     * @return
     */
    @ApiOperation(value = "首页初始化")
    @GetMapping(value = {"/findByUserId"})
    public ItooResult findById() {
//        String userId = "888";
        String userId =  UserUtil.getCurrentUser().getUserId();
        HomePageNumsModel homePageNumsModel = homePageService.stayWords(userId);
       // homePageNumsModel.setUserId(null);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", homePageNumsModel);
    }


    /**
     * 首页初始化--董可
     * @return
     */
    @ApiOperation(value = "首页初始化")
    @GetMapping(value = {"/findByUserId1"})
    public ItooResult findById1() {
//        String userId = "888";
//        String userId =  UserUtil.getCurrentUser().getUserId();
//        HomePageNumsModel homePageNumsModel = homePageService.stayWords(userId);
        logger.warn("qmx");
        // homePageNumsModel.setUserId(null);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功");
    }
}
