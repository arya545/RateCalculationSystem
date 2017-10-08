/**
 * 
 */
package com.zopa.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.zopa.main.App;

public class AppTest {
	
	@Test
	public void returnNegativeExitStatusForLoanAmountLessThanLimit() throws IOException {
		String fileName="E://Ganesh//Zopa Questions//Market_Data_for_Exercise.csv";
		long loanAmount=100;
		int expectedValue=-1;
		assertEquals(expectedValue,App.getBestQuote(fileName, loanAmount));
	}
	
	@Test
	public void returnNegativeExitStatusForLoanAmountGreaterThanLimit() throws IOException {
		String fileName="E://Ganesh//Zopa Questions//Market_Data_for_Exercise.csv";
		long loanAmount=20000;
		int expectedValue=-1;
		assertEquals(expectedValue,App.getBestQuote(fileName, loanAmount));
	}
	
	@Test
	public void returnPositiveExitStatusForLoanAmountWithinLimit() throws IOException {
		String fileName="E://Ganesh//Zopa Questions//Market_Data_for_Exercise.csv";
		long loanAmount=3000;
		int expectedValue=0;
		assertEquals(expectedValue,App.getBestQuote(fileName, loanAmount));
	}
	
	@Test
	public void returnNegativeExitStatusForLoanAmountNonMultipleOfHundred() throws IOException {
		String fileName="E://Ganesh//Zopa Questions//Market_Data_for_Exercise.csv";
		long loanAmount=1050;
		int expectedValue=-1;
		assertEquals(expectedValue,App.getBestQuote(fileName, loanAmount));
	}
}
