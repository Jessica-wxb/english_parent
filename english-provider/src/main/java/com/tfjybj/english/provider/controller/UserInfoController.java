package com.tfjybj.english.provider.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.google.common.collect.Maps;
import com.tfjybj.english.Enum.PetListEnumUntil;
import com.tfjybj.english.model.*;
import com.dmsdbj.itoo.tool.business.ItooResult;

import com.tfjybj.english.provider.service.common.UsePetService;
import com.tfjybj.english.provider.service.common.UserInfoAndSetService;
import com.tfjybj.english.model.MineModel;
import com.tfjybj.english.model.RankLocalModel;
import com.tfjybj.english.model.RankModel;

import com.tfjybj.english.provider.service.common.RankService;
import com.tfjybj.english.provider.service.UserInfoService;
import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 * UserInfoController
 * userInfo表
<
 *
 * @author 张凯超
 * @version 1.0.0
 * @since  2019-08-16 08:47:57
 */
@Api(tags = {"userInfo表接口"})
@RequestMapping(value = "/userInfo")
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoAndSetService userInfoAndSetService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UsePetService usePetService;

    /**
     * 用户登录
     * @return
     * @author 闫伟强
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "用户登录")
    @PostMapping(value = {"/login"})
    public ItooResult login(@RequestBody UserInfoModel userInfoModel) {

        UserPartModel userPartModel= userInfoAndSetService.login(userInfoModel.getUserCode());

        if (userPartModel!=null) {
            Map<String,String> userInfo = Maps.newHashMap();
            userInfo.put("userCode",userInfoModel.getUserCode());
            userInfo.put("password",userInfoModel.getUserCode());
            String authentication = userInfoAndSetService.authentication(JSON.toJSONString(userInfo));
            Object parse = JSONObject.parse(authentication);
            JSONObject object=(JSONObject) parse;
            String token = object.getJSONObject("data").getString("token");
            return ItooResult.build(ItooResult.SUCCESS, "登录成功!",token);
        }
        return ItooResult.build("401", "登录失败,请重新登录!");
    }

    @Autowired
    private RankService rankService;


    /**
     * 查询E币排行
     * @return E币排行
     * @author 董可
     * @since 2019年8月16日09:40:57
     */
    @ApiOperation(value = "查询E币排行")
    @GetMapping(value = {"/queryUserIdEAllNum"})
    public ItooResult queryUserIdEAllNum(){
        String userId = UserUtil.getCurrentUser().getUserId();
        List<RankLocalModel> RankModels = rankService.localRankByUserId(userId);
//        List<RankModel> rankModel = rankService.getRanking(userId);
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",RankModels);
    }

    /**
     * @param
     * @return 我的-界面
     * @author 董可
     * @since 2019年8月16日10:09:53
     */
    @ApiOperation(value = "查询-我的")
    @GetMapping(value = {"/findById"})
    public  ItooResult queryMineByUserId(@RequestParam String userCode){

        MineModel mineModel = rankService.Mine(userCode);
        mineModel.setUserId(null);
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",mineModel);
    }

    /**
     * @param
     * @return 坚持天数加一
     * @author 董可
     * @since 2019年8月16日10:09:53
     */
    @ApiOperation(value = "坚持天数加一")
    @GetMapping(value = {"/addInsistDays"})
    public  ItooResult addInsistDays(String userCode){
        rankService.addInsistDays(userCode);
        return ItooResult.build(ItooResult.SUCCESS,"添加成功");
    }

    /**
     * @param
     * @return E币排行定位--这个方法不用了
     * @author 董可
     * @since 2019年8月16日10:09:53
     */
    @ApiOperation(value = "E币排行定位")
    @GetMapping(value = {"/UserRank/findLocal"})
    public  ItooResult findLocal(){
//        String userId = "2";
        String userId = UserUtil.getCurrentUser().getUserId();
        List<RankLocalModel> RankModels = rankService.localRankByUserId(userId);
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",RankModels);
    }

    /**
     * @param
     * @return 从redis的Rank中查询出【我的】用户头像右侧的E币数
     * @author 王小波
     * @since 2019年9月8日21:17:38
     */
    @ApiOperation(value = "从redis的Rank中查询出【我的】用户头像右侧的E币数")
    @GetMapping(value = {"/UserRank"})
    public  ItooResult findUserENum(){
//        String userId = "2";
        String userId1 = UserUtil.getCurrentUser().getUserId();
        RankLocalModel RankModels = rankService.findRankByUserId(userId1);
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",RankModels);
    }

    /**
     *     E币商城页面初始化的时候判断宠物是否带锁
     *         1、根据userId获取用户的宠物列表pet_list.
     *         2、将pet_list与PetListEnumUtil遍历对比。
     *         3、将petName相同的petUrl传到前端
     * @Param userId
     * @return 用户所拥有宠物前端文件地址（pet_list）
     * @author
     * @since 2019年9月18日
     */

    @ApiOperation(value = "E币商城页面初始化的时候判断宠物是否带锁")
    @GetMapping(value = "/queryPetListByUserId")
    public ItooResult queryPetListByUserId(){
        String userId = UserUtil.getCurrentUser().getUserId();
        UserPetListModel userPetListModel = userInfoService.qureyPetListByUserId(userId);
        // 截取；号前的数据
        String [] urls = userPetListModel.getPetUrl().split(";");
        List<String> list = new ArrayList<>();
        // 将截取的数据于枚举对比获得宠物地址
        for (String url:urls){
            if (PetListEnumUntil.PET_DOG.getPetName().equals(url)){
                list.add(PetListEnumUntil.PET_DOG.getPetUrl());
            }
            if (PetListEnumUntil.PET_CAT.getPetName().equals(url)){
                list.add(PetListEnumUntil.PET_CAT.getPetUrl());
            }
            if (PetListEnumUntil.PET_RABBIT.getPetName().equals(url)){
                list.add(PetListEnumUntil.PET_RABBIT.getPetUrl());
            }
            if (PetListEnumUntil.PET_SUPER_RABBIT.getPetName().equals(url)){
                list.add(PetListEnumUntil.PET_RABBIT.getPetUrl());
            }
        }
        // 将对比的宠物地址设置
        userPetListModel.setPetUrls(list);
        if(userPetListModel == null){
            return ItooResult.build(ItooResult.FAIL,"查询失败",userPetListModel);
        }
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
        UsePetModel usePetModelNew = usePetService.queryUsePetByUserId();
        // 获取当前正在使用的宠物usePet
        String urls = usePetModelNew.getUsePet();
//        String usePetUrl;
        List<String> list = new ArrayList<>();
        // 将查询到的用户当前正在使用的宠物与枚举对比获取当前宠物地址
        if(PetListEnumUntil.PET_DOG.getPetName().equals(urls)){
            String usePetUrl = PetListEnumUntil.PET_DOG.getPetUrl();
            return ItooResult.build(ItooResult.SUCCESS,"查询成功",usePetUrl);

        }
        else if(PetListEnumUntil.PET_CAT.getPetName().equals(urls)){
            String usePetUrl = PetListEnumUntil.PET_CAT.getPetUrl();
            return ItooResult.build(ItooResult.SUCCESS,"查询成功",usePetUrl);

        }
        else if(PetListEnumUntil.PET_RABBIT.getPetName().equals(urls)){
            String usePetUrl = PetListEnumUntil.PET_RABBIT.getPetUrl();
            return ItooResult.build(ItooResult.SUCCESS,"查询成功",usePetUrl);

        }
        else if(PetListEnumUntil.PET_SUPER_RABBIT.getPetName().equals(urls)){
            String usePetUrl = PetListEnumUntil.PET_SUPER_RABBIT.getPetUrl();
            return ItooResult.build(ItooResult.SUCCESS,"查询成功",usePetUrl);
        }

        // 如何将查询出的当前宠物路径返回给前端？？？？？
        if(usePetModelNew == null){
            return ItooResult.build(ItooResult.FAIL,"查询失败",usePetModelNew);
        }
        return ItooResult.build(ItooResult.SUCCESS,"查询成功",usePetModelNew);
    }







}
