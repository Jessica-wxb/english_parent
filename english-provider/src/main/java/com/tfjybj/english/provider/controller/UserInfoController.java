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
import com.tfjybj.english.provider.service.common.InsertExpensedRecordService;
import com.tfjybj.english.model.MineModel;
import com.tfjybj.english.model.RankLocalModel;

import com.tfjybj.english.provider.service.common.RankService;
import com.tfjybj.english.provider.service.UserInfoService;
import io.swagger.annotations.*;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.util.logging.PlatformLogger;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
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
    private RankService rankService;

    @Autowired
    private UserInfoAndSetService userInfoAndSetService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UsePetService usePetService;

    @Autowired
    private EStoreUpdateENowNumService eStoreUpdateENowNumService;

    @Autowired
    private InsertExpensedRecordService insertExpensedRecordService;
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
        UserPetListModel userPetListModel = userInfoService.queryPetListByUserId(userId);
        // 截取；号前的数据
        String[] names = userPetListModel.getPetList().split(";");
        List<String> list = new ArrayList<>();

        // 将截取的数据于枚举对比获得宠物地址
        for (String name:names){
            if (PetListEnumUntil.PET_DOG.getPetName().equals(name)){
                list.add(PetListEnumUntil.PET_DOG.getPetName());
            }
            if (PetListEnumUntil.PET_CAT.getPetName().equals(name)){
                list.add(PetListEnumUntil.PET_CAT.getPetName());
            }
            if (PetListEnumUntil.PET_RABBIT.getPetName().equals(name)){
                list.add(PetListEnumUntil.PET_RABBIT.getPetName());
            }
            if (PetListEnumUntil.PET_SUPER_RABBIT.getPetName().equals(name)){
                list.add(PetListEnumUntil.PET_SUPER_RABBIT.getPetName());
            }
        }

        // 将对比的宠物地址设置
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
    @GetMapping(value = "/buyPet/userCode/expensedENum/description/usePet")
    public ItooResult buyPet(String userCode,String expensedENum,String description,String usePet){
        // 获取userId
        String userId = UserUtil.getCurrentUser().getUserId();
        boolean userInfoModel=userInfoService.buyPet(userCode,usePet,description,expensedENum);
        return ItooResult.build(ItooResult.SUCCESS,"宠物购买成功");
    }

    /**
     * @param
     * @return  更换宠物形象
     * @author
     * @since 2019年9月19日15:13:33
     */
    @ApiOperation(value = "更换宠物形象")
    @GetMapping(value = "/changeUsePet/userCode/usePet")
    public ItooResult changeUsePet(String userCode,String usePet){
//        String userId = "1071008933394640898";
        // 获取userId
        String userId = UserUtil.getCurrentUser().getUserId();
        boolean userInfoModel = userInfoService.changeUsePet(userId,usePet);

        return ItooResult.build(ItooResult.SUCCESS,"更换宠物成功");
    }

    /**
     * @param
     * @return 兑换积分
     * @author
     * @since 2019年9月19日15:13:33
     */
    @ApiOperation(value = "兑换积分")
    @GetMapping(value = "/changeIntegral/userCode/expensedENum/description")
    public ItooResult changeIntegral(String userCode,String expensedENum,String description){

        // 获取userId
        String userId = UserUtil.getCurrentUser().getUserId();
        boolean userInfoModel=userInfoService.changeIntegral(userCode,description,expensedENum);
        return ItooResult.build(ItooResult.SUCCESS,"积分兑换成功");
    }







}
