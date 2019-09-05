package com.tfjybj.english.provider.service.common;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.google.common.collect.Lists;
import com.tfjybj.english.model.*;
import com.tfjybj.english.provider.dao.UserInfoDao;
import com.tfjybj.english.provider.dao.UserSetDao;
import com.tfjybj.english.provider.dao.WordDao;
import com.tfjybj.english.provider.dao.WordRecordDao;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.cache.RedisUtil;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.json.Json;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("rankService")
public class RankService {

    private String ENGLISH_RANK = "English:Rank";
    private String ENGLISH_USERINFO = "ENGLISH:USERINFO:";
    private Integer MAX_WORDS = 500;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    UserSetDao userSetDao;

    @Autowired
    WordRecordDao wordRecordDao;
    @Autowired
     WordDao wordDao;

    /**
     * 获取排行榜--董可
     *
     * @return
     */
    public List<RankModel> getRanking(String userId) {
        boolean flag = redisUtil.hasKey(EnglishRedis.Rank);
        if (!flag) {
            List<RankModel> rankModelList = userInfoDao.queryUserIdEAllNum();
            Map<String, Object> map = rankModelList.stream().collect(Collectors.toMap(RankModel::getUserId, RankModel -> JSON.toJSONString(RankModel)));
            redisUtil.hmset(EnglishRedis.Rank, map);
            return rankModelList;
        }
        Map<Object, Object> rankMap = redisUtil.hmget(EnglishRedis.Rank);
        if (rankMap == null || rankMap.size() == 0) {
            return null;
        }
        List<RankModel> rankList = rankMap.entrySet().stream().map(e -> JSON.parseObject(String.valueOf(e.getValue()), RankModel.class)).collect(Collectors.toList());
        List<RankModel> rankNewList = rankList.stream().sorted(Comparator.comparing(RankModel::getIntRank).reversed()).collect(Collectors.toList());
        rankNewList.forEach(item -> {
            item.setRank(rankNewList.indexOf(item) + 1);
        });

        return rankNewList;
    }

    //遍历出登录的用户，给当前登陆用户的isLight赋值为1--董可
    public List<RankLocalModel> localRankByUserId(String userId) {
        List<RankModel> rankModelList = getRanking(userId);
        List<RankLocalModel> result = Lists.newArrayList();
        for (int i = 0; i < rankModelList.size(); i++) {
            RankModel rankModel = rankModelList.get(i);
            RankLocalModel rankLocalModel = new RankLocalModel();
            BeanUtils.copyProperties(rankModel, rankLocalModel);
            rankLocalModel.setIsLight(0);
            if (rankLocalModel.getUserId().equals(userId)) {
                rankLocalModel.setIsLight(1);
            }
            result.add(rankLocalModel);
        }
        return result;
    }

    /**
     * 定位到某一个人--董可--这个方法不用了
     *
     * @param userId
     * @return
     */
    private List<RankModel> localRank(String userId) {
        List<RankModel> rankNewList = getRanking(userId);
        //小于10个，返回
        if (rankNewList == null || rankNewList.size() < 10) {
            return rankNewList;
        }
        //如果有10个，是否是top10
        List<RankModel> rankModelList = rankNewList.subList(0, 9).stream().filter(s -> s.getUserId().equals(userId)).collect(Collectors.toList());
        if (rankModelList != null && rankModelList.size() > 0) {
            return rankModelList;
        }
        //判断是否是最后三名
        List<RankModel> rankModelResult = Lists.newArrayList();
        List<RankModel> rankModelListOnly = rankNewList.subList(rankNewList.size() - 3, rankNewList.size() - 1).stream().filter(s -> s.getUserId().equals(userId)).collect(Collectors.toList());
        rankModelResult.addAll(rankNewList.subList(0, 3));
        if (rankModelListOnly != null && rankModelListOnly.size() > 0) {
            //是最后三名中的一个
            rankModelResult.addAll(rankNewList.subList(0, 3));
            return rankNewList.subList(rankNewList.size() - 7, rankNewList.size() - 0);
        }
        //不是最后三名中一个
        List<RankModel> rankModelListMe = rankNewList.stream().filter(s -> s.getUserId().equals(userId)).collect(Collectors.toList());
        if (rankModelListMe == null || rankModelListMe.size() == 0) {
            return rankNewList.subList(0, 9).stream().collect(Collectors.toList());
        }
        RankModel rankModel = rankModelListMe.get(0);//
        int index = rankModel.getRank();
        rankModelResult.addAll(rankNewList.subList(index - 4, index - 1));
        rankModelResult.add(rankModel);
        rankModelResult.addAll(rankNewList.subList(index, index + 3));

        return rankModelResult;
    }

    /**
     * 加e币--董可（别人调这个方法）--利用枚举实现此功能
     *
     * @param userId
     * @param index  0:学单词；1：单词检测；2：归仓检测
     * @return
     */
    public boolean addE(String userId, int index) {
        boolean flag = redisUtil.hasKey(EnglishRedis.Rank);
        if (!flag) {//如果redis中没有数据，则从数据库中拿数据
            List<RankModel> rankModelList = userInfoDao.queryUserIdEAllNum();
            Map<String, Object> map = rankModelList.stream().collect(Collectors.toMap(RankModel::getUserId, RankModel::toString));
            redisUtil.hmset(EnglishRedis.Rank, map);
        }
        //从redis中拿数据
        String userEInfoNew = redisUtil.hget(EnglishRedis.Rank, userId);

        //从redis中取一条数据，ENGLISH_USERINFO，要加E币---有问题
        //String userInfoEAllNum = redisUtil.hget(ENGLISH_USERINFO,userId);

        if (!StringUtils.isEmpty(userEInfoNew)) { //判断redis中是否为空，不为空则执行以下代码
            RankModel rankModel = JSON.parseObject(userEInfoNew, RankModel.class);

            //RankModel userEAllNumModel = JSONObject.parseObject(userInfoEAllNum, RankModel.class);//---有问题,保留

            rankModel.setEAllNum(String.valueOf(Integer.valueOf(rankModel.getEAllNum()) + AddEType.getENum(index)));//加分，然后存到rankModel中

            //有问题
            //rankModel.setEAllNum(String.valueOf(Integer.valueOf(userEAllNumModel.getEAllNum()) + AddEType.getENum(index)));//加分，然后存到ENGLISH_USERINFO中

            redisUtil.hset(EnglishRedis.Rank, userId, JSON.toJSONString(rankModel));//存到redis中
            //redisUtil.hset(ENGLISH_USERINFO, userId, JSON.toJSONString(userEAllNumModel));//存到redis中--有问题
            return true;
        }else{//为空，说明redis中没有这个人，则从数据库中拿出这个人，放到redis中
            //从数据库中拿一条数据
            RankModel rankModel = userInfoDao.queryUserIdAddE(userId);
            //将拿出的这条数据放到redis中
            redisUtil.hset(EnglishRedis.Rank, userId, JSON.toJSONString(rankModel));
            //之后再进行加e币
            rankModel.setEAllNum(String.valueOf(Integer.valueOf(rankModel.getEAllNum()) + AddEType.getENum(index)));
            //加E币之后放到redis中
            redisUtil.hset(EnglishRedis.Rank, userId, JSON.toJSONString(rankModel));//存到redis中
        }
        return false;
    }

    /**
     * “我的”界面（首页右滑）--董可
     *
     * @return
     */
    public MineModel Mine(String userCode){
        //从Token中获取userId
        String userId = UserUtil.getCurrentUser().getUserId();

        //从数据库中查询出用户学过的所有单词数量
        int allWordNum = wordRecordDao.queryAllWordNumByuserId(userId);
        //从redis中获取
        String allWordNumJson = redisUtil.get(EnglishRedis.UserInfo + userCode);
        //转实体
        UserPartModel allWordNumModel = JSON.parseObject(allWordNumJson,UserPartModel.class);
        //更新wordNum
        allWordNumModel.setWordNum(String.valueOf(allWordNum));
        //转Json存到redis中
        redisUtil.set(EnglishRedis.UserInfo+userCode, JSON.toJSONString(allWordNumModel));

        //redis中查询是否有userInfo
        boolean flag = redisUtil.hasKey(EnglishRedis.UserInfo + userCode);

        if (!flag) {
            List<MineModel> mineModels = userInfoDao.queryMineByUserId(userId);
            Map<String, Object> map = mineModels.stream().collect(Collectors.toMap(MineModel::getUserId, MineModel -> JSON.toJSONString(MineModel)));
            redisUtil.hmset(EnglishRedis.UserInfo, map);
        }
//        addInsistDays(userCode);
        //MineModel mineModelnew = JSON.parseObject(redisUtil.hget(ENGLISH_USERINFO, userCode), MineModel.class);
        String json = redisUtil.get(EnglishRedis.UserInfo + userCode);

        MineModel mineModelnew = JSON.parseObject(json,MineModel.class);
        return mineModelnew;
    }

    /**
     * 查询【我的】界面，坚持天数，需要用到的方法--董可
     * @param userCode
     */
    public void addInsistDays(String userCode) {
        String userId = UserUtil.getCurrentUser().getUserId();
        // 先获取今天剩余单词数量
        int needStudyNums = wordDao.findWordnumsById(UserUtil.getCurrentUser().getUserId()); //今天还需要的学习数量 = 今天设置的单词数 - 今天已学单词
        // 判断数据库updatetime是否是今天
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        String timenow = format2.format(Calendar.getInstance().getTime());
       String timeDate = userInfoDao.selectNowDay(userId);
        String[] timeDateNews = timeDate.split(" ");
        timeDate =  timeDateNews[0];




        if (needStudyNums <= 0 && timenow != timeDate ) {//根据时间和剩余学习单词数量判断是否 坚持天数加1

            // insistday +1
            userInfoDao.updateInsistDay(userId);
            //从数据库中取数据
            String insistDays = userInfoDao.selectInsistDays(userId);
            // 从redis中取数据
            String addInsistDay = redisUtil.get(EnglishRedis.UserInfo +userCode);
            //转类型
            MineModel sddInsistDayModel = JSON.parseObject(addInsistDay,MineModel.class);
            //更改坚持天数
            sddInsistDayModel.setInsistDays(String.valueOf(insistDays));
            //存redis中
            redisUtil.set(EnglishRedis.UserInfo+userCode, JSON.toJSONString(sddInsistDayModel));
            return ;
        } else {
            return ;
        }
    }
}
