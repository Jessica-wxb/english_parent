package com.tfjybj.english.provider.controller;

import com.tfjybj.english.provider.until.ContrastImgUntil;
import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class PhoneficControllerTest {

    @Test
    public void testFingerPrint() throws IOException {
        Thumbnails.of(new File("C:\\Users\\Limit\\Desktop\\img\\timg.jpg")).size(300, 300).toFile("../rose.jpg");

        Thumbnails.of(new File("C:\\Users\\Limit\\Desktop\\img\\timgqqweqweqwe.jpg")).size(300, 300).toFile("../rose1.jpg");
        ContrastImgUntil fp1 = new ContrastImgUntil(ImageIO.read(new File("../rose.jpg")));
        ContrastImgUntil fp2 = new ContrastImgUntil(ImageIO.read(new File("../rose1.jpg")));
        System.out.println("图片1二进制码:" + "\r\n" + fp1.toString(true));
        System.out.println("图片2二进制码:" + "\r\n" + fp2.toString(true));
        System.out.printf("图片相似率" + "\r\n" + fp1.compare(fp2) + "\r\n" +"");
    }


}