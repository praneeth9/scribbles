package com.vijayrc.scribble.redis.sample;

import lombok.extern.log4j.Log4j;
import redis.clients.jedis.Jedis;

@Log4j
public class KeyValueSample extends BaseSample {

    @Override
    public void run() throws Exception {
        Jedis jedis = newJedis();

        jedis.set("key_1", "value_1");
        jedis.append("key_1", " value_1.1");
        log.info(" key_1 => " + jedis.get("key_1"));

        jedis.incr("key_2");
        log.info(" key_2 => " + jedis.get("key_2"));
        jedis.incrBy("key_2", 3);
        log.info(" key_2 => " + jedis.get("key_2"));
        jedis.decrBy("key_2", 2);
        log.info(" key_2 => " + jedis.get("key_2"));

        jedis.set("key_3", "value_3");
        log.info(" key_3 => " + jedis.get("key_3"));
        jedis.expire("key_3", 2);
        Thread.sleep(3000);
        log.info(" key_3 => " + jedis.get("key_3"));

        jedis.del("key_1", "key_2", "key_3");
    }

}
