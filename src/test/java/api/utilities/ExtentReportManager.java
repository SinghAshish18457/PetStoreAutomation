package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
/*
   ITestListener is predefined interface 
  1)  Once test method executed all report should be there.To generate all report ITestListener is using.  
 */
public class ExtentReportManager implements ITestListener {
	
	/* Below are predefined class which is coming from extent report
	 * 1) ExtentSparkReporter is responsible for UI of the report.
	 * 2) ExtentReports is responsible for common data like- environment information,user info,operating info,
	 *    project name,module name
	 * 3)ExtentTest is responsible to creating entry in report.(Like if any test got fail)   
	 * 
	 */
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testcontext) {
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.SS").format(new Date());//time stamp
		repName="Test-Report-"+timeStamp+".html";
		
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);//specify location of the report
		
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");//Title of report
		sparkReporter.config().setReportName("Pet Store User API");//name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pet Store User API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Ashish");
		
	}
	
	     public void onTestSuccess(ITestResult result) {
	    	 
	    	 test=extent.createTest(result.getName());
	    	 test.assignCategory(result.getMethod().getGroups());
	    	 test.createNode(result.getName());
	    	 test.log(Status.PASS, "Test Passed");
	    	 
	     }
	     
        public void onTestFailure(ITestResult result) {
	    	 
	    	 test=extent.createTest(result.getName());
	    	 test.assignCategory(result.getMethod().getGroups());
	    	 test.createNode(result.getName());
	    	 test.log(Status.FAIL, "Test Failed");
	    	 test.log(Status.FAIL, result.getThrowable().getMessage());
	    	 
	     }
        public void onTestSkipped(ITestResult result) {
	    	 
	    	 test=extent.createTest(result.getName());
	    	 test.assignCategory(result.getMethod().getGroups());
	    	 test.createNode(result.getName());
	    	 test.log(Status.SKIP, "Test Skipped");
	    	 test.log(Status.SKIP, result.getThrowable().getMessage());
	    	 
	     }
        public void onFinish(ITestContext testcontext) {
        	extent.flush();
        }

	     
	     

}
