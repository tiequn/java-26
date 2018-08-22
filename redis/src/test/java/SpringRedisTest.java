import com.alibaba.fastjson.JSON;
import com.kaishengit.entity.User;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/8/21 0021
 */
public class SpringRedisTest {

    @Test
    public void setString(){

        // 获取jedis连接
        Jedis jedis = new Jedis("192.168.1.124",6379);
        // 设置字符串 key value
        jedis.set("name","tom");
        // 释放连接
        jedis.close();
    }

    @Test
    public void getStringValue(){

        Jedis jedis = new Jedis("192.168.1.124",6379);
        String name = jedis.get("name");

        System.out.println("naem:" + name);

        jedis.close();
    }

    @Test
    public void setHash(){

        Jedis jedis = new Jedis("192.168.1.124",6379);

        jedis.hset("user:1001","name","lisi");
        Map<String,String> params = new HashMap<String, String>();
        params.put("id","1001");
        params.put("age","22");
        params.put("address","kf");

        jedis.hmset("user:1001",params);

        jedis.close();

    }

    @Test
    public void getHashValue(){
        Jedis jedis = new Jedis("192.168.1.124",6379);
       //  Object arras = jedis.hget("user:1001","name");
        Object[] array = jedis.hkeys("user:1001").toArray();
        String[] strArray = new String[array.length];
        for(int i = 0; i < array.length; i++){
            Object obj = array[i];
            String str = obj.toString();
            strArray[i] = str;
        }
        List<String> values = jedis.hmget("user:1001",strArray);
        for (String value : values){
            System.out.println(value);
        }

        jedis.close();

    }

    @Test
    public void setUser(){

        Jedis jedis = new Jedis("192.168.1.124",6379);

        User user = new User();
        user.setId(99);
        user.setUserName("tom");
        user.setAddress("kf");
        String json = JSON.toJSONString(user);
        jedis.set("user:10002",json);

        jedis.close();

    }


    @Test
    public void getUserValue(){
        Jedis jedis = new Jedis("192.168.1.124",6379);
        String json = jedis.get("user:10002");
        User user = JSON.parseObject(json,User.class);

        System.out.println(user);

        jedis.close();
    }


    @Test
    public void setUser2(){
        // 连接池连接
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(30);
        config.setMinIdle(5);

        JedisPool jedisPool = new JedisPool(config,"192.168.1.124",6379);
        Jedis jedis = jedisPool.getResource();

        jedis.set("address","jack");

        jedis.close();

    }


}
