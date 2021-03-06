package com.tfjybj.english.provider.until.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SimilarImageSearch {
	public SimilarImageSearch() {
	}

	public static void main(String[] args) {
		/*List<String> hashCodes = new ArrayList();
		String filename = ImageHelper.path + "\\images\\";
		String hashCode = null;

		for(int i = 0; i < 6; i++) {
			hashCode = produceFingerPrint(filename  + (i + 1) + ".png");
			hashCodes.add(hashCode);
		}

		System.out.println("Resources: ");
		System.out.println(hashCodes);
		System.out.println();
		String sourceHashCode = produceFingerPrint(filename + "source.jpg");
		System.out.println("Source: ");
		System.out.println(sourceHashCode);
		System.out.println();

		for(int i = 0; i < hashCodes.size(); ++i) {
			int difference = hammingDistance(sourceHashCode, (String)hashCodes.get(i));
			System.out.print("汉明距离:" + difference + "     ");
			if (difference == 0) {
				System.out.println("source.jpg图片跟example" + (i + 1) + ".jpg一样");
			} else if (difference <= 5) {
				System.out.println("source.jpg图片跟example" + (i + 1) + ".jpg非常相似");
			} else if (difference <= 10) {
				System.out.println("source.jpg图片跟example" + (i + 1) + ".jpg有点相似");
			} else if (difference > 10) {
				System.out.println("source.jpg图片跟example" + (i + 1) + ".jpg完全不一样");
			}
		}*/
//		String hashCode = SimilarImageSearch.produceFingerPrint("http://a.hiphotos.baidu.com/lvpics/h=800/sign=2d496375d739b60052ce02b7d9513526/a6efce1b9d16fdfa97d6a678b68f8c5495ee7be9.jpg");
//		System.out.println(hashCode);
		/*String SourceCode = SimilarImageSearch.produceFingerPrint("C:\\Users\\Administrator\\Desktop\\image\\3.jpg");
		int difficulty = SimilarImageSearch.hammingDistance(hashCode, SourceCode);
		System.out.println(difficulty);*/
		String data = ",123";
		String[] de = data.split(",",0);
		System.out.println(de);
	}
	public static int hammingDistance(String sourceHashCode, String hashCode) {
		int difference = 0;
		int len = sourceHashCode.length();

		for(int i = 0; i < len; ++i) {
			if (sourceHashCode.charAt(i) != hashCode.charAt(i)) {
				++difference;
			}
		}

		return difference;
	}

	public static String produceFingerPrint(String filename) {
		BufferedImage source = ImageHelper.readPNGImage(filename);
		int width = 8;
		int height = 8;
		BufferedImage thumb = ImageHelper.thumb(source, width, height, false);
		int[] pixels = new int[width * height];

		int avgPixel;
		for(avgPixel = 0; avgPixel < width; ++avgPixel) {
			for(int j = 0; j < height; ++j) {
				pixels[avgPixel * height + j] = ImageHelper.rgbToGray(thumb.getRGB(avgPixel, j));
			}
		}

		avgPixel = ImageHelper.average(pixels);
		int[] comps = new int[width * height];

		for(int i = 0; i < comps.length; ++i) {
			if (pixels[i] >= avgPixel) {
				comps[i] = 1;
			} else {
				comps[i] = 0;
			}
		}

		StringBuffer hashCode = new StringBuffer();

		for(int i = 0; i < comps.length; i += 4) {
			int result = comps[i] * (int)Math.pow(2.0D, 3.0D) + comps[i + 1] * (int)Math.pow(2.0D, 2.0D) + comps[i + 2] * (int)Math.pow(2.0D, 1.0D) + comps[i + 2];
			hashCode.append(binaryToHex(result));
		}

		return hashCode.toString();
	}

	private static char binaryToHex(int binary) {
		char ch;
		switch(binary) {
			case 0:
				ch = '0';
				break;
			case 1:
				ch = '1';
				break;
			case 2:
				ch = '2';
				break;
			case 3:
				ch = '3';
				break;
			case 4:
				ch = '4';
				break;
			case 5:
				ch = '5';
				break;
			case 6:
				ch = '6';
				break;
			case 7:
				ch = '7';
				break;
			case 8:
				ch = '8';
				break;
			case 9:
				ch = '9';
				break;
			case 10:
				ch = 'a';
				break;
			case 11:
				ch = 'b';
				break;
			case 12:
				ch = 'c';
				break;
			case 13:
				ch = 'd';
				break;
			case 14:
				ch = 'e';
				break;
			case 15:
				ch = 'f';
				break;
			default:
				ch = ' ';
		}

		return ch;
	}
}
