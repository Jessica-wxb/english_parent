package com.tfjybj.english.provider.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.google.common.collect.Maps;
import com.tfjybj.english.model.*;
import com.dmsdbj.itoo.tool.business.ItooResult;

import com.tfjybj.english.provider.service.common.UserInfoAndSetService;
import com.tfjybj.english.model.MineModel;
import com.tfjybj.english.model.RankLocalModel;
import com.tfjybj.english.model.RankModel;

import com.tfjybj.english.provider.service.common.RankService;
import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * UserInfoController
 * userInfo表
 * <
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Api(tags = {"userInfo表接口"})
@RequestMapping(value = "/userInfo")
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoAndSetService userInfoAndSetService;

    /**
     * 用户登录
     *
     * @return
     * @author 闫伟强
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "用户登录")
    @PostMapping(value = {"/login"})
    public ItooResult login(@RequestBody UserInfoModel userInfoModel) {
        Map<String, String> userInfo = Maps.newHashMap();
        userInfo.put("userCode", userInfoModel.getUserCode());
        userInfo.put("password", userInfoModel.getUserCode());
        String authentication = userInfoAndSetService.authentication(JSON.toJSONString(userInfo));
        Object parse = JSONObject.parse(authentication);
        JSONObject object = (JSONObject) parse;
        String code = object.getString("code");
        String falsecode=new String("1111");
        if (code.equals(falsecode)) {
            return ItooResult.build("401", "登录失败,请重新登录!");
        }
        String token = object.getJSONObject("data").getString("token");
        UserPartModel userPartModel = userInfoAndSetService.login(userInfoModel.getUserCode());
        return ItooResult.build(ItooResult.SUCCESS, "登录成功!", token);
//        if (userPartModel!=null) {
////            Map<String,String> userInfo = Maps.newHashMap();
////            userInfo.put("userCode",userInfoModel.getUserCode());
////            userInfo.put("password",userInfoModel.getUserCode());
////            String authentication = userInfoAndSetService.authentication(JSON.toJSONString(userInfo));
////            Object parse = JSONObject.parse(authentication);
////            JSONObject object=(JSONObject) parse;
////            String token = object.getJSONObject("data").getString("token");
//            return ItooResult.build(ItooResult.SUCCESS, "登录成功!",token);
//        }
//        return ItooResult.build("401", "登录失败,请重新登录!");
    }

    @Autowired
    private RankService rankService;


    /**
     * 查询E币排行
     *
     * @return E币排行
     * @author 董可
     * @since 2019年8月16日09:40:57
     */
    @ApiOperation(value = "查询E币排行")
    @GetMapping(value = {"/queryUserIdEAllNum"})
    public ItooResult queryUserIdEAllNum() {
        String userId = UserUtil.getCurrentUser().getUserId();
        List<RankLocalModel> RankModels = rankService.localRankByUserId(userId);
//        List<RankModel> rankModel = rankService.getRanking(userId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", RankModels);
    }

    /**
     * @param
     * @return 我的-界面
     * @author 董可
     * @since 2019年8月16日10:09:53
     */
    @ApiOperation(value = "查询-我的")
    @GetMapping(value = {"/findById"})
    public ItooResult queryMineByUserId(@RequestParam String userCode) {

        MineModel mineModel = rankService.Mine(userCode);
        mineModel.setUserId(null);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", mineModel);
    }

    /**
     * @param
     * @return 坚持天数加一
     * @author 董可
     * @since 2019年8月16日10:09:53
     */
    @ApiOperation(value = "坚持天数加一")
    @GetMapping(value = {"/addInsistDays"})
    public ItooResult addInsistDays(String userCode) {
        rankService.addInsistDays(userCode);
        return ItooResult.build(ItooResult.SUCCESS, "添加成功");
    }

    /**
     * @param
     * @return E币排行定位--这个方法不用了
     * @author 董可
     * @since 2019年8月16日10:09:53
     */
    @ApiOperation(value = "E币排行定位")
    @GetMapping(value = {"/UserRank/findLocal"})
    public ItooResult findLocal() {
//        String userId = "2";
        String userId = UserUtil.getCurrentUser().getUserId();
        List<RankLocalModel> RankModels = rankService.localRankByUserId(userId);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", RankModels);
    }

    /**
     * @param
     * @return 从redis的Rank中查询出【我的】用户
     * @author 王小波
     * @since 2019年9月8日21:17:38头像右侧的E币数
     */
    @ApiOperation(value = "从redis的Rank中查询出【我的】用户头像右侧的E币数")
    @GetMapping(value = {"/UserRank"})
    public ItooResult findUserENum() {
//        String userId = "2";
        String userId1 = UserUtil.getCurrentUser().getUserId();
        RankLocalModel RankModels = rankService.findRankByUserId(userId1);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", RankModels);
    }


}
