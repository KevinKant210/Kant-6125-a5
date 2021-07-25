/*
 *
 *  *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  *  Copyright 2021 Kevin Kant
 *
 */

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
        //check user value for valid name
        if(newName.length() < 2 || newName.length() > 256){
            return false;
        }

        char[] name = newName.toCharArray();

        for(char c : name){
            if(!Character.isAlphabetic(c) && !Character.isWhitespace(c))return false;
        }
        //set name
        itemName = newName;

        return true;

    }


    public String getSerialNum(){
        return serialNum;
    }

    public boolean setSerialNum(String newSerial){
        //check value for valid serial num
        if(newSerial.length() != 10)return false;

       char[] serial = newSerial.toCharArray();

       for(char c : serial){
           if(!Character.isLetterOrDigit(c)) return false;
       }
        //set serial num
       serialNum = newSerial;

        return true;
    }

    public String getMonetaryValue(){


        return String.format("$%.2f",monetaryValue);
    }

    public Double getMonetaryValueAsDouble(){
        return monetaryValue;
    }

    public boolean setMonetaryValue(String newValue){
        //check value for valid value
        if(newValue.isEmpty() || newValue.isBlank()){
            return false;
        }
        Double val;
        try {
            val = Double.parseDouble(newValue);
        }catch(NumberFormatException e) {
            return false;
        }
        //set value
        monetaryValue = val;

        return true;
    }






}
