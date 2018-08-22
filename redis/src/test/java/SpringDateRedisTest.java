import com.kaishengit.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author guojiabang
 * @date 2018/8/22 0022
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-data-redis.xml")
public class SpringDateRedisTest {


    private RedisTemplate<String,User> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        //this.redisTemplate.setValueSerializer(new StringRedisSerializer());
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
    }

    @Test
    public void setUserToRedis(){
        User user = new User();
        user.setId(1002);
        user.setUserName("tom");
        user.setAddress("hnkf");

        redisTemplate.opsForValue().set("username:1002",user);
    }

    @Test
    public void getUserFromRedis(){
        ValueOperations<String, User> stringUserValueOperations = redisTemplate.opsForValue();
        User user = stringUserValueOperations.get("username:1002");

        System.out.println(user);

    }

    /*@Test
    public void setStringValue(){

        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        //valueOperations.set("user:1001","lisi");
        valueOperations.set("user:1002","lisi");
    }

    @Test
    public void  getStringValue(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object obj = valueOperations.get("user:1001");

        System.out.println(obj);

    }*/

}
