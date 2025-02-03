package com.pyramid.tech.core.cache;

import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactory;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.transcoders.SerializingTranscoder;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Created by Suvorov Vassilievitch
 * Date: 03/02/2025
 * Time: 21:28
 * Project Name: pyramid-game-security-oauth2
 */

@Slf4j
public class Memcached implements Cache {

    private String name;
    private MemcachedClient cache;
    private int expiration;

    public Memcached(String name, String memcacheAddresses, int expiration) throws IOException {
        this.name = name;
        this.expiration = expiration;
        cache = new MemcachedClient(
                new ConnectionFactoryBuilder()
                        .setTranscoder(new SerializingTranscoder())
                        .setProtocol(ConnectionFactoryBuilder.Protocol.BINARY)
                        .build(),
                AddrUtil.getAddresses(memcacheAddresses)
        );
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return cache;
    }

    @Override
    public ValueWrapper get(final Object key) {
        Object value = null;
        try {
            value = cache.get(key.toString());
        } catch(final Exception e) {
            log.warn(e.getMessage());
        }
        if (value == null) {
            log.debug("cache miss for key: " + key.toString());
            return null;
        }
        log.debug("cache hit for key: " + key.toString());
        return new SimpleValueWrapper(value);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {

    }

    @Override
    public void evict(final Object key) {
        this.cache.delete(key.toString());
        log.debug("cache delete for key: " + key.toString());
    }

    @Override
    public void clear() {
        cache.flush();
        log.debug("cache clea completed");
    }
}
