package com.tfjybj.english.provider.service.impl;

import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.provider.dao.WordDao;
import com.tfjybj.english.provider.service.WordService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.provider.until.UploadPictureUntil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
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
@Service("wordService")
@Slf4j
public class WordServiceImpl extends BaseServicePlusImpl<WordDao, WordEntity> implements WordService {

    //region 模板生成
    @Resource
    private WordDao wordDao;
    @Resource
    private UploadPictureUntil uploadPictureUntil;
    @Override
    public List<WordModel> selDataNum(Integer setNumber) {
        return wordDao.selDataNum(setNumber);
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */

    /**
     * 查所有单词数接口实现类
     * word表
     *
     * @author 邢美玲
     * @version 1.0
     * @since ${version} 2019年6月9日15:47:16
     */
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
            // 文件单个存储,不走库,直接跳过!
            if (tempList[i].isFile()) {
                continue;
            }
            // 批量存储
            if (tempList[i].isDirectory() && ((tempList[i].listFiles().length > 0 ? !tempList[i].listFiles()[0].isFile() : true))) {
                batchInsert(tempList[i].getAbsolutePath());
            } else if (tempList[i].listFiles()[0].isFile()) {
                WordEntity wordEntity = new WordEntity();
                // 获取文件夹名称
                wordEntity.setWord(tempList[i].getName());
                // 获取子文件夹中的数据
                File[] tepmWord = tempList[i].listFiles();
                int picNum = 0;
                for (int j = 0; j < tepmWord.length; j++) {
                    // 通过路径转换成文件流picture
//                    FileInputStream wordStrm = new FileInputStream(tepmWord[j]);
//                    MultipartFile multipartFile = new MockMultipartFile(tepmWord[j].getName(), tepmWord[j].getName(), "text/plain", wordStrm);
                    String picture = uploadPictureUntil.uploadPicture(tepmWord[j]);
                    // 通过截取路径获取后缀
                    if (picture == "" || picture == null) {
                        log.error("文件上传失败!");
                        return false;
                    }
                    if (UploadPictureUntil.FILE_FORMAT.contains(picture.substring(picture.lastIndexOf('.') + 1).toUpperCase())) {
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
                    } else if (UploadPictureUntil.AUDIO_FREQUENCY_FORMAT.contains(picture.substring(picture.lastIndexOf('.') + 1).toUpperCase())) {
                        wordEntity.setAudio(picture);
                    }
                }

                wordEntitieList.add(wordEntity);
            }

        }
        return this.saveBatch(wordEntitieList);


    }

    //endregion

    /**
     * @param studSum 当天学习任务量
     * @return Id 返回一个Id值
     * @author 任嘉颖
     * @since 2019年6月10日15:13:41
     */

    @Override
    public List<WordModel> selectPhoneficPictureById(Integer studSum) {
        return wordDao.selectPhoneficPictureById(studSum);
    }

    /**
     * 根据学习任务查询单词数量不包含记录表中的数据
     * @param setNumber 设定当天学习任务量
     * @return 任务量条数
     * @author 谷海涛
     * @since 2019年6月10日15:13:41
     */
    @Override
    public List<WordModel> queryWordData(Integer setNumber,String userId){ return  wordDao.queryWordData(setNumber,userId);}
    @Override
    public WordEntity queryStateByWord(String word) {
        return wordDao.queryStateByWord(word);
    }

    @Override
    public WordEntity queryPictureByWord(String word) {
        return wordDao.queryPictureByWord(word);
    }

    @Override
    public List<WordModel> queryWordAll() {
        return wordDao.queryWordAll();
    }

    /**
     * 根据单词Id查询单词音频
     *
     * @param wordId 单词Id
     * @return 单词音频
     * @author 张凯超
     */
    @Override
    public WordModel queryAudioBywordId(String wordId) {
        return wordDao.queryAudioBywordId(wordId);

    }

    /**
     * 根据用户ID获取用户记录中单词、单词Id
     * @param userId 用户Id
     * @param num
     * @return 单词、单词Id
     * @since  2019年6月14日21:25:14
     */
    @Override
    public List<WordModel> queryWordAboutByUserId(String userId, Integer num) {
        return wordDao.queryWordAboutByUserId(userId,num);
    }

}
