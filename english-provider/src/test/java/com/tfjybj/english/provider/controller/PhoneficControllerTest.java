package com.tfjybj.english.provider.controller;

import com.tfjybj.english.model.WordModel;
import com.tfjybj.english.provider.until.ContrastImgUntil;
import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PhoneficControllerTest {

    @Test
    public void testFingerPrint() throws IOException {
        Thumbnails.of(new File("C:\\Users\\Limit\\Desktop\\img\\roseTwo.jpg")).size(300, 300).toFile("../rose.jpg");

        Thumbnails.of(new File("C:\\Users\\Limit\\Desktop\\img\\roseOne.jpg")).size(300, 300).toFile("../rose1.jpg");
        ContrastImgUntil fp1 = new ContrastImgUntil(ImageIO.read(new File("../rose.jpg")));
        ContrastImgUntil fp2 = new ContrastImgUntil(ImageIO.read(new File("../rose1.jpg")));
//        System.out.println(fp1.toString(true));
        System.out.printf(fp1.compare(fp2) + "");

        System.out.println("");
    }


}