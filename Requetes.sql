--
create or replace view getIngredientByFab as 
SELECT 
Ig.nomIngredients nomIngredients,
Pp.nomProduit nomProduit
from 
FabricationIngredients fi
join FabricationProduit fp 
on
fi.idFabricationProduit = fp.idFabricationProduit
join Produit Pp 
on
fp.idProduit = Pp.idProduit
join Ingredients Ig
on 
fi.idIngredients = Ig.idIngredients
;

-- Vue ProduitQuantite ahitana ny quantite ana produit ray
CREATE or replace VIEW VueProduitQuantite AS
SELECT 
    idProduit, 
    nomProduit, 
    prixProduit, 
    SUM(quantite) AS totalQuantite,
    duree
FROM 
    Produit
GROUP BY 
    idProduit, nomProduit, prixProduit,duree;


CREATE OR REPLACE VIEW ProduitConseil AS
SELECT 
    p.idProduit, 
    p.nomProduit, 
    p.prixProduit, 
    p.quantite, 
    p.duree, 
    p.idCategorie, 
    p.idParfum, 
    c.idConseil, 
    c.Date AS DateConseil
FROM 
    Produit p
JOIN 
    Conseil c
ON 
    p.idProduit=c.idProduit;    


CREATE OR REPLACE FUNCTION calculer_commission(date1 DATE, date2 DATE)
RETURNS TABLE (
    vendeur_id INT,
    vendeur_nom VARCHAR,
    vendeur_prenom VARCHAR,
    commission_totale DECIMAL
) AS $$
BEGIN
    RETURN QUERY
    SELECT
        v.idVendeur AS vendeur_id,
        v.nomVendeur AS vendeur_nom,
        v.prenomVendeur AS vendeur_prenom,
        SUM(ms.quantite * up.prixProduit * 0.05) AS commission_totale
    FROM
        Vente ms
    JOIN
        Produit up ON ms.idProduit = up.idProduit
    JOIN
        Vendeur v ON ms.idVendeur = v.idVendeur
    WHERE
        ms.dateVente BETWEEN date1 AND date2  -- Filtrage des dates
    GROUP BY
        v.idVendeur, v.nomVendeur, v.prenomVendeur
    ORDER BY
        commission_totale DESC;  -- Optionnel, pour trier les vendeurs par commission totale
END;
$$ LANGUAGE plpgsql;


--view pour calculer commission par genre
CREATE OR REPLACE FUNCTION calculer_commissionGenre(date1 DATE, date2 DATE)
RETURNS TABLE (
    genre_id INT,
    genre_nom VARCHAR,
    commission_totale DECIMAL
) AS $$
BEGIN
    RETURN QUERY
    SELECT
        v.idVendeur AS vendeur_id,
        v.nomVendeur AS vendeur_nom,
        v.prenomVendeur AS vendeur_prenom,
        SUM(ms.quantite * up.prixProduit * 0.05) AS commission_totale
    FROM
        Vente ms
    JOIN
        Produit up ON ms.idProduit = up.idProduit
    JOIN
        Vendeur v ON ms.idVendeur = v.idVendeur
    JOIN 
        Genre g ON v.idGenre= g.idGenre
    WHERE
        ms.dateVente BETWEEN date1 AND date2  -- Filtrage des dates
    GROUP BY
        g.idGenre, g.nomGenre
    ORDER BY
        commission_totale DESC;  -- Optionnel, pour trier les vendeurs par commission totale
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION calculer_commissionGenre(date1 DATE, date2 DATE,prixMax int)
RETURNS TABLE (
    genre_id INT,
    genre_nom VARCHAR,
    commission_totale DECIMAL
) AS $$
BEGIN
    RETURN QUERY
    SELECT
        g.idGenre AS genre_id,
        g.nomGenre AS genre_nom,
       SUM(CASE WHEN ms.quantite * up.prixProduit >= prixMax THEN ms.quantite * up.prixProduit * 0.05 ELSE 0 END) AS commission_totale
    FROM
        Vente ms
    JOIN
        Produit up ON ms.idProduit = up.idProduit
    JOIN
        Vendeur v ON ms.idVendeur = v.idVendeur
    JOIN
        Genre g ON v.idGenre= g.idGenre
    WHERE
        ms.dateVente BETWEEN date1 AND date2
    GROUP BY
        g.idGenre, g.nomGenre
    ORDER BY
        commission_totale DESC;
END;
$$ LANGUAGE plpgsql;