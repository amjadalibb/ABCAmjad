package com.webdriver.tests;

import org.junit.Assert;
import com.webdriver.pages.RadioNationalPage;

import org.junit.Test;

public class SectionBTests extends TestBase {
	String rnURL = "http://www.abc.net.au/radionational/";
	String rnSearch = "Sample Program";
	String rnNavigateProgram = "Big Ideas", rnNavigateProgramTitle = "Big Ideas", rnNavigateProgramURLContains = "/bigideas";
	//String rnNavigateProgram = "All Programs", rnNavigateProgramTitle = "Programs A-Z", rnNavigateProgramURLContains = "/programs";
	String rnFortuneUniverseURL = "http://www.abc.net.au/radionational/programs/bigideas/a-fortunate-universe/8076406";
	String rnListenNowURLVerify = "https://radio.abc.net.au/programitem/pg1aGbWlx6?play=true";
	
	RadioNationalPage rnpg;

	// Test 1)	Verify can navigate to a ‘Program’ (e.g. ‘Big Ideas’) from the Programs sub-menu.
	@Test
	public void TestCases1() throws InterruptedException {
		
		rnpg = new RadioNationalPage(this.driver);
		
		Assert.assertTrue("ABC Radio National is not Loaded",rnpg.isRadioNationalPageLoaded(rnURL));
		Assert.assertTrue("Can't navigate to Big Ideas Menu in Programs",rnpg.navigateToProgramMenuItem(rnNavigateProgram , rnNavigateProgramTitle, rnNavigateProgramURLContains));
	}
	
	// Test 2)	Navigate to the last item in the ‘Program guide’ banner (underneath the primary navigation) and select the last program
	@Test
	public void TestCases2() throws InterruptedException {

		rnpg = new RadioNationalPage(this.driver);
		Assert.assertTrue("Can't navigate to Big Ideas Menu in Programs",rnpg.navigateToLastProgramInBanner(rnURL));
	}
	
	// Test 3)	Verify can search for content in the search bar and that content is returned
	@Test
	public void TestCases3() throws InterruptedException {

		rnpg = new RadioNationalPage(this.driver);
		Assert.assertTrue("Search is not working properly",rnpg.searchRN(rnURL, rnSearch));
	}

	// Test 4)	Verify you can click on Social media ‘Share’ icon and the correct pop-up appears
	@Test
	public void TestCases4() throws InterruptedException {
		rnpg = new RadioNationalPage(this.driver);
		Assert.assertTrue("Social Media popup is not loading correct",rnpg.socialMediaPopUp(rnFortuneUniverseURL));
	}
	
	// Test 5)	Verify that when you click on ‘Download audio’ you are directed to the mp3 file (will play in browser unless right click and select Download)
	@Test
	public void TestCases5() throws InterruptedException {
		rnpg = new RadioNationalPage(this.driver);		
		Assert.assertTrue("Audio file is not downloading",rnpg.downloadAudio(rnFortuneUniverseURL));
	}
	
	// Test 6)	Verify that when you click on ‘Listen now’ (from previous url) you are re-directed to the following url
	@Test
	public void TestCases6() throws InterruptedException {
		rnpg = new RadioNationalPage(this.driver);		
		Assert.assertTrue("Listen Now is not working",rnpg.verifyListenNow(rnFortuneUniverseURL, rnListenNowURLVerify));
	}
	
	// Test 7)	Verify that the audio player loads successfully when you load url: https://radio.abc.net.au/programitem/pg1aGbWlx6?play=true
	@Test
	public void TestCases7() throws InterruptedException {
		rnpg = new RadioNationalPage(this.driver);		
		Assert.assertTrue("Audi Player in Listen Now is not working",rnpg.verifyAudioPlayerInListenNow(rnListenNowURLVerify));
	}
		
}

