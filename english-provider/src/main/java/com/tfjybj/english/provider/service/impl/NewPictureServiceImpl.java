package com.tfjybj.english.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.tfjybj.english.entity.NewPictureEntity;
import com.tfjybj.english.model.UserNewpictureModel;
import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.provider.dao.NewPictureDao;
import com.tfjybj.english.provider.service.NewPictureService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.cache.RedisUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * NewPictureService接口实现类
 * ${base}表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Service("newPictureService")
public class NewPictureServiceImpl extends BaseServicePlusImpl<NewPictureDao,NewPictureEntity> implements NewPictureService {


	//region 模板生成
    @Resource
    private NewPictureDao newPictureDao;

    @Resource
    private RedisUtil redisUtil;
    //endregion

    /* **********************************以下为非模板生成的内容********************************* */


    @Override
    public List<UserNewpictureModel> newPictureInsertRedis() {
        List<UserNewpictureModel> userNewpictureModels = newPictureDao.newPictureInsertRedis();

        for(int i = 0;i<userNewpictureModels.size();i++){
            redisUtil.set(EnglishRedis.NewPicture + userNewpictureModels.get(i).getUserId()+"_"+userNewpictureModels.get(i).getWordId(), JSON.toJSONString(userNewpictureModels.get(i)));
        }
        return userNewpictureModels;
    }

}
