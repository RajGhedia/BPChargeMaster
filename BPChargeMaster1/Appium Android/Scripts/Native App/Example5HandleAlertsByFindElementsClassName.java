import java.net.URL;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Example5HandleAlertsByFindElementsClassName {
	
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
		
		List<WebElement> val=driver.findElementsByClassName("android.widget.Button");
		
		for(int i=0;i<val.size();i++){
			
			String p=val.get(i).getText();
			
			//if the text present on p is yes then click on yes button
			if(p.equalsIgnoreCase("YES")){
				
				val.get(i).click();
				
				System.out.println("this logic clicked on yes button finally");
				
			}
		}
		
		
		
		
		
		Thread.sleep(9500);
		driver.quit();
	}

}
