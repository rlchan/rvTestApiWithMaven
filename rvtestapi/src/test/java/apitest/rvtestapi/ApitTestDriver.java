package apitest.rvtestapi;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.apache.http.HttpStatus;


import java.io.IOException;

	public class ApitTestDriver {
		
		//Please note below variables can be passed through TestNG paramters--skipping that step for now in TODO

		String uri="http://ec2-54-174-213-136.compute-1.amazonaws.com:3000/posts";
		String expectedMimeType="application/json";
		int statusCode;
		String ctType;
		
		//variable to hold number of expected Json objects from Api posts
		int exepectedJsonResObjs=89;
		
		
	    @Test(description="Get API Operation Test")
	    public void testGet() throws IOException {

	      //performing GetTest operations       
	        GetApiTest adapter =new GetApiTest();
			adapter.setContentType(expectedMimeType);
	        adapter.setEndPoint(uri);
	        //adapter.setMethod("Get");
	        System.out.println("\nSending 'GET' request to URL : " + uri);
	        statusCode=adapter.getStatusCode();
    		System.out.println("Response Code : " + statusCode);

    		//Step 1: Verify ResponseStatusCode
   			Assert.assertEquals(statusCode ,HttpStatus.SC_OK, "Response Code test Failed");
	        
   			//Step2: Verify Response Content-Type
   			ctType=adapter.getResponeContentType();
   			Assert.assertEquals(ctType, expectedMimeType, "Content Type Test Failed");	
   			
   			//Step3: Verify Get response content, number of expected objects are matching to returned?
   			
   			int retSize=adapter.testGetResponseContent();
   			 Assert.assertEquals(retSize, exepectedJsonResObjs, "Number of objects returned from API doesn't match with Exepected Obj counts");
   			
	    }
	    
	    
	    @Test(description="Post API operation test")
	    public void testPost() throws IOException{
	    	//performing post operations test
	    	
	    	//creating post object
	    	 Book b=new Book();
	         b.setuserId("148");
	         b.setTitle("testnew");
	         b.setBody("abcd");
	        
	       //post data verification
	         PostApiTest adp=new PostApiTest();
	         adp.setContentType(expectedMimeType);
		     adp.setEndPoint(uri);	
		     adp.setObject(b);
		     System.out.println("\nSending 'POST' request to URL : " + uri);
		     int rCode=adp.getStatusCode();
   
		     System.out.println("Here is the  PostResponse Code......... : " + rCode);
		     //to verify Post operation has created record, verify the return status code
		     
   		     Assert.assertEquals(rCode ,HttpStatus.SC_CREATED, "Response Code test Failed");
   			
	    }
	    
	    
	}

