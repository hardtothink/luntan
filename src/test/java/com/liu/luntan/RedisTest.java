package com.liu.luntan;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.ContextConfiguration;

import java.util.concurrent.TimeUnit;

//@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(
        classes = {LuntanApplication.class}
)
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    public RedisTest() {
    }

    @Test
    public void testStrings() {
        String redisKey = "test:count";
        this.redisTemplate.opsForValue().set(redisKey, 1);
        System.out.println(this.redisTemplate.opsForValue().get(redisKey));
        System.out.println(this.redisTemplate.opsForValue().increment(redisKey));
        System.out.println(this.redisTemplate.opsForValue().decrement(redisKey));
    }

    @Test
    public void testHashes() {
        String redisKey = "test:user";
        this.redisTemplate.opsForHash().put(redisKey, "id", 1);
        this.redisTemplate.opsForHash().put(redisKey, "username", "zhangsan");
        System.out.println(this.redisTemplate.opsForHash().get(redisKey, "id"));
        System.out.println(this.redisTemplate.opsForHash().get(redisKey, "username"));
    }

    @Test
    public void testLists() {
        String redisKey = "test:ids";
        this.redisTemplate.opsForList().leftPush(redisKey, 101);
        this.redisTemplate.opsForList().leftPush(redisKey, 102);
        this.redisTemplate.opsForList().leftPush(redisKey, 103);
        System.out.println(this.redisTemplate.opsForList().size(redisKey));
        System.out.println(this.redisTemplate.opsForList().index(redisKey, 0L));
        System.out.println(this.redisTemplate.opsForList().range(redisKey, 0L, 2L));
        System.out.println(this.redisTemplate.opsForList().leftPop(redisKey));
        System.out.println(this.redisTemplate.opsForList().leftPop(redisKey));
        System.out.println(this.redisTemplate.opsForList().leftPop(redisKey));
    }

    @Test
    public void testSets() {
        String redisKey = "test:teachers";
        this.redisTemplate.opsForSet().add(redisKey, new Object[]{"刘备", "关羽", "张飞", "赵云", "诸葛亮"});
        System.out.println(this.redisTemplate.opsForSet().size(redisKey));
        System.out.println(this.redisTemplate.opsForSet().pop(redisKey));
        System.out.println(this.redisTemplate.opsForSet().members(redisKey));
    }

    @Test
    public void testSortedSets() {
        String redisKey = "test:students";
        this.redisTemplate.opsForZSet().add(redisKey, "唐僧", 80.0);
        this.redisTemplate.opsForZSet().add(redisKey, "悟空", 90.0);
        this.redisTemplate.opsForZSet().add(redisKey, "八戒", 50.0);
        this.redisTemplate.opsForZSet().add(redisKey, "沙僧", 70.0);
        this.redisTemplate.opsForZSet().add(redisKey, "白龙马", 60.0);
        System.out.println(this.redisTemplate.opsForZSet().zCard(redisKey));
        System.out.println(this.redisTemplate.opsForZSet().score(redisKey, "八戒"));
        System.out.println(this.redisTemplate.opsForZSet().reverseRank(redisKey, "八戒"));
        System.out.println(this.redisTemplate.opsForZSet().reverseRange(redisKey, 0L, 2L));
    }

    @Test
    public void testKeys() {
        this.redisTemplate.delete("test:user");
        System.out.println(this.redisTemplate.hasKey("test:user"));
        this.redisTemplate.expire("test:students", 10L, TimeUnit.SECONDS);
    }

    @Test
    public void testBoundOperations() {
        String redisKey = "test:count";
        BoundValueOperations operations = this.redisTemplate.boundValueOps(redisKey);
        operations.increment();
        operations.increment();
        operations.increment();
        operations.increment();
        operations.increment();
        System.out.println(operations.get());
    }

    @Test
    public void testTransactional() {
        Object obj = this.redisTemplate.execute(new SessionCallback() {
            public Object execute(RedisOperations operations) throws DataAccessException {
                String redisKey = "test:tx";
                operations.multi();
                operations.opsForSet().add(redisKey, new Object[]{"zhangsan"});
                operations.opsForSet().add(redisKey, new Object[]{"lisi"});
                operations.opsForSet().add(redisKey, new Object[]{"wangwu"});
                System.out.println(operations.opsForSet().members(redisKey));
                return operations.exec();
            }
        });
        System.out.println(obj);
    }
}
