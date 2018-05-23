/**
 * Created by zongzhaoxiu on 2017/11/14.
 */


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Init {
    DesiredCapabilities capabilities = new DesiredCapabilities();

    WebElement element = null;

    //public AppiumDriver driver;

    public AndroidDriver<AndroidElement> driver;

    private boolean isInstall = false;

    @BeforeClass
    public void setup() throws Exception
    {
        capabilities.setCapability("deviceName","9a93fab1");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion","5.1.1");

        capabilities.setCapability("appPackage","com.pingan.papd");
        capabilities.setCapability("appActivity",".ui.activities.JKLogoActivity");
        capabilities.setCapability("sessionOverride",true);
        capabilities.setCapability("unicodeKeyboard", true);    //设置键盘
        capabilities.setCapability("resetKeyboard", false);

        //driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



       /*
        if(isInstall)
        {
            File classpathRoot = new File(System.getProperty("user.dir"));
            File appDir = new File(classpathRoot, "apps");
            File app = new File(appDir, "zhihu.apk");
            capabilities.setCapability("app", app.getAbsolutePath());

        }*/


    }

    @org.testng.annotations.AfterClass
    public void tearDown() throws Exception
    {
        driver.quit();
    }

    @Test
    public void login() throws Exception
    {
         element = driver.findElementById("com.pingan.papd:id/iv_ignore");

        if (element!=null)
        {
            element.click();
            Thread.sleep(300);
        }

        driver.findElementById("com.pingan.papd:id/phone_number_edit").clear();
        driver.findElementById("com.pingan.papd:id/phone_number_edit").sendKeys("16012332595");
        driver.findElementById("com.pingan.papd:id/send_reqcode_btn").click();
        Thread.sleep(200);
        driver.findElementById("com.pingan.papd:id/verify_code_et").sendKeys("666666");
        Thread.sleep(200);
       driver.findElementById ("com.pingan.papd:id/login_btn").click();

        Thread.sleep(100);

        //是否有新手教程动画

       try {
           element = Utility.waitForVisible(driver, By.id("com.pingan.papd:id/viewSwitch"), 1);

           //System.out.println(element.toString() + "||" + i);

           do {
               element.click();
               System.out.println("进入判断");
           } while (element.isDisplayed());
       }

       catch (Exception e)
       {
           System.out.println("没有新手引导动画教程");
       }

        //进入首页，先判断是否有升级提示，是否有顶通广告，如果有先关闭升级再关顶通

        ArrayList list = new ArrayList<String>();
        list.add("com.pingan.papd:id/msg_dialog_btn_cancel");
        list.add("com.pingan.papd:id/pop_ad_close_half");
        list.add("com.pingan.papd:id/pop_ad_close_full");

        String[] strArray1 = new String[3];
        strArray1[0] = "com.pingan.papd:id/msg_dialog_btn_cancel";
        strArray1[1] = "com.pingan.papd:id/pop_ad_close_half";
        strArray1[2] = "com.pingan.papd:id/pop_ad_close_full";


        for(int i = 0;i<2;i++)
        {
            if(i==0)
            {
                for(int j = 0;j<strArray1.length;j++)
                {
                    try

                    {
                        //System.out.println("id:"+list.get(j).toString());
                        element = Utility.waitForVisible(driver, By.id(strArray1[j]), 1);

                        System.out.println("update" + element.toString());

                        do {
                            element.click();
                            System.out.println("进入判断2");
                        } while (element.isDisplayed());

                    } catch (Exception e) {
                        System.out.println("当前页面没有此元素：" + list.get(j).toString());
                    }
                }
            }

            else
            {
                for(int j = strArray1.length-1;j>0;j--)
                {
                    try

                    {
                        System.out.println("出错了吗？");
                        element = Utility.waitForVisible(driver, By.id(strArray1[j]), 1);

                        System.out.println("update" + element.toString());

                        do {
                            element.click();
                            System.out.println("进入判断3");
                        } while (element.isDisplayed());

                    } catch (Exception e) {
                        System.out.println("当前页面没有此元素：" + list.get(j).toString());
                    }
                }
            }
        }


//            for (int j = 0; j < list.size(); j++)
//            {
//                try
//
//                {
//                    System.out.println("id:"+list.get(j).toString());
//                    element = Utility.waitForVisible(driver, By.id(list.get(j).toString()), 1);
//
//                    System.out.println("update" + element.toString());
//
//                    do {
//                        element.click();
//                        System.out.println("进入判断2");
//                    } while (element.isDisplayed());
//
//                } catch (Exception e) {
//                    System.out.println("当前页面没有此元素：" + list.get(j).toString());
//                }
//
//            }
//
//        for (int j = 1; j < list.size()&&j>0; j--)
//        {
//            try
//
//            {
//                System.out.println("id:"+list.get(j).toString());
//                element = Utility.waitForVisible(driver, By.id(list.get(j).toString()), 1);
//
//                System.out.println("update" + element.toString());
//
//                do {
//                    element.click();
//                    System.out.println("进入判断3");
//                } while (element.isDisplayed());
//
//            } catch (Exception e) {
//                System.out.println("当前页面没有此元素：" + element.toString());
//            }
//
//        }

        //首页弹框结束
        Thread.sleep(200);
        System.out.println("首页弹框结束");


    }
    @Test
    public void search() throws Exception
    {
        String[] tabNames = new String[6];
        tabNames[0] = "全部";
        tabNames[1] = "商城";
        tabNames[2] = "医生";
        tabNames[3] = "医院";
        tabNames[4] = "疾病";
        tabNames[5] = "头条";

        //Thread.sleep(800);
        System.out.println("回到首页");
        //System.out.println(driver.getContext());
        element = driver.findElementById("com.pingan.papd:id/search_tip");
        element.click();

        System.out.println("应该到了搜索落地页");
        Thread.sleep(500);

        //先判断落地页是否有暗提示
        String tip = driver.findElementById("com.pingan.papd:id/et_search_content").getText();
        Assert.assertNotNull(tip);

        driver.findElementById("com.pingan.papd:id/et_search_content").sendKeys("测试");

        //点击搜索按钮，并发起搜索
        element = driver.findElementById("com.pingan.papd:id/bt_search");
        element.click();

        //搜索结果页向上滑动屏幕
        Utility.swipeUP(driver);
        System.out.println("出搜索结果");

        //再滑到最上面，准备开始点击tab
        Utility.swipeDown(driver);

        List<AndroidElement> searchTabs = new ArrayList<AndroidElement>();

        searchTabs = driver.findElementsByClassName("android.widget.HorizontalScrollView");

        element = driver.findElementById("com.pingan.papd:id/tab_indicator");

        System.out.println("testing:"+driver.findElementsById("com.pingan.papd:id/tab_indicator").iterator());

        System.out.println("searchTabSize:"+searchTabs.size());

        for (int i = 0;i <searchTabs.size();i++)
        {
            element = searchTabs.get(i).findElementByClassName("android.widget.TextView");
            element.click();
            System.out.println("分别点击了动态tab："+element.getText());
            Thread.sleep(1500);
        }

        }


}
