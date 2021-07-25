


import org.junit.jupiter.api.Test;
import ucf.assignments.InventoryItem;
import ucf.assignments.InventoryManager;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryManagerTest {


    @Test
    public void ensure_manager_finds_searched_for_items(){
        //generate inventorymanager with items in it
        InventoryManager testManager = new InventoryManager();

        for(int i = 0; i < 10 ; i ++){
            InventoryItem item = new InventoryItem();
            item.setItemName("name");
            item.setMonetaryValue("200");
            item.setSerialNum("1234567890");
            testManager.itemInventory.add(item);
        }


        InventoryItem testItem = new InventoryItem();
        testItem.setItemName("test");
        testItem.setSerialNum("1234567890");
        testItem.setMonetaryValue("200");

        testManager.itemInventory.add(testItem);
        //search for specific test item

        //assert true item found = test item
        assertTrue(testManager.searchInventory("test").contains(testItem));
    }

    @Test
    public void ensure_manager_can_find_item_based_on_serial_num(){
        //generate inventorymanager with items in it
        InventoryManager testManager = new InventoryManager();

        for(int i = 0; i < 10 ; i ++){
            InventoryItem item = new InventoryItem();
            item.setItemName("name");
            item.setMonetaryValue("200");
            item.setSerialNum("1234567890");
            testManager.itemInventory.add(item);
        }


        InventoryItem testItem = new InventoryItem();
        testItem.setItemName("name");
        testItem.setSerialNum("1234567891");
        testItem.setMonetaryValue("200");

        testManager.itemInventory.add(testItem);
        //search for specific test item

        //assert true item found = test item
        assertTrue(testManager.searchInventory("1234567891").contains(testItem));
    }
}


