package com.webdriver.tests;

import org.junit.Assert;
import com.webdriver.pages.JustInPage;
import org.junit.Test;

public class JustInTests extends TestBase {
	
	String justInURL = "http://www.abc.net.au/news/justin/";
	JustInPage justinpg ;
	
	// Test - Extra - Test to load JustInPage via the URL
	@Test
	public void isJustInPageLoaded() throws InterruptedException {
		justinpg = new JustInPage(this.driver);		
		Assert.assertTrue("ABC Just In page is not Loaded",justinpg.isJustInPageLoaded(justInURL));
	}

	// Test 4)	Verify that on the ‘Just In’ page (http://www.abc.net.au/news/justin/)  that the content per article loads correctly, i.e. must contain
	@Test
	public void isJustInPageArticleContentLoaded() throws InterruptedException {
		justinpg = new JustInPage(this.driver);		
		Assert.assertTrue("ABC Just In Article content is not Loaded properly",justinpg.isJustInPageArticleContentLoaded(justInURL));
	}
}
