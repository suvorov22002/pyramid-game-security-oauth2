package com.pyramid.tech.core.config;

import com.pyramid.tech.core.cache.Memcached;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.*;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Suvorov Vassilievitch
 * Date: 03/02/2025
 * Time: 21:47
 * Project Name: pyramid-game-security-oauth2
 */

@Configuration
public class MemcachedConfiguration implements CachingConfigurer {

    @Value("${memcached.addresses}")
    private String memcachedAddresses;

    @Value("${memcached.expiration.sec}")
    private int expirationSec;

    @Override
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        try {
            cacheManager.setCaches(internalCaches());
            return cacheManager;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Collection<Memcached> internalCaches() throws IOException {
        final Collection<Memcached> caches = new ArrayList<>();
        System.out.println("memcachedAddresses: " + memcachedAddresses);
        String[] var2 = memcachedAddresses.split("(?:\\s|,)+");
        System.out.println("memcachedAddresses var2: " + var2[0]);
        caches.add(new Memcached("usersCache", memcachedAddresses, expirationSec));
        return caches;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new SimpleCacheErrorHandler();
    }

    @Override
    public CacheResolver cacheResolver() {
        return null;
    }
}
