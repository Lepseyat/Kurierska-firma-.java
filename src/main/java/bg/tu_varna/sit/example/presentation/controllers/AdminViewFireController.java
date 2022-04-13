package bg.tu_varna.sit.example.presentation.controllers;

import bg.tu_varna.sit.example.application.HelloApplication;
import bg.tu_varna.sit.example.data.access.SQLClass;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.List;

public class AdminViewFireController {

    @FXML
    ComboBox<String> fireB;

    @FXML
    Button btnBackFireId;
    @FXML
    public void initialize(){
        List<String> users =  SQLClass.getCouriers();
        fireB.setItems(FXCollections.observableArrayList(users));


    }
    @FXML
    public void fireCourier(){SQLClass.fireCourier((String) fireB.getValue());
        List<String> users =  SQLClass.getCouriers();
        fireB.setItems(FXCollections.observableArrayList(users));
    }
    public void backFire() throws Exception {

        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("/bg/tu_varna/sit/example/presentation.views/admin-view.fxml"));

        Stage window = (Stage) btnBackFireId.getScene().getWindow();
        window.setScene(new Scene(root.load(), 605, 385));
    }
}
