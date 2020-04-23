import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Example5HandleAlertsById {
	
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
		
		//-******* above code copied from previouew program
		
		//below cmd wil click on show alert
		
		driver.findElementById("com.android.androidui:id/buttonAlert").click();
		
		//below cmd wil get the text from the alert
		String t=driver.findElementById("android:id/message").getText();
		
		System.out.println("text present on the alert is: " +t);
		
		//click on no button
		
		driver.findElementById("android:id/button2").click();
		
		//lets try to click on yes button, again we need to click on show alert then only we can select yes button
		
		
		//below cmd wil click on show alert
		
		driver.findElementById("com.android.androidui:id/buttonAlert").click();
		
		driver.findElementById("android:id/button1").click();
		
		
		
		
		
		
		Thread.sleep(9500);
		driver.quit();
	}

}
