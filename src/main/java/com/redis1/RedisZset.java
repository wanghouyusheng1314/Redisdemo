package com.redis1;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Redis set数据类型
 *
 */
public class RedisZset {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1",6379);
		System.out.println(jedis.flushDB());
		String key = "mysortset";
		Map scoreMembers = new HashMap();
		scoreMembers.put("aaa", 1001.0);
		scoreMembers.put("bbb", 1002.0);
		scoreMembers.put("ccc", 1003.0);
		// 添加数据
		jedis.zadd(key, 1004.0, "ddd");
		jedis.zadd(key, scoreMembers);
		// 获取一个排序的集合中的成员数量
		System.out.println(jedis.zcard(key));
		// 返回的成员在指定范围内的有序集合，以0表示有序集第一个成员，以1表示有序集第二个成员，以此类推。
		// 负数下标，以-1表示最后一个成员，-2表示倒数第二个成员
		Set<String> coll = jedis.zrange(key, 0, -1);
		System.out.println(coll);
		// 返回的成员在指定范围内的逆序集合
		coll = jedis.zrevrange(key, 0, -1);
		System.out.println(coll);
		// 元素下标
		System.out.println(jedis.zscore(key, "bbb"));
		// 删除元素
		System.out.println(jedis.zrem(key, "aaa"));
		System.out.println(jedis.zrange(key, 0, -1));
		// 给定值范围内的成员数
		System.out.println(jedis.zcount(key, 1002.0, 1003.0));
	}




}
