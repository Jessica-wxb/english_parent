package com.tfjybj.english.provider.service;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.english.entity.WordEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.WordModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    WordEntity queryStateByWord(String word);

    WordEntity queryPictureByWord(String word);

    /**
     * 无参查询所有word表中的数据
     *
     * @return 所有word表中的实体集合
     * @author 马莹
     * @since 2019-6-12 20:30:00
     */
    List<WordModel> queryWordAll();

    /**
     * 根据单词Id查询单词音频
     * @author 张凯超
     * @param wordId 单词Id
     * @return 单词音频
     */
    WordModel queryAudioBywordId(String wordId);

    /**
     * 根据用户ID获取用户记录中单词、单词Id
     * @param userId 用户Id
     * @return 单词、单词Id
     * @since 2019年6月14日21:24:13
     */
    List<WordModel> queryWordAboutByUserId(String userId);
}
