package ru.job4j.it;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int rsl = -1;
        int index = indexOf(id);
        if (index != -1) {
            mem.set(index, model);
            rsl = index;
        }
        return rsl != -1;
    }

    @Override
    public boolean delete(String id) {
        int rsl = -1;
        int index = indexOf(id);
        if (index != -1) {
            mem.remove(index);
            rsl = index;
        }
        return rsl != -1;
    }

    @Override
    public T findById(String id) {
        int index = indexOf(id);
        return index != -1 ? mem.get(index) : null;
    }

      private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < mem.size(); index++) {
            if (mem.get(index).getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

//    private int indexOf(String id) {
//        int rsl = -1;
//        return  mem.stream()
//                .filter(e -> e.getId().equals(id))
//                .findAny();
//    }
}