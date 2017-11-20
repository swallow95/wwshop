package com.wqy.wwshop.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {
    @Test
    public void testJedis(){
        Jedis jedis=new Jedis("10.31.161.28",6379);
        jedis.set("name","wwww");
        System.out.println(jedis.get("name"));
        jedis.close();

    }
    @Test
    public void testJedis1(){
        //获取jedis池
        JedisPool jedisPool = new JedisPool("10.31.152.30",6379);
        //获取Jedis对象
        Jedis jedis = jedisPool.getResource();
        jedis.set("key1","value1");
        System.out.println(jedis.get("key1"));
        //关闭连接
        jedis.close();
        jedisPool.close();
    }


}
