package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		
		faker=new Faker();
		userPayload=new User();
		
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
	    userPayload.setPhone(faker.phoneNumber().cellPhone());
	    
	    //logs
	    logger=LogManager.getLogger(this.getClass());
	    
	    
		
	}
	
	//We are not returning anything so user void returnType
	
	@Test(priority = 1, enabled=true)
	public void testPostUser(){
	
		// Directly we can call createUser method through class name because create user is a static method	
	logger.info("************* Creating user *********************************");
	Response response=UserEndPoints2.createUser(userPayload);
	response.then().log().all();
	
	Assert.assertEquals(response.getStatusCode(), 200);
	
	logger.info("****************** User is created ***********************");
			
	}
	
	@Test(priority = 2, enabled=true)
	public void testGetUserByName(){
	    
		//using this keyword we are calling getusername from userPayload
	logger.info("****************** Reading User Info  ***********************");
			
	Response response=UserEndPoints2.readUser(this.userPayload.getUsername());
	response.then().log().all();
	
	Assert.assertEquals(response.getStatusCode(), 200);
	
	logger.info("****************** User info is displayed ***********************");
	
	
	}
	
	@Test(priority = 3, enabled=true)
	public void testUpdateUserByName() {
		
		logger.info("****************** Updating User ***********************");
		
		
		//Update data by using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("****************** User is Updated ***********************");
		
		
		//Checking data after update
		Response responseAfterUpdate=UserEndPoints2.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().body();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
	}
	
	@Test(priority = 4, enabled=true)
	public void testDeleteUserByName(){
		
		logger.info("****************** Deleting User  ***********************");
		
		//using this keyword we are calling getusername from userPayload
		Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());
		//response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);	
		
		logger.info("****************** User deleted ***********************");
		
		
	}
	
	

}
