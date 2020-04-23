import java.net.URL;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Example8SwitchesinAPIDemosAPP {
	
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
	
		
		//the whole for loop will verify desired text until it will display and it will scroll and it wll click
		for(int j=1;j<5;j++){
			String p2="Switches";
		//logic for scroll to starts here
		int x2=x1 /2;
		int y2=y1 /2;
					
		TouchAction a = new TouchAction((MobileDriver) driver);
				//as we have given y2 which is a new y coornidates which is for sure below the last object for sure it should scroll down
		a.longPress(info).moveTo(x1, y2).release().perform();
		
		// this logic for scroll to ends here

		//lets try to retrive all the objects once again and see wherther our required object is there r not
		
		List<WebElement> val=driver.findElementsById("android:id/text1");
		//it should retrive all the objects means 30 or 40 whatever present in that app
		//below for loop will verify whether the desired text is there r not, if t is there then it will click on that object
		for(int i=0;i<val.size();i++){
			
			String p1=val.get(i).getText();
			
			if(p1.equalsIgnoreCase(p2)){
				val.get(i).click();
				break;
			}
			
			
		}
		
		}
		// end for loop.. we need to copy this whole for loop n we need to paste it where ever we want just we need to change the desired text under the loop
		
		
		driver.findElementById("io.appium.android.apis:id/monitored_switch").click();
	
		Thread.sleep(9500);
		driver.quit();	
				
		
		
	}

}
