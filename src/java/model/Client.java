package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Client {

    private int id;
    private String nom;
    private String prenom;

    public Client() {
    }

    public Client(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public static Client getById(Connection connex, int id) throws SQLException {
        PreparedStatement st = null;
        ResultSet res = null;
        Boolean creatingConn = false;
        Client client = null;
        Connect co=new Connect();

        try {
            client = new Client();
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }
            String sql = "SELECT * FROM Client WHERE idClient = ?";
            st = connex.prepareStatement(sql);
            st.setInt(1, id);
            res = st.executeQuery();

            if (res.next()) {
                client.setId(res.getInt("idClient"));
                client.setNom(res.getString("nomClient"));
                client.setPrenom(res.getString("prenomClient"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (res != null) res.close();
            if (st != null) st.close();
            if (creatingConn && connex != null) connex.close();
        }

        return client;
    }

    // Get all clients
    public static Vector<Client> getAll(Connection connex) throws SQLException {
        PreparedStatement st = null;
        ResultSet res = null;
        Vector<Client> clients = new Vector<>();
        Boolean creatingConn = false;
        Connect co=new Connect();

        try {
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }
            String sql = "SELECT * FROM Client";
            st = connex.prepareStatement(sql);
            res = st.executeQuery();

            while (res.next()) {
                Client client = new Client();
                client.setId(res.getInt("idClient"));
                client.setNom(res.getString("nomClient"));
                client.setPrenom(res.getString("prenomClient"));

                clients.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (res != null) res.close();
            if (st != null) st.close();
            if (creatingConn && connex != null) connex.close();
        }

        return clients;
    }

    public static void main(String[] args) throws SQLException {
        Connect co=new Connect();
        Connection connex=co.connect();

        Client client= Client.getById(connex, 1);
        System.out.println(client.getNom());
    }


}
