import java.net.URL;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Example10ExpandableList {

	
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
		
		
		//below cmd will click on Explandable Lists as i notices hst Explandable list has 8th index and id as android:id/text1
		driver.findElementsById("android:id/text1").get(8).click();
		
		//try accesability id
		
		driver.findElementByAccessibilityId("1. Custom Adapter").click();
		
		//People Names
		driver.findElementsByClassName("android.widget.TextView").get(0).click();
		/*
		for(int i=0;i<val.size();i++){
			String x1=val.get(i).getText();
			if(x1.equalsIgnoreCase("People Names")){
				
				val.get(i).click();
			}
			
			
		}*/
		
		//we can write a commdn to find al the objects in the page witgh the sam classname
			

		Thread.sleep(9500);
		driver.quit();	
		
	}
}
