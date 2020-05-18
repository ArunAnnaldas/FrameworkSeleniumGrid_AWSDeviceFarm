package seleniumGrid;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.devicefarm.DeviceFarmClient;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlRequest;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlResponse;


public class TestGridClass {
	public WebDriver driver;
	public String URL, Node;
	public DesiredCapabilities desired_capabilities;
	
	@Parameters("browser")
	@BeforeTest
	public void launchbrowser(String browser) throws MalformedURLException {
		String URL = "http://www.calculator.net";
		desired_capabilities = new DesiredCapabilities();
		desired_capabilities.setCapability("build","1");

		if (browser.equalsIgnoreCase("firefox")) {
/*			System.out.println(" Executing on FireFox");
			String Node = "http://10.0.75.1:5566/wd/hub";
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			driver = new RemoteWebDriver(new URL(Node), cap);*/
			
			String myProjectARN = "arn:aws:devicefarm:us-west-2:114338154283:testgrid-project:005ac4b4-e1a9-48b6-8e68-54390e75d6e6";
		    DeviceFarmClient client  = DeviceFarmClient.builder().region(Region.US_WEST_2).build();
		    CreateTestGridUrlRequest request = CreateTestGridUrlRequest.builder()
		      .expiresInSeconds(300)
		      .projectArn(myProjectARN)
		      .build();
		    CreateTestGridUrlResponse response = client.createTestGridUrl(request);
		    URL testGridUrl = new URL(response.url());
		    
	        desired_capabilities.setCapability("browserName","firefox");
	        desired_capabilities.setCapability("browserVersion", "latest");
	        //desired_capabilities.setCapability("platform", "linux");
		    
		    driver = new RemoteWebDriver(testGridUrl, desired_capabilities);
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Launch website
			driver.navigate().to(URL);
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("chrome")) {
			/*System.out.println(" Executing on CHROME");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			String Node = "http://10.0.75.1:5567/wd/hub";
			driver = new RemoteWebDriver(new URL(Node), cap);*/
			
			String myProjectARN = "arn:aws:devicefarm:us-west-2:114338154283:testgrid-project:005ac4b4-e1a9-48b6-8e68-54390e75d6e6";
		    DeviceFarmClient client  = DeviceFarmClient.builder().region(Region.US_WEST_2).build();
		    CreateTestGridUrlRequest request = CreateTestGridUrlRequest.builder()
		      .expiresInSeconds(300)
		      .projectArn(myProjectARN)
		      .build();
		    CreateTestGridUrlResponse response = client.createTestGridUrl(request);
		    URL testGridUrl = new URL(response.url());
		    //DesiredCapabilities desired_capabilities = new DesiredCapabilities();
		    desired_capabilities.setCapability("browserName","chrome");
	        desired_capabilities.setCapability("browserVersion", "latest");
	        desired_capabilities.setCapability("platform", "windows");
		    
		    driver = new RemoteWebDriver(testGridUrl, desired_capabilities);
		    
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Launch website
			driver.navigate().to(URL);
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("ie")) {
			/*System.out.println(" Executing on IE");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setBrowserName("ie");
			String Node = "http://10.0.75.1:5568/wd/hub";
			driver = new RemoteWebDriver(new URL(Node), cap);*/
			
			String myProjectARN = "arn:aws:devicefarm:us-west-2:114338154283:testgrid-project:005ac4b4-e1a9-48b6-8e68-54390e75d6e6";
		    DeviceFarmClient client  = DeviceFarmClient.builder().region(Region.US_WEST_2).build();
		    CreateTestGridUrlRequest request = CreateTestGridUrlRequest.builder()
		      .expiresInSeconds(300)
		      .projectArn(myProjectARN)
		      .build();
		    CreateTestGridUrlResponse response = client.createTestGridUrl(request);
		    URL testGridUrl = new URL(response.url());
		    //DesiredCapabilities desired_capabilities = new DesiredCapabilities();
		    desired_capabilities.setCapability("browserName","internet explorer");
	        desired_capabilities.setCapability("browserVersion", "latest");
	        /*desired_capabilities.setCapability("platform", "mavericks");
	        desired_capabilities.setCapability("version", "os x 10.9");*/
		    
		    driver = new RemoteWebDriver(testGridUrl, desired_capabilities);		    
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Launch website
			driver.navigate().to(URL);
			driver.manage().window().maximize();
		} else {
			throw new IllegalArgumentException("The Browser Type is Undefined");
		}
	}

	@Test
	public void calculatepercent() {
		// Click on Math Calculators
		driver.findElement(By.xpath("//a[contains(text(),'Math')]")).click();
		// Click on Percent Calculators
		driver.findElement(By.xpath("//a[contains(text(),'Percentage Calculator')]")).click();
		// Enter value 17 in the first number of the percent Calculator
		driver.findElement(By.id("cpar1")).sendKeys("17");
		// Enter value 35 in the second number of the percent Calculator
		driver.findElement(By.id("cpar2")).sendKeys("35");

		// Click Calculate Button
		driver.findElement(By.xpath("(//input[contains(@value,'Calculate')])[1]")).click();
		// Get the Result Text based on its xpath
		String result = driver.findElement(By.xpath(".//*[@id='content']/p[2]/font/b")).getText();
		// Print a Log In message to the screen
		System.out.println(" The Result is " + result);
		if (result.equals("5.95")) {
			System.out.println(" The Result is Pass");
		} else {
			System.out.println(" The Result is Fail");
		}
	}

	 @AfterTest
	  void tearDown() {
	    // make sure to close your WebDriver:
	    driver.quit();
	  }

}