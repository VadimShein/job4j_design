package ru.job4j.synch;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class Cache {
    private final  ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();

    public void add(Base model) {
        map.put(model.getId(), model);
    }

    public void update(Base model) {
        int id = model.getId();
        int oldVersion = map.get(model.getId()).getVersion();

        map.computeIfPresent(id, (key, val) -> {
                    if (oldVersion != val.getVersion()) {
                        OptimisticException ex = new OptimisticException();
                        ex.getException();
                    }
            val.setVersion(val.getVersion() + 1);
                    return val;
                }
        );
    }

    public void delete(Base model) {
        map.remove(model.getId());
    }

    public Base get(int id) {
        return map.get(id);
    }
}
