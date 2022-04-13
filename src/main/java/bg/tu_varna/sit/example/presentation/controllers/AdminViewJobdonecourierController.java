package bg.tu_varna.sit.example.presentation.controllers;

import bg.tu_varna.sit.example.application.HelloApplication;
import bg.tu_varna.sit.example.data.access.SQLClass;
import bg.tu_varna.sit.example.data.entities.Poruchki;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.ArrayList;

public class AdminViewJobdonecourierController {
    @FXML
    Button btnBackAdminViewId;
    @FXML
    DatePicker data01,data02;
    @FXML
    ListView<Poruchki> lists;
    public void Chek() throws Exception {
        Date tData01 = Date.valueOf(data01.getValue());
        Date tData02 = Date.valueOf(data02.getValue());
        SQLClass.CourierJobDone(tData01,tData02);

        ArrayList<Poruchki> poruchki = SQLClass. CourierJobDone(tData01, tData02);
        lists.setItems(FXCollections.observableArrayList(poruchki));
    }

    public void backAdminView() throws Exception {

        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("/bg/tu_varna/sit/example/presentation.views/admin-view.fxml"));

        Stage window = (Stage) btnBackAdminViewId.getScene().getWindow();
        window.setScene(new Scene(root.load(), 605, 385));
    }
}
