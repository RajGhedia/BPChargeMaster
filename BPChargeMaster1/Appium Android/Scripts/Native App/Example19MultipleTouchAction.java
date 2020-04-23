import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Example19MultipleTouchAction {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		DesiredCapabilities dc= new DesiredCapabilities();
		
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android"); //Android Emulator
		
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4.2");

		//when we ara working on multiple apps we can just copy these lines of command only thing we need to change is apk file location thats it
		dc.setCapability(MobileCapabilityType.APP, "C:\\Users\\Srujan\\Desktop\\APk Files\\MultiTouchTester.apk");
		
		URL url =new URL("http://127.0.0.1:4723/wd/hub");

		AndroidDriver<WebElement> driver= new AndroidDriver<WebElement>(url,dc);
		
		//
		//i am capturing coordinates for the netre page int x1= (int)(s.width *0.20);or app
		
		Dimension d=driver.manage().window().getSize(); 
		
		int x1=(int) (d.getWidth() * 0.2);
		int y1=(int) (d.getWidth() * 0.2);
		
		int x2=(int) (d.getWidth() * 0.2);
		int y2=(int) (d.getWidth() * 0.8);
		/*
		int x3=(int) (d.getWidth() * 0.4);
		int y3=(int) (d.getWidth() * 0.8);
		
		int x4=(int) (d.getWidth() * 0.8);
		int y4=(int) (d.getWidth() * 0.4);
		
		int x5=(int) (d.getWidth() * 0.2);
		int y5=(int) (d.getWidth() * 0.6);
		
		*/
		
		MultiTouchAction maction= new MultiTouchAction((MobileDriver) driver);
		
		//int xp= 1500;

	//	TouchAction action1= new TouchAction((MobileDriver) driver).longPress(x1, y1).waitAction(1500);
		//TouchAction action2= new TouchAction((MobileDriver) driver).longPress(x2, y2).waitAction(1500);
		
		// we identified issue with java c;ient : appium java ; withearlier version of hava clients we can perform this operations
	//	maction.add(action1).add(action2).perform();
		
		Thread.sleep(9500);
		
		driver.quit();
		
		
		
		
		
		
		
		
		
	}

}
