/*
 *  Copyright (c) 2018 Mann-India Technologies Pvt. Ltd., Noida, India
 *  All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of Mann-India Technologies Pvt. Ltd.
 *  You shall not disclose such confidential information and shall use it only in accordance with 
 *  the terms of the license agreement you entered into with Mann-India Technologies Pvt. Ltd.
 *
 */
package com.mannindia.processors.ocr.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.mannindia.processors.ocr.exceptions.ProcessException;

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

	
}
