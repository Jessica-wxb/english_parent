package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.entity.WordPhoneficEntity;
import com.tfjybj.english.model.PhoneficModel;
import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.model.WordPhoneficModel;
import com.tfjybj.english.provider.dao.WordPhoneficDao;
import com.tfjybj.english.provider.service.PhoneficService;
import com.tfjybj.english.provider.service.PhoneficWordService;
import com.tfjybj.english.provider.service.WordService;
import com.tfjybj.english.provider.service.WordTestService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.provider.until.UploadPictureUntil;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WordTestService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Service("wordTestService")
public class WordTestServiceImpl extends BaseServicePlusImpl<WordPhoneficDao, WordPhoneficEntity> implements WordTestService {

    //region 模板生成
    @Resource
    private WordPhoneficDao wordPhoneficDao;
    @Resource
    private PhoneficService phoneficService;
    @Resource
    private UploadPictureUntil uploadPictureUntil;


    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 根据单词Id获取对应单词audio
     *
     * @param wordId 单词Id
     * @return 单词audio
     * @author 张凯超
     */
    @Override
    public WordEntity queryAudioByWordId(String wordId) {
        return wordPhoneficDao.queryAudioByWordId(wordId);
    }

    /**
     * 根据单词id匹配对应两个音标
     *
     * @param wordId 单词Id
     * @return 单词Id对应音标
     * @author 张凯超
     */
    @Override
    public List<WordPhoneficEntity> queryPhoneticByWordId(String wordId) {
        return wordPhoneficDao.queryPhoneticByWordId(wordId);
    }

    /**
     * 根据音标Id拼写查找状态
     *
     * @param phoneficId 单词
     * @return state 0 正确 1 错误
     * @author
     */
    @Override
    public WordPhoneficEntity queryWordStateByphoneficId(String phoneficId) {
        return wordPhoneficDao.queryWordStateByphoneficId(phoneficId);
    }

    /**
     * 根据单词Id获取相关音标信息
     *
     * @param wordId 单词Id
     * @return 音标信息
     * @author 张凯超
     * @since 2019年6月14日22点35分
     */
    @Override
    public WordPhoneficModel queryPhoneficAboutByWordId(String wordId) {
        return wordPhoneficDao.queryPhoneficAboutByWordId(wordId);
    }

    /**
     * 根据主键Id查询所有信息
     * @author 张凯超
     * @param phoneficid
     *
     * @param id
     * @return
     * @author 张凯超
     * @since 2019年6月16日-21点14分
     */
    @Override
    public List<WordPhoneficModel> queryAllById(String phoneficid) {
        return wordPhoneficDao.queryAllById(phoneficid);
    }

    @Override
    public boolean insertPhoneWordTable(String phoneWordPath, Map<String, PhoneficModel> phoneficWordMap) {

        // 查询所有的音标数据
        if (phoneficWordMap == null || phoneficWordMap.size() == 0) {
            phoneficWordMap = new HashMap<>();
            List<PhoneficModel> list = phoneficService.getPhonefic();
            for (PhoneficModel phoneficModel : list) {
                phoneficWordMap.put(phoneficModel.getPhonefic(), phoneficModel);
            }
        }


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
                insertPhoneWordTable(fileList[i].getAbsolutePath(), phoneficWordMap);
            }
        } else if (listFiles.length > 0 && fileDicWordPhone.length > 0) {
            WordPhoneficEntity wordPhoneEntity = new WordPhoneficEntity();
            wordPhoneEntity.setWordAudio(uploadPictureUntil.uploadPicture(listFiles[0]));
            for (int i = 0; i < fileDicWordPhone.length; i++) {
                for (int j = 0; j < fileDicWordPhone[i].listFiles().length; j++) {
                    String uploadPicture = uploadPictureUntil.uploadPicture(fileDicWordPhone[i].listFiles()[j]);
                    String upperCase = uploadPicture.substring(uploadPicture.lastIndexOf('.') + 1).toUpperCase();
                    if (fileDicWordPhone[i].getName().equals("错误")) {
                        // 截取文件名称,当做map的key值,然后利用key值获取map中的value,value是一个model.然后get model中的id
                        PhoneficModel phoneficModel = phoneficWordMap.get(fileDicWordPhone[i].listFiles()[j].getName().substring(0, fileDicWordPhone[i].listFiles()[j].getName().lastIndexOf('.')));
                        wordPhoneEntity.setPhoneficFalseId(phoneficModel == null ? null : phoneficModel.getId());

                        if (UploadPictureUntil.AUDIO_FREQUENCY_FORMAT.contains(upperCase)) {
                            wordPhoneEntity.setPhoneficFalseAudio(uploadPicture);
                        } else if (UploadPictureUntil.FILE_FORMAT.contains(upperCase)) {
                            wordPhoneEntity.setPhoneficFalsePicture(uploadPicture);
                        }
                    } else if (fileDicWordPhone[i].getName().equals("正确")) {
                        // 截取文件名称,当做map的key值,然后利用key值获取map中的value,value是一个model.然后get model中的id
                        PhoneficModel phoneficModel = phoneficWordMap.get(fileDicWordPhone[i].listFiles()[j].getName().substring(0, fileDicWordPhone[j].listFiles()[0].getName().lastIndexOf('.')));
                        wordPhoneEntity.setPhoneficTrueId(phoneficModel == null ? null : phoneficModel.getId());
                        if (UploadPictureUntil.AUDIO_FREQUENCY_FORMAT.contains(upperCase)) {
                            wordPhoneEntity.setPhoneficTrueAudio(uploadPicture);
                        } else if (UploadPictureUntil.FILE_FORMAT.contains(upperCase)) {
                            wordPhoneEntity.setPhoneficTruePicture(uploadPicture);
                        }
                    }
                }

            }
            flag = this.save(wordPhoneEntity);
        }
        return flag;
    }
}
