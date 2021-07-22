package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MenuController {



    private InventoryManager inventoryManager= new InventoryManager();




    @FXML
    public TableColumn<InventoryItem, String> ItemCol;
    @FXML
    public TableColumn<InventoryItem, String> SerialNumberCol;
    @FXML
    public TableColumn<InventoryItem, String> ValueCol;
    @FXML
    public TableView InformationTable;
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



    public void initialize(){



          ItemCol.setCellValueFactory(new PropertyValueFactory<InventoryItem,String>("itemName"));
          SerialNumberCol.setCellValueFactory(new PropertyValueFactory<>("serialNum"));

        DecimalFormat currency = new DecimalFormat("$0.00");
          //work on format for table




    }


    public void NewMenuButtonClicked(ActionEvent actionEvent) {
    }

    public void SaveInventoryButtonClicked(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter htmlFilter = new FileChooser.ExtensionFilter("HTML files (*.html)","*.html");

        fileChooser.getExtensionFilters().add(txtFilter);
        fileChooser.getExtensionFilters().add(htmlFilter);

        File filePath = fileChooser.showSaveDialog(SearchButton.getScene().getWindow());


    }

    public void LoadInventoryButtonClicked(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter htmlFilter = new FileChooser.ExtensionFilter("HTML files (*.html)","*.html");

        fileChooser.getExtensionFilters().add(txtFilter);
        fileChooser.getExtensionFilters().add(htmlFilter);

        File filePath = fileChooser.showOpenDialog(SearchButton.getScene().getWindow());


    }

    public void AddItemButtonClicked(ActionEvent actionEvent) {

        InventoryItem newItem = new InventoryItem();

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

        inventoryManager.itemInventory.add(newItem);

        InformationTable.getItems().add(newItem);
        InformationTable.refresh();

    }

    public void DeleteItemButtonClicked(ActionEvent actionEvent) {


       InventoryItem item = (InventoryItem)  InformationTable.getSelectionModel().getSelectedItem();
       inventoryManager.itemInventory.remove(item);
       InformationTable.refresh();

    }

    public void SearchButtonClicked(ActionEvent actionEvent) {

        ArrayList<InventoryItem> foundItems = inventoryManager.searchInventory(SearchTextField.getText());
        if(foundItems.isEmpty()){
            ErrorWindowController.generateError("Could Not Find Search Results Please Try Something Else");
            return;
        }

        InformationTable.getItems().clear();
        for(InventoryItem item : foundItems){
            InformationTable.getItems().add(item);
        }

        InformationTable.refresh();

    }

    public void ClearSearchButtonClicked(ActionEvent actionEvent) {
        InformationTable.getItems().clear();

        for(InventoryItem item : inventoryManager.itemInventory){
            InformationTable.getItems().add(item);
        }

        InformationTable.refresh();
    }
}
