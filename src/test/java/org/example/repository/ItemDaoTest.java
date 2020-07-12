package org.example.repository;

import org.example.ApplicationBootstrap;
import org.example.model.Item;
import org.example.model.Rating;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class ItemDaoTest {
    @Autowired
    private ItemDao itemDao;
    private Item item1;

    @Before
    public void SetUp(){
        item1 = new Item();
        item1.setItemId(0);
        item1.setItemName("LAY'S CHIPS");
        item1.setItemDescription("cucumber chips");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        item1.setFirstCameOut(timestamp);
        item1=itemDao.save(item1);
    }
    @After
    public void tearDown(){
        itemDao.delete(item1);
    }

    @Test
    public void getItemsTest(){
        List<Item> items = itemDao.getItems();
        int expectedNumberOfItem = 1;
        assertEquals(expectedNumberOfItem, items.size());
    }

}
