package com.xujie.mysecret.cache;

/**
 * @author xujie17
 */
public interface ICache<K,V> {

    /**
     * 存储缓存
     * @param k k
     * @param v v
     */
    void save(K k, V v);

    /**
     * 获取缓存
     * @param k k
     * @return v
     */
    V get(K k);
}
