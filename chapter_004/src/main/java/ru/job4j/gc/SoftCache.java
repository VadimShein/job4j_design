package ru.job4j.gc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache implements Cache {
    private Map<String, SoftReference<String>> cacheMap = new ConcurrentHashMap<>();

    public String get(String key) {
        String rsl;
        if (!cacheMap.containsKey(key)) {
            String value = download(key);
            SoftReference<String> vol = new SoftReference<>(value);
            cacheMap.put(key, vol);
            rsl = value;
        } else {
            rsl = cacheMap.get(key).get();
        }
        return rsl;
    }

    public static String download(String key) {
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
        return rsl.toString();
    }

    public static void main(String[] args) {
        Cache sf = new SoftCache();
        String rsl = sf.get("Address.txt");
        System.out.println(rsl);
    }
}
