/*
 *  Copyright (c) 2018 Mann-India Technologies Pvt. Ltd., Noida, India
 *  All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of Mann-India Technologies Pvt. Ltd.
 *  You shall not disclose such confidential information and shall use it only in accordance with 
 *  the terms of the license agreement you entered into with Mann-India Technologies Pvt. Ltd.
 *
 */
package com.mannindia.processors.barcode.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.mannindia.processors.barcode.exceptions.ProcessException;

public class ImageHelper {

	public BufferedImage getBufferedImage(InputStream inputStream) throws ProcessException {
		BufferedImage image = null;
		try {
			image = ImageIO.read(inputStream);
		} catch (IOException ex) {
			throw new ProcessException(ResponseEnum.ERR_STREAM_PROCESSING, ex);
		}
		return image;
	}

	/**
	 * This method is used to get the binary bitmap from image
	 * 
	 * @param image
	 * @return
	 */
	public BinaryBitmap getBinaryBitmap(BufferedImage image) {
		int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
		RGBLuminanceSource source = new RGBLuminanceSource(image.getWidth(), image.getHeight(), pixels);
		HybridBinarizer hybridBinarizer = new HybridBinarizer(source);
		BinaryBitmap binaryBitmap = new BinaryBitmap(hybridBinarizer);
		return binaryBitmap;
	}
}
