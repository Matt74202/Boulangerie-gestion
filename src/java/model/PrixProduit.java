package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class PrixProduit {
    int idPrixProduit;
    Produit produit;
    Double prix;
    Date date;

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getIdPrixProduit() {
        return idPrixProduit;
    }
    public void setIdPrixProduit(int idPrixProduit) {
        this.idPrixProduit = idPrixProduit;
    }
    public Produit getProduit() {
        return produit;
    }
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    public Double getPrix() {
        return prix;
    }
    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public static Vector<PrixProduit> search(Connection connex, int idProduit, Date date) throws SQLException {
        Connect co = new Connect();
        PreparedStatement st = null;
        ResultSet res = null;
        Boolean creatingConn = false;
        Vector<PrixProduit> prixProduits = new Vector<>();
    
        try {
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }
    
            StringBuilder sql = new StringBuilder("SELECT * FROM PrixProduit WHERE 1=1");
    
            if (idProduit > 0) {
                sql.append(" AND idProduit = ? ");
            }
            if (date != null) {
                sql.append(" AND date = ? ");
            }
    
            st = connex.prepareStatement(sql.toString());
    
            int paramIndex = 1;
            if (idProduit > 0) {
                st.setInt(paramIndex++, idProduit);
            }
            if (date != null) {
                st.setDate(paramIndex++, date);
            }
    
            res = st.executeQuery();
    
            while (res.next()) {
                PrixProduit pp = new PrixProduit();
                pp.setIdPrixProduit(res.getInt("idPrixProduit"));
                pp.setPrix(res.getDouble("prix"));
                pp.setDate(res.getDate("date"));
    
                Produit produit = Produit.getById(connex, res.getInt("idProduit"));
                pp.setProduit(produit);
    
                prixProduits.add(pp);
            }
        } finally {
            if (res != null) res.close();
            if (st != null) st.close();
            if (creatingConn && connex != null) connex.close();
        }
    
        return prixProduits;
    }
    public static Vector<PrixProduit> getAll(Connection connex) throws SQLException {
        Connect co = new Connect();
        PreparedStatement st = null;
        ResultSet res = null;
        Boolean creatingConn = false;
        Vector<PrixProduit> prixProduits = new Vector<>();

        try {
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }
            String sql = "SELECT * FROM PrixProduit";
            st = connex.prepareStatement(sql);
            res = st.executeQuery();

            while (res.next()) {
                PrixProduit pp = new PrixProduit();
                pp.setIdPrixProduit(res.getInt("idPrixProduit"));
                pp.setPrix(res.getDouble("prix"));
                pp.setDate(res.getDate("date"));

                Produit produit = Produit.getById(connex, res.getInt("idProduit"));
                pp.setProduit(produit);

                prixProduits.add(pp);
            }
        } finally {
            if (res != null) res.close();
            if (st != null) st.close();
            if (creatingConn && connex != null) connex.close();
        }

        return prixProduits;
    }

    // Optional: Add a main method for testing
    public static void main(String[] args) throws SQLException {
        Connect co = new Connect();
        Connection conn = co.connect();
        Vector<PrixProduit> prixList = PrixProduit.getAll(conn);
        for (PrixProduit pp : prixList) {
            System.out.println(pp.getProduit().getNom() + " " + pp.getPrix() + " " + pp.getDate());
        }
        conn.close();
    }
}