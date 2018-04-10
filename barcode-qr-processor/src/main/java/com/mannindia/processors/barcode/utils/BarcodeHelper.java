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

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;

public class BarcodeHelper {

	public Map<DecodeHintType, Object> getHintsMap(EnumSet<BarcodeFormat> barcodeEnumSet,
			boolean tryHarderToFindBarcode, boolean isPureMonochromeImage) {
		Map<DecodeHintType, Object> hintsMap = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
		hintsMap.put(DecodeHintType.TRY_HARDER, tryHarderToFindBarcode);
		hintsMap.put(DecodeHintType.POSSIBLE_FORMATS, barcodeEnumSet);
		hintsMap.put(DecodeHintType.PURE_BARCODE, isPureMonochromeImage);
		return hintsMap;
	}
}
