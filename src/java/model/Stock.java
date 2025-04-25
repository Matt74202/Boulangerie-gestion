package model;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Stock {
    int id;
    Ingredients ingredient;
    int quantiteDispo ;
    LocalDateTime dateMouvement;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Ingredients getIngredient() {
        return ingredient;
    }
    public void setIngredient(int idIngredient) throws SQLException {
        this.ingredient =Ingredients.getById(null, idIngredient);
    }
    public int getQuantiteDispo() {
        return quantiteDispo;
    }
    public void setQuantiteDispo(int quantiteDispo) {
        this.quantiteDispo = quantiteDispo;
    }
    public LocalDateTime getDateMouvement() {
        return dateMouvement;
    }
    public void setDateMouvement(LocalDateTime dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

}
