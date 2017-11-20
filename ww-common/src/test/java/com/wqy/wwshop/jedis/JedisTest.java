package com.wqy.wwshop.jedis;
/*

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

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
    @Test
    public void testJedis3(){
        //创建集群节点
        Set<HostAndPort> nodes=new HashSet<>();
        //将6个节点加入到集合中
        nodes.add(new HostAndPort("10.31.161.28",9001));
        nodes.add(new HostAndPort("10.31.161.28",9002));
        nodes.add(new HostAndPort("10.31.161.28",9003));
        nodes.add(new HostAndPort("10.31.161.28",9004));
        nodes.add(new HostAndPort("10.31.161.28",9005));
        nodes.add(new HostAndPort("10.31.161.28",9006));
        //创建集群对象
        JedisCluster jedisCluster = new JedisCluster(nodes);
        //存入数据
        jedisCluster.set("key2","value2");
        System.out.println(jedisCluster.get("key2"));
        //关闭连接
        jedisCluster.close();


    }

}
*/
