/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Matthew
 */
public class Genre {
    int idGenre;
    String nomGenre;

    public int getIdGenre() {
        return idGenre;
    }
    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }
    public String getNomGenre() {
        return nomGenre;
    }
    public void setNomGenre(String nomGenre) {
        this.nomGenre = nomGenre;
    }

    public Genre(){

    }

     public static Genre getById(Connection connex, int id) throws SQLException {
        PreparedStatement st = null;
        ResultSet res = null;
        Boolean creatingConn = false;
        Genre genre = null;
        Connect co=new Connect();

        try {
            genre = new Genre();
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }
            String sql = "SELECT * FROM Genre WHERE idGenre = ?";
            st = connex.prepareStatement(sql);
            st.setInt(1, id);
            res = st.executeQuery();

            if (res.next()) {
                genre.setIdGenre(res.getInt("idGenre"));
                genre.setNomGenre(res.getString("nomGenre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (res != null) res.close();
            if (st != null) st.close();
            if (creatingConn && connex != null) connex.close();
        }

        return genre;
    }

    public static Vector<Genre> getAll(Connection connex) throws SQLException {
        PreparedStatement st = null;
        ResultSet res = null;
        Vector<Genre> genres = new Vector<>();
        Boolean creatingConn = false;
        Connect co=new Connect();

        try {
            if (connex == null) {
                connex = co.connect();
                creatingConn = true;
            }
            String sql = "SELECT * FROM Genre";
            st = connex.prepareStatement(sql);
            res = st.executeQuery();

            while (res.next()) {
                Genre genre = new Genre();
                genre.setIdGenre(res.getInt("idGenre"));
                genre.setNomGenre(res.getString("nomGenre"));

                genres.add(genre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (res != null) res.close();
            if (st != null) st.close();
            if (creatingConn && connex != null) connex.close();
        }

        return genres;
    }
}
