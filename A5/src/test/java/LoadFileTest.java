/*
 *
 *  *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  *  Copyright 2021 Kevin Kant
 *
 */

import org.junit.jupiter.api.Test;
import ucf.assignments.InventoryManager;
import ucf.assignments.LoadFile;

import static org.junit.jupiter.api.Assertions.*;

public class LoadFileTest {

    String TSVPath = "C:\\Users\\Kevin\\Desktop\\Git Projects\\Kant-6125-a5\\A5\\src\\test\\FileIOHolder\\TSV.txt";

    String HTMLPath = "C:\\Users\\Kevin\\Desktop\\Git Projects\\Kant-6125-a5\\A5\\src\\test\\FileIOHolder\\file.html";


    @Test
    public void ensure_can_generate_inventory_from_tsv_value_test(){
        LoadFile fileLoader = new LoadFile(TSVPath);

        InventoryManager testInventory = fileLoader.loadInventory();



        assertEquals("$420.69",fileLoader.loadInventory().itemInventory.get(0).getMonetaryValue());

    }

    @Test
    public void ensure_can_generate_inventory_from_tsv_name_test(){
        LoadFile fileLoader = new LoadFile(TSVPath);

        InventoryManager testInventory = fileLoader.loadInventory();



        assertEquals("name",fileLoader.loadInventory().itemInventory.get(0).getItemName());

    }

    @Test
    public void ensure_can_generate_inventory_from_tsv_serial_test(){
        LoadFile fileLoader = new LoadFile(TSVPath);

        InventoryManager testInventory = fileLoader.loadInventory();



        assertEquals("1234567890",fileLoader.loadInventory().itemInventory.get(0).getSerialNum());

    }

    @Test
    public void ensure_can_generate_inventory_from_tsv_got_all_items(){
        LoadFile fileLoader = new LoadFile(TSVPath);

        InventoryManager testInventory = fileLoader.loadInventory();



        assertEquals(5,fileLoader.loadInventory().itemInventory.size());

    }

    @Test
    public void ensure_can_generate_inventory_from_html_name_test(){
        LoadFile fileLoader = new LoadFile(HTMLPath);

        InventoryManager testInventory = fileLoader.loadInventory();

        assertEquals("name",testInventory.itemInventory.get(0).getItemName());
    }

    @Test
    public void ensure_can_generate_inventory_from_html_serial_test(){
        LoadFile fileLoader = new LoadFile(HTMLPath);

        InventoryManager testInventory = fileLoader.loadInventory();

        assertEquals("1234567890",testInventory.itemInventory.get(0).getSerialNum());
    }

    @Test
    public void ensure_can_generate_inventory_from_html_value_test(){
        LoadFile fileLoader = new LoadFile(HTMLPath);

        InventoryManager testInventory = fileLoader.loadInventory();

        assertEquals("$420.69",testInventory.itemInventory.get(0).getMonetaryValue());
    }

    @Test
    public void ensure_can_generate_inventory_from_html_got_all_test(){
        LoadFile fileLoader = new LoadFile(HTMLPath);

        InventoryManager testInventory = fileLoader.loadInventory();

        assertEquals(5,testInventory.itemInventory.size());
    }

}
