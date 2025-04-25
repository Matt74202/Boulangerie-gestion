package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Categorie {
    // idCategorie serial PRIMARY KEY,
    // nomCategorie VARCHAR(50)

    int idCategorie ;
    String nomCategorie;

    public Categorie (){

    }
    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }
    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }
    public String getNomCategorie() {
        return nomCategorie;
    }
    public int getIdCategorie() {
        return idCategorie;
    }
   public Vector<Categorie> getAllCategorie(Connection co) throws Exception {
        Vector<Categorie> allCateg = new Vector<>();
        String querry = "select * from categorie";
        Statement stmt = null;
        ResultSet res = null;
        stmt = co.createStatement();
        res = stmt.executeQuery(querry);
        while (res.next()) {
            int idCategorie = res.getInt(1);
            String nom  = res.getString(2);
            Categorie categ =  new Categorie();
            categ.setIdCategorie(idCategorie);
            categ.setNomCategorie(nom);
            allCateg.add(categ);
        }
        return allCateg;
    }
     public static Categorie getById(Connection connex, int id) throws SQLException {
    PreparedStatement st = null;
    ResultSet res = null;
    Categorie categorie = null;
    Boolean creatingConn = false;
    try {
        if (connex == null) {
            creatingConn = true;
        }
        String sql = "SELECT * FROM categorie WHERE id = ?";
        st = connex.prepareStatement(sql);
        st.setInt(1, id);
        res = st.executeQuery();
        if (res.next()) {
            int idCategorie = res.getInt(1);
            String nom  = res.getString(2);
            categorie =  new Categorie();
            categorie.setIdCategorie(idCategorie);
            categorie.setNomCategorie(nom);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (res != null)
            res.close();
        if (st != null)
            st.close();
        if (creatingConn)
            connex.close();
    }
    return categorie;
}

}
