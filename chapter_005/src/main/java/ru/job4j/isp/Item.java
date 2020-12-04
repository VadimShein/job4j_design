package ru.job4j.isp;

import java.util.List;

public interface Item {
  //  void add();
  String getItemName();
    void addSubItem(Item subItem);
    void print();
    void performAction();
    List<Item> getSubItems();
}
