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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ErrorWindowController {


    @FXML
    public Button OkButton;
    @FXML
    public Text ErrorMessageText;


    public void OkButtonClicked(ActionEvent actionEvent) {
        //function to close window when user says they understand error
        Stage stage = (Stage) OkButton.getScene().getWindow();

        stage.close();


    }

    public static void generateError(String errorMessage) {
        //begin generating the error Window
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader (ErrorWindowController.class.getResource("ErrorWindow.fxml"));
            root = loader.load();

            //set the error to the requested description

           ErrorWindowController controller =  loader.getController();
            controller.ErrorMessageText.setText(errorMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //launc herror scene
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Error!");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }



}
