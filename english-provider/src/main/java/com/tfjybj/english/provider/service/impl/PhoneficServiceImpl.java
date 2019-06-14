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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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
    public boolean phonePathInstert(String phonePath) throws IOException {

        List<PhoneficEntity> phoneficEntityArrayList = new ArrayList<>();
        File file = new File(phonePath);
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            // 单文件不走,直接返回
            if (fileList[i].isFile()) {
                continue;
            }
            // 收集本次循环的文件
            File[] fileFil = fileList[i].listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isFile();
                }
            });
            // 收集本次循环中的文件夹
            File[] fileDir = fileList[i].listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathnameDir) {
                    return pathnameDir.isDirectory();
                }
            });
            // 如果没有文件只有文件夹,直接回调自己,再次循环
            if (fileFil.length == 0 && fileDir.length > 0) {
                phonePathInstert(fileList[i].getAbsolutePath());
            } else if (fileFil.length > 0 && fileDir.length >= 0) {
                PhoneficEntity phoneficEntity = new PhoneficEntity();
                PhoneficEntity phonefic1 = new PhoneficEntity();
                List<PhoneficEntity> phoneficEntityList = new ArrayList<>();
                // 使用雪花算法生成id
                String phoneId = IdWorker.getIdStr();
                phoneficEntity.setPhonefic(fileList[i].getName());
                phoneficEntity.setId(phoneId);
                for (int j = 0; j < fileFil.length; j++) {
                    String uploadPath = uploadPictureUntil.uploadPicture(fileFil[j]);

                    String suffix = uploadPath.substring(uploadPath.lastIndexOf('.') + 1).toUpperCase();
                    // 如果是webm格式的就是插入到视频字段中,如果是音频格式,插入到音频格式;如果是发音诀窍插入到,音标诀窍地址;
                    phonefic1 = suffix == UploadPictureUntil.VIDEO_FORMAT ? phoneficEntity.setVideo(uploadPath) :
                            UploadPictureUntil.AUDIO_FREQUENCY_FORMAT.contains(suffix) == true ? phoneficEntity.setVideo(uploadPath) :
                                    fileFil[j].getName().contains("发音诀窍") == true ? phoneficEntity.setKnackPicture(uploadPath) :
                                            UploadPictureUntil.FILE_FORMAT.contains(suffix) == true ?
                                                    phoneficEntity.setPhoneficPicture(uploadPath) : phoneficEntity.setPhoneficPicture(suffix);


                    if (suffix == UploadPictureUntil.VIDEO_FORMAT) {
                        phoneficEntity.setVideo(uploadPath);
                    } else if (UploadPictureUntil.AUDIO_FREQUENCY_FORMAT.contains(suffix)) {
                        phoneficEntity.setAudio(uploadPath);
                    } else if (fileFil[j].getName().contains("发音诀窍")) {
                        phoneficEntity.setKnackPicture(uploadPath);
                    } else if (UploadPictureUntil.FILE_FORMAT.contains(suffix)) {
                        phoneficEntity.setPhoneficPicture(uploadPath);
                    }
                }
                // 循环完成之后插入到音标表里
                if (this.save(phoneficEntity)) {
                    for (int d = 0; d < fileDir.length; d++) {
                        PhoneticCorrespondWordsEntity pcwe = new PhoneticCorrespondWordsEntity();
                        for (int f = 0; f < fileDir[d].listFiles().length; f++) {
                            pcwe.setPhoneficId(phoneId);
                            pcwe.setWord(fileDir[d].listFiles()[d].getName())
                                    .setAudio(uploadPictureUntil.uploadPicture(fileDir[d].listFiles()[d]));
//                                    .setVowelOrConsonant(UploadPictureUntil.AUDIO_FREQUENCY_FORMAT);
                        }
                        phoneticCorrespondWordsService.save(pcwe);
                    }
                }

                phoneficEntityList.add(phonefic1);

            }


        }

        return false;
    }


}
