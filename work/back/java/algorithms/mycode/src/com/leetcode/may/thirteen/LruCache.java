package com.leetcode.may.thirteen;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This is Description
 *
 * @author yang
 * @date 2020/06/05
 */
public class LruCache<K,V> extends LinkedHashMap<K,V> {

    private final int CACHE;

    public LruCache(int cache){
        super(cache,0.75f,true);
        this.CACHE = cache;
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size() >= CACHE;
    }

    public void test(){
        LruCache<String, String> cache = new LruCache<String, String>(4);
        cache.put("1","1");
        cache.put("2","2");
        cache.put("3","3");
        System.out.println(cache.size());
        cache.put("4","4");
        cache.put("5","5");
        System.out.println(cache.size());
        for (String str:cache.keySet()) {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        long a = 1;
        long b = a << 10;
        System.out.println(b);

    }
}
