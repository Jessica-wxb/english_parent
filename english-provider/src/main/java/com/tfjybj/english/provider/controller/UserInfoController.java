package com.tfjybj.english.provider.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.google.common.collect.Maps;
import com.tfjybj.english.Enum.PetListEnumUntil;
import com.tfjybj.english.entity.EExpensedRecordEntity;
import com.tfjybj.english.model.*;
import com.dmsdbj.itoo.tool.business.ItooResult;

import com.tfjybj.english.provider.service.EExpensedRecordService;
import com.tfjybj.english.provider.service.common.EStoreUpdateENowNumService;
import com.tfjybj.english.provider.service.common.UsePetService;
import com.tfjybj.english.provider.service.common.UserInfoAndSetService;
//import com.tfjybj.english.provider.service.common.InsertExpensedRecordService;
import com.tfjybj.english.model.MineModel;
import com.tfjybj.english.model.RankLocalModel;

import com.tfjybj.english.provider.service.common.RankService;
import com.tfjybj.english.provider.service.UserInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private RankService rankService;

    @Autowired
    private UserInfoAndSetService userInfoAndSetService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UsePetService usePetService;

    @Autowired
    private EStoreUpdateENowNumService eStoreUpdateENowNumService;

    @Resource
    EExpensedRecordService eExpensedRecordService;



    /**
     * 用户登录
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


    /**
     * 查询E币排行
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
        String userId = UserUtil.getCurrentUser().getUserId();

        MineModel mineModel = rankService.Mine(userCode);
        mineModel.setUserId(null);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", mineModel);
    }

    /**
     * @param
     * @return 我的-界面
     * @author 董可
     * @since 2019年8月16日10:09:53
     */
    @ApiOperation(value = "查询-[我的]界面-所有单词数量")
    @GetMapping(value = {"/findWordAllNum"})
    public  ItooResult queryAllWordsNum(){
        int allWordsNum = rankService.AllWordsNum();
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",allWordsNum);
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
        String userId1 = UserUtil.getCurrentUser().getUserId();
        RankLocalModel RankModels = rankService.findRankByUserId(userId1);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", RankModels);
    }

    /**
     * E币商城页面初始化的时候判断宠物是否带锁
     * 1、根据userId获取用户的宠物列表pet_list.
     * 2、将pet_list与PetListEnumUtil遍历对比。
     * 3、将petName相同的petUrl传到前端
     *
     * @return 用户所拥有宠物前端文件地址（pet_list）
     * @Param userId
     * @author
     * @since 2019年9月18日
     */

    @ApiOperation(value = "E币商城页面初始化的时候判断宠物是否带锁")
    @GetMapping(value = "/queryPetListByUserId")
    public ItooResult queryPetListByUserId() {
        String userId = UserUtil.getCurrentUser().getUserId();
        UserPetListModel userPetListModel = userInfoService.queryPetListByUserId(userId);
        // 截取；号前的数据
        List<String> list = Arrays.asList(userPetListModel.getPetList().split(";"));
        // 过滤掉当前使用的宠物
        list= list.stream().filter(s -> !s.equals(userPetListModel.getUsePet()) ).collect(Collectors.toList());
        // 把当前使用的宠物放到list首位
        list.add(0, userPetListModel.getUsePet());
        userPetListModel.setPetNames(list);
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",userPetListModel);
    }

    /**
     * @param
     * @return 查询当前使用宠物
     * @author
     * @since 2019年9月19日15:13:33
     */
    @ApiOperation(value = "查询当前正在使用的宠物")
    @GetMapping(value = "/queryUsePetByUserId")
    public ItooResult queryUsePetByUserId(){
        // 获取当前正在使用的宠物usePet
        String userPetJson = usePetService.queryUsePetByUserId();
        if(userPetJson == null && userPetJson== ""){
            return ItooResult.build(ItooResult.FAIL,"对不起，您当前没有宠物！");
        }
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",userPetJson);
    }


    /**
     * @param
     * @return 购买宠物
     * @author
     * @since 2019年9月19日15:13:33
     *
     *  业务逻辑：
     *          购买宠物->成功之后：
     *             0.往E币消费记录表tn_e_expensed_record中插入一条消费记录
     *             1.更新redis中ENGLISH:USEPET:userId中的当前宠物替换成刚刚购买的宠物
     *             2.更新数据库中的usePet和petList
     *             3.用户在E币商城消费后，更新用户当前可用的ENowNum
     *             购买宠物->失败之后：提示插入失败
     *
     */
    @ApiOperation(value = "购买宠物")
    @GetMapping(value = "/buyPet/{userCode}/{expensedENum}/{description}/{usePet}")
    public ItooResult buyPet( @RequestParam String userCode,
                              @ApiParam(value = "消费的E币数", required = true) String expensedENum,
                              @ApiParam(value = "描述：购买宠物", required = true) String description,
                              @ApiParam(value = "用户购买的宠物", required = true) String usePet) {
        boolean buyPet = false;
        if (!userCode.equals("") && !expensedENum.equals("") && !description.equals("") && !usePet.equals("")) {

            buyPet = userInfoService.buyPet(userCode, usePet, description, expensedENum);

        }
        if (buyPet) {
            return ItooResult.build(ItooResult.SUCCESS, "宠物购买成功");

        } else {
            return ItooResult.build(ItooResult.FAIL, "购买宠物失败！");
        }
    }


    /**
     * @param
     * @return  更换宠物形象
     * @author
     * @since 2019年9月19日15:13:33
     */
    @ApiOperation(value = "更换宠物形象")
    @GetMapping(value = "/changeUsePet/{userCode}/{usePet}")
    public ItooResult changeUsePet(@RequestParam String userCode,
                                   @ApiParam(value = "用户更换的宠物", required = true) String usePet) {
        // 获取userId
        String userId = UserUtil.getCurrentUser().getUserId();
        boolean changeUsePet = false;
        if (!userCode.equals("") && !usePet.equals("")) {
            changeUsePet = userInfoService.changeUsePet(userId, usePet);

        }
        if (changeUsePet) {
            return ItooResult.build(ItooResult.SUCCESS, "更换宠物成功", changeUsePet);

        } else {
            return ItooResult.build(ItooResult.FAIL, "更换宠物失败");
        }


    }

    /**
     * @param
     * @return 兑换积分
     * @author
     * @since 2019年9月19日15:13:33
     */
    @ApiOperation(value = "兑换积分")
    @GetMapping(value = "/changeIntegral/{userCode}/{expensedENum}/{description}")
    public ItooResult changeIntegral( @RequestParam String userCode,
                                      @ApiParam(value = "消费的E币数", required = true) String expensedENum,
                                      @ApiParam(value = "描述：兑换积分", required = true) String description) {
        boolean changeIntegral = false;
        if (!userCode.equals("") && !expensedENum.equals("") && !description.equals("")) {
            changeIntegral = userInfoService.changeIntegral(userCode, description, expensedENum);
        }

        if (changeIntegral) {
            return ItooResult.build(ItooResult.SUCCESS, "积分兑换成功");

        } else {
            return ItooResult.build(ItooResult.FAIL, "购买宠物失败！");
        }

    }


}
