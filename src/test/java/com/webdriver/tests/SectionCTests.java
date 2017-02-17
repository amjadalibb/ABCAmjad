package com.webdriver.tests;

import org.junit.Assert;
import org.junit.Test;

public class SectionCTests {
			
	ReadJsonWithJackson readJson;
		
	boolean loadJsonFilePath;		

	//Test 1)	Verify the key/value pairs from the following jSon output http://program.abcradio.net.au/api/v1/programs/ppJj0E8g2R.json  (note this file is also attached as ‘ppJj0E8g2R.json’). 
	@Test
	public void SingleEnvAndProgram() throws InterruptedException {	
		String JSONURL = "http://program.abcradio.net.au/api/v1/programs/ppJj0E8g2R.json";
		
		readJson= new ReadJsonWithJackson(JSONURL, "", false);
		Assert.assertTrue("JSON [ " + JSONURL+ " ] could not load",readJson.printAllKeyValuePairs());     		
		
    }
	
	//Test 2)	Let’s assume we want to run the previous tests again (http://program.abcradio.net.au/api/v1/programs/ppJj0E8g2R.json) but this time against different environments. How would you develop your tests so that you can run these same tests against many other environments?
	@Test
	public void multipleEnvironment() throws InterruptedException {	
		
		String envVal[] = {Environment.stagging, Environment.test};
		String programValue = Programs.morning;	
		
		String testResultString = "";
		for(int i=0;i<envVal.length;i++)
		{
			readJson= new ReadJsonWithJackson("http://"+ envVal[i] +".abcradio.net.au/api/v1/programs/"+ programValue +".json", "", false);
			if(!readJson.printAllKeyValuePairs())
			{
				testResultString +="\n Program [ " + programValue + " ] could not load from this Environment [ " + envVal[i] + " ]";
			}
		}
		Assert.assertTrue(testResultString, testResultString.isEmpty());		
    }
	
	//Test 3)	We have many programs that have a unique id (key/value arid. See attached files or url outputs) that can be accessed via public APIs, where the domain and url structure is exactly same save for the actual program ID at the end. How would you develop your tests so that you can traverse the same tests against many other programs?
	@Test
	public void multiplePrograms() throws InterruptedException {	
		
		String envVal = Environment.live;
		String programValue[] = {Programs.afternoon, Programs.morning};
		
		String testResultString = "";
		for(int i=0;i<programValue.length;i++)
		{
			readJson= new ReadJsonWithJackson("http://"+ envVal +".abcradio.net.au/api/v1/programs/"+ programValue[i]+".json", "", false);
			if(!readJson.printAllKeyValuePairs())
			{
				testResultString +="\n Program [ " + programValue[i] + " ] could not load from this Environment [ " + envVal + " ]";
			}
		}
		Assert.assertTrue(testResultString, testResultString.isEmpty());		
    }
		
	//Test - Extra - Run Many programs on Many Environments
	@Test
	public void multipleEnvAndProgram() throws InterruptedException {	
		
		String envVal[] = {Environment.live, Environment.stagging, Environment.test};
		String programValue[] = {Programs.afternoon, Programs.morning}; 
		
		String testResultString = "";
		for(int i=0;i<programValue.length;i++)
			for(int j=0;j<envVal.length;j++)
			{
				readJson= new ReadJsonWithJackson("http://"+ envVal[j] +".abcradio.net.au/api/v1/programs/"+ programValue[i] +".json", "", false);
				if(!readJson.printAllKeyValuePairs())
				{
					testResultString +="\n Program [ " + programValue[i] + " ] could not load from this Environment [ " + envVal[j] + " ]";
				}
			}
		Assert.assertTrue(testResultString, testResultString.isEmpty());		
    }
	
	//Test - Extra - Run JSON file - If you want to load the program with JSON file stored in directory then Update the path and change the value loadJsonFilePath to true
	@Test
	public void runJSONFile() throws InterruptedException {	
		String JsonFilePath = System.getProperty("user.dir") + "\\src\\jsonfiles\\ppJj0E8g2R.json"; 
		loadJsonFilePath = true;
		readJson= new ReadJsonWithJackson("", JsonFilePath, loadJsonFilePath);
		Assert.assertTrue("JSON [ " + JsonFilePath+ " ] could not load",readJson.printAllKeyValuePairs());   
    }
}


