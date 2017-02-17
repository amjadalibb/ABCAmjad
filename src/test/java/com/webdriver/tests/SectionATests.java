package com.webdriver.tests;

import org.junit.Assert;

import com.webdriver.pages.JustInPage;
import com.webdriver.pages.NewsPage;
import org.junit.Test;

public class SectionATests  extends TestBase{

	String newsURL = "http://www.abc.net.au/news/";
	String videoURL = "http://www.abc.net.au/news/2017-02-09/weatherill-promises-to-intervene-dramatically/8254908";
	String imageURL = "http://www.abc.net.au/news/2017-02-10/abc-open-pic-of-the-week/8256256";
	String justInURL = "http://www.abc.net.au/news/justin/";
	NewsPage newspg;
	JustInPage justinpg;
	
	// Test 1)	Verify that the page loads successful.
	@Test
	public void TestCase1() throws InterruptedException {
		newspg = new NewsPage(this.driver);	
		Assert.assertTrue("ABC News is not Loaded",newspg.isNewsPageLoaded(newsURL));
	}
	
	//Test 2)	Verify that News banner loads
	@Test
	public void TestCase2() throws InterruptedException {
		newspg = new NewsPage(this.driver);		
		Assert.assertTrue("News Header is not Loaded",newspg.isNewsHeaderPresent(newsURL));
	}
	
	//Test 3)	Verify can navigate to the ‘Just In’ page via the link on the primary navigation
	@Test
	public void TestCase3() throws InterruptedException {
		newspg = new NewsPage(this.driver);		
		Assert.assertTrue("Can't Navigate to Just In",newspg.isNavigationalToJustIn(newsURL));
	}

	// Test 4)	Verify that on the ‘Just In’ page (http://www.abc.net.au/news/justin/)  that the content per article loads correctly, i.e. must contain
	@Test
	public void TestCase4() throws InterruptedException {
		JustInPage justinpg = new JustInPage(this.driver);	
		Assert.assertTrue("ABC Just In Article content is not Loaded properly",justinpg.isJustInPageArticleContentLoaded(justInURL));
	}
	
	//Test 5) 	Verify that a video loads and appears successfully on the following page
	@Test
	public void TestCase5() throws InterruptedException {
		newspg = new NewsPage(this.driver);		
		Assert.assertTrue("Video can't load and appear",newspg.isVideoLoaded(videoURL));
	}
	
	//Test 6)	Verify that the Image Gallery successfully loads and images appear correctly
	@Test
	public void TestCase6() throws InterruptedException {
		newspg = new NewsPage(this.driver);		
		Assert.assertTrue("Image can't load and appear",newspg.isImageLoaded(imageURL));
	}	
}
