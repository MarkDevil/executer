import com.codeborne.selenide.Configuration;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by MingfengMa .
 * Data   : 2017/4/10
 * Author : mark
 * Desc   :
 */

public class TestWeb {

    @Test
    public void test1(){
        Configuration.browser= "chrome";
        System.setProperty("webdriver.chrome.driver",this.getClass().getResource("/webdriver/chromedriver").getPath());
        open("https://www.baidu.com/");
        //根据id查找元素，并进行操作
        $("#kw").sendKeys("selenide");
        $("#su").click();
        //断言验证搜索到的信息是否等于selenide
        $(By.xpath("//*[@id=\"1\"]/h3/a/em")).shouldHave(text("selenide"));
    }
}
