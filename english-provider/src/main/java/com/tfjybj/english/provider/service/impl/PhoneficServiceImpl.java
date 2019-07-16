package com.tfjybj.english.provider.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.tfjybj.english.entity.PhoneficEntity;
import com.tfjybj.english.entity.PhoneticCorrespondWordsEntity;
import com.tfjybj.english.model.PhoneficModel;
import com.tfjybj.english.provider.dao.PhoneficDao;
import com.tfjybj.english.provider.service.PhoneficService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.provider.service.PhoneticCorrespondWordsService;
import com.tfjybj.english.provider.until.UploadPictureUntil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

/**
 * PhoneficService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Service("phoneficService")
@Slf4j
public class PhoneficServiceImpl extends BaseServicePlusImpl<PhoneficDao, PhoneficEntity> implements PhoneficService {

    //region 模板生成
    @Resource
    private PhoneficDao phoneficDao;

    @Resource
    private UploadPictureUntil uploadPictureUntil;

    @Resource
    private PhoneticCorrespondWordsService phoneticCorrespondWordsService;
    //endregion

    /* **********************************以下为非模板生成的内容********************************* */
    @Override
    public PhoneficEntity selectAudioByPhonefic(String phonefic) {
        return phoneficDao.selectAudioByPhonefic(phonefic);
    }

    /**
     * 通过音标Id查找对应图片
     *
     * @param phoneficId 音标
     * @return 音标对应图片
     * @Author 张凯超
     */
    @Override
    public List<PhoneficEntity> queryPictureByPhonefic(String phoneficId) {
//        return phoneficDao.queryPictureByPhonefic(phoneficId);
        return null;
    }

    // 音标练习(看)-根据id查询所有图片等_xml
    @Override
    public List<PhoneficModel> getPhoneficById(Integer id) {

        return phoneficDao.getPhoneficById(id);
    }

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
            PhoneficEntity phoneficEntity = new PhoneficEntity();
            // 使用雪花算法生成id
            String phoneId = IdWorker.getIdStr();
            // 单词
            phoneficEntity.setPhonefic(fileName);
            // 给id赋值
            phoneficEntity.setId(phoneId);
            for (int j = 0; j < fileFile.length; j++) {
                String uploadPath = uploadPictureUntil.uploadPicture(fileFile[j]);
                // 获取后缀
                String suffix = uploadPath.substring(uploadPath.lastIndexOf('.') + 1).toUpperCase();
                // 如果是webm格式的就是插入到视频字段中,如果是音频格式,插入到音频格式;如果是发音诀窍插入到,音标诀窍地址;
                phoneficEntity = UploadPictureUntil.VIDEO_FORMAT.contains(suffix) ? phoneficEntity.setVideo(uploadPath) :
                        UploadPictureUntil.AUDIO_FREQUENCY_FORMAT.contains(suffix) == true ? phoneficEntity.setAudio(uploadPath) :
                                fileFile[j].getName().contains("发音诀窍") == true ? phoneficEntity.setKnackPicture(uploadPath) :
                                        UploadPictureUntil.FILE_FORMAT.contains(suffix) == true ?
                                                phoneficEntity.setPhoneficPicture(uploadPath) : phoneficEntity.setVideo("无视频!");
            }
            // 循环完成之后插入到音标表里
            if (this.save(phoneficEntity)) {
//                for (int d = 0; d < fileDire.length; d++) {
                for (int f = 0; f < fileDire[0].listFiles().length; f++) {
                    PhoneticCorrespondWordsEntity pcwe = new PhoneticCorrespondWordsEntity();
                    pcwe.setPhoneficId(phoneId);
                    // 截取单词
                    pcwe.setWord(fileDire[0].listFiles()[f].getName().substring(0, fileDire[0].listFiles()[f].getName().indexOf('.')))
                            // 获取音频地址,并且赋值
                            .setAudio(uploadPictureUntil.uploadPicture(fileDire[0].listFiles()[f]));
                    flag = phoneticCorrespondWordsService.save(pcwe);
                }
//                }
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

    @Override
    public List<PhoneficModel> getPhonefic() {
        return null;
    }

    /**
     * 根据音标模糊查询
     *
     * @return 音标模糊查询结果
     * @Author 白靖
     */
    @Override
    public List<PhoneficEntity> queryLikePhonefic(String phonefic) {
        return phoneficDao.queryLikePhonefic(phonefic);
    }
}
