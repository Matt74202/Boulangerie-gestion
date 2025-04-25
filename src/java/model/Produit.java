package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;



public class Produit{
    int idProduit;
    String nom;
    double prixVente;
    int quantite;
    double duree;

    public int getId() {
        return idProduit;
    }
    public void setId(int id) {
        this.idProduit = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public double getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public double getDuree() {
        return duree;
    }
    public void setDuree(double duree) {
        this.duree = duree;
    }
    public Produit(){}

    public Produit(int id, String nom, double prixVente, int quantite,double duree){
        this.setId(id);
        this.setNom(nom);
        this.setPrixVente(prixVente);
        this.setQuantite(quantite);
        this.setDuree(duree);
    }

    public Vector<Produit> listeProduit(Connection co){
        if(co==null){
            Connect new_co=new Connect();
            co=new_co.connect();
        }
        Vector<Produit> list= new Vector<Produit>();
        Statement st=null;
        ResultSet res=null;
        try {
            st=co.createStatement();
            String querry="select * from VueProduitQuantite";
            res=st.executeQuery(querry);
            while (res.next()) {
                int id=res.getInt("idProduit");
                String nom=res.getString("nomProduit");
                double prix=res.getDouble("prixProduit");
                int quantite=res.getInt("totalQuantite");
                double duree=res.getDouble("duree");
                Produit produit=new Produit(id,nom,prix,quantite,duree);
                list.add(produit); 
            }
        } catch (Exception e) {
            e.printStackTrace();        
        }
                return list;

        
    }

    private static boolean verifierStock(Connection connection, int idIngredient, double quantiteUtilise) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "SELECT quantiteDisponible FROM Stock WHERE idIngredients = ?");
        stmt.setInt(1, idIngredient);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            double quantiteDisponible = rs.getDouble("quantitéDisponible");
            return quantiteDisponible >= quantiteUtilise; 
        }
        return false;
    }

    public static void fabriquerProduit(int idProduit, int quantiteProduit, Connection co) {
    try {
        
        PreparedStatement stmt = co.prepareStatement(
                "SELECT idIngredients, quantiteUtilise FROM FabricationProduit WHERE idProduit = ?");
        stmt.setInt(1, idProduit);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int idIngredient = rs.getInt("idIngredients");
            double quantiteUtilisee = rs.getDouble("quantiteUtilise") * quantiteProduit; 
            if (!verifierStock(co, idIngredient, quantiteUtilisee)) {
                System.out.println("Pas assez de stock pour l'ingrédient ID: " + idIngredient);
                return;
            }

            PreparedStatement insertStmt = co.prepareStatement(
                    "INSERT INTO MouvementStock (idIngredients, typeMouvement, quantite) VALUES (?, 'sortie', ?)");
            insertStmt.setInt(1, idIngredient);
            insertStmt.setDouble(2, quantiteUtilisee);
            insertStmt.executeUpdate();
        }

        System.out.println("Fabrication de " + quantiteProduit + " unités du produit avec ID " + idProduit + " réussie.");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public static Produit getById(Connection conn, int id) throws SQLException {
    PreparedStatement st = null;
    ResultSet res = null;
    Produit produit = null;
    Boolean creatingConn = false;
    try {
        if (conn == null) {
            creatingConn = true;
            Connect co=new Connect();
            conn=co.connect();
            
        }
        String sql = "SELECT * FROM produit WHERE idProduit = ?";
        st = conn.prepareStatement(sql);
        st.setInt(1, id);
        res = st.executeQuery();
        if (res.next()) {
    //         idProduit SERIAL PRIMARY KEY,
    // nomProduit VARCHAR(50),
    // prixProduit DECIMAL,
    // quantite INT,
    // duree decimal
                produit = new Produit();
            int idProduit = res.getInt(1);
            String nomProduit = res.getString(2);
            double prixProduit = res.getDouble(3);
            int quantite = res.getInt(4);
            double duree = res.getDouble(5);

            produit = new Produit(idProduit, nomProduit, prixProduit, quantite, duree);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (res != null)
            res.close();
        if (st != null)
            st.close();
        if (creatingConn)
            conn.close();
    }
    return produit;
}
public Vector<Produit> getAllProduit(Connection co) throws Exception {
    Vector<Produit> parfums = new Vector<>();
    String querry = "select * from produit";
    Statement stmt = null;
    ResultSet res = null;
    stmt = co.createStatement();
    res = stmt.executeQuery(querry);
    while (res.next()) {
        int idProduit = res.getInt(1);
        String nomProduit = res.getString(2);
        double prixProduit = res.getDouble(3);
        int quantite = res.getInt(4);
        double duree = res.getDouble(5);
        Produit prod = new Produit();
        prod.setId(idProduit);
        prod.setDuree(duree);
        prod.setNom(nomProduit);
        prod.setQuantite(quantite);
        prod.setDuree(duree);
        parfums.add(prod);
    }
    return parfums;
}
public static Vector<Produit> getIngredientByFabrication(int idProduit, int idIngredient, Connection co) {
    
        Connect new_co=new Connect();
        co=new_co.connect();  
        Vector<Produit> list= new Vector<Produit>();
        Statement st=null;
        ResultSet res=null;
        try {
            st=co.createStatement();
            String querry="SELECT * FROM getIngredientByFab where idIngredient= ? and idProduit = ?";
            res=st.executeQuery(querry);
            while (res.next()) {
                int id=res.getInt("idProduit");
                String nom=res.getString("nomProduit");
                double prix=res.getDouble("prixProduit");
                int quantite=res.getInt("totalQuantite");
                double duree=res.getDouble("duree");
                Produit produit=new Produit(id,nom,prix,quantite,duree);
                list.add(produit); 
            }
        } catch (Exception e) {
            e.printStackTrace();        
        }
                return list;
    
}

   public void insert(Connection connex) throws Exception {
        PreparedStatement st = null;
        boolean creatingConn = false;
        Connect co= new Connect();
    
        try {
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }
            String sql = "INSERT INTO Produit (nomProduit,prixProduit) VALUES (?, ?)";
    
            st = connex.prepareStatement(sql);
            st.setString(1, this.getNom());
            st.setDouble(2, this.getPrixVente());
            st.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
    
        } finally {
            if (st != null) {
                st.close();
            }
            if (creatingConn && connex != null) {
                connex.close();
            }
        }
    }

    public void update(Connection connex,double prix) throws Exception {
        PreparedStatement st = null;
        boolean creatingConn = false;
        Connect co= new Connect();
    
        try {
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }
            String sql = "Update Produit set prixProduit=? where idProduit=?";
    
            st = connex.prepareStatement(sql);
            st.setDouble(1, prix);
            st.setDouble(2, this.getId());
            st.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
    
        } finally {
            if (st != null) {
                st.close();
            }
            if (creatingConn && connex != null) {
                connex.close();
            }
        }
    }




public static void main(String[] args) {
    
    Connect co=new Connect();
    Connection connection =co.connect();
    

    if (connection != null) {
        Produit produitModel = new Produit();
        Vector<Produit> produits = produitModel.listeProduit(connection);
        System.out.println("Liste des produits :");
        for (Produit produit : produits) {
            System.out.println("ID: " + produit.getId() + ", Nom: " + produit.getNom() + ", Prix: " + produit.getPrixVente() + ", Quantité: " + produit.getQuantite());
        }

        if (!produits.isEmpty()) {
            int idProduit = produits.get(0).getId();
            int quantiteProduit = 10; 
            fabriquerProduit(idProduit, quantiteProduit, connection);
        }
    }
    }

    public void setIdProduit(long aLong) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


