package ucf.assignments;

public class InventoryItem {

    private Double monetaryValue;
    private String serialNum;
    private String itemName;

    public InventoryItem(){

    }

    public String getItemName(){
        return itemName;
    }

    public boolean setItemName(String newName){

        if(newName.length() < 2 || newName.length() > 256){
            return false;
        }

        char[] name = newName.toCharArray();

        for(char c : name){
            if(!Character.isAlphabetic(c) && !Character.isWhitespace(c))return false;
        }

        itemName = newName;

        return true;

    }


    public String getSerialNum(){
        return serialNum;
    }

    public boolean setSerialNum(String newSerial){

        if(newSerial.length() != 10)return false;

       char[] serial = newSerial.toCharArray();

       for(char c : serial){
           if(!Character.isLetterOrDigit(c)) return false;
       }

       serialNum = newSerial;

        return true;
    }

    public Double getMonetaryValue(){
        return monetaryValue;
    }

    public boolean setMonetaryValue(String newValue){

        Double val;
        try {
            val = Double.parseDouble(newValue);
        }catch(NumberFormatException e) {
            return false;
        }

        monetaryValue = val;

        return true;
    }




}
