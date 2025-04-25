// getAllIngredient
// getIngredientById
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Ingredients
 */
public class Vente {
    
    int idVente;
    int quantite;
    Produit produit;
    Categorie categorie;
    Parfum parfum;
    Date date;
    Client client;
    
    
    public Vente() {
        
    }
    public Vente( int idProduit, int quantite) {
        
    }
    public Vente( int idProduit, int idParfum, int idCategorie) throws SQLException  {
        this.setProduit(Produit.getById(null, idProduit));
        this.setParfum(Parfum.getById(null, idParfum));
        this.setCategorie(Categorie.getById(null, idCategorie));
    }

    public int getIdVente() {
        return idVente;
    }
    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }
        
        public void setProduit(Produit produit) {
            this.produit = produit;
        }
        
        public void setParfum(Parfum parfum){
            this.parfum=parfum;
        }
        
     public void setPCategorie(Categorie categorie){
         this.categorie=categorie;
        }
        public void setidVente(int idVente) {
            this.idVente = idVente;
        }
        public void setCategorie(Categorie categorie){
            
        }
        public int getQuantite() {
            return this.quantite;
        }
        public void setQuantite(int quantite) {
            this.quantite = quantite;
        }
        public Produit getProduit(){
            return produit;
        }
        
        public Parfum getParfum(){
            return parfum;
        }
        public Categorie getCategorie(){
            return categorie;
        }
        public Produit getproduit(){
            return produit;
        }
        public void setDate(Date date){
            this.date=date;
        }
        public Date getDate(){
            return date;
        }
        
        public Client getClient() {
            return client;
        }
        public void setClient(Client client) {
            this.client = client;
        }
        //    public Vector<Vente> getAllVente(Connection co) throws Exception {
//        Vector<Vente> allIngredients = new Vector<>();
//        String querry = "select * from vente";
//        Statement stmt = null;
//        ResultSet res = null;
//        stmt = co.createStatement();
//        res = stmt.executeQuery(querry);
//        while (res.next()) {
//            int idVente = res.getInt(1);
//            int idProduit = res.getInt(2);
//            int quantite = res.getInt(3);
//            Vente vente = new Vente(idVente ,idProduit, quantite);
//            allIngredients.add(vente);
//        }
//        return allIngredients;
//    }
    public Vector<Vente> getAllVente(int idCategorie , int idParfum  , Connection co , int idProduit) throws Exception {
        Vector<Vente> allVentes = new Vector<>();
        String querry = "select * from getVenteByParam where idCategorie= ? and idParfum= ? and idProduit= ? ";
        PreparedStatement stmt = null;
        ResultSet res = null;
        stmt = co.prepareStatement(querry);
        stmt.setInt(1, idCategorie);
        stmt.setInt(2,idParfum);
        stmt.setInt(3,idProduit);
        res=stmt.executeQuery();
        while (res.next()) {
            int idProduits = res.getInt("idProduit");
            int idCategories= res.getInt("idCategorie");
            int idParfums= res.getInt("idParfum");
            
            Vente vente = new Vente(idProduits,idParfums,idCategories);
            allVentes.add(vente);
        }
        return allVentes;
    }

    public void insertVente(int idProduit, int quantite, Date date, int idClient,int idVendeur, Connection co) throws SQLException {
        PreparedStatement insertStmt = co.prepareStatement(
                "INSERT INTO vente (idProduit, quantite, dateVente, idClient,idVendeur) VALUES (?, ?, ?, ?,?)");
        insertStmt.setInt(1, idProduit);
        insertStmt.setDouble(2, quantite);
        insertStmt.setDate(3, date);
        insertStmt.setDouble(4, idClient);
        insertStmt.setDouble(5,idVendeur);
        insertStmt.executeUpdate();
    }
    
   public static Vector<Vente> getAll(Connection connex) throws SQLException {
        Connect co=new Connect();
        PreparedStatement st = null;
        ResultSet res = null;
        Boolean creatingConn = false;
        Vector<Vente> ventes = new Vector<>();
    
        try {
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }
            String sql = "SELECT * FROM Vente";
            st = connex.prepareStatement(sql);
            res = st.executeQuery();
    
            while (res.next()) {
                Vente vente = new Vente();
                vente.setidVente(res.getInt("idVente"));
                vente.setDate(res.getDate("dateVente"));
                vente.setQuantite(res.getInt("quantite"));
                Produit produit = Produit.getById(connex, res.getInt("idProduit"));
                vente.setProduit(produit);
                Client client= Client.getById(connex, res.getInt("idClient"));
                vente.setClient(client);
                vente.setDate(res.getDate("dateVente"));
    
                ventes.add(vente);
            }
        } finally {
            if (res != null) res.close();
            if (st != null) st.close();
            if (creatingConn) connex.close();
        }
    
        return ventes;
    }
   
   public static Vector<Vente> search(Connection connex,int idClient, Date date) throws SQLException {
        Connect co=new Connect();
        PreparedStatement st = null;
        ResultSet res = null;
        Boolean creatingConn = false;
        Vector<Vente> ventes  = new Vector<>();
    
        try {
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }

            StringBuilder sql = new StringBuilder("SELECT * FROM Vente WHERE 1=1");
            
            if (idClient > 0) {
                sql.append(" AND idClient = ?");
            }
            if (date != null) {
                sql.append(" AND dateVente = ?");
            }

            st = connex.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (idClient > 0) {
                st.setInt(paramIndex++, idClient);
            }
            if (date != null) {
                st.setDate(paramIndex++, date);
            }

            res = st.executeQuery();
    
            while (res.next()) {
                Vente vente = new Vente();
                vente.setidVente(res.getInt("idVente"));
                vente.setDate(res.getDate("dateVente"));
                vente.setQuantite(res.getInt("quantite"));
                Produit produit = Produit.getById(connex, res.getInt("idProduit"));
                vente.setProduit(produit);
                Client client= Client.getById(connex, res.getInt("idClient"));
                vente.setClient(client);
                vente.setDate(res.getDate("dateVente"));
    
                ventes.add(vente);
            }
        } finally {
            if (res != null) res.close();
            if (st != null) st.close();
            if (creatingConn) connex.close();
        }
    
        return ventes;
    }

    public static void main(String[] args) throws SQLException {
        Connect co=new Connect();
        Connection conn=co.connect();

        Vector<Vente> ventes= Vente.search(conn, 1, null);
        for (Vente v: ventes){
            System.out.println(v.getQuantite());
        }

    }

}