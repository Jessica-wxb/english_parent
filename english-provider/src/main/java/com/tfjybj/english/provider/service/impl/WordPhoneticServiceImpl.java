package com.tfjybj.english.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.tfjybj.english.entity.WordPhoneticEntity;
import com.tfjybj.english.provider.dao.WordPhoneticDao;
import com.tfjybj.english.provider.service.WordPhoneticService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.utils.cache.UploadPictureUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileFilter;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * WordPhoneticService接口实现类
 * ${base}表
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@Service("wordPhoneticService")
public class WordPhoneticServiceImpl extends BaseServicePlusImpl<WordPhoneticDao, WordPhoneticEntity> implements WordPhoneticService {

    //region 模板生成
    @Autowired
    WordPhoneticDao wordPhoneticDao;
    @Autowired
    UploadPictureUntil uploadPictureUntil;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean insertWordPhoneticTable(String phoneWordPath) {

        boolean flag = false;
        File file = new File(phoneWordPath);
        // 筛选文件夹下面所有的文件
        File[] listFiles = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File fileList) {
                return fileList.isFile();
            }
        });
        File[] fileDicWordPhone = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File fileDicWordPhone) {
                return fileDicWordPhone.isDirectory();
            }
        });
        // 筛选文件夹下面所有的文件
        if (listFiles.length == 0 && fileDicWordPhone.length > 0) {
            // 获取所有的文件
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                insertWordPhoneticTable(fileList[i].getAbsolutePath());
            }
        } else if (listFiles.length > 0 && fileDicWordPhone.length >0) {
            WordPhoneticEntity wordPhoneticEntity = new WordPhoneticEntity();
            wordPhoneticEntity.setWordAudio(uploadPictureUntil.uploadPicture(listFiles[0]));
//            wordPhoneticEntity.setPhonetic(listFiles[0].getName().substring(0, listFiles[0].getName().indexOf('.')));
            for (int i = 0; i < fileDicWordPhone.length; i++) {
                for (int j = 0; j < fileDicWordPhone[i].listFiles().length; j++) {
//                    wordPhoneticEntity.setPhonetic(fileDicWordPhone[0].getName().substring(0, listFiles[0].getName().indexOf('.')));
                    String uploadPicture = uploadPictureUntil.uploadPicture(fileDicWordPhone[i].listFiles()[j]);
                    String upperCase = uploadPicture.substring(uploadPicture.lastIndexOf('.') + 1).toUpperCase();
                    if (fileDicWordPhone[i].getName().equals("错误")) {
                        if (UploadPictureUntil.AUDIO_FREQUENCY_FORMAT.contains(upperCase)) {
                            wordPhoneticEntity.setFalseAudio(uploadPicture);
                        } else if (UploadPictureUntil.FILE_FORMAT.contains(upperCase)) {
                            wordPhoneticEntity.setFalsePic(uploadPicture);
                        }
                    } else if (fileDicWordPhone[i].getName().equals("正确")) {
//                        wordPhoneticEntity.setPhoneficTrue(fileDicWordPhone[i].listFiles()[j].getName().substring(0, fileDicWordPhone[j].listFiles()[0].getName().indexOf('.')));
//                        wordPhoneticEntity.setPhoneficTrueId(phoneficModel == null ? null : phoneficModel.getId());
                        wordPhoneticEntity.setPhonetic(fileDicWordPhone[i].listFiles()[j].getName().substring(0, fileDicWordPhone[i].listFiles()[j].getName().indexOf('.')));
                        if (UploadPictureUntil.AUDIO_FREQUENCY_FORMAT.contains(upperCase)) {
                            wordPhoneticEntity.setCorrectAudio(uploadPicture);
                        } else if (UploadPictureUntil.FILE_FORMAT.contains(upperCase)) {
                            wordPhoneticEntity.setCorrectPic(uploadPicture);
                        }
                    }
                }
            }
            long pathInstert=wordPhoneticDao.phonePathInstert(wordPhoneticEntity);
            if (pathInstert>0){
                flag =true;
            }
//            flag = this.save(wordPhoneticEntity);
        }
        return flag;
    }






	//endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 单词选音标插入redis
     *
     * @author 闫伟强
     * @since 2019年9月7日10:13:21
     */
    @Override
    public void WordPhoneticInsertRedis(){
        List<WordPhoneticEntity> wordPhoneticEntities = wordPhoneticDao.WordPhoneticInsertRedis();
        for (int i = 0; i < wordPhoneticEntities.size(); i++) {
            redisUtil.set(EnglishRedis.wordPhonetic + wordPhoneticEntities.get(i).getPhonetic(), JSON.toJSONString(wordPhoneticEntities.get(i)));
        }

    }

}
