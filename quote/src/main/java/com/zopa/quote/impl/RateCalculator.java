package com.zopa.quote.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.zopa.model.Lender;
import com.zopa.quote.IRateCalculator;
import com.zopa.utils.CSVReader;
import com.zopa.utils.DecimalUtils;
import com.zopa.utils.CurrencyUtils;
import com.zopa.utils.PropertyReader;

public class RateCalculator implements IRateCalculator {
	
	final static Logger logger = Logger.getLogger(RateCalculator.class);
	
	private String fileName;
	private long loanAmount;
	private double minRate;
	
	private PropertyReader reader;
	private Locale locale=Locale.UK;
	
	/**Default Constructor
	 * 
	 */
	public RateCalculator()
	{
		
	}
	
	/**Parameterised Constructor
	 * @param fileName
	 * @param loanAmount
	 * @param reader
	 */
	public RateCalculator(String fileName,long loanAmount,PropertyReader reader) 
	{
		this.fileName=fileName;
		this.loanAmount=loanAmount;
		this.reader=reader;
	}
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the loanAmount
	 */
	public long getLoanAmount() {
		return loanAmount;
	}

	/**
	 * @param loanAmount the loanAmount to set
	 */
	public void setLoanAmount(long loanAmount) {
		this.loanAmount = loanAmount;
	}

	/**
	 * @return the minRate
	 */
	public double getMinRate() {
		return minRate;
	}

	/**
	 * @param minRate the minRate to set
	 */
	public void setMinRate(double minRate) {
		this.minRate = minRate;
	}

	
	/* (non-Javadoc)
	 * @see com.zopa.quote.IRateCalculator#calculate()
	 */
	public void calculate() {
		try {
			setMinRate(findMinRate());
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		if (minRate!=-1)
		{
		System.out.println("Requested Amount : "+CurrencyUtils.displayCurrency(locale)+getLoanAmount());
		System.out.println("Rate : "+DecimalUtils.roundDecimals(minRate*100,1)+"%");
		calculateMonthlyPayment(loanAmount,minRate,Integer.parseInt(reader.getTermsInMonth()));
		}
		else
		{
			System.out.println("Not able to provide a quote now");
		}
		

	}
	
	/**Method to find minimum Rate of interest from CSV file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public  double findMinRate() throws FileNotFoundException, IOException
	{
		
		reader.readProperty();
		List<Lender> lenders=CSVReader.read(fileName, loanAmount ,reader);
		if (lenders.isEmpty())
		{
		return -1;
		}
		else
		{
		Collections.sort(lenders, Lender.rateComparator);
		return lenders.get(0).getRate();
		}
		
	}
	
	/**Method to calculate MonthlyPayment
	 * @param requestedAmnt
	 * @param minRate
	 * @param termsinMonths
	 * @return
	 */
	public  double calculateMonthlyPayment(long requestedAmnt,double minRate,int termsinMonths)
	{
		//Monthly interest rate will be yearlyRate/12
		double monthlyRate=minRate/12.0;
		double monthlyPayment=(requestedAmnt*monthlyRate)/(1-Math.pow(1+monthlyRate, -termsinMonths));
		System.out.println("Monthly Repayment : " +CurrencyUtils.displayCurrency(locale)+DecimalUtils.roundDecimals(monthlyPayment, 2));
		calculateTotalPayment(monthlyPayment, termsinMonths);
		return monthlyPayment;
		
	}
	
	
	/**Method to calculate TotalPayment
	 * @param monthlyPayment
	 * @param termsinMonths
	 * @return
	 */
	public  double calculateTotalPayment(double monthlyPayment,int termsinMonths)
	{
		//Total payment will be Monthly Payment multiplied by totalTermsinMonths
		double totalPayment=monthlyPayment*termsinMonths;
		System.out.println("Total Repayment : " +CurrencyUtils.displayCurrency(locale)+DecimalUtils.roundDecimals(totalPayment,2));
		return totalPayment;
	}

}
