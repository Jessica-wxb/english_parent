package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.WordEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.model.WordTestModel;

import java.io.IOException;
import java.util.List;


/**
 * WordService接口
 * word表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 14:26:23
 */
public interface WordService extends BaseServicePlus<WordEntity> {

    /**
     * 根据设定学习量查询数据条数
     *
     * @param setNumber 设定当天学习任务量
     * @return 查询任务量条数
     * @author 马莹
     * @since 2019-6-9 19:41:00
     */
    List<WordModel> selDataNum(Integer setNumber);

    //查询所有单词--邢美玲
    Integer selectAll();


    /**
     * @param studSum 当天的学习任务量
     * @return Id 返回一个Id值
     * @author 任嘉颖
     * @since 2019年6月10日15:13:41
     */

    List<WordModel> selectPhoneficPictureById(Integer studSum);


    /**
     * 根据目录结构插入数据
     *
     * @param path 文件路径
     * @return true/false
     * @author 马莹
     * @since 2019-6-11 19:31:50
     */
    boolean batchInsert(String path) throws IOException;

    /**
     * 根据单词ID查音标
     * @param wordId 单词ID
     * @return 音标
     * @author 张凯超
     * @datetime 2019年6月12日17:14:27
     */
    List<WordTestModel> queryPhoneticByWordId(Integer wordId);
}
