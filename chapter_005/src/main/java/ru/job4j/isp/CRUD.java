package ru.job4j.isp;

public interface CRUD {
    boolean add(String parent, String node, Action action);
//    boolean remove(K node);
//    boolean update(K note, V action);
     Action get(String node);
}