//import com.mark.test.framework.utils.WebDriverFactory;
//import org.junit.After;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by mark .
// * Data   : 2017/4/10
// * Author : mark
// * Desc   :
// */
//
//public class TestWeb {
//    Logger logger = LoggerFactory.getLogger(TestWeb.class);
//    WebDriver driver;
//
//
//    @Test
//    public void testSelenium() throws InterruptedException, IOException {
//        driver = WebDriverFactory.getWebDriverInstance().getWebDriver("chrome");
//        driver.get("https://mail.qq.com");
//        driver.switchTo().frame("login_frame");
//        WebElement user = driver.findElement(By.id("u"));
//        WebElement passwd = driver.findElement(By.id("p"));
//        user.sendKeys("575707315");
//        passwd.sendKeys("Crystal08102");
//        driver.findElement(By.id("login_button")).click();
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        driver.switchTo().frame("mainFrame");
//        driver.findElement(By.id("folder_1")).click();
//
//    }
//
//    @Test
//    public void testHtmlDriver(){
//
//        WebDriver driver = new HtmlUnitDriver(false);
//        driver.get("https://mail.qq.com");
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        driver.switchTo().frame("login_frame");
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        WebElement user = driver.findElement(By.id("u"));
//        WebElement passwd = driver.findElement(By.id("p"));
//        user.sendKeys("575707315");
//        passwd.sendKeys("mmm");
//        driver.findElement(By.id("login_button")).click();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.switchTo().frame("mainFrame");
//        driver.findElement(By.id("folder_1")).click();
//    }
//
//    /**
//     * 使用无界面的浏览器进行UI测试 HtmlUnitDriver
//     */
//    @Test
//    public void doSearch() {
//        String url = "http://www.baidu.com";
//        WebDriver driver = new HtmlUnitDriver(true);
//        driver.get(url);
//        driver.findElement(By.id("kw")).sendKeys("test");
//        Actions action = new Actions(driver);
//        action.sendKeys(Keys.ENTER).perform();
////        System.out.println(driver.getPageSource());
//    }
//
//
//    @After
//    public void clean(){
////        WebDriverFactory.getWebDriverInstance().closeWebDriver();
//    }
//}
