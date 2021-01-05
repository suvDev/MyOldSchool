package com.myoldschool.manager;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableCaching
@ComponentScan(basePackages = {"com.myoldschool.manager", "test"})
@PropertySource("classpath:data")
public class Config extends CachingConfigurerSupport {

    @Bean
    public net.sf.ehcache.CacheManager enCacheManager(){
        CacheConfiguration tenSecondsCache  = new CacheConfiguration();
        tenSecondsCache.setMaxEntriesLocalHeap(1000);
        tenSecondsCache.setName(CacheConstant.TEN_SECONDS_CACHE);
        tenSecondsCache.setMemoryStoreEvictionPolicy(CacheConstant.MEMORY_STORE_EVICTION_POLICY);
        tenSecondsCache.setTimeToLiveSeconds(10);

        CacheConfiguration twentySecondsCache  = new CacheConfiguration();
        twentySecondsCache.setMaxEntriesLocalHeap(1000);
        twentySecondsCache.setName(CacheConstant.TWENTY_SECONDS_CACHE);
        twentySecondsCache.setMemoryStoreEvictionPolicy(CacheConstant.MEMORY_STORE_EVICTION_POLICY);
        twentySecondsCache.setTimeToLiveSeconds(20);

        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(tenSecondsCache);
        config.addCache(twentySecondsCache);

        return net.sf.ehcache.CacheManager.newInstance(config);
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(enCacheManager());
    }
}
