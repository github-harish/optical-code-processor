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

/**
 * 
 * @since 1.0
 */
public enum ResponseEnum {

	ERR_SYSTEM_EXCEPTION("SVC9999", "System Exception"), ERR_FILE_NOT_FOUND("SVC1001",
			"File not Found"), ERR_STREAM_PROCESSING("SVC1002",
					"Error while processing Stream"), ERR_CODE_PROCESSING("SVC1003", "Error while processing Code");

	private String responseCode;
	private String responseMessage;

	private ResponseEnum(String responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public String getMessage() {
		return "Response Code:" + responseCode + ",Response Message Code:" + responseMessage;
	}

}
