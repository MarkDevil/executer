import com.mark.test.framework.core.task.SynDataBaseTask;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by MingfengMa .
 * Data   : 2017/7/12
 * Author : mark
 * Desc   :
 */

public class TestService {
    Logger logger = LoggerFactory.getLogger(TestService.class);

    @Test
    public void testSynDatabase(){
        SynDataBaseTask synDataBaseTask = new SynDataBaseTask();
        synDataBaseTask.run();
    }
}
