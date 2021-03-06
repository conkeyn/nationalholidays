package com.ykaoqin.kit.config;

import com.jarvis.cache.ICacheManager;
import com.jarvis.cache.autoconfigure.AutoloadCacheProperties;
import com.jarvis.cache.clone.ICloner;
import com.jarvis.cache.map.MapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoloadCacheConfiguration {
    @Bean
    public ICacheManager mapCacheManager(AutoloadCacheProperties config, ICloner cloner) {
        return new MapCacheManager(config.getConfig(), cloner);
    }
}
