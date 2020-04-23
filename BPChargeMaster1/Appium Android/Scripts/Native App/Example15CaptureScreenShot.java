import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.xerces.util.SynchronizedSymbolTable;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Example15CaptureScreenShot {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		DesiredCapabilities dc= new DesiredCapabilities();
		
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android"); //Android Emulator
		
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4.2");

		//when we ara working on multiple apps we can just copy these lines of command only thing we need to change is apk file location thats it
		dc.setCapability(MobileCapabilityType.APP, "C:\\Users\\Srujan\\Desktop\\APk Files\\AndroidUI.apk");
		
		URL url =new URL("http://127.0.0.1:4723/wd/hub");

		AndroidDriver<WebElement> driver= new AndroidDriver<WebElement>(url,dc);
		
		//************************* common for any android only u need to change apk files that it 
		
		
		//below liogic will change the switch to ON mode
		driver.findElementById("com.android.androidui:id/mySwitch").click();
		
		String val=driver.findElementById("com.android.androidui:id/switchStatus").getText();
		
		//i want to retreive that text and print that text n whether it is on or not
		
		System.out.println("status or text present aftyer the status change is: " +val);
		
		
		//lt us same commandonce again so that the switch will be off and lets retrve the text in off status aslso
		
		//below liogic will change the switch to ON mode
				driver.findElementById("com.android.androidui:id/mySwitch").click();
				
				String value=driver.findElementById("com.android.androidui:id/switchStatus").getText();
		
				System.out.println("status after off is: " +value);

				/* i would like to capture my screenshot.. how come?? 
				 * whatever the commands we used for selenium webdriver to capture the screenshot use the same commands
				 */

				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				//store screenshot at a specidfied location
				FileUtils.copyFile(scrFile, new File("C:\\Users\\Srujan\\eclipse-workspace\\Batch98Appium\\images\\ramya.png"));
				
				//lets try to store this screenshot within our projects
		
		Thread.sleep(9000);
		
		driver.quit();
		
		
		
	}

}
