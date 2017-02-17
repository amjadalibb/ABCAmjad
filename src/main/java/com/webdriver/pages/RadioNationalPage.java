package com.webdriver.pages;


import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;



public class RadioNationalPage {

	private final WebDriver driver;
	By rnProgramDropDownLocator = By.xpath("//div[@id='rn-navigation']/ul/li[@class='rn-nav-link rn-nav-drop']/a");
	By rnViewFullProgramGuideLocator = By.xpath("//div[@id='content']/div/div/div/div[@class='on-air']/ul/li[@class='guide']/a/div");
	By rnViewFullProgramGuideBannerLocator = By.xpath("//div[@id='content']/div/div/div/div[@class='on-air']/ul");
	By rnSearchResultTextLine = By.xpath("//div[@id='content']/div/div/div/p");
	By rnFacebookIconLocator = By.xpath("//div[@id='content']/div/div/div/div[@class='ct-social-share']/div");
	//By rnTwitterIconLocator = By.xpath("//div[@id='content']/div/div/div/div[@class='ct-social-share']/iframe");
	By rnTwitterIconLocator = By.id("twitter-widget-0");
	By rnTwitterPopupLogoLocator = By.xpath("//div[@id='header']/div/h1/a");
	By rnDownloadAudioLocator = By.xpath("//a[@href='http://mpegmedia.abc.net.au/rn/podcast/2017/02/bia_20170208_2005.mp3']");
	By rnListenNowLocator = By.xpath("//div[@id='content']/div/div/div/div/ul/li/a");
	By rnProgramIndexLocator = By.id("rn-programindex");
	By rnSearchTextField = By.id("search-simple-input-query");
	By rnProgramGuideBannerRightArrow = By.id("right-arrow");
	By rnFacebookPopUpLogoLocator = By.id("homelink");
	
	public RadioNationalPage(WebDriver driver) {
        this.driver = driver;

        // Check that we're on the right page.
       // if (!driver.getTitle().contains("ABC")) {
        //    throw new IllegalStateException("This is not the ABC Radio National page , current page is " + driver.getTitle());
       // }
    }
	public void openPage(String rnurl) throws InterruptedException{
		driver.navigate().to(rnurl);
		Thread.sleep(5000);
	}
	
	
	public boolean isRadioNationalPageLoaded(String url) throws InterruptedException{

		openPage(url);
		return (driver.getCurrentUrl()).contains("/radionational") && driver.getTitle().contains("ABC Radio National");
	}
	public boolean navigateToProgramMenuItem(String MenuName, String titleName, String urlContains) throws InterruptedException{

		if(driver.findElement(rnProgramDropDownLocator).isDisplayed())
		{
			driver.findElement(rnProgramDropDownLocator).click();
			Thread.sleep(1000);
			
			WebElement programIndex = driver.findElement(rnProgramIndexLocator);
			List<WebElement> rows = programIndex.findElements(By.tagName("li"));
			Iterator<WebElement> i = rows.iterator();
			WebElement row = rows.get(0);
			while(i.hasNext()) {
		    	Thread.sleep(1000);
			    	
			    if(row.getText().contains(MenuName))
			    {
			    	row.click();
			    	Thread.sleep(2000);
			    	if(driver.getTitle().contains(titleName) && driver.getPageSource().contains(urlContains))
			    		return true;
			    	else 
			    		return false;
			    }
			    row = i.next();
			}		
		}
		return false;
	}
	
	public boolean navigateToLastProgramInBanner(String url) throws InterruptedException{

		if(isRadioNationalPageLoaded(url))
		{
			if(driver.findElement(rnProgramGuideBannerRightArrow).isDisplayed())
			{
				WebElement programIndex = driver.findElement(rnViewFullProgramGuideBannerLocator);
				List<WebElement> rows = programIndex.findElements(By.tagName("li"));
				WebElement row = rows.get(rows.size()-2);
				String currentURL = driver.getCurrentUrl();
				for(Iterator<WebElement> iRows = rows.iterator(); iRows.hasNext(); ) {
					iRows.next();
					if(row.isDisplayed())
					{
						row.click(); Thread.sleep(2000);
						// Some time it requires to click twice
						if(row.isDisplayed()== true)
							row.click(); Thread.sleep(2000);
						if(currentURL != driver.getCurrentUrl())
							return true;
						else
							return false;
					}
					else
						driver.findElement(rnProgramGuideBannerRightArrow).click();
				}	
			}
		}
		return false;
	}
	
	public boolean searchRN(String url, String rnSearch) throws InterruptedException{

		if(isRadioNationalPageLoaded(url))
		{
			driver.findElement(rnSearchTextField).sendKeys(rnSearch);
			driver.findElement(rnSearchTextField).submit();
			
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			ExpectedCondition<Boolean> pageLoadCondition = new
	               ExpectedCondition<Boolean>() {
	                   public Boolean apply(WebDriver driver) {
	                       return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	                    }
	                };
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(pageLoadCondition);
			
			if(driver.findElement(rnSearchResultTextLine).isDisplayed())
			{
				String rnSearchResult = driver.findElement(rnSearchResultTextLine).getText();
				if(rnSearchResult.contains("Your search for \"" + rnSearch + "\" matched "))
					return true;
			}			
		}
		return false;
	}

	public boolean socialMediaPopUp(String url) throws InterruptedException{
		openPage(url);
		
		if(driver.findElement(rnFacebookIconLocator).isDisplayed())
		{
			driver.findElement(rnFacebookIconLocator).click();
			String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
			String subWindowHandler = null;

			Set<String> handles = driver.getWindowHandles(); // get all window handles
			Iterator<String> iterator = handles.iterator();
			while (iterator.hasNext()){
			    subWindowHandler = iterator.next();
			}
			driver.switchTo().window(subWindowHandler); // switch to popup window
			if(driver.findElement(rnFacebookPopUpLogoLocator).getText().contains("Facebook") && driver.getTitle().contains("Facebook"))
			{
				driver.close();
				// perform operations on popup
				driver.switchTo().window(parentWindowHandler);  // switch back to parent window
				if(driver.findElement(rnTwitterIconLocator).isDisplayed())
				{
					driver.findElement(rnTwitterIconLocator).click();
					handles = driver.getWindowHandles(); // get all window handles
					iterator = handles.iterator();
					while (iterator.hasNext()){
					    subWindowHandler = iterator.next();
					}
					driver.switchTo().window(subWindowHandler); // switch to popup window
					if(driver.findElement(rnTwitterPopupLogoLocator).isDisplayed() && driver.getTitle().contains("Twitter"))
					{
						driver.close();
						driver.switchTo().window(parentWindowHandler);  // switch back to parent window
						return true;
					}
				}
			}
			driver.switchTo().window(parentWindowHandler);  // switch back to parent window
		}
		
		return false;
	}
	
	public boolean downloadAudio(String url) throws InterruptedException{
		openPage(url);
		
		if(driver.findElement(rnDownloadAudioLocator).isDisplayed())
		{
			String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
			String openInNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); 
			driver.findElement(rnDownloadAudioLocator).sendKeys(openInNewTab);
			
			try {
				ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				
				driver.switchTo().window(tabs.get(1));
				Thread.sleep(4000);
				
				//Use the list of window handles to switch between windows
				if(driver.getCurrentUrl().contains("mp3"))
				{
					driver.close();
					Thread.sleep(1000);
					driver.switchTo().window(parentWindowHandler);	
					return true;
				}
			}
			catch (Exception e) {
					e.printStackTrace();
				}
			}		
		return false;
	}
	
	public boolean verifyListenNow(String url, String verifyURL) throws InterruptedException{
		openPage(url);
		if(driver.findElement(rnListenNowLocator).isDisplayed())
		{
			String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
			driver.findElement(rnListenNowLocator).click();
			Thread.sleep(5000);
			try {
				ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				
				driver.switchTo().window(tabs.get(1));
				Thread.sleep(4000);
				
				//Use the list of window handles to switch between windows
				if(driver.getCurrentUrl().contentEquals(verifyURL))
				{
					driver.close();
					Thread.sleep(1000);
					driver.switchTo().window(parentWindowHandler);	
					return true;
				}
			}
			catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		return false;
	}
	
	public boolean verifyAudioPlayerInListenNow(String url) throws InterruptedException{
		openPage(url);
		if(driver.getTitle().contains("ABC Radio"))
		{
			if(driver.findElement(By.xpath("//div[@id='player']/div/div")).isDisplayed())
			{
				Thread.sleep(5000);
				if(driver.findElement(By.xpath("//div[@id='main']/div/div[2]/div/div/div/div/div/section/div/div/div")).isDisplayed())
					driver.findElement(By.xpath("//div[@id='main']/div/div[2]/div/div/div/div/div/section/div/div/div")).click();
				String attr;
				String[] parts;
				int retries = 0;
				while (retries++ < 5)
				{
					Thread.sleep(2000);
					attr = driver.findElement(By.xpath("//div[@id='player']/div/div[@class='columns small-8 medium-10 large-9']/div/div[2]/div/div/div/div")).getAttribute("Style");
					parts = (((attr.split(";"))[1]).split(":")[1]).split("%");
				
					if(Float.parseFloat(parts[0]) > 0.0 )
						return true;
				}
			}
		}
		return false;
	}
}
