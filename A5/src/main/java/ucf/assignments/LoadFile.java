/*
 *
 *  *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  *  Copyright 2021 Kevin Kant
 *
 */

package ucf.assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadFile extends FileManager{

    public LoadFile(String filePath) {
        super(filePath);
    }


    public LoadFile(File filePath){
        super(filePath);

    }

    public InventoryManager loadInventory(){

        InventoryManager loadedInventory = new InventoryManager();

        if(filePath.toString().endsWith(".txt")){


            loadedInventory.itemInventory = grabInventoryTSV();

            return loadedInventory;
        }

        if(filePath.toString().endsWith(".html")){

            loadedInventory.itemInventory = grabInventoryHTML();
            return loadedInventory;
        }


        return null;
    }



    private ArrayList<InventoryItem> grabInventoryTSV(){

        ArrayList<InventoryItem> inventory = new ArrayList<>();

        try {
             Scanner reader = new Scanner(filePath.getAbsoluteFile());
            while(reader.hasNextLine()){

                String line = reader.nextLine();


                inventory.add(generateItemTSV(line));


            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return inventory;
    }


    private InventoryItem generateItemTSV(String line){
        String[] splitItem = line.split("\t");
        InventoryItem newItem = new InventoryItem();
        newItem.setItemName(splitItem[0]);
        newItem.setSerialNum(splitItem[1]);
        newItem.setMonetaryValue(splitItem[2]);

        return newItem;
    }

    private ArrayList<InventoryItem> grabInventoryHTML(){

        try{

            ArrayList<InventoryItem> inventory = new ArrayList<>();
            Scanner reader = new Scanner(filePath);


            while(reader.hasNextLine()){
                String line = reader.nextLine();

                if(line.contains("<td>")){
                    String[] itemContent = new String[3];
                    itemContent[0] = line;
                    itemContent[1] = reader.nextLine();
                    itemContent[2] = reader.nextLine();
                    inventory.add(generateItemHTML(itemContent));
                }


            }

            return inventory;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private InventoryItem generateItemHTML(String[] itemContent){

        InventoryItem tempItem = new InventoryItem();
        ArrayList<String> parsedContent = new ArrayList<>();

        for(String line : itemContent){


            line = line.replace("<td>","");
            line = line.replace("</td>","");

            parsedContent.add(line);


        }

        tempItem.setItemName(parsedContent.get(0));

        tempItem.setSerialNum(parsedContent.get(1));
        tempItem.setMonetaryValue(parsedContent.get(2));

        return tempItem;
    }


}
