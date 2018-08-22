import org.junit.Test;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author guojiabang
 * @date 2018/8/22 0022
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class SpringJedisTest {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void setString(){

        Jedis jedis = jedisPool.getResource();
        jedis.set("name","zhangsan");

        jedis.close();

    }

    @Test
    public void getString(){

        Jedis jedis = jedisPool.getResource();
        String name = jedis.get("name");

        System.out.println("name:" + name);

        jedis.close();
    }


}
