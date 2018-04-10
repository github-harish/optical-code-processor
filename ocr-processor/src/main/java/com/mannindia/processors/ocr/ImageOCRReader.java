/*
 *  Copyright (c) 2018 Mann-India Technologies Pvt. Ltd., Noida, India
 *  All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of Mann-India Technologies Pvt. Ltd.
 *  You shall not disclose such confidential information and shall use it only in accordance with 
 *  the terms of the license agreement you entered into with Mann-India Technologies Pvt. Ltd.
 *
 */
package com.mannindia.processors.ocr;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import com.mannindia.processors.ocr.exceptions.ProcessException;
import com.mannindia.processors.ocr.utils.ImageHelper;
import com.mannindia.processors.ocr.utils.ResponseEnum;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

/**
 * @author Harish Chandra
 *
 * @since 1.0
 */
public class ImageOCRReader {

	/**
	 * This method is used to read the OCR text in the Image
	 * 
	 * @param inputStream
	 *            the image stream
	 * @return the Result
	 * @throws ProcessException
	 *             the exception
	 */
	public String decodeImage(InputStream inputStream) throws ProcessException {
		return this.decodeImage(inputStream, null);
	}

	/**
	 * This method is used to read the OCR text in the Image from a specific
	 * dimension
	 * 
	 * @param inputStream
	 *            the input stream
	 * @param upperLeftX
	 *            the X coordinate of the upper left corner of the image
	 * @param upperLeftY
	 *            the Y coordinate of the upper left corner of the image
	 * @param width
	 *            the width of the image to read
	 * @param height
	 *            the height of the image to read
	 * @return The Result
	 * @throws ProcessException
	 *             the exception
	 */
	public String decodeImage(InputStream inputStream, int upperLeftX, int upperLeftY, int width, int height)
			throws ProcessException {
		if (upperLeftX < 0 || upperLeftY < 0 || width <= 0 || height <= 0) {
			throw new ProcessException(ResponseEnum.ERR_IMAGE_DIMENTION_INVALID);
		}
		Rectangle rect = new Rectangle(upperLeftX, upperLeftY, width, height);
		return this.decodeImage(inputStream, rect);
	}

	/**
	 * This method is used to read the OCR text in the Image from a specific
	 * dimension
	 * 
	 * @param inputStream
	 *            the input stream
	 * @param rect
	 *            the dimension of the image to read - {@code Rectangle}
	 * @return the Result
	 * @throws ProcessException
	 *             the exception
	 */
	private String decodeImage(InputStream inputStream, Rectangle rect) throws ProcessException {
		String result = null;

		if (inputStream == null) {
			throw new ProcessException(ResponseEnum.ERR_FILE_NOT_FOUND);
		}

		ImageHelper imageHelper = new ImageHelper();
		BufferedImage bufferedImage = imageHelper.getBufferedImage(inputStream);
		if (bufferedImage == null) {
			throw new ProcessException(ResponseEnum.ERR_STREAM_PROCESSING);
		}

		if (rect != null && ((rect.getX() + rect.getWidth()) > bufferedImage.getWidth()
				|| (rect.getY() + rect.getHeight()) > bufferedImage.getHeight())) {
			throw new ProcessException(ResponseEnum.ERR_IMAGE_DIMENTION_INVALID);
		}

		ITesseract instance = new Tesseract();

		// let the resource 'tessdata' be extracted from classpath
		File tessDataFolder = LoadLibs.extractTessResources("tessdata");

		// Set the tessdata path
		instance.setDatapath(tessDataFolder.getAbsolutePath());
		try {
			result = instance.doOCR(bufferedImage, rect);
		} catch (TesseractException ex) {
			throw new ProcessException(ResponseEnum.ERR_CODE_PROCESSING, ex);
		} catch (Exception ex) {
			throw new ProcessException(ResponseEnum.ERR_CODE_PROCESSING, ex);
		}
		return result;
	}

}