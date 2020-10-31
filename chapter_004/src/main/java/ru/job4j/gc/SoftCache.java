package ru.job4j.gc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache<K, V> implements Cache<K, V> {
    private Map<K, SoftReference<V>> cacheMap = new ConcurrentHashMap<>();

    @Override
    public V get(K key) {
        V rsl;
        if (!cacheMap.containsKey(key)) {
            V value = download(key);
            SoftReference<V> vol = new SoftReference<>(value);
            cacheMap.put(key, vol);
            rsl = value;
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
        String rsl = sf.get("Address.txt");
        System.out.println(rsl);
    }
}
