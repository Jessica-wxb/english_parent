package com.tfjybj.english.provider.until.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

public class ImageHelper {
    public static final String path = "C:\\Users\\qmx\\Desktop\\compare";

    public ImageHelper() {
    }

    public static BufferedImage thumb(BufferedImage source, int width, int height, boolean b) {
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) width / (double) source.getWidth();
        double sy = (double) height / (double) source.getHeight();
        if (b) {
            if (sx > sy) {
                sx = sy;
                width = (int) (sy * (double) source.getWidth());
            } else {
                sy = sx;
                height = (int) (sx * (double) source.getHeight());
            }
        }

        if (type == 0) {
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, (Hashtable) null);
        } else {
            target = new BufferedImage(width, height, type);
        }

        Graphics2D g = target.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }

    public static BufferedImage readPNGImage(String filename) {
        try {
            InputStream file = saveToFile(filename);
            BufferedImage sourceImage = ImageIO.read(file);
            return sourceImage;
        } catch (FileNotFoundException var3) {
            var3.printStackTrace();
        }  catch (IOException var5) {
            var5.printStackTrace();
        }
        return null;
    }

    public static int rgbToGray(int pixels) {
        int _red = pixels >> 16 & 255;
        int _green = pixels >> 8 & 255;
        int _blue = pixels & 255;
        return (int) (0.3D * (double) _red + 0.59D * (double) _green + 0.11D * (double) _blue);
    }

    public static int average(int[] pixels) {
        float m = 0.0F;

        for (int i = 0; i < pixels.length; ++i) {
            m += (float) pixels[i];
        }

        m /= (float) pixels.length;
        return (int) m;
    }

    public static BufferedInputStream saveToFile(String destUrl) {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        try {
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
        } catch (IOException e) {
        } catch (ClassCastException e) {
        } finally {
            try {
                fos.close();
                bis.close();
                httpUrl.disconnect();
            } catch (IOException e) {
            } catch (NullPointerException e) {
            }
            return bis;
        }
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        InputStream file = saveToFile("http://a.hiphotos.baidu.com/lvpics/h=800/sign=2d496375d739b60052ce02b7d9513526/a6efce1b9d16fdfa97d6a678b68f8c5495ee7be9.jpg");
        BufferedImage sourceImage = ImageIO.read(file);
        System.out.println(sourceImage);
    }
}

