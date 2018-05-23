import com.gargoylesoftware.htmlunit.javascript.host.canvas.ext.WEBGL_debug_renderer_info;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.support.ui.*;
import java.util.concurrent.TimeUnit;


/**
 * Created by zongzhaoxiu on 2017/12/2.
 */

public class Utility {

    public static org.openqa.selenium.support.ui.Duration duration =  new org.openqa.selenium.support.ui.Duration(3,TimeUnit.SECONDS);

    public static WebElement waitForVisible(WebDriver driver, final By by,int waitTime)
    {
        WebDriverWait wait  = new WebDriverWait(driver,waitTime);

        for(int attempt = 0;attempt<waitTime;attempt++)
        {
            try
            {
                driver.findElement(by);
                break;
            }

            catch(Exception e)
            {
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
            }

        }

        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));

    }


    public static void swipeUP(AndroidDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;

        TouchAction action =
                new TouchAction(driver).press(width / 2, height * 4 / 5).waitAction().moveTo(width / 2, height / 4).release();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        action.perform();


    }

    public static void swipeDown(AndroidDriver driver)
    {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction action1 = new TouchAction(driver).press(width / 2,height/4).waitAction().moveTo(width /2, height* 3/4).release();

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        action1.perform();
    }
}
