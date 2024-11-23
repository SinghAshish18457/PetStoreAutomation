package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		
		
	    //logs
	    logger=LogManager.getLogger(this.getClass());
	    
	    
		
	}
	
		
	/*which data provider is giving data to test method same name need to mention so here "Data" is the data provider.
	/and where data provider is available that also need to specify if data provider is there in same class then this is enough but
	/if data provider is available in different class or package that need to specify by using "dataProviderClass"
	/Here dataProvider="Data" is giving the data but we need to received also through 
	/parameter then we have to create multiple parameter that too same order whatever available in excel sheet.*/
	
	@Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void testPostuser(String userID,String userName,String fName,String lName,String useremail,String pwd,String ph) {
		
		User userPayload=new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
	    userPayload.setPhone(ph);
	    
	 logger.info("************* Creating user *********************************");
		
	 // Directly we can call createUser method through class name because create user is a static method	
	Response response=UserEndPoints.createUser(userPayload);
	//response.then().log().all();
	
	Assert.assertEquals(response.getStatusCode(), 200); 
	logger.info("****************** User is created ***********************");
		
	}
	
	@Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void testGetUserByName(String userName) {
		
	logger.info("****************** Reading User Info  ***********************");	
	Response response=UserEndPoints.readUser(userName);
	 response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("****************** User info is displayed ***********************");
	
	}
	
	
	  @Test(priority = 3,dataProvider = "Data1",dataProviderClass = DataProviders.class)
	  public void testPutuser(String userID,String userName,String fName,String lName,String useremail,String pwd,String ph) {
	  
	  User userPayload=new User(); 
	  //Faker faker=new Faker();
	  userPayload.setId(Integer.parseInt(userID));
	  userPayload.setEmail(useremail);
	  userPayload.setPassword(pwd);
	  
	  // Directly we can call createUser method through class name because create
	 // user is a static method Response
	  
	  logger.info("****************** Updating User ***********************");
	  Response response=UserEndPoints.updateUser(userName,userPayload);
	  response.then().log().all();
	  
	  Assert.assertEquals(response.getStatusCode(), 200);
	  logger.info("****************** User is Updated ***********************");
	  
	  } 
	  
	  
	  @Test(priority = 4,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	  
	  public void testDeleteUserByName(String userName) {
		  
	  logger.info("****************** Deleting User  ***********************");
	  Response response=UserEndPoints.deleteUser(userName);
	  Assert.assertEquals(response.getStatusCode(), 200);
	  logger.info("****************** User deleted ***********************");
	  }
	 
	
	

}
