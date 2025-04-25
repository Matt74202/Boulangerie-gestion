create table vente(
    idVente serial primary key,
    idProduit int REFERENCES Produit(idProduit),
    dateVente Date,
    idClient int references Client(idClient),
    quantite int, 
    idVendeur int references Vendeur(idVendeur) 
 );