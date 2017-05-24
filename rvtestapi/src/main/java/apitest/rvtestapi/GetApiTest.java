package apitest.rvtestapi;


import java.io.IOException;

import org.json.*;
import com.google.gson.JsonSyntaxException;
import com.google.gson.Gson;

import org.apache.http.client.methods.*;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.commons.*;
import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//mport org.apache.commons.*;
import org.apache.http.annotation.*;

public class GetApiTest extends RestAdapter{

public int stCode;
public HttpResponse response; 
public String uri;

private String endPoint;
private String contentType;

public String setEndPoint(String uri) {
	this.endPoint=uri;
    return endPoint;
}

public String setContentType(String cType) {
	this.contentType=cType;
    return contentType;
}

@Override
public int getStatusCode ()
{
	try{
		HttpResponse response=this.getUrlResponse();
		this.stCode = response.getStatusLine().getStatusCode() ;
		System.out.println("Resp code is "+stCode);
		System.out.println("Inside GetStatusCode subclass");
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

	HttpUriRequest request = new HttpGet(this.endPoint);
	HttpResponse hResponse=null;
	try {
		hResponse = HttpClientBuilder.create().build().execute(request);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return hResponse;
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


public int testGetResponseContent() throws IOException, JsonSyntaxException {
	
	Book b= new Book();
	HttpResponse resp=getUrlResponse();
	long len=resp.getEntity().getContentLength(); 
	
	//converting returned json to string
	String jsonData=EntityUtils.toString(resp.getEntity()); 
	
	// System.out.println(jsonData);
		
	Gson gson=new Gson();
	Book[] bookResults=gson.fromJson(jsonData,Book[].class);
	int bsize=bookResults.length;
	System.out.println("Number of records returns from Get Operation are: "+bsize);

	 return bsize;
}

}
