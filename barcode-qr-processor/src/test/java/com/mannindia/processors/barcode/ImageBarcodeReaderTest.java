/*
 *  Copyright (c) 2017 Mann-India Technologies Pvt. Ltd., Noida, India
 *  All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of Mann-India Technologies Pvt. Ltd.
 *  You shall not disclose such confidential information and shall use it only in accordance with 
 *  the terms of the license agreement you entered into with Mann-India Technologies Pvt. Ltd.
 *
 */
package com.mannindia.processors.barcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.mannindia.processors.barcode.exceptions.ProcessException;

/**
 * @author Harish Chandra
 *
 * @since 1.0
 */
public class ImageBarcodeReaderTest {

	InputStream inputStream = null;

	@Before
	public void initialize() {
		inputStream = this.getClass().getResourceAsStream("/images/pdf417.jpeg");
	}

	@Test
	public void testDecodeImage() throws FileNotFoundException, ProcessException {
		Result result = new ImageBarcodeReader().decodeImage(inputStream);

		assertNotNull(result);
		assertEquals(BarcodeFormat.PDF_417, result.getBarcodeFormat());
		assertNotNull(result.getText());
	}

	@Test
	public void testDecodeImageWithBarCodeFormat() throws FileNotFoundException, ProcessException {
		Result result = new ImageBarcodeReader().decodeImage(inputStream, BarcodeFormat.PDF_417);

		assertNotNull(result);
		assertEquals(BarcodeFormat.PDF_417, result.getBarcodeFormat());
		assertNotNull(result.getText());
	}

	@Test
	public void testDecodeImageWithParams() throws FileNotFoundException, ProcessException {
		Result result = new ImageBarcodeReader().decodeImage(inputStream, Boolean.TRUE, Boolean.TRUE);

		assertNotNull(result);
		assertEquals(BarcodeFormat.PDF_417, result.getBarcodeFormat());
		assertNotNull(result.getText());
	}

	@Test
	public void testDecodeImageWithBarCodeFormatAndParams() throws FileNotFoundException, ProcessException {
		Result result = new ImageBarcodeReader().decodeImage(inputStream, BarcodeFormat.PDF_417, Boolean.TRUE,
				Boolean.TRUE);

		assertNotNull(result);
		assertEquals(BarcodeFormat.PDF_417, result.getBarcodeFormat());
		assertNotNull(result.getText());
	}

	@After
	public void deleteOutputFile() {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
