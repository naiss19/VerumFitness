import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;


import java.awt.image.BufferedImage;


public class Skeleton {
	private static BufferedImage img;
	private static int[] rgbArray;
	private static boolean[] pixels;
	private int marginOfError = 5;
	private int height, width;
	private ArrayList<ArrayList<int[]>> polyLines = new ArrayList<ArrayList<int[]>>();
	

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public static boolean[] getPixels() {
		return pixels;
	}

	public static void setPixels(boolean[] pixels) {
		Skeleton.pixels = pixels;
	}

	public int getMarginOfError() {
		return marginOfError;
	}

	public void setMarginOfError(int marginOfError) {
		this.marginOfError = marginOfError;
	}

	public static BufferedImage getImg() {
		return img;
	}

	public static void setImg(BufferedImage img) {
		Skeleton.img = img;
	}

	public Skeleton() {
		try {
			img = ImageIO.read(new File(
					"/Users/BrandonHall/Documents/Development/eclipse/Verum Fitness/skeleton-tracing/test_images/horse_r.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setHeight(getImg().getHeight());
		setWidth(getImg().getWidth());
		
	}

	public static void main(String[] args) {
		Skeleton s = new Skeleton();
		
		
		s.bitMap();
		s.skeleton();
//		TraceSkeleton.thinningZS(getPixels(), s.width, s.height);
//		for (int i = 0; i < polyLines.size(); i++) {
//			for (int j = 0; j < polyLines.get(i).size(); j++) {
//				System.out.println(polyLines.get(i).get(j));
//			}
//		}
		System.out.println(s.polyLines.size());
		System.out.println("Size:" + s.polyLines.get(0).get(0).length);
		for (int i = 0; i < s.polyLines.get(0).get(0).length; i++) {
			System.out.println(s.polyLines.get(0).get(0)[i]);
		}
			
		System.out.println("done");

	}
	
//	public static void skeletonization() {
//		MyImage image = new MyImage();
//		image.readImage("/Users/BrandonHall/Downloads/StimmyMan.JPG");
//		Detect.edge(image);
//		Skeletonization.binaryImage(image);
//		image.writeImage("/Users/BrandonHall/Downloads/StimmyMan2.JPG");
//
//	}

	public void skeleton() {
		
		TraceSkeleton t = new TraceSkeleton();

//		TraceSkeleton.thinningZS(pixels, width, height);
		polyLines = t.trace(getPixels(), width, height, 100);
	}

	public boolean[] bitMap() {
		
		rgbArray = new int[img.getHeight() * img.getWidth()];
		pixels = new boolean[img.getHeight() * img.getWidth()];
//			for(int i = 0; i < img.getHeight(); i++) {
//				for(int j = 0; j < img.getWidth(); j++) {
//					img.getRGB(j, i);
//					if(img.getR)
//				}
//					
//			}
		rgbArray = img.getRGB(0, 0, img.getWidth(), img.getHeight(), rgbArray, 0, 1);

		for (int i = 0; i < rgbArray.length; i++) {
			if (rgbArray[i] == -16777216)
				pixels[i] = true;
			if (rgbArray[i] == 0)
				pixels[i] = false;
//			else
//				pixels[i] = false;
		}

		for (int i = 0; i < rgbArray.length / 100; i++)
			System.out.println(pixels[i] + ", " + rgbArray[i]);

		return pixels;

	}

	public static boolean[] bitMap(File f) {
		try {
			img = ImageIO.read(f);
			rgbArray = new int[img.getHeight() * img.getWidth()];
			pixels = new boolean[img.getHeight() * img.getWidth()];
//			for(int i = 0; i < img.getHeight(); i++) {
//				for(int j = 0; j < img.getWidth(); j++) {
//					img.getRGB(j, i);
//					if(img.getR)
//				}
//					
//			}
			rgbArray = img.getRGB(0, 0, img.getWidth(), img.getHeight(), rgbArray, 0, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < rgbArray.length; i++) {
			if (rgbArray[i] == -16777216)
				pixels[i] = true;
			if (rgbArray[i] == 0)
				pixels[i] = false;
//			else
//				pixels[i] = false;
		}

		for (int i = 0; i < rgbArray.length / 100; i++)
			System.out.println(pixels[i] + ", " + rgbArray[i]);

		return pixels;

	}

	private int angle(TraceSkeleton t) {
		return 0;
	}

	private boolean withinRange(int real, int model) {

		if (real <= model + marginOfError)
			return true;
		if (real >= model - marginOfError)
			return true;

		return false;
	}

}
