package OtherTests;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import java.awt.*;
        import java.awt.geom.*;
        import javax.swing.*;
        import java.lang.*;

public class Map extends JPanel {


            public static void main(String args []) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //driver.get("https://www.qa.rightmove.com/property-for-sale/draw-a-search.html?locationIdentifier=REGION%5E578&includeSSTC=false&edit=true");

        //driver.get("https://www.qa.rightmove.com/draw-a-search.html");

       //driver.get("http://h3-qpvw01.rightmove.com/property-for-sale/map.html?locationIdentifier=POSTCODE%5E902806&numberOfPropertiesPerPage=499&radius=0.25&includeSSTC=false&viewType=MAP&viewport=-0.481253%2C-0.440055%2C51.4444%2C51.4567");
                driver.get("http://h1-qpvw01.rightmove.com/property-for-sale/map.html?locationIdentifier=POSTCODE%5E902806&numberOfPropertiesPerPage=499&radius=0.25&includeSSTC=false&viewType=MAP&viewport=-0.481253%2C-0.440055%2C51.4444%2C51.4567");

       // driver.get("https://www.qa.rightmove.com/property-for-sale/map.html?locationIdentifier=POSTCODE%5E902806&numberOfPropertiesPerPage=499&radius=0.25&includeSSTC=false&viewType=MAP&viewport=-0.481253%2C-0.440055%2C51.4444%2C51.4567");

        Thread.sleep(500);

   // Zoom In
                driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Report a map error'])[1]/following::button[1]")).click();

                Thread.sleep(10000);

  driver.quit();



       /* //let's setup the frame so we can keep adding to our program
        JFrame f = new JFrame("Spider Monkey Maps");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new Map());
        f.setSize(290, 325);
        f.setLocation(550, 25);
        f.setVisible(true);

        */

    }

    public void paint(Graphics g)

    {


        // Run for Maps

        //custom color
        String hexColor = new String("0x45e5B");
        g.setColor(Color.decode(hexColor));
        //draw a line (starting x,y; ending x,y)
       // g.drawLine(10, 10, 40, 10);


        //g2.setStroke(new BasicStroke(5));
        Graphics2D g2 = (Graphics2D) g;

        g2.fillRect(10, 20, 150, 40);

        RoundRectangle2D rd = new RoundRectangle2D.Float(10, 20, 150, 40, 5, 5);
        g2.draw(rd);
    }
}