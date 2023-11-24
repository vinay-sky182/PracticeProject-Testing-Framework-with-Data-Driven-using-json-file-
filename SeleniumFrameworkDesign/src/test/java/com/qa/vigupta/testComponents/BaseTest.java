package com.qa.vigupta.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.exec.launcher.Java13CommandLauncher;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.vigupta.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage lPage;
	
	public WebDriver initializeDriver() throws IOException 
	{
		Properties prop = new Properties(); // creating object of 'Properties' class	
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\vigupta\\resources\\GlobalData.properties");
		// creating the 'FileInputStream' object and passing the path of '.properties' extension file
		// System.getProperty("user.dir") :- It will give the project path from the local system, dynamically "C:\Users\vigupta\eclipseProjects\Ecommerce Demo\SeleniumFrameworkDesign"
		
		prop.load(fis); // calling the load() function of 'Properties' class which takes the 'FileInputStream' object
		
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");
		
//		 prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) 
		{
			 driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			 driver = new FirefoxDriver();
		}
		else 
		{
			 driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	// JSON file reader utility :- It will read JSON file & convert into String after that It will use 'jackson-databind api' to convert String to HashMap.
	
	public List<HashMap<String, String>> getJsonDatatoMap(String filePath) throws IOException
	{
	   // Read Json to String.
	   String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
	   	   
       // String to HashMap using jackson-databind api    
	   ObjectMapper mapper = new ObjectMapper();
	   
	   List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});	   
	   // "List<HashMap<String, String>>" :- It's a List which contains HashMap elements with a key of String type and a value of String type
	   
	   //TypeReference is an abstract class. The {} provides an empty implementation via an anonymous class, without which you would get a compile-time error.
	   //Note the use of {} in the syntax for declaring the new TypeReference. 
	   
	   return data;
	   
	}
	
	// Screenshot utility
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot tScreenshot = (TakesScreenshot) driver;
		File source = tScreenshot.getScreenshotAs(OutputType.FILE);
//		the argument “OutputType.FILE” specified in the getScreenshotAs() method will return the capture screenshot in the form of file.
		File destination = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, destination);
		
//		we convert the WebDriver object (driver) to TakeScreenshot. And call getScreenshotAs() method to create an image file by providing the parameter *OutputType.FILE.
//		Now we can use this File object to copy the image at our desired location, as shown below, using the FileUtils Class.

		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
		
	
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		lPage = new LandingPage(driver);
		lPage.goTo();
		return lPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();	
	}

}
