package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, UserS> map = new HashMap<>();

    public synchronized boolean add(UserS user) {
        map.put(user.getId(), UserS.of(user.getId(), user.getAmount()));
        return map.containsKey(user.getId());
    }

    public synchronized boolean update(UserS user) {
        map.put(user.getId(), UserS.of(user.getId(), user.getAmount()));
        return map.containsValue(user);
    }

    public synchronized boolean delete(UserS user) {
        map.remove(user.getId(), UserS.of(user.getId(), user.getAmount()));
        return !map.containsKey(user.getId());
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
        UserS fromUser = map.get(fromId);
        UserS toUser = map.get(toId);

        if (map.containsKey(fromId) & map.containsKey(toId) & fromUser.getAmount() > amount) {
            fromUser.setAmount(fromUser.getAmount() - amount);
            toUser.setAmount(toUser.getAmount() + amount);
            rsl = true;
        }
        return rsl;
    }
}
