import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Example11DatePicker {
	
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
		
		
		//below cmd will click on date widgets Lists as i notices hst date widgets list has 6th index and id as android:id/text1
		driver.findElementsById("android:id/text1").get(6).click();

		driver.findElementByAccessibilityId("2. Inline").click();
		/*
		//lets see where it is entering the value
		driver.findElementById("android:id/numberpicker_input").clear();
		driver.findElementById("android:id/numberpicker_input").sendKeys("10"); */
		
		//as all  r having asme id why cant we use findElemnts here?
		
		driver.findElementsById("android:id/numberpicker_input").get(0).clear();
		driver.findElementsById("android:id/numberpicker_input").get(0).sendKeys("10");
		
		driver.findElementsById("android:id/numberpicker_input").get(1).clear();
		driver.findElementsById("android:id/numberpicker_input").get(1).sendKeys("10");

		driver.findElementsById("android:id/numberpicker_input").get(2).clear();
		driver.findElementsById("android:id/numberpicker_input").get(2).sendKeys("AM");
		
	Thread.sleep(9500);
	driver.quit();	
		
		
	}

}
