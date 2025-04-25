package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Conseil {
    int idConseil;
    Produit produit;
    Date date;
    public int getIdConseil() {
        return idConseil;
    }
    public void setIdConseil(int idConseil) {
        this.idConseil = idConseil;
    }
    public Produit getProduit() {
        return produit;
    }
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Conseil(){
        
    }
    public Conseil(Produit idProduit, Date date){
        this.setProduit(idProduit);
        this.setDate(date);
    }

    public void InsertConseil(int idProduit,Date date, Connection co) throws SQLException{
        PreparedStatement insertStmt = co.prepareStatement(
        "INSERT INTO Conseil (idProduit, Date) VALUES (? , ? )");
        insertStmt.setInt(1, idProduit);
        insertStmt.setDate(2, date);
        insertStmt.executeUpdate();
    }

    public static Vector<Conseil> search(Connection connex, Integer mois, Integer annee) throws SQLException {
        Connect co=new Connect();
        PreparedStatement st = null;
        ResultSet res = null;
        Boolean creatingConn = false;
        Vector<Conseil> conseils = new Vector<>();
    
        try {
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }

            StringBuilder sql = new StringBuilder("SELECT * FROM Conseil WHERE 1=1");
    
            if (mois != null && mois > 0) {
                sql.append(" AND EXTRACT(MONTH FROM date) = ? ");
            }
            if (annee != null && annee > 0) {
                sql.append(" AND EXTRACT(YEAR FROM date) = ? ");
            }

            st = connex.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (mois != null && mois > 0) {
                st.setInt(paramIndex++, mois);
            }
            if (annee != null && annee > 0) {
                st.setInt(paramIndex++, annee);
            }

            res = st.executeQuery();
    
            while (res.next()) {
                Conseil conseil = new Conseil();
                conseil.setIdConseil(res.getInt("idConseil"));
                conseil.setDate(res.getDate("date"));
    
                // Récupération du produit associé
                Produit produit = Produit.getById(connex, res.getInt("idProduit"));
                conseil.setProduit(produit);
    
                conseils.add(conseil);
            }
        } finally {
            if (res != null) res.close();
            if (st != null) st.close();
            if (creatingConn) connex.close();
        }
    
        return conseils;
    }
    public static Vector<Conseil> getAll(Connection connex) throws SQLException {
        Connect co=new Connect();
        PreparedStatement st = null;
        ResultSet res = null;
        Boolean creatingConn = false;
        Vector<Conseil> conseils = new Vector<>();
    
        try {
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }
            String sql = "SELECT * FROM Conseil";
            st = connex.prepareStatement(sql);
            res = st.executeQuery();
    
            while (res.next()) {
                Conseil conseil = new Conseil();
                conseil.setIdConseil(res.getInt("idConseil"));
                conseil.setDate(res.getDate("date"));
                Produit produit = Produit.getById(connex, res.getInt("idProduit"));
                conseil.setProduit(produit);
    
                conseils.add(conseil);
            }
        } finally {
            if (res != null) res.close();
            if (st != null) st.close();
            if (creatingConn) connex.close();
        }
    
        return conseils;
    }

    public static void main(String[] args) throws SQLException {
        Connect co=new Connect();
        Connection conn=co.connect();
        List<Conseil> conseils= Conseil.getAll(conn);
        for (Conseil c: conseils){
            System.out.println(c.getProduit().getNom()+ " "+ c.getDate());
        }
        
    }
}
