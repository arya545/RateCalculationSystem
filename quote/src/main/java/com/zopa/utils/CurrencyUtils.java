package com.zopa.utils;

import java.util.Currency;
import java.util.Locale;

public class CurrencyUtils {
	
	/**Method to display the currency symbol based on Locale.
	 * @param currentLocale
	 * @return String
	 */
	public static String displayCurrency(Locale currentLocale)
	{
		return Currency.getInstance(currentLocale).getSymbol(currentLocale);
	}
}
