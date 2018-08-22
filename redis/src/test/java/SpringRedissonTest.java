import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RBuckets;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author guojiabang
 * @date 2018/8/22 0022
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redisson.xml")
public class SpringRedissonTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void setStringValue(){
        RBucket<Object> username = redissonClient.getBucket("username");
        username.set("jack");
    }

    @Test
    public void getStringValue() throws IOException {

        RBucket<Object> username = redissonClient.getBucket("username");
        username.get();

    }

}
