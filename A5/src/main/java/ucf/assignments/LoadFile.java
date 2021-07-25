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


    public ArrayList<InventoryItem> grabInventoryTSV(){

        ArrayList<InventoryItem> inventory = new ArrayList<>();

        try {
             Scanner reader = new Scanner(filePath.getAbsoluteFile());
            while(reader.hasNextLine()){

                String line = reader.nextLine();


                inventory.add(generateItem(line));


            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return inventory;
    }


    private InventoryItem generateItem(String line){
        String[] splitItem = line.split("\t");
        InventoryItem newItem = new InventoryItem();
        newItem.setItemName(splitItem[0]);
        newItem.setSerialNum(splitItem[1]);
        newItem.setMonetaryValue(splitItem[2]);

        return newItem;
    }





}
