package com.tfjybj.english.provider.controller;

import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.english.model.*;
import com.tfjybj.english.provider.service.common.RedisToDbService;
import com.tfjybj.english.provider.service.common.WordDetectionService;
import com.tfjybj.english.provider.service.common.WordWrongCommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.tomcat.jni.User;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * WordRecordController
 * wordRecord表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since  2019-08-16 08:47:57
 */
@Api(tags = {"wordWrong表接口"})
@RequestMapping(value = "/wordWrong")
@RestController
public class WordWrongController {

    @Resource
    private WordWrongCommonService wordWrongCommonService;


    @Resource
    private WordDetectionService    wordDetectionService ;

    @Resource
    private RedisToDbService redisToDbService;


    /**
     * 获取用户待学习单词记录总数
     * @return
     */
    @ApiOperation("获取用户待学习单词记录总数")
    @GetMapping(value = {"/queryNumStudy"})
    public ItooResult queryNumStudy(){
        String userId = UserUtil.getCurrentUser().getUserId();

        Integer num =  wordWrongCommonService.queryNumStudy(userId);
        if(num > 0 ){
            return ItooResult.build(ItooResult.SUCCESS,"数据查询成功");
        }else {
            return ItooResult.build(ItooResult.FAIL,"暂无数据");
        }
    }


    /**
     * 获取用户待检测单词记录总数
     * @return
     */
    @ApiOperation("获取用户待检测单词记录总数")
    @GetMapping(value = {"/queryNumCheck"})
    public ItooResult queryNumCheck(){
        String userId = UserUtil.getCurrentUser().getUserId();

        Integer num =  wordWrongCommonService.queryNumCheck(userId);
        if(num > 0 ){
            return ItooResult.build("0000","数据查询成功");
        }else {
            return ItooResult.build(ItooResult.SUCCESS,"暂无数据");
        }
    }

    /**
     * 根据id查找未学习数据
     * @param
     * @return 根据id查找的结果
     * @author 张凯超
	 * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "根据id查询待学习数据")
    @GetMapping(value = {"/queryStoreWord"})
    public ItooResult queryStoreWord() {
        String userId = UserUtil.getCurrentUser().getUserId();
        WordModel wordModel = wordWrongCommonService.queryStoreWord(userId);
        if(wordModel !=null){
            return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordModel);
        }else {
            return ItooResult.build(ItooResult.FAIL,"暂无数据");
        }
    }

    /**
     * 根据id查找未检测数据
     *
     * @param
     * @return 根据id查找的结果
     * @author 张凯超
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "根据id查找未检测数据")
    @GetMapping(value = {"/queryStoreCheckWord"})
    public ItooResult queryStoreCheckWord(){
        String userId = UserUtil.getCurrentUser().getUserId();
        WordTemplteModel wordModel = wordWrongCommonService.queryStoreCheckWord(userId);
        if(wordModel != null ){
            return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordModel);
        }else {
            return ItooResult.build(ItooResult.FAIL,"暂无数据");
        }
    }

    /**
     *获取单词归仓待检测数据总数（错题本记录 + 单词检测正确记录）
     * @return
     *  张凯超
     */
    @ApiOperation("获取单词归仓待检测数据总数（错题本记录 + 单词检测正确记录）")
    @GetMapping(value = {"/queryStoreCheckToDo"})
    public Integer queryStoreCheckToDo(){
        String userId = UserUtil.getCurrentUser().getUserId();
        Integer CheckToDoNum = wordWrongCommonService.queryStoreCheckToDoWord(userId);
       if (CheckToDoNum == 0){
           return null;
       }else {
           return CheckToDoNum;
       }
    }


    /**
     * 更新用户单词学习状态
     * @param
     * @return 根据id查找的结果
     * @author 张凯超
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "更新用户单词学习状态")
    @PostMapping(value = {"/updateStoreWord/{userId}/{wordId}"})
    public ItooResult updateStoreWord(@PathVariable String userId, @PathVariable String wordId
            ) {
        WordAndTypeModel wordModel = wordWrongCommonService.updateStoreWord(userId,wordId);
        return ItooResult.build(ItooResult.SUCCESS, "已更新！", wordModel);
    }

    /**
     * 查询待归仓的数量
     * @param
     * @return 根据id查找的结果
     * @author 闫伟强
     * @version 1.0.0
     * @since 2019年8月27日11:31:33
     */
    @ApiOperation(value = "")
    @GetMapping(value = {"/queryStoreNumsByUserId"})
    public ItooResult queryStoreNumsByUserId() {
        Integer nums = wordWrongCommonService.queryStoreNumsByUserId(UserUtil.getCurrentUser().getUserId());
        return ItooResult.build(ItooResult.SUCCESS, "已更新！", nums);
    }


    /**
     *单词归仓待学习数据，切换下一个
     * @return
     */
    @ApiOperation("单词归仓待学习数据，切换下一个")
    @GetMapping(value = {"/queryNextStoreWord"})
    public ItooResult queryNextStoreWord(){
        String userId = UserUtil.getCurrentUser().getUserId();
        WordAndTypeModel wordModel = wordWrongCommonService.queryNextStoreWord(userId);

        return ItooResult.build(ItooResult.SUCCESS,"查询成功",wordModel);
    }

    /**
     *单词归仓待检测数据，切换下一个
     * @return
     */
    @ApiOperation("单词归仓待检测数据，切换下一个")
    @PostMapping(value = {"/queryNextStoreCheckWord"})
    public ItooResult queryNextStoreCheckWord(@RequestBody WordCheckAndTypeModel model){
        String userId = UserUtil.getCurrentUser().getUserId();
        String wordId = model.getId();
        Integer isCheck = model.getIsCheck();
        WordTemplteModel wordModel = wordWrongCommonService.queryNextStoreCheckWord(userId,wordId,isCheck);
        if (wordModel==null)
            {
                redisToDbService.StoreCheckDoneToDB(model.getId());
                return ItooResult.build("2222","记录为空");
            }

        return ItooResult.build(ItooResult.SUCCESS,"查询成功",wordModel);
    }


}    
