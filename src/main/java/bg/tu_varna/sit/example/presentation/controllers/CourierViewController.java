package bg.tu_varna.sit.example.presentation.controllers;

import bg.tu_varna.sit.example.application.HelloApplication;
import bg.tu_varna.sit.example.data.access.SQLClass;
import bg.tu_varna.sit.example.data.entities.Client;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CourierViewController {


    @FXML
    Button btnCourierOrdersId, btnCourierLogOutId,add1, registerClientId,go,btnStatusId;
    @FXML
    ListView<String> listCanceled;
    @FXML
    ListView<String> precenka;
    @FXML
    ComboBox client;


    @FXML
    public void initialize(){
        {ArrayList<Client> user =  SQLClass.getClient();
        client.setItems(FXCollections.observableArrayList(user));}

           List<Integer> poruchki = SQLClass.getCanceledPoruchki();
           for (int id : poruchki) {
               List<String> poruchka = SQLClass.getPoruchka(id);
               listCanceled.getItems().add(poruchka.get(0) + " " + poruchka.get(1) + " " + poruchka.get(2) + " " + poruchka.get(3) + " " + poruchka.get(4));
           }

        ArrayList<Client> stats = SQLClass.getClientStatistics();
           ArrayList<String> statstrings = new ArrayList<String>();
        for(Client client : stats){
            statstrings.add(client.name + "  Prieti:" + client.prieti + "  Neprieti:" + client.neprieti);
        }
        precenka.setItems(FXCollections.observableArrayList(statstrings));
}
    public void goo() throws Exception {

       /* if(SQLClass.loginClient(clview.getValue())){*/
            FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("/bg/tu_varna/sit/example/presentation.views/courier-view-sent.fxml"));

            Stage window = (Stage) go.getScene().getWindow();
            window.setScene(new Scene(root.load(), 605, 385));

    }

    public void courierOrders() throws Exception {

        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("/bg/tu_varna/sit/example/presentation.views/courier-view-orders.fxml"));

        Stage window = (Stage) btnCourierOrdersId.getScene().getWindow();
        window.setScene(new Scene(root.load(), 605, 385));
    }


    public void courierLogOut() throws Exception {

        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("/bg/tu_varna/sit/example/presentation.views/mainpage.fxml"));

        Stage window = (Stage) btnCourierLogOutId.getScene().getWindow();
        window.setScene(new Scene(root.load(), 605, 385));
    }



    public void registerClient() throws Exception {

        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("/bg/tu_varna/sit/example/presentation.views/courier-view-registerClient.fxml"));

        Stage window = (Stage) registerClientId.getScene().getWindow();
        window.setScene(new Scene(root.load(), 605, 385));
    }

    public void statusId() throws Exception {

        FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("/bg/tu_varna/sit/example/presentation.views/client-view-status.fxml"));

        Stage window = (Stage) btnStatusId.getScene().getWindow();
        window.setScene(new Scene(root.load(), 605, 385));
    }
}

