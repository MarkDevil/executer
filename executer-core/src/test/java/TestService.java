import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by mark .
 * Data   : 2017/7/12
 * Author : mark
 * Desc   :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-xml/*.xml")
public class TestService {
    Logger logger = LoggerFactory.getLogger(TestService.class);


    @Test
    public void testSynDatabase(){
//        SynDataBaseTask synDataBaseTask = new SynDataBaseTask();
//        synDataBaseTask.run();
    }


    @Test
    public void testUserInfo(){

    }
}
