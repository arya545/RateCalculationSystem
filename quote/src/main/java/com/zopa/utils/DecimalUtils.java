package com.zopa.utils;

import java.math.BigDecimal;

public class DecimalUtils {
	
	/**Method to round the amount
	 * @param value
	 * @param numOfDigits
	 * @return double
	 */
	public static double roundDecimals(double value,int numOfDigits)
	{
		BigDecimal bigDecimal=new BigDecimal(value);
		bigDecimal = bigDecimal.setScale(numOfDigits,BigDecimal.ROUND_HALF_UP);
		return bigDecimal.doubleValue();
	
	}
}
