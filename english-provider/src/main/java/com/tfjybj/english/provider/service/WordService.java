package com.tfjybj.english.provider.service;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.english.entity.WordEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.WordModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    /**
     * 根据设定学习量查询数据条数
     *
     * @param  Id 根据Id查找当前显示图片
     * @return Id 返回一个Id值
     * @author 任嘉颖
     * @since 2019年6月10日15:13:41
     */

    List<WordModel> selectPhoneficPictureById( Integer Id) ;



}
