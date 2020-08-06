package org.example.repository;

import org.example.model.Item;

import java.util.List;

public interface ItemDao {
    Item save(Item item);
    Item update(Item item);
    boolean delete(Item item);
    List<Item> getItems();
    Item getBy(Long id);
}
