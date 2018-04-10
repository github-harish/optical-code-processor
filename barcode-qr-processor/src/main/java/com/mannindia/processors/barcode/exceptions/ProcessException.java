/*
 *  Copyright (c) 2018 Mann-India Technologies Pvt. Ltd., Noida, India
 *  All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of Mann-India Technologies Pvt. Ltd.
 *  You shall not disclose such confidential information and shall use it only in accordance with 
 *  the terms of the license agreement you entered into with Mann-India Technologies Pvt. Ltd.
 *
 */
package com.mannindia.processors.barcode.exceptions;

import com.mannindia.processors.barcode.utils.ResponseEnum;

public class ProcessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;

	public ProcessException() {
		super();
		errorCode = "Unknown";
		errorMessage = "Unknown";
	}

	public ProcessException(ResponseEnum response) {
		super(response.getMessage());
		this.errorCode = response.getResponseCode();
		this.errorMessage = response.getResponseMessage();
	}

	public ProcessException(ResponseEnum response, Throwable e) {
		super(response.getMessage(), e);
		this.errorCode = response.getResponseCode();
		this.errorMessage = response.getResponseMessage();
	}

	public ProcessException(String errorCode, String errorMessage) {
		super("Response Code:" + errorCode + ",Response Message Code:" + errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public ProcessException(String errorCode, String errorMessage, Throwable e) {
		super("Response Code:" + errorCode + ",Response Message Code:" + errorMessage, e);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
