package apitest.rvtestapi;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.annotation.*;

public abstract class RestAdapter {
    
  protected String endPoint;
  protected String contentType;
  protected int stCode; 
  protected HttpResponse response; 
  
  
  public String getEndPoint() {
      return endPoint;
  }


  public String getContentType() {
      return contentType;
  }

  public abstract int getStatusCode();
  
  public abstract String getResponeContentType() throws IOException;
    
   public abstract HttpResponse getUrlResponse() throws IOException;
  
  
}

