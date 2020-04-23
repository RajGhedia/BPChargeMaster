import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.asprise.util.ocr.OCR;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Example7SPopup {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		DesiredCapabilities dc= new DesiredCapabilities();
		
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator"); //Android Emulator
		
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
		dc.setCapability("browserName", "chrome");

		AndroidDriver driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		
		driver.get("https://www.kroger.com/signin?redirectUrl=/");
		Thread.sleep(8000);
		/*
		//when we ara working on multiple apps we can just copy these lines of command only thing we need to change is apk file location thats it
		dc.setCapability(MobileCapabilityType.APP, "C:\\Users\\Srujan\\Desktop\\APk Files\\ApiDemos.apk");
		
		URL url =new URL("http://127.0.0.1:4723/wd/hub");

		AndroidDriver<WebElement> driver= new AndroidDriver<WebElement>(url,dc);
		
		//-******* above code copied from previouew program
		/*
		
		driver.findElementsById("android:id/text1").get(10).click();
		
		//how to perform scroll to select last values .. again we need to use touch actions here..
		
		//object information
		
		WebElement info=driver.findElementsById("android:id/text1").get(10);
		
		
		int x1=info.getLocation().getX();
		int y1=info.getLocation().getY();
		
		//we need end coornidates a.longPress(x).moveTo(startX, endY).release().perform();too hence , lets use some formulat to geterate
	
		
		//the whole for loop will verify desired text until it will display and it will scroll and it wll click
		for(int j=1;j<3;j++){
			String p2="Popup Menu";
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
		
		driver.findElementByClassName("android.widget.Button").click();
		driver.findElementsById("android:id/title").get(2).click();
		*/
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//store screenshot at a specidfied location
		File targetFile=new File("C:\\Users\\Srujan\\eclipse-workspace\\Batch98Appium\\images\\kiran.png");
		FileUtils.copyFile(scrFile,targetFile );
		System.out.println(targetFile.toString());
		
		
		// URL url1 = new URL(targetFile.toString());  
		 Image image = ImageIO.read(targetFile);
		
		 
		 // we are asking it to recognize the characers present in the image and we are asking to it to read the contnet and to store the content in a variable
		 String s = new OCR().recognizeCharacters((RenderedImage) image);  
		 
		 //below cmd is simply printing the information
		 System.out.println("Text From Image : \n"+ s);  
	
			if(s.contains("Monitored switch is on")){
				System.out.println("toastr verified successfully");
			}else{
				System.out.println("toastr not verified successfully");
			}

		
		

		
		
		
		Thread.sleep(9500);
	driver.quit();	
		
	}

}