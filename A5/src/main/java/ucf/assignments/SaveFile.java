/*
 *
 *  *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  *  Copyright 2021 Kevin Kant
 *
 */

package ucf.assignments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

public class SaveFile extends FileManager{



    public SaveFile(String filePath) {
        super(filePath);


    }

    public SaveFile(File filePath) {
        super(filePath);


    }




    public boolean saveFile(InventoryManager inventory){
        //generate file and create a file writer
        try {
            if(!filePath.exists()){
                if(!filePath.createNewFile())return false;
            }

            FileWriter author = new FileWriter(filePath);


            //determine what format to save in
            //if html call html save
            //if tsv call tsv save
            if(filePath.toString().endsWith(".html")){

                return saveHTML(inventory.itemInventory,author);
            }

            if(filePath.toString().endsWith(".txt")){
               return saveTSV(inventory.itemInventory, author);
            }


        } catch (IOException e) {
            e.printStackTrace();

           return false;
        }

        return true;
    }

    private boolean saveHTML(ArrayList<InventoryItem> inventoryItems,FileWriter author){

        try {

            author.write("<!DOCTYPE html\n>");
            author.write("<html>\n");
            author.write("<body>\n");
            author.write("<h1> Inventory </h1>\n");

            author.write("<table style=\"width:100%\">\n");
            author.write("<tr>\n");
            author.write("<th>Item</th>\n");
            author.write("<th>Serial Num</th>\n");
            author.write("<th>Value</th>\n");
            author.write("</tr>\n");

            for(InventoryItem item: inventoryItems){

                author.write("<tr>\n");
                author.write("<td>" + item.getItemName() + "</td>\n");
                author.write("<td>" + item.getSerialNum() + "</td>\n");
                author.write("<td>" + item.getMonetaryValueAsDouble() + "</td>\n");
                author.write("</tr>\n");
            }

            author.write("</table>\n");
            author.write("</body>\n");
            author.write("</html>\n");
            author.close();

        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

    private boolean saveTSV(ArrayList<InventoryItem> inventoryItems, FileWriter author){

        try{

            for(InventoryItem item : inventoryItems){

                author.write(item.getItemName()+"\t"+item.getSerialNum()+"\t"+item.getMonetaryValueAsDouble()+"\n");

            }
            author.close();
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }




        return true;
    }

    //utility function for tests if needed
    public void deleteFile(){
        filePath.delete();
    }

}
