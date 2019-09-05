package com.tfjybj.english.provider.service.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.english.entity.UserSetEntity;
import com.tfjybj.english.entity.WordRecordEntity;
import com.tfjybj.english.model.*;
import com.tfjybj.english.provider.dao.UserInfoDao;
import com.tfjybj.english.provider.dao.UserSetDao;
import com.tfjybj.english.provider.dao.WordDao;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.cache.FastJsonWrapper;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.json.Json;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service("wordListService")
public class WordListService {


    @Resource
    private WordDao wordDao;

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 根据wordId查找属于它的图片
     *
     * @param wordId 单词Id
     * @return 根据wordId查找属于它的图片
     * @author 闫伟强
     * @since 2019-08-16 08:47:57
     */
    public WordModel findWordPicture(String wordId,String word) {

        String userId = UserUtil.getCurrentUser().getUserId();

        String json = redisUtil.get(EnglishRedis.Word  + word);

        WordModel wordModel = JSON.parseObject(json,WordModel.class);

        //将新图表中的数据替换旧的
        if (!redisUtil.hasKey(EnglishRedis.NewPicture + userId +"_"+ wordId)) {
            return wordModel;
        }
        NewPicturePartModel newPicturePartModel =JSONObject.parseObject(redisUtil.get(EnglishRedis.NewPicture + userId +"_"+ wordId), NewPicturePartModel.class);
        String[] strs=newPicturePartModel.getPictures().split(",");
        //List<String> list = Arrays.asList(strs);
        List<String> list=new ArrayList<>();
        for (int i=0;i<strs.length;i++) {
            list.add(strs[i]);
        }
        if (list.size()<4) {
            list.add(wordModel.getWordPicture1());
            list.add(wordModel.getWordPicture2());
            list.add(wordModel.getWordPicture3());
            list.add(wordModel.getWordPicture4());
        }

        wordModel.setWordPicture1(list.get(0));
        wordModel.setWordPicture2(list.get(1));
        wordModel.setWordPicture3(list.get(2));
        wordModel.setWordPicture4(list.get(3));
        return wordModel;
    }


    /**
     * 将word表插入Redis
     *
     * @author 闫伟强
     * @since 2019-08-16 08:47:57
     */
    public List<WordModel> wordInsertRedis() {

        List<WordModel> wordModels = wordDao.wordInsertRedis();

        for (int i = 0; i < wordModels.size(); i++) {
            redisUtil.set(EnglishRedis.Word + wordModels.get(i).getWord(), JSON.toJSONString(wordModels.get(i)));
        }

        return wordModels;
    }

    /**
     * 通过单词拼写模糊查询单词
     *
     * @param word 单词拼写
     * @author 闫伟强
     * @since 2019-08-16 08:47:57
     */
    public List<WordModel> findWordByNameLR(String word) {
        Set<String> keys = redisTemplate.keys(EnglishRedis.Word  + "*" + word + "*");
        List<String> valueList = redisTemplate.opsForValue().multiGet(keys);
        List<WordModel> wordModels = JSONObject.parseArray(valueList.toString(), WordModel.class);
        return wordModels;
    }

    /**
     * 通过单词拼写模糊查询单词 以A-Z打头
     *
     * @param word 单词拼写
     * @author 闫伟强
     * @since 2019-08-16 08:47:57
     */
    public List<WordModel> findWordByNameR(String word) {
        Set<String> keys = redisTemplate.keys(EnglishRedis.Word  + word.toLowerCase() + "*");
        List<String> valueList = redisTemplate.opsForValue().multiGet(keys);
        List<WordModel> wordModels = JSONObject.parseArray(valueList.toString(), WordModel.class);
        return wordModels;
    }

    /**
     * 分页查询所有Word
     *
     * @param pageNo   页码
     * @param pageSize 每页条数
     * @return 分页查询的结果
     * @author 闫伟强
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    public List<WordModel> queryPageAll(Integer pageNo, Integer pageSize) {

        Integer pageNoNew = pageNo * pageSize;
        List<String> wordIdList = wordDao.queryPageAll(pageNoNew, pageSize);
        List<String> valueListNew = wordIdList.stream().map(item -> EnglishRedis.Word  + item).collect(Collectors.toList());
        List<String> valueList = redisTemplate.opsForValue().multiGet(valueListNew);
        List<WordModel> wordModels = JSONObject.parseArray(valueList.toString(), WordModel.class);
        return wordModels;
    }

    /**
     * 查询所有Word
     *
     * @return 查询的结果
     * @author 闫伟强
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    public List<WordModel> selectAll() {
        List<WordModel> wordModels = wordDao.wordInsertRedis();
        return wordModels;
    }
}
