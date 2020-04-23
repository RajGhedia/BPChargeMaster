import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Example6DragnDropRandomValues {

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
		
		
		WebElement obj= driver.findElementById("com.android.androidui:id/seekBar1");
		
		String val1=driver.findElementById("com.android.androidui:id/textProgress").getText();
		System.out.println("before chnaging the value is : " +val1);
		
		
		//press that object and we need to move tha object to a location
		/* long press : x and y coordniaes of the desired object which is obj
		 * moveto: for which i will specifiy x and y corrdniates the reason is: i dont knwo the exct location where i need to drop hence i will give random coornidtes n i will try to move it
		 * TouchAction a= new TouchAction((MobileDriver) driver);
		 */
		
		
		TouchAction a = new TouchAction((MobileDriver) driver);
		
		a.longPress(obj).moveTo(250, 250).release().perform();
		String val2=driver.findElementById("com.android.androidui:id/textProgress").getText();
		
		System.out.println("value after change is: " +val2);
		
		
		
		Thread.sleep(9500);
		
		driver.quit();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
