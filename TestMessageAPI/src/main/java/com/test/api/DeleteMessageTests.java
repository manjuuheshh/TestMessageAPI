package com.test.api;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import junit.framework.Assert;

public class DeleteMessageTests {
	BaseMessageClass msgclass = new BaseMessageClass();
	DataMappingClass dmap;
	From fro = new From("user1");
	To t1 = new To("user2");
	
	@BeforeTest
    public static void setup() {
        RestAssured.baseURI = "http://localhost:3000/api/messages";
    }
	
	@Test(priority=1,description="verify that API allows successful deletion of the message with valid message id")
	public void verify_Deletion_of_message_is_success() {
		dmap = new DataMappingClass("deleting message", fro, t1);
		
		//creating message
		String messageId = msgclass.createMessage(dmap,RestAssured.baseURI );
		msgclass.getMessage(""); 
		String messageEndpoint = RestAssured.basePath+messageId;
		String ids = msgclass.getResponseBodyUsingKey("id");
		String idArray[]=  ids.split(", ");
		int NumberOfMessages = idArray.length;		
		
		//deleting message
		msgclass.deleteMessage(messageEndpoint);
		Assert.assertEquals(msgclass.getStatusCode(), 204);
		Assert.assertEquals(msgclass.getStatusText(), "HTTP/1.1 204 No Content");		
		msgclass.getMessage("");
		String ids2 = msgclass.getResponseBodyUsingKey("id");
		String idArray2[]=  ids2.split(", ");
		int NumberOfMessagesAfterDeletion = idArray2.length;
		
		//asserting number of messages before and after deletion operation is not same
		Assert.assertNotSame(NumberOfMessages, NumberOfMessagesAfterDeletion);		
		
		//assert api returns 404 on retrieving deleted message id   
		Assert.assertEquals(404,msgclass.getStatusCode());
	}
	
	@Test(priority=2,description="verify that API returns 404 error code while deletion of the message with invalid message id")
	public void verify_API_returns_404_error_code_with_invalid_message_id() {
		//message id does not exist
		msgclass.deleteMessage(RestAssured.baseURI+"9145f7ef-ab21-4ba7-9462-3cac1a28f46b");  
		Assert.assertEquals(msgclass.getStatusCode(), 404);
		Assert.assertEquals(msgclass.getStatusText(), "HTTP/1.1 404 Not Found");
	}
	
	
}
