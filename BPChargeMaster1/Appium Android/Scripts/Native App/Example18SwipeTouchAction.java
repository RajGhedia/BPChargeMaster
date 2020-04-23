import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Example18SwipeTouchAction {
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		DesiredCapabilities dc= new DesiredCapabilities();
		
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android"); //Android Emulator
		
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4.2");

		//when we ara working on multiple apps we can just copy these lines of command only thing we need to change is apk file location thats it
		dc.setCapability(MobileCapabilityType.APP, "C:\\Users\\Srujan\\Desktop\\APk Files\\SwipeListView.apk");
		
		URL url =new URL("http://127.0.0.1:4723/wd/hub");

		AndroidDriver<WebElement> driver= new AndroidDriver<WebElement>(url,dc);
		
		
		//click on ok button from the popup
		
		driver.findElementById("android:id/button1").click();
		
		Thread.sleep(9500);
		
		//identify 1 object : if we want to perform swipe on different objects then change the index to that value...
		
		//if we woild like to perform operations on the objects which are below the screen then use the logic which we have implemeted in apidemos app to scroll the page down and then perform the oprations
		
		WebElement obj=driver.findElementsById("com.fortysevendeg.android.swipelistview:id/example_row_tv_title").get(0);
		//get x,y, height, width of the object
		Point p=obj.getLocation();
		int x1=p.getX();
		int y1=p.getY();
		
		Dimension d=obj.getSize();
		int h1=d.getHeight();
		int w1=d.getWidth();
		
		//;ets generate end coordniates for the object so that we can perform swipe operations
		
		int x2= h1 /2;
		int y2= w1 /2;
		
		TouchAction a = new TouchAction((MobileDriver) driver);
		
		
		a.longPress(obj).moveTo(h1, y2).release().perform();
		
		Thread.sleep(9500);
		
		driver.quit();
		
		
	}

}
