import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by MingfengMa .
 * Data : 2017/3/20
 * Desc :
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:executer-servlet.xml")
@WebAppConfiguration
public class TestMockController extends AbstractJUnit4SpringContextTests{

    Logger logger = LoggerFactory.getLogger(TestMockController.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    MockMvc mockMvc;

    @Test
    public void mockTest() throws Exception {
          mockMvc.perform(get("/api/queryPage"))
          .andDo(print());
    }
}
