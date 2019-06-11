package com.tfjybj.english.provider.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import com.tfjybj.english.entity.AllusersEntity;
import com.tfjybj.english.provider.dao.AllusersDao;
import com.tfjybj.english.provider.service.AllusersService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.provider.until.UploadPictureUntil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * AllusersService接口实现类
 * ${base}表
 *
 * @author 马莹
 * @version ${version}
 * @since ${version} 2019-06-08 17:38:43
 */
@Slf4j
@Service("allusersService")
public class AllusersServiceImpl extends BaseServicePlusImpl<AllusersDao, AllusersEntity> implements AllusersService {

    //region 模板生成
    @Resource
    private AllusersDao allusersDao;

    @Resource
    private UploadPictureUntil uploadPictureUntil;

    @Override
    public String upLoadPicture(MultipartFile file) throws IOException {

        String uploadPicture = uploadPictureUntil.uploadPicture(file);
        return uploadPicture;
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */


}
