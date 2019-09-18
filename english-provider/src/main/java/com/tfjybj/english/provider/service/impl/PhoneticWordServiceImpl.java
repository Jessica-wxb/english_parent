package com.tfjybj.english.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.tfjybj.english.entity.PhoneticWordEntity;
import com.tfjybj.english.provider.dao.PhoneticWordDao;
import com.tfjybj.english.provider.service.PhoneticWordService;
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
 * PhoneticWordService接口实现类
 * ${base}表
 *
 * @author 闫伟强
 * @version 1.0.0
 * @since 1.0.0 2019-09-06 11:10:13
 */
@Service("phoneticWordService")
public class PhoneticWordServiceImpl extends BaseServicePlusImpl<PhoneticWordDao,PhoneticWordEntity> implements PhoneticWordService {
	
	//region 模板生成
    @Autowired
    PhoneticWordDao phoneticWordDao;
    @Autowired
    RedisUtil redisUtil;

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */
    @Autowired
    UploadPictureUntil uploadPictureUntil;


    @Override
    public boolean insertPhoneticWordTable(String phoneWordPath) {
        boolean flag = false;
        File file = new File(phoneWordPath);
        // 筛选文件夹下面所有的文件
        File[] listFiles = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File fileList) {
                return fileList.isFile();
            }
        });
        File[] fileDicPhoneWord = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File fileDicPhoneWord) {
                return fileDicPhoneWord.isDirectory();
            }
        });
        // 筛选文件夹下面所有的文件
        if (listFiles.length == 0 && fileDicPhoneWord.length > 0) {
            // 获取所有的文件
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                insertPhoneticWordTable(fileList[i].getAbsolutePath());
            }
        } else if (listFiles.length > 0 && fileDicPhoneWord.length > 0) {
            PhoneticWordEntity phoneticWordEntity = new PhoneticWordEntity();
            phoneticWordEntity.setPhoneticAudio(uploadPictureUntil.uploadPicture(listFiles[0]));
            phoneticWordEntity.setPhonetic(listFiles[0].getName().substring(0, listFiles[0].getName().indexOf('.')));
            for (int i = 0; i < fileDicPhoneWord.length; i++) {
                for (int j = 0; j < fileDicPhoneWord[i].listFiles().length; j++) {
                    String uploadPicture = uploadPictureUntil.uploadPicture(fileDicPhoneWord[i].listFiles()[j]);
                    String upperCase = uploadPicture.substring(uploadPicture.lastIndexOf('.') + 1).toUpperCase();
                    if (fileDicPhoneWord[i].getName().equals("错误")) {
                        if (UploadPictureUntil.AUDIO_FREQUENCY_FORMAT.contains(upperCase)) {
                            phoneticWordEntity.setFalseAudio(uploadPicture);
                        } else if (UploadPictureUntil.FILE_FORMAT.contains(upperCase)) {
                            phoneticWordEntity.setFalsePic(uploadPicture);
                        }
                    } else if (fileDicPhoneWord[i].getName().equals("正确")) {
//                        phoneticWordEntity.setPhoneficTrue(fileDicPhoneWord[i].listFiles()[j].getName().substring(0, fileDicPhoneWord[j].listFiles()[0].getName().indexOf('.')));
//                        phoneticWordEntity.setPhoneficTrueId(phoneficModel == null ? null : phoneficModel.getId());
                        if (UploadPictureUntil.AUDIO_FREQUENCY_FORMAT.contains(upperCase)) {
                            phoneticWordEntity.setCorrectAudio(uploadPicture);
                        } else if (UploadPictureUntil.FILE_FORMAT.contains(upperCase)) {
                            phoneticWordEntity.setCorrectPic(uploadPicture);
                        }
                    }
                }
            }
            long pathInstert = phoneticWordDao.phonePathInstert(phoneticWordEntity);
            if (pathInstert > 0) {
                flag = true;
            }
//            flag = this.save(phoneticWordEntity);
        }
        return flag;
    }



	//endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 音标选单词插入redis
     *
     * @author 闫伟强
     * @since 2019年9月7日10:13:21
     */
    public void PhoneticWordInsertRedis(){
        List<PhoneticWordEntity> phoneticWordEntity = phoneticWordDao.PhoneticWordInsertRedis();
        for (int i = 0; i < phoneticWordEntity.size(); i++) {
            redisUtil.set(EnglishRedis.phnoeticWord + phoneticWordEntity.get(i).getPhonetic(), JSON.toJSONString(phoneticWordEntity.get(i)));
        }
    }
}
