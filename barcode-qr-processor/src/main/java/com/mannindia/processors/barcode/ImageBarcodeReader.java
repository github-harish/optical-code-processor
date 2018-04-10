/*
 *  Copyright (c) 2018 Mann-India Technologies Pvt. Ltd., Noida, India
 *  All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of Mann-India Technologies Pvt. Ltd.
 *  You shall not disclose such confidential information and shall use it only in accordance with 
 *  the terms of the license agreement you entered into with Mann-India Technologies Pvt. Ltd.
 *
 */
package com.mannindia.processors.barcode;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.mannindia.processors.barcode.exceptions.ProcessException;
import com.mannindia.processors.barcode.utils.BarcodeHelper;
import com.mannindia.processors.barcode.utils.ImageHelper;
import com.mannindia.processors.barcode.utils.ResponseEnum;

/**
 * This class is used to decode the barcode/QR code from a Image file
 * 
 * @since 1.0
 */
public class ImageBarcodeReader {

	/**
	 * This method is used to decode the Image
	 * 
	 * @param inputStream
	 *            the image stream
	 * @return the Result
	 * @throws ProcessException
	 *             the exception
	 */
	public Result decodeImage(InputStream inputStream) throws ProcessException {
		if (inputStream == null) {
			throw new ProcessException(ResponseEnum.ERR_FILE_NOT_FOUND);
		}
		return this.decodeImage(inputStream, null);
	}

	/**
	 * This method is used to decode the Image for a specific Bar code format
	 * 
	 * @param inputStream
	 *            the Image stream
	 * @param barcodeFormat
	 *            the format which needs be decoded
	 * @return the Result
	 * @throws ProcessException
	 *             the exception
	 */
	public Result decodeImage(InputStream inputStream, BarcodeFormat barcodeFormat) throws ProcessException {
		if (inputStream == null) {
			throw new ProcessException(ResponseEnum.ERR_FILE_NOT_FOUND);
		}
		EnumSet<BarcodeFormat> barcodeEnumSet;
		if (barcodeFormat == null) {
			barcodeEnumSet = EnumSet.allOf(BarcodeFormat.class);
		} else {
			barcodeEnumSet = EnumSet.of(barcodeFormat);
		}
		return this.decodeImage(inputStream, barcodeEnumSet, Boolean.TRUE, Boolean.TRUE);
	}

	/**
	 * @param inputStream
	 *            the Image stream
	 * @param tryHarderToFindBarcode
	 *            flag if spend more time to try to find a barcode; optimize for
	 *            accuracy, not speed.
	 * @param isPureMonochromeImage
	 *            flag if stream is having pure monochrome image of a barcode
	 * @return the Result
	 * @throws ProcessException
	 *             the exception
	 */
	public Result decodeImage(InputStream inputStream, boolean tryHarderToFindBarcode, boolean isPureMonochromeImage)
			throws ProcessException {
		if (inputStream == null) {
			throw new ProcessException(ResponseEnum.ERR_FILE_NOT_FOUND);
		}
		return this.decodeImage(inputStream, EnumSet.allOf(BarcodeFormat.class), tryHarderToFindBarcode,
				isPureMonochromeImage);
	}

	/**
	 * @param inputStream
	 *            the Image stream
	 * @param barcodeFormat
	 *            the format which needs be decoded
	 * @param tryHarderToFindBarcode
	 *            flag if spend more time to try to find a barcode; optimize for
	 *            accuracy, not speed.
	 * @param isPureMonochromeImage
	 *            flag if stream is having pure monochrome image of a barcode
	 * @return the Result
	 * @throws ProcessException
	 *             the exception
	 */
	public Result decodeImage(InputStream inputStream, BarcodeFormat barcodeFormat, boolean tryHarderToFindBarcode,
			boolean isPureMonochromeImage) throws ProcessException {
		return this.decodeImage(inputStream, EnumSet.of(barcodeFormat), tryHarderToFindBarcode, isPureMonochromeImage);
	}

	/**
	 * @param inputStream
	 *            the Image stream
	 * @param barcodeEnumSet
	 *            the format which needs be decoded
	 * @param tryHarderToFindBarcode
	 *            flag if spend more time to try to find a barcode; optimize for
	 *            accuracy, not speed.
	 * @param isPureMonochromeImage
	 *            flag if stream is having pure monochrome image of a barcode
	 * @return the Result
	 * @throws ProcessException
	 */
	private Result decodeImage(InputStream inputStream, EnumSet<BarcodeFormat> barcodeEnumSet,
			boolean tryHarderToFindBarcode, boolean isPureMonochromeImage) throws ProcessException {
		if (inputStream == null) {
			throw new ProcessException(ResponseEnum.ERR_FILE_NOT_FOUND);
		}
		BarcodeHelper barcodeHelper = new BarcodeHelper();
		Map<DecodeHintType, Object> tmpHintsMap = barcodeHelper.getHintsMap(barcodeEnumSet, tryHarderToFindBarcode,
				isPureMonochromeImage);

		ImageHelper imageHelper = new ImageHelper();
		BufferedImage image = imageHelper.getBufferedImage(inputStream);
		if (image == null) {
			throw new ProcessException(ResponseEnum.ERR_STREAM_PROCESSING);
		}

		BinaryBitmap bitmap = imageHelper.getBinaryBitmap(image);

		// QRCodeMultiReader multiReader = new QRCodeMultiReader();
		MultiFormatReader multiReader = new MultiFormatReader();

		Result result = null;
		try {
			result = multiReader.decode(bitmap, tmpHintsMap);
		} catch (NotFoundException ex) {
			throw new ProcessException(ResponseEnum.ERR_CODE_PROCESSING, ex);
		}

		return result;
	}

}