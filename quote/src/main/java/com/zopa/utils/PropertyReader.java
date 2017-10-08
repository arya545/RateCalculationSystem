package com.zopa.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyReader {
	
	final static Logger logger = Logger.getLogger(PropertyReader.class);

	InputStream inputstream;
	Properties props;
	private  String propFileName="quoteDetails.properties";

	private String rateColumn;
	private String availableAmountColumn;
	private String minLoanAmount;
	private String maxLoanAmount;
	private String termsInMonth;
		
	
	/**Method to read and set property
	 * @throws IOException
	 */
	public  void readProperty() throws IOException 
	{
		props=new Properties();
		inputstream=this.getClass().getClassLoader().getResourceAsStream(propFileName);
		try
		{
			if (inputstream!=null)
			{
			props.load(inputstream);
			}
			else
			{
				throw new FileNotFoundException("PropertyFile"+propFileName+ " is not found in the class path");
			}
			setRateColumn(props.getProperty("rate_column"));
			setAvailableAmountColumn(props.getProperty("available_amount_column"));
			setMinLoanAmount(props.getProperty("minimum_requestable_loan"));
			setMaxLoanAmount(props.getProperty("maximum_requestable_loan"));
			setTermsInMonth(props.getProperty("terms_in_months"));
	
		}
		catch (IOException e)
		{
			logger.error(e.getMessage());
		}
		finally
		{
			inputstream.close();
		}
		
	}


	/**
	 * @return the rateColumn
	 */
	public String getRateColumn() {
		return rateColumn;
	}


	/**
	 * @param rateColumn the rateColumn to set
	 */
	public void setRateColumn(String rateColumn) {
		this.rateColumn = rateColumn;
	}


	/**
	 * @return the availableAmountColumn
	 */
	public String getAvailableAmountColumn() {
		return availableAmountColumn;
	}


	/**
	 * @param availableAmountColumn the availableAmountColumn to set
	 */
	public void setAvailableAmountColumn(String availableAmountColumn) {
		this.availableAmountColumn = availableAmountColumn;
	}


	/**
	 * @return the minLoanAmount
	 */
	public String getMinLoanAmount() {
		return minLoanAmount;
	}


	/**
	 * @param minLoanAmount the minLoanAmount to set
	 */
	public void setMinLoanAmount(String minLoanAmount) {
		this.minLoanAmount = minLoanAmount;
	}


	/**
	 * @return the maxLoanAmount
	 */
	public String getMaxLoanAmount() {
		return maxLoanAmount;
	}


	/**
	 * @param maxLoanAmount the maxLoanAmount to set
	 */
	public void setMaxLoanAmount(String maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
	}


	/**
	 * @return the termsInMonth
	 */
	public String getTermsInMonth() {
		return termsInMonth;
	}


	/**
	 * @param termsInMonth the termsInMonth to set
	 */
	public void setTermsInMonth(String termsInMonth) {
		this.termsInMonth = termsInMonth;
	}
}
