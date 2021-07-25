/*
 *
 *  *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  *  Copyright 2021 Kevin Kant
 *
 */

package ucf.assignments;


import java.util.ArrayList;

public class InventoryManager {

    //inventory that will hold all items
    public ArrayList<InventoryItem> itemInventory = new ArrayList<>();




    public ArrayList<InventoryItem> searchInventory(String searchQuery){
        //generate temp list for items found
        ArrayList<InventoryItem> holder = new ArrayList<>();


        //search inventory and collect any value that has corresponding name or serial num
        for(InventoryItem i : itemInventory){
            if(i.getSerialNum().compareToIgnoreCase(searchQuery) == 0 || i.getItemName().compareTo(searchQuery) == 0){
                //add it to the temp list
                holder.add(i);
            }
        }

        //return temp list
        return holder;

    }




}
