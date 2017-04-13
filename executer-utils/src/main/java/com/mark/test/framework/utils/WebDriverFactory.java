package com.mark.test.framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;

/**
 * Created by MingfengMa .
 * Data : 2016/7/21
 * Project : autotest
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

    public WebDriver createAndstartService() throws IOException {
        service = new ChromeDriverService.Builder().
                usingDriverExecutable(new File("src/test/resources/webdriver/chromedriver")).
                usingAnyFreePort().build();
        service.start();
        return null;
    }

    public void stopService(){
        service.stop();
    }


    public void closeWebDriver(){
        this.webDriver.close();
        this.webDriver.quit();
        webDriverFactory = null;
    }

}
