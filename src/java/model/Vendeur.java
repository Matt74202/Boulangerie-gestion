package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Vendeur{
    int idVendeur;
    String NomVendeur;
    String PrenomVendeur;
    Genre genre;

    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public int getId() {
        return idVendeur;
    }
    public void setId(int idVendeur) {
        this.idVendeur = idVendeur;
    }
    public String getNom() {
        return NomVendeur;
    }
    public void setNom(String nomVendeur) {
        NomVendeur = nomVendeur;
    }
    public String getPrenom() {
        return PrenomVendeur;
    }
    public void setPrenom(String prenomVendeur) {
        PrenomVendeur = prenomVendeur;
    }
    public Vendeur(){

    }

    public static Vendeur getById(Connection connex, int id) throws SQLException {
        PreparedStatement st = null;
        ResultSet res = null;
        boolean creatingConn = false;
        Connect co=new Connect();
        Vendeur vendeur = null;

        try {
            vendeur = new Vendeur();
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }
            String sql = "SELECT * FROM vendeur WHERE idVendeur = ?";
            st = connex.prepareStatement(sql);
            st.setInt(1, id);
            res = st.executeQuery();

            if (res.next()) {
                vendeur.setId(res.getInt("idVendeur"));
                vendeur.setNom(res.getString("nomVendeur"));
                vendeur.setPrenom(res.getString("prenomVendeur"));
                
                Genre genre= Genre.getById(connex, res.getInt("idGenre"));
                vendeur.setGenre(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (res != null) res.close();
            if (st != null) st.close();
            if (creatingConn && connex != null) connex.close();
        }

        return vendeur;
}

    // Get all clients
public static Vector<Vendeur> getAll(Connection connex) throws SQLException {
    PreparedStatement st = null;
    ResultSet res = null;
    boolean creatingConn = false;
    Connect co=new Connect();
    Vector<Vendeur> vendeurs = new Vector<>();

    try {
        if (connex == null) {
            connex = co.connect();
            creatingConn = true;
        }

        String sql = "SELECT * FROM vendeur";
        st = connex.prepareStatement(sql);
        res = st.executeQuery();

        while (res.next()) {
            Vendeur vendeur = new Vendeur();
            vendeur.setId(res.getInt("idVendeur"));
            vendeur.setNom(res.getString("nomVendeur"));
            vendeur.setPrenom(res.getString("prenomVendeur"));
            Genre genre= Genre.getById(connex, res.getInt("idGenre"));
            vendeur.setGenre(genre);
            
            vendeurs.add(vendeur);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (res != null) res.close();
        if (st != null) st.close();
        if (creatingConn && connex != null) connex.close();
    }

    return vendeurs;
}    
}
