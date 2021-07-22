package ucf.assignments;

import java.sql.Array;
import java.util.ArrayList;

public class InventoryManager {
    public ArrayList<InventoryItem> itemInventory = new ArrayList<>();

    public ArrayList<InventoryItem> searchInventory(String searchQuery){

        ArrayList<InventoryItem> holder = new ArrayList<>();

        for(InventoryItem i : itemInventory){
            if(i.getSerialNum().compareToIgnoreCase(searchQuery) == 0 || i.getItemName().compareTo(searchQuery) == 0){
                holder.add(i);
            }
        }

        return holder;

    }
}
