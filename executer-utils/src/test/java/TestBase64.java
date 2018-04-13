import com.mark.test.framework.util.Base64Utils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mark .
 * Data   : 2018/3/30
 */

public class TestBase64 {
    private Logger logger = LoggerFactory.getLogger(TestBase64.class);
    private String source = "mark@gmail.com";

    @Test
    public void testEncode(){
        String ret = Base64Utils.encode64(source);
        logger.info("source string ：{} ,after encode: {}",source,ret);
        logger.info(Base64Utils.decode64(ret));
    }
}
