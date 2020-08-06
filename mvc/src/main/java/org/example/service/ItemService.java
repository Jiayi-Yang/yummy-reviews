package org.example.service;

import org.example.model.Item;
import org.example.repository.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemDao itemDao;

    public Item save(Item item){
        return itemDao.save(item);
    }
    public Item update(Item item){
        return itemDao.update(item);
    }
    public boolean delete(Item item){
        return itemDao.delete(item);
    }
    public List<Item> getItems(){
        return itemDao.getItems();
    }
}
