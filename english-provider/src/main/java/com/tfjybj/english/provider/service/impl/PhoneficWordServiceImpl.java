package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.PhoneficEntity;
import com.tfjybj.english.entity.PhoneficWordEntity;
import com.tfjybj.english.model.PhoneficModel;
import com.tfjybj.english.model.PhoneficWordModel;
import com.tfjybj.english.provider.dao.PhoneficTestDao;
import com.tfjybj.english.provider.service.PhoneficService;
import com.tfjybj.english.provider.service.PhoneficWordService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.provider.until.UploadPictureUntil;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PhoneficWordService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Service("phoneficWordService")
public class PhoneficWordServiceImpl extends BaseServicePlusImpl<PhoneficTestDao, PhoneficWordEntity> implements PhoneficWordService {

    //region 模板生成
    @Resource
    private PhoneficTestDao phoneficTestDao;
    @Resource
    private UploadPictureUntil uploadPictureUntil;
    @Resource
    private PhoneficService phoneficService;

    @Override
    public List<PhoneficWordModel> queryAudioByPhoneficId(String phoneficId) {

        return phoneficTestDao.queryAudioByPhoneficId(phoneficId);
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    // 根据音标id匹配对应两个单词word-薛帅行-2019年6月11日15:47:59
    @Override
    public List<PhoneficWordModel> selectWordByPhoneficId(String phoneficId) {
        return phoneficTestDao.selectWordByPhoneficId(phoneficId);
    }

    @Override
    public PhoneficWordEntity queryStateByWord(String word) {
        return phoneficTestDao.queryStateByWord(word);
    }

    //音标练习(听)_根据音标id查询对应正确单词_邢美玲
    @Override
    public List<PhoneficWordModel> getPhoneficTestByIdById(Integer phoneficid) {
        return phoneficTestDao.getPhoneficTestById(phoneficid);
    }


    /**
     * 根据用户Id查询音标Id、音频
     *
     * @param userId 用户Id
     * @return 音标Id、音频
     * @since 2019年6月13日22:31:07
     */
    @Override
    public List<PhoneficEntity> queryAudioByUserId(String userId, Integer num) {
        return phoneficTestDao.queryAudioByUserId(userId, num);
    }

    /**
     * 根据音标的ID查询的tn_phonefic_word中所有的字段信息
     *
     * @param phoneficId 音标Id
     * @return tn_phonefic_word中所有的字段
     * @autor 冯佳兴
     * @since 2019年6月15日10:27:04
     */
    @Override
    public List<PhoneficWordModel> selectAllById(String phoneficId) {
        return phoneficTestDao.selectAllById(phoneficId);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertPhoneWordTable(String phoneWordPath, Map<String, PhoneficModel> phoneficWordMap) throws IOException {
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
        File[] fileDic = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File fileDic) {
                return fileDic.isDirectory();
            }
        });
        // 筛选文件夹下面所有的文件
        if (listFiles.length == 0 && fileDic.length > 0) {
            // 获取所有的文件
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                insertPhoneWordTable(fileList[i].getAbsolutePath(), phoneficWordMap);
            }
        } else if (listFiles.length > 0 && fileDic.length > 0) {
            PhoneficWordEntity pwEntity = new PhoneficWordEntity();
            pwEntity.setPhoneficAudio(uploadPictureUntil.uploadPicture(listFiles[0]));
            // 截取文件名称,当做map的key值,然后利用key值获取map中的value,value是一个model.然后get model中的id
            PhoneficModel phoneficModel = phoneficWordMap.get(listFiles[0].getName().substring(0, listFiles[0].getName().lastIndexOf('.')));
            pwEntity.setPhoneficId(phoneficModel == null ? null : phoneficModel.getId());
            for (int i = 0; i < fileDic.length; i++) {
                for (int j = 0; j < fileDic[i].listFiles().length; j++) {
                    String uploadPicture = uploadPictureUntil.uploadPicture(fileDic[i].listFiles()[j]);
                    String upperCase = uploadPicture.substring(uploadPicture.lastIndexOf('.') + 1).toUpperCase();
                    if (fileDic[i].getName().equals("错误")) {
                        if (UploadPictureUntil.AUDIO_FREQUENCY_FORMAT.contains(upperCase)) {
                            pwEntity.setWordFalseAudio(uploadPicture);
                        } else if (UploadPictureUntil.FILE_FORMAT.contains(upperCase)) {
                            pwEntity.setWordFalsePicture(uploadPicture);
                        }
                    } else if (fileDic[i].getName().equals("正确")) {
                        if (UploadPictureUntil.AUDIO_FREQUENCY_FORMAT.contains(upperCase)) {
                            pwEntity.setWordTrueAudio(uploadPicture);
                        } else if (UploadPictureUntil.FILE_FORMAT.contains(upperCase)) {
                            pwEntity.setWordTruePicture(uploadPicture);
                        }
                    }
                }

            }
            flag = this.save(pwEntity);
        }
        return flag;
    }

}
