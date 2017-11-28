package com.tmall.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageUtil {
	public static BufferedImage change2jpg(File f) {
		try {
			java.awt.Image i = Toolkit.getDefaultToolkit().createImage(f.getAbsolutePath());
			PixelGrabber pg = new PixelGrabber(i, 0, 0, -1, -1, true);
			pg.grabPixels();
			int width = pg.getWidth(), height = pg.getHeight();
			final int[] RGB_MASKS = { 0xFF0000, 0xFF00, 0xFF };
			final ColorModel RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);
			DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), pg.getWidth() * pg.getHeight());
			WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
			BufferedImage img = new BufferedImage(RGB_OPAQUE, raster, false, null);
			return img;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void resizeImage(File src, int width, int height, File dest) {
		try {
			Image img = ImageIO.read(src);
			img = resizeImage(img, width, height);
			ImageIO.write((RenderedImage) img, "jpg", dest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage resizeImage(Image srcImg, int width, int height) {
		BufferedImage img = null;
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		img.getGraphics().drawImage(srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
		return img;
	}
}