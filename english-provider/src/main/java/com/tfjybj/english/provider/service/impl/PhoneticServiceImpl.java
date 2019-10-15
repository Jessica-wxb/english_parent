package com.tfjybj.english.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.google.common.collect.Maps;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.english.entity.PhoneticEntity;
import com.tfjybj.english.entity.UserSetEntity;
import com.tfjybj.english.provider.dao.PhoneticDao;
import com.tfjybj.english.provider.service.PhoneticService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.RecordDate;
import com.tfjybj.english.utils.cache.RedisUtil;
import com.tfjybj.english.utils.cache.UploadPictureUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.json.JsonObject;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * PhoneticService接口实现类
 * ${base}表
 *
 * @author 白靖
 * @version ${version}
 * @since ${version} 2019-09-05 17:36:14
 */
@Service("phoneticService")
public class PhoneticServiceImpl extends BaseServicePlusImpl<PhoneticDao, PhoneticEntity> implements PhoneticService {

    //region 模板生成
    @Resource
    private PhoneticDao phoneticDao;

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UploadPictureUntil uploadPictureUntil;

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 查询要学习的音标
     *
     * @return 音标实体
     * @author 白靖
     * @since ${version} 2019年9月5日19:47:57
     */
    @Override
    public PhoneticEntity PhoneticToDo() {
        String userId = UserUtil.getCurrentUser().getUserId();
        //判断todo缓存中是否有数据
        boolean flag = redisUtil.hasKey(EnglishRedis.Phonetic + userId + EnglishRedis.ToDo);
        if (!flag) {
            //同步48个缓存到todo缓存中
            Set<String> keys = redisTemplate.keys(EnglishRedis.PHONETICID + "*");
            List<String> valueList = redisTemplate.opsForValue().multiGet(keys);
            List<PhoneticEntity> PhoneticEntitys = JSONObject.parseArray(valueList.toString(), PhoneticEntity.class);
            redisUtil.llSetAll(EnglishRedis.Phonetic + userId + EnglishRedis.ToDo, PhoneticEntitys);
        }
        //从todo缓存中拿取一条数据
        String strPhoneticToDo = (String) redisUtil.leftPop(EnglishRedis.Phonetic + userId + EnglishRedis.ToDo);
        PhoneticEntity PhoneticToDo = JSON.parseObject(strPhoneticToDo, PhoneticEntity.class);
        Map<String, String> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("phonetic", PhoneticToDo.getPhonetic());
        redisUtil.sSet(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.Done, JSON.toJSONString(map));
        return PhoneticToDo;
    }

    /**
     * 左滑下一个音标
     *
     * @param phonetic
     * @return 音标实体
     * @author 白靖
     * @since ${version} 2019年9月5日19:47:57
     */
    @Override
    public PhoneticEntity NextPhonetic(String phonetic) {
        String userId = UserUtil.getCurrentUser().getUserId();
        //判断todo缓存中是否有音标
        boolean flag = redisUtil.hasKey(EnglishRedis.Phonetic + userId + EnglishRedis.ToDo);
        if (flag) {
            String strPhoneticToDo = (String) redisUtil.leftPop(EnglishRedis.Phonetic + userId + EnglishRedis.ToDo);
            PhoneticEntity PhoneticToDo = JSON.parseObject(strPhoneticToDo, PhoneticEntity.class);
            //将学过的这个音标缓存到done中
            Map<String, String> map = Maps.newHashMap();
            map.put("userId", userId);
            map.put("phonetic", PhoneticToDo.getPhonetic());
            redisUtil.sSet(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.Done, JSON.toJSONString(map));
            //过期时间为两天s
            redisUtil.expire(EnglishRedis.Phonetic + userId + ":" + RecordDate.Date() + EnglishRedis.Done + ":" + phonetic, 2 * 24 * 3600);
            return PhoneticToDo;
        }
        return null;
    }

    /**
     * 侧边点击音标进行查询
     *
     * @return 音标实体
     * @author 白靖
     * @since ${version} 2019年9月5日19:47:57
     */
    @Override
    public PhoneticEntity queryPhonetic(String phonetic) {
        String value = redisUtil.get(EnglishRedis.PHONETICID + phonetic);
        PhoneticEntity queryPhonetic = JSON.parseObject(value, PhoneticEntity.class);
        return queryPhonetic;
    }

    /**
     * 将路径中的音标文件导入到数据库
     * @param phonePath 文件所在的路径
     * @author 陈广晗
     * @return
     * @throws IOException
     */
    @Override
    @Transactional(rollbackOn = IOException.class)
    public boolean phonePathInstert(String phonePath) throws IOException {
        boolean flag = false;
        File file = new File(phonePath);
        File[] fileFile = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        File[] fileDire = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        // 获取文件名称
        String fileName = phonePath.substring(phonePath.lastIndexOf('\\') + 1);
        if (fileDire.length > 0 && fileFile.length > 0) {
            PhoneticEntity phoneticEntity = new PhoneticEntity();
            // 单词
            phoneticEntity.setPhonetic(fileName);
            Integer picNum=0;
            for (int j = 0; j < fileFile.length; j++) {
                String uploadPath = uploadPictureUntil.uploadPicture(fileFile[j]);
                // 获取后缀
                String suffix = uploadPath.substring(uploadPath.lastIndexOf('.') + 1).toUpperCase();
                // 如果是webm格式的就是插入到视频字段中,如果是音频格式,插入到音频格式;如果是发音诀窍插入到,音标诀窍地址;
                phoneticEntity = UploadPictureUntil.VIDEO_FORMAT.contains(suffix) ? phoneticEntity.setVideo(uploadPath) :
                        UploadPictureUntil.AUDIO_FREQUENCY_FORMAT.contains(suffix) == true ? phoneticEntity.setPhoneticAudio(uploadPath) :
                                fileFile[j].getName().contains("发音诀窍") == true ? phoneticEntity.setKnack(uploadPath) :
                                        UploadPictureUntil.FILE_FORMAT.contains(suffix) == true ?
                                                phoneticEntity.setPhoneticPic(uploadPath) : phoneticEntity.setVideo("无视频!");
            }
           // 循环完成之后插入到音标表里

            for (int f = 0; f < fileDire[0].listFiles().length; f++) {

                String picture = uploadPictureUntil.uploadPicture(fileDire[0].listFiles()[f]);
                picNum++;
                switch (picNum) {
                    case 1:
                        phoneticEntity.setWordAudio1(picture);
                        continue;
                    case 2:
                        phoneticEntity.setWordAudio2(picture);
                        continue;
                    case 3:
                        phoneticEntity.setWordAudio3(picture);
                        continue;
                }
            }
            long pathInstert=phoneticDao.phonePathInstert(phoneticEntity);
            if (pathInstert>0){
                flag=true;
            }
        } else if (fileFile.length == 0 && fileDire.length > 0) {
            // 获取所有的文件
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 回调自身
                phonePathInstert(fileList[i].getAbsolutePath());
            }
        }
        return flag;
    }
    /**
     * 将音标插入redis
     *
     * @author 闫伟强
     * @since 2019年9月7日10:21:24
     */
    @Override
    public void  PhoneticInsertRedis() {
        List<PhoneticEntity> phoneticEntities = phoneticDao.PhoneticInsertRedis();
        for (int i = 0; i < phoneticEntities.size(); i++) {
            redisUtil.set(EnglishRedis.PHONETICID + phoneticEntities.get(i).getPhonetic(), JSON.toJSONString(phoneticEntities.get(i)));
        }
    }

    //设置过期时间
    //redisUtil.expire()
}
