import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Example9CheckboxRadioButtonTextField {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		DesiredCapabilities dc= new DesiredCapabilities();
		
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android"); //Android Emulator
		
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4.2");

		//when we ara working on multiple apps we can just copy these lines of command only thing we need to change is apk file location thats it
		dc.setCapability(MobileCapabilityType.APP, "C:\\Users\\Srujan\\Desktop\\APk Files\\ApiDemos.apk");
		
		URL url =new URL("http://127.0.0.1:4723/wd/hub");

		AndroidDriver<WebElement> driver= new AndroidDriver<WebElement>(url,dc);
		
		//-******* above code copied from previouew program
		
		//beloc ode will clikc on views
		driver.findElementsById("android:id/text1").get(10).click();
		
		
		//below cmd will click on controls as i notices hst ctrls has 4th index and id as android:id/text1
		driver.findElementsById("android:id/text1").get(4).click();
		
		driver.findElementByAccessibilityId("2. Dark Theme").click();
		
		//we can use this property to identify a links basically we can give text present on those links using this proeprty
		
		//enter value to the text field
		
		driver.findElementById("io.appium.android.apis:id/edit").sendKeys("Surendra Jaganadam");
		
		//select 2nd radio button
		driver.findElementById("io.appium.android.apis:id/radio2").click();
		
		//1st checkbox
		driver.findElementById("io.appium.android.apis:id/check1").click();
		
		

		Thread.sleep(9500);
		driver.quit();	
				
		
		
	}

}
