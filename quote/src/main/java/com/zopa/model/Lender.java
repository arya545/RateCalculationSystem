package com.zopa.model;

import java.util.Comparator;

public class Lender {
	
	private double rate;
	private long capAmount;
	
	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}
	/**
	 * @return the capAmount
	 */
	public long getCapAmount() {
		return capAmount;
	}
	/**
	 * @param capAmount the capAmount to set
	 */
	public void setCapAmount(long capAmount) {
		this.capAmount = capAmount;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Lender [rate=" + rate + ", capAmount=" + capAmount + "]";
	}
	
	
	/**Method to compare rate in ascending order.
	 * 
	 */
	public static Comparator<Lender> rateComparator=new Comparator<Lender>(){
		public int compare(Lender l1,Lender l2)
		{
			return  (int) ((l1.getRate()*1000)-(l2.getRate()*1000));
		}
	};
	
}
