package com.tfjybj.english.provider.service;

import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.english.entity.EExpensedRecordEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import com.tfjybj.english.model.EExpensedRecordModel;
import com.tfjybj.english.provider.dao.EExpensedRecordDao;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * EExpensedRecordService接口
 * eExpensedRecord表
 *
 * @author 王小波
 * @version ${version}
 * @since ${version} 2019-09-20 16:05:03
 */

public interface EExpensedRecordService extends BaseServicePlus<EExpensedRecordEntity> {
//@Autowired
//private EExpensedRecordDao eExpensedRecordDao;
//    public String InsertExpensedRecord(String userId,String description,String expensedENum){
//        // 获取userID
//        //  String userId = UserUtil.getCurrentUser().getUserId();
//        int b= eExpensedRecordDao.InsertExpensedRecord(description,expensedENum,userId);
//        if(b>0) {
//            return ItooResult.SUCCESS;
//        }else{
//            return  ItooResult.FAIL;
//        }
//    }
	
}
