package apitest.rvtestapi;

import java.io.IOException;

import org.json.*;

import com.google.gson.JsonSyntaxException;
import com.google.gson.Gson;

import java.net.SocketException;
import java.util.*;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;

import org.apache.http.entity.StringEntity;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.*;
import org.apache.http.impl.client.*;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.util.EntityUtils;
import org.apache.http.annotation.*;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;


public class PostApiTest extends RestAdapter{

public int stCode;
public HttpResponse response; 
public String uri;

private String endPoint;
private String contentType;
private Object obj;

public String setEndPoint(String uri) {
	this.endPoint=uri;
    return endPoint;
}

public String setContentType(String cType) {
	this.contentType=cType;
    return contentType;
}

public Object setObject(Object obj) {
	this.obj=obj;
    return obj;
}

@Override
public int getStatusCode ()
{
	try{
		HttpResponse response=getUrlResponse();      
	    //System.out.println(response);
	    this.stCode=response.getStatusLine().getStatusCode();
	    System.out.println("Return Post Response code is: "+stCode);
	    
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return stCode;
}


@Override
public HttpResponse getUrlResponse() throws IOException
{
	
    HttpResponse response = null;

	try{
			 HttpPost request = new HttpPost(this.endPoint);
			 Gson gson=new Gson();
			 String postjson=gson.toJson(this.obj);
			 StringEntity entity = new StringEntity(postjson);
			    
			 entity.setContentType(this.contentType);
			 entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
			 request.setEntity(entity);
		     HttpClient httpclient = HttpClientBuilder.create().build();
		     response = httpclient.execute(request);
	}
     catch(Exception e)
     {
    	 	e.printStackTrace();
     }
	 //System.out.println(response);
	    //int stCode=response.getStatusLine().getStatusCode();
	   // System.out.println("Post Response code is: "+stCode);
	   
	 return response;
}



@Override
public String getResponeContentType() throws IOException {
HttpResponse resp=this.getUrlResponse();
	
	String responseContentType=resp.getEntity().getContentType().getValue();

	System.out.println("Response Content type is': "+responseContentType);
	String[] retVal= responseContentType.split(";");
	String actualVal=retVal[0];
	System.out.println("Return Content Mime Type first part from Url is :"+actualVal);
	return actualVal;
}




}
