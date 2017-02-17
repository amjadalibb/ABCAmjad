package com.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewsPage {

	private final WebDriver driver;
	
	By newsheaderLocator = By.id("header");
	By newsJustInLocator = By.id("n-justin");
	By existingJustinHeaderLocator = By.xpath("//div[@id='container_subheader']/div/div/h1");
	By newsVideoPlayButtonLocator = By.xpath("//div[@id='jwplayer-video-0_display_button_play']");
	By newsJavaVideoControlElapsedLocator = By.xpath("//span[@id='jwplayer-video-0_controlbar']/span[@class='jwgroup jwcenter']/span/span[@class='jwrail']/span[@class='jwprogressOverflow']");
	By newsImageLocator = By.xpath("//div[@id='main_content']/div[@class='section media-article media-article-gallery media-article-gallery-ssp']/div/div/div/div/ul/li/img");
	
		public NewsPage(WebDriver driver) {
        this.driver = driver;

        // Check that we're on the right page.
        //if (!driver.getTitle().contains("ABC")) {
        //    throw new IllegalStateException("This is not the ABC News page , current page is " + driver.getTitle());
        //}
    }
	
	public void openPage(String newsurl) throws InterruptedException{
		driver.navigate().to(newsurl);
		Thread.sleep(5000);
	}
	
	public boolean isNewsPageLoaded(String url) throws InterruptedException{

		openPage(url);
		return (driver.getCurrentUrl()).contains("/news") && driver.getTitle().contains("ABC News");
	}
	
	public boolean isNewsHeaderPresent(String url) throws InterruptedException{
		openPage(url);
		return (driver.findElement(newsheaderLocator).isDisplayed() && driver.getCurrentUrl().contains("/news"));
	}
	
	public boolean isNavigationalToJustIn(String url) throws InterruptedException{
		openPage(url);		
		driver.findElement(newsJustInLocator).click();
		Thread.sleep(5000);
		return (driver.getCurrentUrl()).contains("/justin") && (driver.findElement(existingJustinHeaderLocator).getText().equals("Just In"));
	}
	
	public boolean isVideoLoaded(String url) throws InterruptedException{

		openPage(url);
		if(driver.findElement(newsVideoPlayButtonLocator).isDisplayed() )
		{
			driver.findElement(newsVideoPlayButtonLocator).click();
			Thread.sleep(3000);
			WebElement we = driver.findElement(newsJavaVideoControlElapsedLocator);
			String attr = we.getAttribute("Style");
			String[] parts = attr.split(";");
			if(parts[0].contains("width: 0%"))
			{
				return false;
			}
		}
		else
			return false;
		
		return true; 
	}
	
	public boolean isImageLoaded(String url) throws InterruptedException{

		openPage(url);
		WebElement we = driver.findElement(newsImageLocator);
		if(we.isDisplayed() )
		{
					
			String attr = we.getAttribute("Style");
			String[] parts = attr.split(";");
			if(parts[0].contains("max-height:") && parts[1].contains("max-width:"))
			{
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
}
