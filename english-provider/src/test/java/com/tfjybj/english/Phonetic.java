package com.tfjybj.english;

import com.tfjybj.english.provider.service.PhoneticWordService;
import com.tfjybj.english.utils.cache.UploadPictureUntil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class Phonetic {
    @Autowired
    PhoneticWordService phoneticWordService;
    @Autowired
    UploadPictureUntil uploadPictureUntil;
    @Test
    public void phoneticToDb(){
        String pic="com/tfjybj/english/ɝ.png";
        File file = new File(pic);
        String url = uploadPictureUntil.uploadPicture(file);
        System.out.println(url);
    }
}
