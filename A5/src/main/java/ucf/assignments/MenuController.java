/*
 *
 *  *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  *  Copyright 2021 Kevin Kant
 *
 */

package ucf.assignments;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MenuController {



    private InventoryManager inventoryManager = new InventoryManager();




    @FXML
    public TableColumn<InventoryItem, String> ItemCol;
    @FXML
    public TableColumn<InventoryItem, String> SerialNumberCol;
    @FXML
    public TableColumn<InventoryItem, String> ValueCol;
    @FXML
    public TableView<InventoryItem> InformationTable;
    @FXML
    public Button SearchButton;
    @FXML
    public TextField ItemNameTextField;
    @FXML
    public TextField ItemSerialNumberTextField;
    @FXML
    public TextField ItemValueTextField;
    @FXML
    public TextField SearchTextField;
    @FXML
    public Button ClearSearchButton;



    public void initialize(){

            //set up columns and editable properties

          ItemCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
          ItemCol.setCellFactory(TextFieldTableCell.forTableColumn());


          SerialNumberCol.setCellValueFactory(new PropertyValueFactory<>("serialNum"));
          SerialNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());


          ValueCol.setCellValueFactory(new PropertyValueFactory<>("monetaryValue"));
          ValueCol.setCellFactory(TextFieldTableCell.forTableColumn());




    }


    public void NewMenuButtonClicked(ActionEvent actionEvent) {
        Parent root = null;
        try {
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("Menu.fxml"));
            root = loader.load();





        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Inventory Manager");
        stage.setScene(scene);

        stage.show();

        Stage currStage = (Stage) ClearSearchButton.getScene().getWindow();

        currStage.close();


    }

    public void SaveInventoryButtonClicked(ActionEvent actionEvent) {

        //launch a file chooser with constraints on file type

        //save users filename and path of choice
        //create SaveFile Obj to save the file
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter htmlFilter = new FileChooser.ExtensionFilter("HTML files (*.html)","*.html");

        fileChooser.getExtensionFilters().add(txtFilter);
        fileChooser.getExtensionFilters().add(htmlFilter);

        File filePath = fileChooser.showSaveDialog(SearchButton.getScene().getWindow());

        if(filePath == null)return;
        //ensure they choose a valid file
        if(! (filePath.toString().endsWith(".html")) && ! (filePath.toString().endsWith(".txt"))){
            ErrorWindowController.generateError("Please Choose a .txt file or .html file case matters! Stop trolling");
            return;
        }

        SaveFile fileSaver = new SaveFile(filePath);

        fileSaver.saveFile(inventoryManager);


    }

    public void LoadInventoryButtonClicked(ActionEvent actionEvent) {
        //launch a file chooser with constraints on file type

        //save users filename and path of choice
        //create loadFile Obj to save the file
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter htmlFilter = new FileChooser.ExtensionFilter("HTML files (*.html)","*.html");

        fileChooser.getExtensionFilters().add(txtFilter);
        fileChooser.getExtensionFilters().add(htmlFilter);

        File filePath = fileChooser.showOpenDialog(SearchButton.getScene().getWindow());
        if(filePath == null)return;


        inventoryManager = new LoadFile(filePath).loadInventory();

        ClearSearchButton.fire();

    }

    public void AddItemButtonClicked(ActionEvent actionEvent) {

        //generate a new item obj
        InventoryItem newItem = new InventoryItem();

        //ensure all inputs are valid if not give an error to berade the user
        if(!newItem.setItemName(ItemNameTextField.getText())){
            ErrorWindowController.generateError("Please Enter a Valid Name Between 2-256 characters");
            return;
        }

        if(!newItem.setMonetaryValue(ItemValueTextField.getText())){
            ErrorWindowController.generateError("Please Enter a Valid monetary Value in USD");
            return;
        }

        if(!newItem.setSerialNum(ItemSerialNumberTextField.getText())){
            ErrorWindowController.generateError("Please Enter a Valid Serial Number in XXXXXXXXXX where X can be a letter or digit");
            return;
        }

        if(!inventoryManager.searchInventory(newItem.getSerialNum()).isEmpty()){
            for(InventoryItem i : inventoryManager.itemInventory){
                if(i.getSerialNum().compareTo(newItem.getSerialNum()) == 0){
                    ErrorWindowController.generateError("We already have this serial number in our system please choose another one");
                    return;
                }
            }
        }

        //add item to the inventory
        inventoryManager.itemInventory.add(newItem);
        //display the item in the table
        InformationTable.getItems().add(newItem);
        InformationTable.refresh();

    }

    public void DeleteItemButtonClicked(ActionEvent actionEvent) {

        //grab users item selection
       InventoryItem item = InformationTable.getSelectionModel().getSelectedItem();

       //delete item
       InformationTable.getItems().remove(item);
       inventoryManager.itemInventory.remove(item);

       InformationTable.refresh();

    }

    public void SearchButtonClicked(ActionEvent actionEvent) {

        //call inventory manager search function
        ArrayList<InventoryItem> foundItems = inventoryManager.searchInventory(SearchTextField.getText());

        //if item not found give error
        if(foundItems.isEmpty()){
            ErrorWindowController.generateError("Could Not Find Search Results Please Try Something Else");
            SearchTextField.clear();
            return;
        }

        //if item found clear table and display items
        InformationTable.getItems().clear();
        for(InventoryItem item : foundItems){
            InformationTable.getItems().add(item);
        }

        InformationTable.refresh();

    }

    public void ClearSearchButtonClicked(ActionEvent actionEvent) {

        //clear table
        InformationTable.getItems().clear();

        //re add all items to display
        for(InventoryItem item : inventoryManager.itemInventory){
            InformationTable.getItems().add(item);
        }

        InformationTable.refresh();
    }

    public void NewItemName(TableColumn.CellEditEvent<InventoryItem, String> inventoryItemStringCellEditEvent) {

        //grab cell that user is trying to edit
        InventoryItem item = InformationTable.getSelectionModel().getSelectedItem();

        //ensure edit is valid if not give error
        if(!item.setItemName(inventoryItemStringCellEditEvent.getNewValue())){
            ErrorWindowController.generateError("Please Ensure Name is Properly Formatted");
            InformationTable.refresh();
            return;
        }

        InformationTable.refresh();

    }

    public void NewSerialNum(TableColumn.CellEditEvent<InventoryItem, String> inventoryItemStringCellEditEvent) {

        //grab cell and item that user is trying to edit
        InventoryItem item = InformationTable.getSelectionModel().getSelectedItem();

        String newSerial = inventoryItemStringCellEditEvent.getNewValue();

        //ensure user input is valid

        if (!inventoryManager.searchInventory(newSerial).isEmpty()){
            for(InventoryItem i : inventoryManager.itemInventory){
                if(i.getSerialNum().compareTo(newSerial) == 0){
                    ErrorWindowController.generateError("Please Enter a Unique Serial Number");
                }
            }
        }

        if(!item.setSerialNum(newSerial)){
            ErrorWindowController.generateError("Please Enter a Valid Serial Number");
            InformationTable.refresh();
            return;
        }

        InformationTable.refresh();



    }

    public void NewValue(TableColumn.CellEditEvent<InventoryItem, String> inventoryItemStringCellEditEvent) {

        //grab cell and item that user is trying to edit
        InventoryItem item = InformationTable.getSelectionModel().getSelectedItem();

        //ensure user input is valid

        //quality of life for users who may have kept the dollar sign in on edit
        String newVal = inventoryItemStringCellEditEvent.getNewValue().replace("$","");

        if(!item.setMonetaryValue(newVal)){
            ErrorWindowController.generateError("Please Enter a Valid Value");
            InformationTable.refresh();
            return;
        }

        InformationTable.refresh();
    }



}
