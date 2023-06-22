package org.example.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

public class Service {
    public void service(String id){
        Jedis jedis = new Jedis();

        String value = jedis.get("id:"+id);
        try{
            if (value == null){
                jedis.setex("id:"+id,10,Long.MAX_VALUE-10+"");

            } else {
                Long val = jedis.incr("id:" + id);
                business(id,val);
            }
        } catch (JedisDataException e){
            System.out.println("You have reached the usage limit, please upgradex");
            return;
        } finally {
            jedis.close();
        }
    }
    public void business(String id, Long val){
        val = Long.MAX_VALUE-val+1;
        System.out.println("User "+id+" processing "+val);
    }

}
