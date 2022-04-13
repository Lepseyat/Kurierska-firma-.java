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

public class AdminViewController {

    @FXML
    Button btnBackAdminId, btnAdminHireId, btnAdminFireId,bpratka,bkyrier,bklient,register;

    @FXML
    ComboBox izbor;
    @FXML
    public void initialize(){
        List<String> users =  SQLClass.getCouriers();
        izbor.setItems(FXCollections.observableArrayList(users));
    }

    public void courierJobDone() throws Exception {

        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("/bg/tu_varna/sit/example/presentation.views/admin-view-jobdonecourier.fxml"));

        Stage window = (Stage) bkyrier.getScene().getWindow();
        window.setScene(new Scene(root.load(), 605, 385));
    }

    public void adminHire() throws Exception {

        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("/bg/tu_varna/sit/example/presentation.views/admin-view-hire.fxml"));

        Stage window = (Stage) btnAdminHireId.getScene().getWindow();
        window.setScene(new Scene(root.load(), 605, 385));
    }

    public void adminFire() throws Exception {

        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("/bg/tu_varna/sit/example/presentation.views/admin-view-fire.fxml"));

        Stage window = (Stage) btnAdminFireId.getScene().getWindow();
        window.setScene(new Scene(root.load(), 605, 385));
    }

    public void backAdmin() throws Exception {

        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("/bg/tu_varna/sit/example/presentation.views/mainpage.fxml"));

        Stage window = (Stage) btnBackAdminId.getScene().getWindow();
        window.setScene(new Scene(root.load(), 605, 385));
    }
    public void orderStatistics() throws Exception {

        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("/bg/tu_varna/sit/example/presentation.views/admin-view-statistics.fxml"));

        Stage window = (Stage) bpratka.getScene().getWindow();
        window.setScene(new Scene(root.load(), 605, 385));
    }
  
    public void klientStatus() throws Exception //

    {

        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("/bg/tu_varna/sit/example/presentation.views/statistikaKlienti.fxml"));

        Stage window = (Stage) bklient.getScene().getWindow();
        window.setScene(new Scene(root.load(), 605, 385));
    }
    public void Reg() throws Exception

    {

        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("/bg/tu_varna/sit/example/presentation.views/admin-view-registerclient.fxml"));

        Stage window = (Stage) register.getScene().getWindow();
        window.setScene(new Scene(root.load(), 605, 385));
    }




    //public void proverka() throws Exception {
//
    //    Date tData1 = Date.valueOf(data1.getValue());
    //   Date tData2 = Date.valueOf(data2.getValue());
    //   SQLClass.testPoruchki(tData1,tData2);
//
    //    ArrayList<Poruchki> test = SQLClass.testPoruchki(tData1,tData2);
    //    poruchki.setItems(FXCollections.observableArrayList(test));
    //    }
//
}
