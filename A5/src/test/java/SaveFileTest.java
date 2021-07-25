/*
 *
 *  *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  *  Copyright 2021 Kevin Kant
 *
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ucf.assignments.InventoryItem;
import ucf.assignments.InventoryManager;
import ucf.assignments.SaveFile;

import java.io.IOException;

public class SaveFileTest {

    String dirPath = "C:\\Users\\Kevin\\Desktop\\Git Projects\\Kant-6125-a5\\A5\\src\\test\\FileIOHolder";

    @Test
    public void ensure_save_file_as_html_generates_html_file(){
        SaveFile fileSaver = new SaveFile(dirPath+"\\file.html");

        InventoryManager testInventory = new InventoryManager();

        System.out.println(fileSaver.getFilePath());
        for(int i = 0 ; i < 5; i++){
            InventoryItem testItem = new InventoryItem();
            testItem.setItemName("name");
            testItem.setSerialNum("1234567890");
            testItem.setMonetaryValue("420.69");
            testInventory.itemInventory.add(testItem);
        }

       assertTrue(fileSaver.saveFile(testInventory));
        //fileSaver.deleteFile();
    }

    @Test
    public void ensure_save_file_as_tsv_generates_tsv(){
        SaveFile fileSaver = new SaveFile(dirPath+"\\TSV.txt");

        InventoryManager testInventory = new InventoryManager();

        System.out.println(fileSaver.getFilePath());
        for(int i = 0 ; i < 5; i++){
            InventoryItem testItem = new InventoryItem();
            testItem.setItemName("name");
            testItem.setSerialNum("1234567890");
            testItem.setMonetaryValue("420.69");
            testInventory.itemInventory.add(testItem);
        }

        assertTrue(fileSaver.saveFile(testInventory));
        //fileSaver.deleteFile();
    }
}
