import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by MingfengMa .
 * Data : 2017/3/20
 * Desc :
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-xml/applicationContext.xml")
public class TestMockController extends AbstractJUnit4SpringContextTests{

    Logger logger = LoggerFactory.getLogger(TestMockController.class);
    MockMvc mockMvc;

    @Test
    public void mockTest() throws Exception {
          mockMvc.perform(get("http://www.baidu.com"))
          .andDo(print());
    }
}
