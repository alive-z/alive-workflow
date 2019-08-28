//package com.mizhi.btbs.cache.util;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.redis.connection.RedisSentinelConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.lang.reflect.Field;
//import java.util.Vector;
//
///**
// * RedisTemplate 创建器
// * 根据 DatabaseIndex 创建对应的RedisTemplate，可对 redis 的16个数据库进行操作
// *
// * @author shenwei
// * @since 1.0
// */
//public class RedisTemplateCreator {
//    private final static Logger LOGGER = LoggerFactory.getLogger(RedisTemplateCreator.class);
//
//    private final static int DATABASE_COUNT = 16;//Redis16个库
//    //支持多数据库，redis共16个数据库
//    private static final Vector<RedisTemplate> redisTemplates = new Vector<RedisTemplate>(DATABASE_COUNT);
//
//    private JedisConnectionFactory connectionFactory;
//    private RedisSentinelConfiguration sentinelConfig;
//
//    public RedisTemplateCreator(JedisConnectionFactory connectionFactory) {
//        for (int i = 0; i < DATABASE_COUNT; i++) {
//            redisTemplates.add(null);
//        }
//
//        this.connectionFactory = connectionFactory;
//        //this.poolConfig = poolConfig;
//
//        try {
//            Field field = JedisConnectionFactory.class.getDeclaredField("sentinelConfig");
//            field.setAccessible(true);
//            Object o = field.get(connectionFactory);
//            if (o != null) {
//                sentinelConfig = (RedisSentinelConfiguration) o;
//            }
//        } catch (NoSuchFieldException ex) {
//            throw new IllegalStateException("RedisTemplateCreator init fail," + ex.getMessage(), ex);
//        } catch (IllegalAccessException ex) {
//            throw new IllegalStateException("RedisTemplateCreator init fail," + ex.getMessage(), ex);
//        }
//
//        LOGGER.info("RedisTemplateCreator inited,{},{}", connectionFactory, sentinelConfig);
//    }
//
//    public RedisTemplate getRedisTemplate(int databaseIndex) {
//        if (databaseIndex >= DATABASE_COUNT || databaseIndex < 0) {
//            throw new IllegalArgumentException("Redis databaseIndex mush >= 0 and < 16!");
//        }
//
//        RedisTemplate redisTemplate = null;
//
//        if (redisTemplates.get(databaseIndex) == null) { //如果没有，新创建一个redisTemplate
////            JedisPoolConfig poolConfig1 = new JedisPoolConfig();
////            poolConfig1.setMaxIdle(poolConfig.getMaxIdle());
////            poolConfig1.setMinIdle(poolConfig.getMinIdle());
////            poolConfig1.setMaxTotal(poolConfig.getMaxTotal());
////            poolConfig1.setMaxWaitMillis(poolConfig.getMaxWaitMillis());
////            poolConfig1.set
//            JedisConnectionFactory connectionFactory1 = null;
//            if (this.sentinelConfig == null) {
//                connectionFactory1 = new JedisConnectionFactory(connectionFactory.getPoolConfig());
//            } else {
//                connectionFactory1 = new JedisConnectionFactory(this.sentinelConfig, connectionFactory.getPoolConfig());
//            }
//            connectionFactory1.setPassword(connectionFactory.getPassword());
//            connectionFactory1.setPort(connectionFactory.getPort());
//            connectionFactory1.setTimeout(connectionFactory.getTimeout());
//            //connectionFactory1.setClientName(connectionFactory.getClientName());
//            connectionFactory1.setHostName(connectionFactory.getHostName());
//            connectionFactory1.setShardInfo(connectionFactory.getShardInfo());
//            connectionFactory1.setUsePool(connectionFactory.getUsePool());
//            connectionFactory1.setConvertPipelineAndTxResults(connectionFactory.getConvertPipelineAndTxResults());
//            connectionFactory1.setDatabase(databaseIndex);
//            connectionFactory1.afterPropertiesSet();
//
//            redisTemplate = new RedisTemplate();
//            redisTemplate.setConnectionFactory(connectionFactory1);
//            redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//            redisTemplate.setKeySerializer(new StringRedisSerializer());
//            redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//            redisTemplate.afterPropertiesSet();
//            redisTemplates.set(databaseIndex, redisTemplate);
//
//            if (LOGGER.isDebugEnabled()) {
//                LOGGER.debug("RedisTemplate inited, dababase is [{}]", databaseIndex);
//            }
//        } else {
//            redisTemplate = redisTemplates.get(databaseIndex);
//            if (LOGGER.isDebugEnabled()) {
//                LOGGER.debug("get RedisTemplate, dababase is [{}]", databaseIndex);
//            }
//        }
//        return redisTemplate;
//    }
//
//    public void destroy() {
//        for (int i = 0; i < DATABASE_COUNT; i++) {
//            RedisTemplate redisTemplate = redisTemplates.get(i);
//            if (redisTemplate != null) {
//                JedisConnectionFactory JedisConnectionFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
//                JedisConnectionFactory.destroy();
//            }
//        }
//    }
//
//}
