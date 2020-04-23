package systemApp;

import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;

public class Scenario2DialNumber {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		DesiredCapabilities dc= new DesiredCapabilities();
		
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android"); //Android Emulator
		
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4.2");

		//when we ara working on multiple apps we can just copy these lines of command only thing we need to change is apk file location thats it
		//dc.setCapability(MobileCapabilityType.pa, "C:\\Users\\Srujan\\Desktop\\APk Files\\ApiDemos.apk");
		
		dc.setCapability("appPackage", "com.android.contacts");
		dc.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");
		//dc.setCapability("appActivity", "com.android.contacts.DialactsActivity");
		
		URL url =new URL("http://127.0.0.1:4723/wd/hub");

		AndroidDriver<WebElement> driver= new AndroidDriver<WebElement>(url,dc);
		
		
		driver.pressKeyCode(AndroidKeyCode.HOME);
		
		driver.findElementsByClassName("android.widget.ImageView").get(1).click();
		
		//9618817771 : so i captured id proeprties for all those nos and eneterd heres
		
		driver.findElementById("com.android.contacts:id/nine").click();
		driver.findElementById("com.android.contacts:id/six").click();
		driver.findElementById("com.android.contacts:id/one").click();
		driver.findElementById("com.android.contacts:id/eight").click();
		driver.findElementById("com.android.contacts:id/eight").click();
		driver.findElementById("com.android.contacts:id/one").click();
		driver.findElementById("com.android.contacts:id/seven").click();
		driver.findElementById("com.android.contacts:id/seven").click();
		driver.findElementById("com.android.contacts:id/seven").click();
		driver.findElementById("com.android.contacts:id/one").click();
		
		
		
		//Thread.sleep(9500);
	//	driver.quit();
	}

}
