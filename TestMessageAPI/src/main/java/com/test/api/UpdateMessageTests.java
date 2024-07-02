package com.test.api;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import junit.framework.Assert;

public class UpdateMessageTests {
	BaseMessageClass msgclass = new BaseMessageClass();
	int idLength = 36;
	From fro = new From("user1");
	To t1 = new To("user2");
	String messageString;
	DataMappingClass dmap; 
	DataMappingClass dmap2; 
	
	@BeforeTest
    public static void setup() {
        RestAssured.baseURI = "http://localhost:3000/api/messages";
    }	
	
	
	/***** Below Test is failing as the functional tests is also failing*****/
	@Test(description="verify API to update message from user1 to user2")
	public void sendMessage(){
		dmap = new DataMappingClass("hello1", fro, t1);
		dmap2 = new DataMappingClass("hello2", fro, t1);
		
		msgclass.createMessage(dmap, RestAssured.baseURI );
		
		/****  Asserting Response code *****/
		Assert.assertEquals(msgclass.getStatusCode(), 200);
		
		String msg = msgclass.getResponseBodyUsingKey("message");
		/****  Asserting key "message" in Response is not null *****/
		Assert.assertNotNull(msg);
		
		String id = msgclass.getResponseBodyUsingKey("id");
		System.out.println(id);
		
		/****  Updating message Response code *****/
		msgclass.updateMessage(dmap2,  RestAssured.baseURI+"/"+id);
		
		/****  Asserting Response code *****/
		Assert.assertEquals(msgclass.getStatusCode(), 200);
		Assert.assertEquals("hello2",msgclass.getResponseBodyUsingKey("message"));		
	}
}
