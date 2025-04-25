/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Matthew
 */
public class Commission {
    int id;
    Vendeur vendeur;
    double somme;
    Genre genre;

    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Vendeur getVendeur() {
        return vendeur;
    }
    public void setVendeur(Vendeur vendeur) {
        this.vendeur = vendeur;
    }
    public double getSomme() {
        return somme;
    }
    public void setSomme(double somme) {
        this.somme = somme;
    }
    
     public static Vector<Commission> getAll(Connection connex) throws SQLException {
        PreparedStatement st = null;
        ResultSet res = null;
        Vector<Commission> commissions = new Vector<>();
        boolean creatingConn = false;
        Connect co=new Connect();
    
        try {
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }
    
            // Appel de la fonction calculer_commission entre 1970-01-01 et 2070-12-31
            String sql = "SELECT * FROM calculer_commission('1970-01-01', '2070-12-31')";
            st = connex.prepareStatement(sql);
            res = st.executeQuery();
    
            while (res.next()) {
                Commission commission = new Commission();
                Vendeur vendeur = new Vendeur();
    
                vendeur.setId(res.getInt("vendeur_id"));
                vendeur.setNom(res.getString("vendeur_nom"));
                vendeur.setPrenom(res.getString("vendeur_prenom"));
    
                commission.setVendeur(vendeur);
                commission.setSomme(res.getDouble("commission_totale"));
    
                commissions.add(commission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (res != null) res.close();
            if (st != null) st.close();
            if (creatingConn && connex != null) connex.close();
        }
    
        return commissions;
    }

public static Vector<Commission> search(Connection connex, int idVendeur, Date date1, Date date2) throws SQLException {
        PreparedStatement st = null;
        ResultSet res = null;
        Vector<Commission> commissions = new Vector<>();
        boolean creatingConn = false;
        Connect co=new Connect();

        // Valeurs par défaut pour date1 et date2 si elles sont nulles
        if (date1 == null) {
            date1 = new Date(0); // 01-01-1970
        }
        if (date2 == null) {
            Calendar cal = Calendar.getInstance();
            cal.set(2070, Calendar.DECEMBER, 31);
            date2 = new Date(cal.getTimeInMillis()); // 31-12-2070
        }

        try {
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }

            // Construction de la requête SQL avec des filtres facultatifs
            StringBuilder sql = new StringBuilder("SELECT * FROM calculer_commission(?, ?)");
            if (idVendeur > 0) {
                sql.append(" WHERE vendeur_id = ?");
            }

            st = connex.prepareStatement(sql.toString());

            // Assignation des paramètres
            int paramIndex = 1;
            st.setDate(paramIndex++, new java.sql.Date(date1.getTime()));  // date de début (01-01-1970 par défaut)
            st.setDate(paramIndex++, new java.sql.Date(date2.getTime()));  // date de fin (31-12-2070 par défaut)

            if (idVendeur > 0) {
                st.setInt(paramIndex++, idVendeur);  // filtre sur l'id du vendeur
            }

            // Exécution de la requête et traitement des résultats
            res = st.executeQuery();

            while (res.next()) {
                Commission commission = new Commission();
                Vendeur vendeur = new Vendeur();

                vendeur.setId(res.getInt("vendeur_id"));
                vendeur.setNom(res.getString("vendeur_nom"));
                vendeur.setPrenom(res.getString("vendeur_prenom"));

                commission.setVendeur(vendeur);
                commission.setSomme(res.getDouble("commission_totale"));

                commissions.add(commission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (res != null) res.close();
            if (st != null) st.close();
            if (creatingConn && connex != null) connex.close();
        }

        return commissions;
    }

    public static Vector<Commission> getAllByGenre(Connection connex) throws SQLException {
        PreparedStatement st = null;
        ResultSet res = null;
        Vector<Commission> commissions = new Vector<>();
        boolean creatingConn = false;
        Connect co=new Connect();
    
        try {
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }
    
            // Appel de la fonction calculer_commission entre 1970-01-01 et 2070-12-31
            String sql = "SELECT * FROM calculer_commissionGenre('1970-01-01', '2070-12-31')";
            st = connex.prepareStatement(sql);
            res = st.executeQuery();
    
            while (res.next()) {
                Commission commission = new Commission();
                Genre genre = new Genre();
    
                genre.setIdGenre(res.getInt("genre_id"));
                genre.setNomGenre(res.getString("genre_nom"));
    
                commission.setGenre(genre);
                commission.setSomme(res.getDouble("commission_totale"));
    
                commissions.add(commission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (res != null) res.close();
            if (st != null) st.close();
            if (creatingConn && connex != null) connex.close();
        }
    
        return commissions;
    }

    public static Vector<Commission> searchGenre(Connection connex, int idGenre, Date date1, Date date2) throws SQLException {
        PreparedStatement st = null;
        ResultSet res = null;
        Vector<Commission> commissions = new Vector<>();
        boolean creatingConn = false;
        Connect co=new Connect();
        int prixMax=200000;

        if (date1 == null) {
            date1 = new Date(0); // 01-01-1970
        }
        if (date2 == null) {
            Calendar cal = Calendar.getInstance();
            cal.set(2070, Calendar.DECEMBER, 31);
            date2 = new Date(cal.getTimeInMillis()); // 31-12-2070
        }

        try {
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }

            StringBuilder sql = new StringBuilder("SELECT * FROM calculer_commissionGenre(?, ?,?)");
            if (idGenre > 0) {
                sql.append(" WHERE genre_id = ?");
            }

            st = connex.prepareStatement(sql.toString());

            int paramIndex = 1;
            st.setDate(paramIndex++, new java.sql.Date(date1.getTime())); 
            st.setDate(paramIndex++, new java.sql.Date(date2.getTime()));
            st.setInt(paramIndex++,prixMax);

            if (idGenre > 0) {
                st.setInt(paramIndex++, idGenre); 
            }

            res = st.executeQuery();

            while (res.next()) {
                Commission commission = new Commission();
                Genre genre = new Genre();
    
                genre.setIdGenre(res.getInt("genre_id"));
                genre.setNomGenre(res.getString("genre_nom"));
    
                commission.setGenre(genre);
                commission.setSomme(res.getDouble("commission_totale"));
    
                commissions.add(commission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (res != null) res.close();
            if (st != null) st.close();
            if (creatingConn && connex != null) connex.close();
        }

        return commissions;
    }



    public static void main(String[] args) throws SQLException {
        Connect co=new Connect();
        Connection connex=co.connect();
        
        Vector<Commission>commissions=Commission.getAll(connex);
        for(Commission c:commissions){
            System.out.println(c.getSomme());
        }
    }
}
