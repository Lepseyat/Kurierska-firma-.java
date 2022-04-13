package bg.tu_varna.sit.example.presentation.controllers;

import bg.tu_varna.sit.example.application.HelloApplication;
import bg.tu_varna.sit.example.data.access.SQLClass;
import bg.tu_varna.sit.example.data.entities.Poruchki;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class ClientStatusController {

    @FXML
    Button btnBackStatusId;

    @FXML
    ListView<Poruchki> list;

    public void initialize(){
        List<Poruchki> poruchki = SQLClass.getPoruchki();
        list.setItems(FXCollections.observableArrayList(poruchki));
    }


    public void BackStatus() throws Exception {

        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("/bg/tu_varna/sit/example/presentation.views/courier-view.fxml"));

        Stage window = (Stage) btnBackStatusId.getScene().getWindow();
        window.setScene(new Scene(root.load(), 605, 385));
    }

}
