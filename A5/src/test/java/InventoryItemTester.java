

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import ucf.assignments.InventoryItem;

public class InventoryItemTester {

    @Test
    public void ensure_setname_catches_numeric_sets(){
        InventoryItem item = new InventoryItem();

        assertFalse(item.setItemName("12232"));

    }

    @Test
    public void ensure_setname_catches_short_names(){
        InventoryItem item = new InventoryItem();

        assertFalse(item.setItemName("h"));

    }

    @Test
    public void ensure_setname_catches_too_long_names(){
        InventoryItem item = new InventoryItem();
        String string = "";

        for(int i = 0 ; i < 300; i++){
          string = string.concat("a");
        }


        assertFalse(item.setItemName(string));
    }


    @Test
    public void ensure_inventoryitem_catches_inproper_serialnum_sets(){
        InventoryItem item = new InventoryItem();



        assertFalse(item.setSerialNum("$$$$$$$$$$"));
    }

    @Test
    public void ensure_setserial_catches_to_short_serialnums(){
        InventoryItem item = new InventoryItem();



        assertFalse(item.setSerialNum("aaaaaaaaaaaa"));
    }

    @Test
    public void ensure_setserial_catches_to_long_serialnums(){
        InventoryItem item = new InventoryItem();



        assertFalse(item.setSerialNum("aa"));
    }

    @Test
    public void ensure_setVal_catches_non_numeric_input(){
        InventoryItem item = new InventoryItem();



        assertFalse(item.setMonetaryValue("hi"));
    }

    @Test
    public void ensure_proper_names_get_set(){
        InventoryItem item = new InventoryItem();



        assertTrue(item.setItemName("valid name"));


    }

    @Test
    public void ensure_proper_serialnum_gets_set(){
        InventoryItem item = new InventoryItem();



        assertTrue(item.setSerialNum("12345ggggg"));
    }

    @Test
    public void ensure_proper_monetary_value_gets_set(){
        InventoryItem item = new InventoryItem();



        assertTrue(item.setMonetaryValue("200.14"));
    }

    @Test
    public void get_monetaryValue_returns_formatted_string(){
        InventoryItem item = new InventoryItem();

        item.setMonetaryValue("245.5666");

        assertEquals("$245.57",item.getMonetaryValue());


    }

    @Test
    public void set_monetary_value_rejects_blank_strings(){
        InventoryItem item = new InventoryItem();



        assertFalse(item.setMonetaryValue(""));
    }

}
