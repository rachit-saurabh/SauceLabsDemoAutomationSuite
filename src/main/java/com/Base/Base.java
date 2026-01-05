package com.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Base 
{
	
		public WebDriver driver;
		public Properties prop;
	
		@BeforeClass
	public void setUp() throws IOException 
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/java/com/utilities/config.properties");
		prop.load(fis);
	}
	
		@BeforeMethod
	public void initialize() 
	{
		String browser = prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "src/main/java/com/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		ChromeOptions options = new ChromeOptions();

        // Disable browser notifications
        options.addArguments("--disable-notifications");

        // Use a fresh automation profile
        options.addArguments("--incognito");

        Map<String, Object> prefs = new HashMap<>();

        // Disable password manager
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        // Disable password leak detection (IMPORTANT)
        prefs.put("profile.password_leak_detection_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(prop.getProperty("baseUrl"));
	}
	
		@AfterMethod
	public void tearDown() 
	{
			if(driver != null) 
			{
				driver.close();
				driver.quit();
			}
	}

}
