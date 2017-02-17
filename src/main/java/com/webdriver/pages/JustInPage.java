package com.webdriver.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JustInPage {

	private final WebDriver driver;
	By newsJustInLocator = By.id("n-justin");
	By existingJustinHeaderLocator = By.xpath("//div[@id='container_subheader']/div/div/h1");
    
	By justInMainContentArticle = By.xpath("//div[@id='main_content']/div[@class='page section']/div/div/div/ul");
	
	
	public JustInPage(WebDriver driver) {
        this.driver = driver;

        // Check that we're on the default page.
        //if (!driver.getTitle().contains("ABC")) {
        //    throw new IllegalStateException("This is not ABC Just In page , current page is " + driver.getTitle());
        //}
    }
	
	public void openJustInPage(String justinurl) throws InterruptedException{
		driver.navigate().to(justinurl);
		Thread.sleep(5000);
	}
	
	public boolean isJustInPageLoaded(String url) throws InterruptedException{

		openJustInPage(url);
		return (driver.getCurrentUrl()).contains("/justin") && driver.getTitle().contains("Just In") && (driver.findElement(existingJustinHeaderLocator).getText().equals("Just In"));
	}
	
	public boolean isJustInPageArticleContentLoaded(String justInURL) throws InterruptedException{

		boolean TestCaseResult = false; 
		if(isJustInPageLoaded(justInURL))
		{
			try {
				WebElement table = driver.findElement(justInMainContentArticle);
				List<WebElement> rows = table.findElements(By.tagName("li"));
				Iterator<WebElement> i = rows.iterator();
		
				WebElement row = rows.get(0);
				while(i.hasNext()) {
					//Thread.sleep(1000);
				    String text = row.getText();
				    String[] parts = text.split("\n");
				    
				    //Ignoring Author because assuming all content has Author - as per instructions 				    
				    if((parts.length == 3 && !parts[0].isEmpty() && parts[1].contains("ago") && !parts[2].isEmpty()) 
				    		|| (parts.length == 4 && !parts[0].isEmpty() && parts[2].contains("ago") && !parts[3].isEmpty())
			    			|| (parts.length >= 5 && !parts[0].isEmpty() && parts[2].contains("ago") && !parts[4].isEmpty())) 
				    	TestCaseResult = true;
				    else
				    {
				    	TestCaseResult = false;
				    }
				    row = i.next();
				}
			}
			catch (ArrayIndexOutOfBoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return TestCaseResult;
	}
	
	
	
}
