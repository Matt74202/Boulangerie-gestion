package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Parfum {
    int idParfum ;
    String nomParfum;
    public Parfum (){

    }
    public void setIdParfum(int idParfum) {
        this.idParfum = idParfum;
    }
    public void setNomParfum(String nomParfum) {
        this.nomParfum = nomParfum;
    }
    public int getIdParfum() {
        return idParfum;
    }
    public String getNomParfum() {
        return nomParfum;
    }
    public Vector<Parfum> getAllParfum(Connection co) throws Exception {
        Vector<Parfum> parfums = new Vector<>();
        String querry = "select * from parfum";
        Statement stmt = null;
        ResultSet res = null;
        stmt = co.createStatement();
        res = stmt.executeQuery(querry);
        while (res.next()) {
            int idParfum = res.getInt(1);
            String nomParfum = res.getString(2);
            Parfum parfum = new Parfum();
            parfum.setIdParfum(idParfum);
            parfum.setNomParfum(nomParfum);
            parfums.add(parfum);
        }
        return parfums;
    }
    public static Parfum getById(Connection connex, int id) throws SQLException {
    PreparedStatement st = null;
    ResultSet res = null;
    Parfum parfum = null;
    Boolean creatingConn = false;
    try {
        if (connex == null) {
            creatingConn = true;
        }
        String sql = "SELECT * FROM parfum WHERE id = ?";
        st = connex.prepareStatement(sql);
        st.setInt(1, id);
        res = st.executeQuery();
        if (res.next()) {
            int idParfum = res.getInt(1);
            String nomParfum = res.getString(2);
             parfum = new Parfum();
             parfum.setIdParfum(idParfum);
             parfum.setNomParfum(nomParfum);
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
    return parfum;
}
}
