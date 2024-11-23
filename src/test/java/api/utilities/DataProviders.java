package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException{
	    // Current project location- System.getProperty("user.dir")---> Give path of excel
		String path=System.getProperty("user.dir")+"//TestData//Userdata.xlsx";
		
		//Excel Utility class and pass path as constructor to access utility class
		XLUtility xl=new XLUtility(path);
		
		//Get total number of rows 
		int rownum=xl.getRowCount("Sheet1");
		
		//Get total number of column
		int colcount=xl.getCellCount("Sheet1", 1);
		
		// Total number of row and column should be same
		String apidata[][]=new String [rownum][colcount];
	    
		
		//Below for loop get the data and pass to the two dimensional array
		
		for(int i=1;i<=rownum;i++) {
			
			for(int j=0;j<colcount;j++) {
				
				apidata[i-1][j]=xl.getCellData("Sheet1", i, j);
				
			}
			
		}
		
		return apidata;
	}
	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException {
		
		String path=System.getProperty("user.dir")+"//TestData//Userdata.xlsx";
		XLUtility xl=new XLUtility(path);
		
		int rownum=xl.getRowCount("Sheet1");
		
		String apidata[]=new String[rownum];
		
		for(int i=1;i<=rownum;i++) {
			apidata[i-1]=xl.getCellData("Sheet1", i, 1);
		}
		
		return apidata;
		
	}
	
	@DataProvider(name="Data1")
	public String[][] updateData() throws IOException{
	    // Current project location- System.getProperty("user.dir")---> Give path of excel
		String path=System.getProperty("user.dir")+"//TestData//Userdata.xlsx";
		
		//Excel Utility class and pass path as constructor to access utility class
		XLUtility xl=new XLUtility(path);
		
		//Get total number of rows 
		int rownum=xl.getRowCount("Sheet1");
		
		//Get total number of column
		int colcount=xl.getCellCount("Sheet1", 1);
		
		// Total number of row and column should be same
		String apidata[][]=new String [rownum][colcount];
	    
		
		//Below for loop get the data and pass to the two dimensional array
		
		for(int i=1;i<=rownum;i++) {
			
			for(int j=0;j<colcount;j++) {
				
				apidata[i-1][j]=xl.getCellData("Sheet2", i, j);
				
			}
			
		}
		
		return apidata;
	}

}
