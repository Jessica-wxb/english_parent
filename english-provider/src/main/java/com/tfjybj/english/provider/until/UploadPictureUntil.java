package com.tfjybj.english.provider.until;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;

@Slf4j
@Component
public class UploadPictureUntil {
    @Resource
    private FastFileStorageClient fastFileStorageClient;
    @Resource
    private TrackerClient trackerClient;

    // 所有音频格式
    public static String AUDIO_FREQUENCY_FORMAT = "CD、WAVE、AIFF、MPEG、MP3、MPEG-4、MIDI、WMA、RealAudio、VQF、OggVorbis、AMR、APE、FLAC、AAC";
    // 所有文件格式
    public static String FILE_FORMAT = "WEBP、BMP、PCX、TIF、GIF、JPEG、TGA、EXIF、FPX、SVG、PSD、CDR、PCD、DXF、UFO、EPS、AI、PNG、HDRI、RAW、WMF、FLIC、EMF、ICO、JPG、JPEG、PNG、GIF";

    public static String PICTURE_FORMAT = "JPG、JPEG、PNG";
    // 目前视频播放支持的格式为wev
    public static String VIDEO_FORMAT = "WEBM";

    // file.getName().substring(file.getName().indexOf('.')+1) 获取后缀
    public String uploadPicture(File file) {
        boolean flag = false;
        try {
            if (PICTURE_FORMAT.contains(file.getName().substring(file.getName().indexOf('.') + 1).toUpperCase())) {
                flag = true;
                Thumbnails.of(file).size(300, 300).toFile("../rose.jpg");
                file = new File("../rose.jpg");
                // 通过路径转换成文件流picture
            }
            FileInputStream wordStrm = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(), "text/plain", wordStrm);
            StorePath storePath = fastFileStorageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), FilenameUtils.getExtension(multipartFile.getOriginalFilename()), null);
            String serverPath = trackerClient.getStoreStorage().getIp();
            String imagePath = "http://" + serverPath + "/" + storePath.getFullPath();
            //  log.info("图片上传成功，地址：" + imagePath);
            if (flag) {
                file.delete();
            }
            return imagePath;
        } catch (Exception e) {
            log.error("图片插入错误,fileOnly=" + file.getAbsolutePath(), e);
            return null;
        }
    }


//    public String uploadPicture(File file) {
//        try {
//            // 通过路径转换成文件流picture
//            FileInputStream wordStrm = new FileInputStream(file);
//            MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(), "text/plain", wordStrm);
//            StorePath storePath = fastFileStorageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), FilenameUtils.getExtension(multipartFile.getOriginalFilename()), null);
//            String serverPath = trackerClient.getStoreStorage().getIp();
//            String imagePath = "http://" + serverPath + "/" + storePath.getFullPath();
//            log.info("图片上传成功，地址：" + imagePath);
//            return imagePath;
//        } catch (Exception e) {
//            log.error("图片插入错误,file="+file.getAbsolutePath() ,e);
//            return null;
//        }
//    }
}

