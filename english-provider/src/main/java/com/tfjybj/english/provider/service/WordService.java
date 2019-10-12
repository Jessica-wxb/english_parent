package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.WordEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.NewPictureAddress;
import com.tfjybj.english.model.WordPartModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * WordService接口
 * word表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
public interface WordService extends BaseServicePlus<WordEntity> {


    // 清空当前的复习内容
    boolean clearnRecordWord(String reviewFlag);

    //根据userid查询返回需要学习的words-邢美玲
    WordPartModel findWordsById(String string,String reviewFlag);

    // 学单词点击下一步---邢美玲
    WordPartModel getNextWord(String string,String reviewFlag);

    //点击字母更新学习列表---邢美玲
    void undateWordList(String word);

    //插入图片地址--邢美玲
    boolean  insertPicture(NewPictureAddress newPictureAddress);

    //插入fastdfs---邢美玲--2019年8月21日
    String upLoadPicture(MultipartFile file) throws IOException;

    // 查询学单词进度条返回值--邢美玲--2019年8月27日
    long queryWordNumsBar();

    // 将文件导入数据库
    boolean batchInsert(String path) throws IOException;

    // 将文件导入数据库
    boolean deleteNull()throws IOException;
}