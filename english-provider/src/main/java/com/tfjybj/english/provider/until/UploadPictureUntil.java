package com.tfjybj.english.provider.until;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Slf4j
@Component
public class UploadPictureUntil {
    @Resource
    private FastFileStorageClient fastFileStorageClient;
    @Resource
    private TrackerClient trackerClient;

    // 所有音频格式
    public static String AUDIO_FREQUENCY_FORMAT = "AVI 、ASF 、WMV 、AVS 、FLV 、MKV 、MOV 、3GP 、MP4 、MPG 、MPEG 、DAT 、DSM 、OGM 、VOB 、RM 、RMVB 、TS 、TP 、IFO 、NSV 、MP3 、AAC 、WAV 、WMA 、CDA 、FLAC 、M4A 、MID 、MKA 、MP2 、MPA 、MPC 、APE 、OFR 、OGG 、RA 、WV 、TTA 、AC3 、DTS 、WV 、SHN 、VQF 、SPC 、NSF 、ADX 、PSF 、MINIPSF 、PSF2 、MINIPSF2 、RSN 、ZST 、BIN 、IMG 、ISO 、MDS 、NRG 、DVR-MS 、DIVX 、M4V 、M2V 、PART 、VP6 、VP7 、RAM 、RMM 、SWF 、TRP 、FLC 、FLI";

    // 所有文件格式
    public static String FILE_FORMAT = "WEBP、BMP、PCX、TIF、GIF、JPEG、TGA、EXIF、FPX、SVG、PSD、CDR、PCD、DXF、UFO、EPS、AI、PNG、HDRI、RAW、WMF、FLIC、EMF、ICO、JPG、JPEG、PNG、GIF";

    public String uploadPicture(MultipartFile file) {
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
            String serverPath = trackerClient.getStoreStorage().getIp();
            String imagePath = "http://" + serverPath + "/" + storePath.getFullPath();
            log.info("图片上传成功，地址：" + imagePath);
            return imagePath;
        } catch (Exception e) {
            log.error("图片插入错误:" + e);
            return null;
        }
    }
}

