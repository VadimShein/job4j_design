package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Storage> storages = new ArrayList<>();

    public void addStorage(Storage storage) {
        storages.add(storage);
    }

    public void distribute(Food food) {
        for (Storage str : storages) {
            if (str.accept(food)) {
                str.add(food);
                break;
            }
        }
    }
}
