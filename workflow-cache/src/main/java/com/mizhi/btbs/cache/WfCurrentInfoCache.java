package com.mizhi.btbs.cache;

import com.mizhi.btbs.domain.dto.WfCurrentInfo;
import net.sf.ehcache.CacheManager;
import org.evanframework.cache.AbstractCache;
import org.evanframework.cache.RedisTemplateCreator;

/**
 *
 * @author chaobo
 * @since 2019/5/27
 */
public class WfCurrentInfoCache extends AbstractCache<WfCurrentInfo> {

    private final static String CACHE_NAME = WfCurrentInfoCache.class.getSimpleName();
    private final static int REDIS_DATABASE_INDEX = 7;

    /**
     * EHCache + Redis
     * @param cacheManager
     * @param redisTemplateCreator
     */
    public WfCurrentInfoCache(CacheManager cacheManager, RedisTemplateCreator redisTemplateCreator) {
        super.init(CACHE_NAME, redisTemplateCreator, REDIS_DATABASE_INDEX, cacheManager);
    }

    /**
     * EHCache + Redis
     * @param cacheManager
     * @param redisTemplateCreator
     * @param dateBaseIndex
     */
    public WfCurrentInfoCache(CacheManager cacheManager, RedisTemplateCreator redisTemplateCreator, int dateBaseIndex) {
        super.init(CACHE_NAME, redisTemplateCreator, dateBaseIndex, cacheManager);
    }

    /**
     * no contains EHCache
     * @param redisTemplateCreator
     * @param dateBaseIndex
     */
    public WfCurrentInfoCache(RedisTemplateCreator redisTemplateCreator, int dateBaseIndex) {
        super.init(CACHE_NAME, redisTemplateCreator, dateBaseIndex);
    }

    /**
     * no contains EHCache
     * @param redisTemplateCreator
     */
    public WfCurrentInfoCache(RedisTemplateCreator redisTemplateCreator) {
        super.init(CACHE_NAME, redisTemplateCreator, REDIS_DATABASE_INDEX);
    }
}
