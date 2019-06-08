package com.tfjybj.english.provider.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import com.tfjybj.english.entity.AllusersEntity;
import com.tfjybj.english.provider.dao.AllusersDao;
import com.tfjybj.english.provider.service.AllusersService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

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
    private FastFileStorageClient fastFileStorageClient;
    @Resource
    private TrackerClient trackerClient;
    @Override
    public String upLoadPicture(MultipartFile file) throws IOException {

        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        String serverPath = trackerClient.getStoreStorage().getIp();
        String imagePath = "http://" + serverPath + "/" + storePath.getFullPath();
        log.info("图片上传成功，地址：" + imagePath);
        return imagePath;
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */
}
