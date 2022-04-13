
package bg.tu_varna.sit.example.data.access;

import bg.tu_varna.sit.example.common.Utils;
import bg.tu_varna.sit.example.data.entities.Poruchki;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class SQLClassTest {

    @Test
    @Order(1)
    public void hireCourier(){

        SQLClass.hireCourier("www", "1234");
        ArrayList<String> result = new ArrayList<>();
        for(String c : SQLClass.getCouriers()){
            if(c == "www") result.add(c);
        }
        assertEquals(1, result.toArray().length);
    }

    @Test
    @Order(2)
    public void fireCourier(){
        SQLClass.fireCourier("www");
        ArrayList<String> result = new ArrayList<>();
        for(String c : SQLClass.getCouriers()){
            if(c == "www") result.add(c);
        }
        assertEquals(0, result.toArray().length);

    }



    @Test
    void loginCourier() {

        boolean result = SQLClass.loginCourier("niki", "1234");
        assertEquals(true, result);
        System.out.println("Login courier successful");
    }

    @Test
    void loginAdmin() {
        boolean result = SQLClass.loginAdmin("ivan", "666666");
        assertEquals(true, result);
        System.out.println("Login admin successful");

    }



    @Test
    void testCanceled() {
        ArrayList<Poruchki> list = new ArrayList<Poruchki>();
        Poruchki p = new Poruchki();
        p.status = 1;
        list.add(p);
        p = new Poruchki();
        p.status = 2;
        list.add(p);

        List<Poruchki> result = Utils.filterCanceled(list);
        assertEquals(1, result.toArray().length);
    }

    @Test
    void getTypeID() {
        int result = SQLClass.getTypeID("envelope");
        assertEquals(1, result);

    }








}