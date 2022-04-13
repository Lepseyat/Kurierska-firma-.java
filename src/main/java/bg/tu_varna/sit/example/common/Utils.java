package bg.tu_varna.sit.example.common;

import bg.tu_varna.sit.example.data.entities.Poruchki;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List filterCanceled(List<Poruchki> list){
        ArrayList<Poruchki> result = new ArrayList<Poruchki>();
        for(Poruchki p : list){
            if(p.status == 5 || p.status == 2) result.add(p);
        }
        return result;
    }

    public static List status(List<Poruchki> list){
        ArrayList<Poruchki> result = new ArrayList<Poruchki>();
        for(Poruchki p : list){
            if(p.status == 1 || p.status == 2) result.add(p);
        }
        return result;
    }

}
