package com.webdriver.tests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonAnnotation {

	@JsonProperty("entity")
    private String Entity;
    @JsonProperty("arid")
    private String ARID;
    @JsonProperty("title")
    private String Title;
    @JsonProperty("mini_synopsis")
    private String miniSynopsis;
    @JsonProperty("short_synopsis")
    private String shortSynopsis;
    @JsonProperty("medium_synopsis")
    private String mediumSynopsis;
    @JsonProperty("created_utc")
    private Date createdUTC;
    @JsonProperty("last_updated_utc")
    private Date lastUpdatedUTC;
    @JsonProperty("service_airport_code")
    private String serviceAirportCode;
    
    SimpleDateFormat utcDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	    
    public JsonAnnotation() {
    	utcDateTimeFormat.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
    }
    
    public JsonAnnotation(String entity_name, String arid, String title_name, String mini_synopsis, String short_synopsis, String medium_synopsis, String created_utc, String last_updated_utc, String service_airport_code) throws ParseException {

    	utcDateTimeFormat.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
        
    	Entity = entity_name;
    	ARID = arid;
    	Title = title_name;
    	miniSynopsis = mini_synopsis;
    	shortSynopsis = short_synopsis;
    	mediumSynopsis = medium_synopsis;
    	createdUTC = utcDateTimeFormat.parse(created_utc);
    	lastUpdatedUTC = utcDateTimeFormat.parse(last_updated_utc);;
    	serviceAirportCode = service_airport_code;
    }

    public String getEntity() {
        return Entity;
    }
    public void setEntity(String entity_name) {
    	Entity = entity_name;
    }
    public String getArid() {
        return ARID;
    }
    public void setArid(String arid) {
    	ARID = arid;
    }
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title_name) {
    	Title = title_name;
    }
    
    public String getMiniSynopsis() {
        return miniSynopsis;
    }
    public void setMiniSynopsis(String mini_synopsis) {
    	miniSynopsis = mini_synopsis;
    }
    
    public String getShortSynopsis() {
        return shortSynopsis;
    }
    public void setShortSynopsis(String short_synopsis) {
    	shortSynopsis = short_synopsis;
    }
    
    public String getMediumSynopsis() {
        return mediumSynopsis;
    }
    public void setMediumSynopsis(String medium_synopsis) {
    	mediumSynopsis = medium_synopsis;
    }
    
    public Date getCreatedUTC() {
        return createdUTC;
    }
    public void setCreatedUTC(String created_utc) throws ParseException {
    	utcDateTimeFormat.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
    	createdUTC = utcDateTimeFormat.parse(created_utc);
    }
    
    public Date getLastUpdatedUTC() {
        return lastUpdatedUTC;
    }
    public void setLastUpdatedUTC(String last_updated_utc) throws ParseException {
    	utcDateTimeFormat.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
    	lastUpdatedUTC = utcDateTimeFormat.parse(last_updated_utc);;
    }
    
    public String getServiceAirportCode() {
        return serviceAirportCode;
    }
    public void setServiceAirportCode(String service_airport_code) {
    	serviceAirportCode = service_airport_code;
    }
    
}
