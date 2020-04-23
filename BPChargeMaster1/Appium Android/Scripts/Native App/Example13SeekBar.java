import java.net.URL;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Example13SeekBar {
	
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
		
		//below logic will select s[inner as it needs tos croll vertically
		driver.findElementsById("android:id/text1").get(10).click();
		
		//how to perform scroll to select last values .. again we need to use touch actions here..
		
		//object information
		
		WebElement info=driver.findElementsById("android:id/text1").get(11);
		
		TouchAction a = new TouchAction((MobileDriver) driver);
		
		int x1=info.getLocation().getX();
		int y1=info.getLocation().getY();
		
		//we need end coornidates a.longPress(x).moveTo(startX, endY).release().perform();too hence , lets use some formulat to geterate
		
		//for j value should be 4 for spinner and 5 for switches 
		//the whole for loop will verify desired text until it will display and it will scroll and it wll click
		for(int j=1;j<4;j++){
			String p2="Seek Bar";
		//logic for scroll to starts here
		int x2=x1 /2;
		int y2=y1 /2;
					
		
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

		WebElement seek=driver.findElementById("io.appium.android.apis:id/seek");
		//below 2 comamnds will capture x n y corrditanes of the object n even height n width of the seek bar object
		Point p=seek.getLocation();
		Dimension d=seek.getSize();
		
		int x11=p.getX();
		int y11=p.getY();
		
	//we are genrating coorndiates to where it needs to drop : depending on intial x and y corridaes which is just multiplying by .5
		/*int x12= (x11 / 2);
		int y12= (y11 / 2);*/
		
		

//		a.longPress(seek).moveTo(x12, y12).release().perform();
	
		//capture the desired cooridnates : i used my UI automarer viewer
		
		a.longPress(x11, y11).moveTo(100, 0).release().perform();
		//above cmd should scroll left
		Thread.sleep(2500);
		a.longPress(x11, y11).moveTo(155, 0).release().perform();
		
		//a.longPress(x11, y11).moveTo(500, 210).release().perform();
		
		Thread.sleep(9500);
		
		driver.quit();
		
		
		
		
}
}