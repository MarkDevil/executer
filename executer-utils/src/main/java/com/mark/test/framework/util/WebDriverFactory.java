package com.mark.test.framework.util;

import org.openqa.selenium.WebDriver;

/**
 * Created by mark .
 * Data : 2016/7/21
 * Project :
 * Desc :
 */
public class WebDriverFactory {

    private static WebDriverFactory webDriverFactory = null;
    private WebDriver webDriver;
    private WebDriverFactory(){

    }


    public static WebDriverFactory getWebDriverInstance(){
        if (webDriverFactory==null){
            synchronized (WebDriverFactory.class){
                if (webDriverFactory == null){
                    webDriverFactory = new WebDriverFactory();
                }
            }

        }
        return webDriverFactory;
    }

    public WebDriver getWebDriver(String browser){
        if (browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver");
//            webDriver = (WebDriver) new ChromeDriver();
            return webDriver;
        }else {
            return null;
        }

    }


    public void closeWebDriver(){
        this.webDriver.close();
        this.webDriver.quit();
        webDriverFactory = null;
    }

}
