/*
 *  Copyright (c) 2017 Mann-India Technologies Pvt. Ltd., Noida, India
 *  All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of Mann-India Technologies Pvt. Ltd.
 *  You shall not disclose such confidential information and shall use it only in accordance with 
 *  the terms of the license agreement you entered into with Mann-India Technologies Pvt. Ltd.
 *
 */
package com.mannindia.processors.ocr;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mannindia.processors.ocr.exceptions.ProcessException;

/**
 * @author Harish Chandra
 *
 * @since 1.0
 */
public class ImageOCRReaderTest {

	InputStream inputStream = null;

	@Before
	public void initialize() {
		inputStream = this.getClass().getResourceAsStream("/images/text-ocr.jpeg");
	}

	@Test
	public void testDecodeImage() throws FileNotFoundException, ProcessException {
		String result = new ImageOCRReader().decodeImage(inputStream);

		assertNotNull(result);
	}

	
	@Test
	public void testDecodeImageWithDimension() throws FileNotFoundException, ProcessException {
		String result = new ImageOCRReader().decodeImage(inputStream, 0, 0, 241,209);
		assertNotNull(result);
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