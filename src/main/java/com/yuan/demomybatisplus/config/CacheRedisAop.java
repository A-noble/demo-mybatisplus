package com.yuan.demomybatisplus.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yuan.demomybatisplus.annotate.CacheRedis;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author MuXue
 * @create 2022-07-13  17:47 PM
 */
@Component
@Aspect
public class CacheRedisAop {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 声明泛型的时候自动注入 @Autowired 会失效，这里使用 @Resource 注解
     * 这种可以操作任意类型的数据，但是存入 redis 的数据为二进制，难以直接查看
     */
//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;


    // 该类只能操作字符类型的数据，存入到 Redis 的为字符串，查看比较友好
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 环绕通知，拦截 redis 的写入和读取，如果方法执行结果为 null 不进行保存
     *
     * @param point      切点对象
     * @param cacheRedis 注解内容
     */
    @Around(value = "@annotation(cacheRedis)", argNames = "point,cacheRedis")
    public Object aop(ProceedingJoinPoint point, CacheRedis cacheRedis) throws Throwable {
        Map<String, Object> param = this.mapParam(point);
        String key = this.buildKey(cacheRedis, param);
        Object value = redisTemplate.opsForValue().get(key);
        if (null == value) {
            value = point.proceed();
            if (value != null) {
                String json = JSON.toJSONString(value, SerializerFeature.WriteMapNullValue);
                if (cacheRedis.deadline() == -1L) {
                    redisTemplate.opsForValue().set(key, json);
                } else {
                    redisTemplate.opsForValue().set(key, json, cacheRedis.deadline(), TimeUnit.SECONDS);
                }
            }
            return value;
        }
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        String className = method.getReturnType().getName();
        Class<?> clazz = Class.forName(className);
        value = JSONObject.parseObject(value.toString(), clazz);
        return value;
    }

    /**
     * 获取参数签名 和 参数值，并封装为 MAP 集合
     *
     * @param point 切点
     */
    private Map<String, Object> mapParam(ProceedingJoinPoint point) {
        Map<String, Object> map = new HashMap<>();
        String[] names = ((CodeSignature) point.getSignature()).getParameterNames();
        Object[] values = point.getArgs();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], values[i]);
        }
        return map;
    }



    /**
     * 拼装 Redis 的健，具体规则：前缀 + :参数 + :参数 ......
     * 对于值为空，或者值为无效字符串的，忽略
     * 目前只支持 参数类型 为基础类型或字符串，如果有兴趣可以自行扩展该方法，比如通过反射机制实现支持类里面的元素
     */
    private String buildKey(CacheRedis cacheRedis, Map<String, Object> param) {
        StringBuilder key = new StringBuilder(32);
        key.append(cacheRedis.prefix());
        for (int i = 0; i < cacheRedis.element().length; i++) {
            String name = cacheRedis.element()[i];
            Object value = param.get(name);
            if (value == null) {
                continue;
            }
            String str = value.toString();
            if (StringUtils.isBlank(str)) {
                continue;
            }
            key.append(":").append(str);
        }
        return key.toString();
    }
}
