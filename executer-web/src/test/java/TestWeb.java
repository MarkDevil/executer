import com.mark.test.framework.utils.WebDriverFactory;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by MingfengMa .
 * Data   : 2017/4/10
 * Author : mark
 * Desc   :
 */

public class TestWeb {
    Logger logger = LoggerFactory.getLogger(TestWeb.class);
    WebDriver driver;
//    @Test
//    public void test1(){
//        Configuration.browser= "chrome";
//        System.setProperty("webdriver.chrome.driver",this.getClass().getResource("/webdriver/chromedriver").getPath());
//        open("https://mail.qq.com/");
//        //根据id查找元素，并进行操作
//        WebDriver webDriver = new ChromeDriver();
//        $("#u").sendKeys("575707315");
//        $("#p").sendKeys("Crystal08102");
//        $("#login_button").click();
//    }

    @Test
    public void testSelenium() throws InterruptedException, IOException {
        driver = WebDriverFactory.getWebDriverInstance().getWebDriver("chrome");
        driver.get("https://mail.qq.com");
        driver.switchTo().frame("login_frame");
        WebElement user = driver.findElement(By.id("u"));
        WebElement passwd = driver.findElement(By.id("p"));
        user.sendKeys("575707315");
        passwd.sendKeys("Crystal08102");
        driver.findElement(By.id("login_button")).click();
//        logger.info(String.valueOf(driver.findElement(By.tagName("iframe"))));
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Thread.sleep(3000);
        driver.switchTo().frame("mainFrame");
        driver.findElement(By.id("folder_1")).click();

    }

    @After
    public void clean(){
        WebDriverFactory.getWebDriverInstance().closeWebDriver();
    }
}
