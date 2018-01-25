package main;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by mark .
 * Data   : 2018/1/25
 *
 * @Author : mark
 * Desc   :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:*.xml","classpath*:*.properties"})
public class RedisTest {

}
