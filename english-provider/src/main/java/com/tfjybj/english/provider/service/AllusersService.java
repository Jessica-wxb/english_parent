package com.tfjybj.english.provider.service;

import com.tfjybj.english.entity.AllusersEntity;
import com.dmsdbj.itoo.tool.base.service.BaseServicePlus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * AllusersService接口
 * allusers表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 17:38:43
 */
public interface AllusersService extends BaseServicePlus<AllusersEntity> {

    /**
     * 上传图片到fastdfs
     *
     * @param file
     * @return
     */
    String upLoadPicture(MultipartFile file) throws IOException;

}
