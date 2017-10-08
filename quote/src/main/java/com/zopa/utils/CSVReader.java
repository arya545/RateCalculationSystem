package com.zopa.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;

import com.zopa.model.Lender;

public class CSVReader {
	
	final static Logger logger = Logger.getLogger(CSVReader.class);

	/**Method to read the CSV file and store the lender details which satisfies the conditions to a list
	 * @param fileName
	 * @param loanAmount
	 * @param reader
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<Lender> read(String fileName,long loanAmount,PropertyReader reader) 
	{
		
   		CSVParser csvFileParser;
   		List<Lender> lenders=new ArrayList<Lender>();
		try {
		csvFileParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(new FileReader(new File(fileName)));
		for (CSVRecord record:csvFileParser)
		{
			Lender lender=new Lender();
			lender.setRate(Double.parseDouble(record.get(Integer.parseInt(reader.getRateColumn()))));
			lender.setCapAmount(Long.parseLong(record.get(Integer.parseInt(reader.getAvailableAmountColumn()))));
			if (loanAmount<=lender.getCapAmount())
			{
				lenders.add(lender);
			}
		}
		csvFileParser.close();
		} 
		catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		}
		catch (IOException e) {
			logger.error(e.getMessage());
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			logger.error(e.getMessage());
		}
		return lenders;
	}
		
}
