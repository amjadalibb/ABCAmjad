package com.webdriver.tests;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


public class ReadJsonWithJackson {

	ObjectMapper objectMapper;
	String JsonURL = "";
	String JsonFilePath = "";
	boolean loadJsonFilePath;
	SimpleDateFormat simpleDateFormat;
	JsonAnnotation testCasesObj;
	
	public ReadJsonWithJackson(String _jsonURL, String _jsonFilePath, boolean _loadJsonFilePath) {
        objectMapper=new ObjectMapper();
		JsonURL = _jsonURL;
		JsonFilePath = _jsonFilePath;
		loadJsonFilePath = _loadJsonFilePath;
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        simpleDateFormat.setTimeZone( TimeZone.getTimeZone( "UTC" ) );		
    }
	
	public boolean printAllKeyValuePairs(){
		boolean testResult = false;
		try {
			//convert the json string to object  
			if(!loadJsonFilePath)
			{
				if(!JsonURL.isEmpty() && exists(JsonURL))
				{
					testCasesObj = objectMapper.readValue(new URL(JsonURL).openStream(), JsonAnnotation.class);
					testResult =true;
				}
			}
			else 
				if(!JsonFilePath.isEmpty())
				{
					testCasesObj = objectMapper.readValue(new File(JsonFilePath), JsonAnnotation.class);
					testResult = true;
				}
			
			if(testResult)
			{
				System.out.println("Reading JSON from a file");
				System.out.println("----------------------------");
				System.out.println("entity: "+ testCasesObj.getEntity());
				System.out.println("arid: "+testCasesObj.getArid());
				System.out.println("title: "+testCasesObj.getTitle());
				System.out.println("mini_synopsis: "+testCasesObj.getMiniSynopsis());
				System.out.println("short_synopsis: "+testCasesObj.getShortSynopsis());
				System.out.println("medium_synopsis: "+testCasesObj.getMediumSynopsis());
				System.out.println("created_utc: "+ simpleDateFormat.format(testCasesObj.getCreatedUTC()));
				System.out.println("last_updated_utc: "+ simpleDateFormat.format(testCasesObj.getLastUpdatedUTC()));
				System.out.println("service_airport_code: "+testCasesObj.getServiceAirportCode());
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testResult;
	}
	
	public static boolean exists(String URLName){
	    try {
	      HttpURLConnection.setFollowRedirects(false);
	      // note : you may also need
	      //        HttpURLConnection.setInstanceFollowRedirects(false)
	      HttpURLConnection con =
	         (HttpURLConnection) new URL(URLName).openConnection();
	      con.setRequestMethod("HEAD");
	      return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	       return false;
	    }
	  }  
}
	