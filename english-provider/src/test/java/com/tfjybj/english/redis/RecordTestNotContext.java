package com.tfjybj.english.redis;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.tfjybj.english.model.UserNewpictureModel;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecordTestNotContext {


    @Test
    public void testByUserId(){
        List<UserNewpictureModel> userNewpictureModels = Lists.newArrayList();
        UserNewpictureModel userNewpictureModel = new UserNewpictureModel();
        userNewpictureModel.setUserId("123");
        userNewpictureModel.setWordId("234");
//        userNewpictureModel.setPictures("456");
        userNewpictureModel.setPictureAddress("456");
        userNewpictureModels.add(userNewpictureModel);

        Map<String,Object> map= userNewpictureModels.stream().collect(Collectors.toMap(UserNewpictureModel -> UserNewpictureModel.getUserId() + UserNewpictureModel.getWordId() , UserNewpictureModel::toString));
        System.out.println(map);
    }

    @Test
    public void test(){
      /*  String ENGLISH_WORD  = "234";
        List<String> valueList = Lists.newArrayList("123");
        List<String> valueListNew = valueList.stream().map(item -> ENGLISH_WORD + item).collect(Collectors.toList());
//        map,foreach,filter,sort,reduce
//        List<String> valueListNew = valueList.stream().forEach(item -> {item = ENGLISH_WORD + item;} );
        System.out.println(valueListNew.toString());*/

     /* Student student = new Student();
        student.setId("123");
        student.setName("234");
        List<Student> students = Lists.newArrayList(student);*/

        //List<StudentNew> studentNews = students.stream().map(new StudentNew(student:: getId,student :: getName,student :: toString)).collect(Collectors.toList());
    }

    @Test
    public void testConfig(){
        /*System.out.println(auth);*/

        Map<String,String> userInfo = Maps.newHashMap();
        userInfo.put("userCode","1573267791");
        userInfo.put("password","1573267791");
        System.out.println(JSON.toJSONString(userInfo));
    }


}
