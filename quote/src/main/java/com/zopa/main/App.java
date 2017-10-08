package com.zopa.main;

import java.io.IOException;

import com.zopa.quote.impl.RateCalculator;
import com.zopa.utils.PropertyReader;

public class App {
	
	public static void main(String[] args) throws IOException {
		
		 //File Name
		 String fileName=args[0];
		 //Loan Amount
		 long loanAmount=Long.parseLong(args[1]);
		 
		 /*String fileName="E://Ganesh//Zopa Questions//Market_Data_for_Exercise.csv";
		 long loanAmount=Long.parseLong("3000");*/
		 getBestQuote(fileName,loanAmount);
	}
	
	
	/**Method to getBestQuote
	 * @param fileName
	 * @param loanAmount
	 * @return exitStatus
	 * @throws IOException
	 */
	public static int getBestQuote(String fileName,long loanAmount) throws IOException
	{
		 int exitStatus=-1;
		 PropertyReader reader=new PropertyReader();
		 reader.readProperty();
		 if (loanAmount>=Long.parseLong(reader.getMinLoanAmount()) && loanAmount<=Long.parseLong(reader.getMaxLoanAmount()))
		 {
			 if (loanAmount%100==0)
			 {
			 exitStatus=0;
		     new RateCalculator(fileName,loanAmount,reader).calculate();
			 }
			 else
			 {
			 System.out.println("Loan Amount must be a denominations of 100 ");
			 }
		 }
		 else
		 {
			 System.out.println("Please enter loan amount between "+reader.getMinLoanAmount()+" and "+reader.getMaxLoanAmount()+" inclusive");
		 }
		 return exitStatus;
	}
}
