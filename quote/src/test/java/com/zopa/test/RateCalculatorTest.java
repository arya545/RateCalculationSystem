package com.zopa.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.zopa.quote.impl.RateCalculator;
import com.zopa.utils.DecimalUtils;

public class RateCalculatorTest {

	private RateCalculator calculator;
	
	@Before
	public void setUp() throws Exception {
		this.calculator=new RateCalculator();
	}

	@Test
	public void returndouble_calculateMonthlyPayment() {
		double expectedValue=27.86;
		double actualValue=DecimalUtils.roundDecimals(this.calculator.calculateMonthlyPayment(1000, 0.002, 36),2);
		assertEquals(expectedValue, actualValue, 0);
		
		double expectedValue1=90.59;
		double actualValue1=DecimalUtils.roundDecimals(this.calculator.calculateMonthlyPayment(3000, 0.055, 36),2);
		assertEquals(expectedValue1, actualValue1, 0);
		
	}
	
	@Test
	public void returndouble_calculateTotalPayment()
	{
		double expectedValue=1003.09;
		double actualValue=DecimalUtils.roundDecimals(this.calculator.calculateTotalPayment(this.calculator.calculateMonthlyPayment(1000, 0.002, 36), 36),2);
		assertEquals(expectedValue, actualValue, 0);
		
		double expectedValue1=3261.16;
		double actualValue1=DecimalUtils.roundDecimals(this.calculator.calculateTotalPayment(this.calculator.calculateMonthlyPayment(3000, 0.055, 36), 36),2);
		assertEquals(expectedValue1, actualValue1, 0);
	}

}
