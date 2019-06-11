package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.provider.dao.WordDao;
import com.tfjybj.english.provider.service.WordService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.provider.until.UploadPictureUntil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.beans.Transient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * WordService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
@Slf4j
@Service("wordService")
public class WordServiceImpl extends BaseServicePlusImpl<WordDao, WordEntity> implements WordService {

    //region 模板生成
    @Resource
    private WordDao wordDao;


    //endregion

    /* **********************************以下为非模板生成的内容********************************* */
    @Resource
    private UploadPictureUntil uploadPictureUntil;

    // 所有音频格式
    @Value("${AUDIO_FREQUENCY_FORMAT}")
    private String AUDIO_FREQUENCY_FORMAT;

    // 所有文件格式
    @Value("${FILE_FORMAT}")
    private String FILE_FORMAT;

    @Override
    public List<WordModel> selDataNum(Integer setNumber) {
        return wordDao.selDataNum(setNumber);
    }

    @Override
    public Integer selectAll() {
        return wordDao.selectAll();
}

    @Override
    public boolean batchInsert(String path) throws IOException {
        List<WordEntity> wordEntitieList = new ArrayList<>();
        File file = new File(path);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            // 文件单个存储
            if (tempList[i].isFile()) {
                FileInputStream fileInputStream = new FileInputStream(tempList[i]);
                MultipartFile multipartFile = new MockMultipartFile(tempList[i].getName(), tempList[i].getName(), "text/plain", fileInputStream);
                uploadPictureUntil.uploadPicture(multipartFile);
            }
            // 批量存储
            if (tempList[i].isDirectory()) {
                WordEntity wordEntity = new WordEntity();
                // 获取文件夹名称
                wordEntity.setWord(tempList[i].getName());
                // 获取子文件夹中的数据
                File[] tepmWord = tempList[i].listFiles();
                int picNum = 0;
                for (int j = 0; j < tepmWord.length; j++) {
                    // 通过路径转换成文件流
                    FileInputStream wordStrm = new FileInputStream(tepmWord[j]);
                    MultipartFile multipartFile = new MockMultipartFile(tepmWord[j].getName(), tepmWord[j].getName(), "text/plain", wordStrm);
                    String picture = uploadPictureUntil.uploadPicture(multipartFile);
                    // 通过截取路径获取后缀
                    if (picture == "" || picture == null) {
                        log.error("文件上传失败!");
                        return false;
                    }
                    if (FILE_FORMAT.contains(picture.substring(picture.lastIndexOf('.') + 1))) {
                        picNum++;
                        switch (picNum) {
                            case 1:
                                wordEntity.setWordPicture1(picture);
                                continue;
                            case 2:
                                wordEntity.setWordPicture2(picture);
                                continue;
                            case 3:
                                wordEntity.setWordPicture3(picture);
                                continue;
                            case 4:
                                wordEntity.setWordPicture4(picture);
                                continue;
                            case 5:
                                wordEntity.setWordPicture5(picture);
                                continue;
                        }
                    } else if (AUDIO_FREQUENCY_FORMAT.contains(picture.substring(picture.lastIndexOf('.') + 1))) {
                        wordEntity.setPhonefic(picture);
                    }
                }

                wordEntitieList.add(wordEntity);
            }

        }
        return this.saveBatch(wordEntitieList);


    }

    //endregion

    /**
     *
     * @param  Id 根据Id查找当前显示图片
     * @return Id 返回一个Id值
     * @author 任嘉颖
     * @since 2019年6月10日15:13:41
     */

    @Override
    public List<WordModel> selectPhoneficPictureById( Integer Id) {return wordDao.selectPhoneficPictureById(Id);}
}
