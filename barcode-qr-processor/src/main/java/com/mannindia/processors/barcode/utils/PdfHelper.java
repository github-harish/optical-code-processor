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

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import com.mannindia.processors.barcode.exceptions.ProcessException;

public class PdfHelper {

	/**
	 * This method is used to get the BufferedImage of PDF document
	 * 
	 * @param fileInputStream
	 * @return
	 * @throws IOException
	 */
	public BufferedImage getBufferedImage(InputStream fileInputStream) throws ProcessException {

		PDDocument document = null;
		try {
			document = PDDocument.load(fileInputStream);
			PDPage firstPage = (PDPage) document.getDocumentCatalog().getAllPages().get(0);
			return firstPage.convertToImage();
		} catch (IOException ex) {
			throw new ProcessException(ResponseEnum.ERR_STREAM_PROCESSING, ex);
		} catch (Exception ex) {
			throw new ProcessException(ResponseEnum.ERR_SYSTEM_EXCEPTION, ex);
		} finally {
			if (document != null) {
				try {
					document.close();
				} catch (IOException ex) {
					throw new ProcessException(ResponseEnum.ERR_SYSTEM_EXCEPTION, ex);
				}
			}
		}
	}

}
