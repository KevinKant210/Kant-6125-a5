package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.HashMap;

public class MenuController {


    private HashMap<String, InventoryItem> itemInventory;

    @FXML
    public TableColumn ItemCol;
    @FXML
    public TableColumn SerialNumberCol;
    @FXML
    public TableColumn ValueCol;
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



    public void initialize(){
        itemInventory = new HashMap<>();



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
        }

        if(!newItem.setMonetaryValue(ItemValueTextField.getText())){
            ErrorWindowController.generateError("Please Enter a Valid monetary Value in USD");
        }

        if(!newItem.setSerialNum(ItemSerialNumberTextField.getText())){
            ErrorWindowController.generateError("Please Enter a Valid Serial Number in XXXXXXXXXX where X can be a letter or digit");
        }

    }

    public void DeleteItemButtonClicked(ActionEvent actionEvent) {
    }

    public void SearchButtonClicked(ActionEvent actionEvent) {

        System.out.println("searching");
    }
}
