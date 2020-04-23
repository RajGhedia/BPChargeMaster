import java.net.URL;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Example7ScrollVertically {
	
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
		
		
		driver.findElementsById("android:id/text1").get(10).click();
		
		//how to perform scroll to select last values .. again we need to use touch actions here..
		
		//object information
		
		WebElement info=driver.findElementsById("android:id/text1").get(11);
		
		
		int x1=info.getLocation().getX();
		int y1=info.getLocation().getY();
		
		//we need end coornidates a.longPress(x).moveTo(startX, endY).release().perform();too hence , lets use some formulat to geterate
		
		
		int x2=x1 /2;
		int y2=y1 /2;
		
			
		TouchAction a = new TouchAction((MobileDriver) driver);
		
		//as we have given y2 which is a new y coornidates which is for sure below the last object for sure it should scroll down
		a.longPress(info).moveTo(x1, y2).release().perform();
		
		
		
		
		
		/*
		List<WebElement> val=driver.findElementsById("android:id/text1");
		//it should retrive all the objects means 30 or 40 whatever present in that app
		
		for(int i=0;i<val.size();i++){
			
			System.out.println(val.get(i).getText());
			
		}
		*/
		
		Thread.sleep(9500);
	driver.quit();	
		
	}

}
