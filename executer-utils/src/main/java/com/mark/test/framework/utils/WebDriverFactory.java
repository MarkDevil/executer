package com.mark.test.framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

/**
 * Created by MingfengMa .
 * Data : 2016/7/21
 * Project :
 * Desc :
 */
public class WebDriverFactory {

    private static WebDriverFactory webDriverFactory = null;
    private WebDriver webDriver;
    private ChromeDriverService service;
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
            webDriver = new ChromeDriver();
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
