package ru.job4j.gc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache<K, V> implements Cache<K, V> {
    private Map<K, SoftReference<V>> cacheMap = new ConcurrentHashMap<>();
    private Map<SoftReference<V>, K> dCacheMap = new ConcurrentHashMap<>();
    private ReferenceQueue<V> refQueue =  new  ReferenceQueue<>();

    @Override
    public V get(K key) {
        SoftReference<V> ref;
        while ((ref = (SoftReference<V>) refQueue.poll()) != null) {
            System.out.println("GC is work ------------------");
            System.out.println(ref + " " + dCacheMap.get(ref) + " " + cacheMap.get(dCacheMap.get(ref)));
            cacheMap.remove(dCacheMap.get(ref));
        }
        V rsl;
        if (!cacheMap.containsKey(key)) {
            V value = download(key);
            SoftReference<V> vol = new SoftReference<>(value, refQueue);
            cacheMap.put(key, vol);
            dCacheMap.put(vol, key);
            rsl = value;
            System.out.println("cache size: " + cacheMap.size());
        } else {
            rsl = cacheMap.get(key).get();
        }
        return rsl;
    }

    @Override
    public V download(K key) {
        String path = "./chapter_004/src/main/java/ru/job4j/gc/data/" + key;
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = in.readLine()) != null) {
                rsl.append(line).append(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (V) rsl.toString();
    }

    public static void main(String[] args) {
        Cache<String, String> sf = new SoftCache<>();
        System.out.println(sf.get("Address.txt"));
        System.out.println(sf.get("Address.txt"));
    }
}
