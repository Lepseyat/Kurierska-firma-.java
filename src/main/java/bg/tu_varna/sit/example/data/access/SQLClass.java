package bg.tu_varna.sit.example.data.access;

import bg.tu_varna.sit.example.data.entities.Client;
import bg.tu_varna.sit.example.data.entities.Poruchki;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLClass {
    static final Logger log = Logger.getLogger(SQLClass.class.getName());


    private static String databaseUrl = "jdbc:sqlserver://127.0.0.1:1433;databaseName=oop";
        private static String  databaseUser = "dani";
        private static String databasePassword="123";
    private static int courier;



    // ADMIN

    public static boolean loginAdmin(String username, String password){
        String query = "SELECT username, password FROM Admin WHERE username = ? AND password = ?";

        try(Connection con = DriverManager.getConnection(databaseUrl , databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            if(result.next()){
                return true;
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return false;
    }

    public static void hireCourier(String username, String password){
        String query = "INSERT INTO courier(username, password) VALUES(?, ?)";
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
        }
        catch(SQLException e){
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    public static void fireCourier(String username){
        String query = "DELETE FROM courier WHERE username = ?";
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, username);

            statement.executeUpdate();
        }
        catch(SQLException e){
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    public static ArrayList<Client> getClientStatistics(){
        String query = "SELECT * FROM CLIENT";
        ArrayList<Client> stat = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while(result.next()){

                Client c = new Client();
                c.name = result.getString("username");
                c.id = result.getInt("id");
                c.prieti = result.getInt("prieti");
                c.neprieti= result.getInt("neprieti");
                stat.add(c);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return stat;
    }

    public static ArrayList<Poruchki> orderStatistics(Date data1, Date data2){
        String query = "SELECT * FROM poruchki WHERE data BETWEEN ? AND ? ";
        ArrayList<Poruchki> test = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            statement.setDate(1, data1);
            statement.setDate(2, data2);

            ResultSet result = statement.executeQuery();
            while(result.next()){
                Poruchki p = new Poruchki();
                p.poruchka = result.getString("poruchka");
                p.adres = result.getString("adres");
                p.data = result.getDate("data");
                p.id = result.getInt("id");
                p.id_klient= result.getInt("id_klient");
                p.id_vid = result.getInt("id_vid");
                p.polychatel = result.getString("polychatel");
                p.status = result.getInt("status");
                test.add(p);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return test;
    }

    public static ArrayList<Poruchki> CourierJobDone(Date data01,Date data02){
        String query = "SELECT * FROM poruchki WHERE data BETWEEN ? AND ? ";
        ArrayList<Poruchki> poruchki = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            statement.setDate(1,data01 );
            statement.setDate(2, data02);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Poruchki p = new Poruchki();
                p.poruchka = result.getString("poruchka");
                p.adres = result.getString("adres");
                p.data = result.getDate("data");
                p.polychatel = result.getString("polychatel");
                p.status = result.getInt("status");
                p.id = result.getInt("id");
                p.id_klient= result.getInt("id_klient");
                p.id_vid = result.getInt("id_vid");
                poruchki.add(p);

            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return poruchki;
    }

    public static List<String> getCouriers(){
        String query = "SELECT username FROM courier";
        ArrayList<String> users = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(databaseUrl , databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);

            ResultSet result = statement.executeQuery();

            while(result.next()){
                users.add(result.getString("username"));
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return users;
    }
    public static ArrayList<Client> getClient(){
        String query = "SELECT id,username FROM client";

        ArrayList<Client> user = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(databaseUrl , databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);

            ResultSet result = statement.executeQuery();

            while(result.next()){
                Client client = new Client();
                client.id = result.getInt("id");
                client.name = result.getString("username");
                user.add(client);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return user;
    }


    // COURIER

    public static boolean loginCourier(String username, String password){
        String query = "SELECT id,username, password FROM courier WHERE username =?  AND password =? ";

        try(Connection con = DriverManager.getConnection(databaseUrl , databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            if(result.next()){
                courier= result.getInt("id");
                return true;
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return false;
    }

    public static void clientBuy(int client, int type, String adres, String opisanie, Date data,String polychatel,String stat){
        String query = "INSERT INTO poruchki(id_klient,id_kourier, id_vid, adres, poruchka, data,polychatel,status) VALUES(?, ?, ?, ?, ?, ?, ?,?)";
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, client);
            statement.setInt(2, courier);
            statement.setInt(3, type);
            statement.setString(4, adres);
            statement.setString(5, opisanie);
            statement.setDate(6, data);
            statement.setString(7, polychatel);
            statement.setString(8, stat);

            statement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    public static List<String> getTypes(){
        String query = "SELECT poruchka FROM vidPorichki";
        ArrayList<String> types = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                types.add(result.getString("poruchka"));
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return types;
    }
    public static int getTypeID(String type){
        String query = "SELECT id_vid FROM vidPorichki WHERE poruchka = ?";
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, type);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                return result.getInt("id_vid");
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return 0;
    }

    public static ArrayList<Poruchki> getPoruchki(){
        String query = "SELECT * FROM poruchki";
        ArrayList<Poruchki> poruchki = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Poruchki p = new Poruchki();
                p.poruchka = result.getString("poruchka");
                p.adres = result.getString("adres");
                p.data = result.getDate("data");
                p.id = result.getInt("id");
                p.id_klient= result.getInt("id_klient");
                p.id_vid = result.getInt("id_vid");
                p.polychatel = result.getString("polychatel");
                p.status = result.getInt("status");
                p.id_kourier= result.getInt("id_kourier");
                poruchki.add(p);


            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return poruchki;
    }


    public static List<Integer> getCanceledPoruchki(){
        String query = "SELECT id FROM poruchki WHERE status  IN(2,5)";
        ArrayList<Integer> poruchki = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                poruchki.add(result.getInt("id"));
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return poruchki;
    }

    public static List<String> getPoruchka(int id){
        String query = "SELECT * from poruchki WHERE id = ?";
        ArrayList<String> poruchka = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                poruchka.add(result.getString("adres"));
                poruchka.add(result.getString("poruchka"));
                poruchka.add(result.getDate("data").toString());
                String query2 = "SELECT status from status WHERE id = ?";
                PreparedStatement statement2 = con.prepareStatement(query2);
                statement2.setInt(1, result.getInt("status"));
                ResultSet result2 = statement2.executeQuery();
                if(result2.next()) poruchka.add(result2.getString("status"));
                String query3 = "SELECT cena from vidPorichki WHERE id_vid = ?";
                PreparedStatement statement3 = con.prepareStatement(query3);
                statement3.setInt(1, result.getInt("id_vid"));
                ResultSet result3 = statement3.executeQuery();
                if(result3.next()) poruchka.add(result3.getString("cena"));
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return poruchka;
    }

    public static void curierRecieve(int id){
        String query = "UPDATE poruchki SET status = 1 WHERE id = ?";
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }
    public static void curierNotRecieved(int id){
        String query = "UPDATE poruchki SET status = 2 WHERE id = ?";
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    public static void cancelOrder(int id){
        String query = "UPDATE poruchki SET status = 5 WHERE id = ?";
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }


    // ADMIN + COURIER

    public static void registerClient(String username){
        String query = "INSERT INTO client(username) VALUES(?)";
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, username);

            statement.executeUpdate();
        }
        catch(SQLException e){
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    public static void Prieti(int id){
        String query = "UPDATE client SET prieti = prieti + 1 WHERE id = ?";
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }
    public static void nePrieti(int id){
        String query = "UPDATE client SET neprieti = neprieti + 1 WHERE id = ?";
        try(Connection con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword)){
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }


}
