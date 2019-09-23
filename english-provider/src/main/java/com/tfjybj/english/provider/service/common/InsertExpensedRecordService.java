package com.tfjybj.english.provider.service.common;

import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.english.model.EExpensedRecordModel;
import com.tfjybj.english.provider.dao.EExpensedRecordDao;
import com.tfjybj.english.provider.service.EExpensedRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("insertExpensedRecordService")
public class InsertExpensedRecordService {

    @Autowired
    EExpensedRecordDao eExpensedRecordDao;


    public String InsertExpensedRecord(String id,String userId, String description, String expensedENum){
        /*
            0.往E表消费记录表中插入数据，如果返货的b>0则插入成功，否则插入失败
        */
         int b= eExpensedRecordDao.InsertExpensedRecord(id,userId,description,expensedENum);
         if(b>0) {
             return ItooResult.SUCCESS;
         }else{
             return  ItooResult.FAIL;
         }
    }

}
