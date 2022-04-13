package bg.tu_varna.sit.example.presentation.controllers;

import bg.tu_varna.sit.example.application.HelloApplication;
import bg.tu_varna.sit.example.data.access.SQLClass;
import bg.tu_varna.sit.example.data.entities.Client;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class statistikaKlienti {

    @FXML
    Button btnBackAdminViewId;

    @FXML
    ListView<String> list;
    @FXML
    public void initialize(){
        ArrayList<Client> statsCl = SQLClass.getClientStatistics();
        ArrayList<String> statsClstrings = new ArrayList<String>();
        for(Client client : statsCl){
            statsClstrings.add(client.name + "  Prieti:" + client.prieti + "  Neprieti:" + client.neprieti);
        }
        list.setItems(FXCollections.observableArrayList(statsClstrings));
    }

    public void backAdminView() throws Exception {

        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("/bg/tu_varna/sit/example/presentation.views/admin-view.fxml"));

        Stage window = (Stage) btnBackAdminViewId.getScene().getWindow();
        window.setScene(new Scene(root.load(), 605, 385));
    }
}
