import com.mark.test.framework.core.task.PrintLogScheduleTask;
import com.mark.test.framework.util.SchedulerManager;
import com.mark.test.framework.util.WebDriverFactory;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by mark .
 * Data   : 2017/4/10
 * Author : mark
 * Desc   :
 */
public class TestWeb {
    Logger logger = LoggerFactory.getLogger(TestWeb.class);
    WebDriver driver;


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
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.switchTo().frame("mainFrame");
        driver.findElement(By.id("folder_1")).click();

    }





    @Test
    public void testTask(){
        new SchedulerManager().addScheJob(PrintLogScheduleTask.class,"*/5 * * * * ?");
    }


    @Test
    public void testTaskinterval(){

    }


    @After
    public void clean(){
//        WebDriverFactory.getWebDriverInstance().closeWebDriver();
    }
}
