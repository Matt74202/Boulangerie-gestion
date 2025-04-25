package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Produit;
import model.Connect;
import model.Vente;
import model.Categorie;
import model.Parfum;


public class ListeVenteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        try {
            PrintWriter out = response.getWriter();
            Connect co=new Connect();
            Connection conn=co.connect();
            Categorie categorie=new Categorie();
            Parfum parfum=new Parfum();
            Produit produits=new Produit();
            Vente ventes=new Vente();
            List<Categorie> categories= categorie.getAllCategorie(conn);
            List<Parfum> Parfums= parfum.getAllParfum(conn);
            List<Produit> Produits= produits.getAllProduit(conn);
            List<Vente> vente= new ArrayList<>();
            int idProduit=0;
            int idParfum=0;
            int idCategorie=0;
            if (request.getParameter("categorie")!=null){
                idCategorie= Integer.parseInt(request.getParameter("categorie"));
            }
            if (request.getParameter("Parfum")!=null){
                idParfum= Integer.parseInt(request.getParameter("Parfum"));
            }
            if (request.getParameter("Produits")!=null){
                idProduit= Integer.parseInt(request.getParameter("Produits"));
            }
            vente= ventes.getAllVente(idCategorie , idParfum ,conn,   idProduit);


            RequestDispatcher dispat = request.getRequestDispatcher("/pages/listeVente.jsp");
            request.setAttribute("categories", categories);
            request.setAttribute("Parfums",Parfums);
            request.setAttribute("Categories", Produits);
            request.setAttribute("mouvements", vente);
            dispat.forward(request, response);

            conn.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur est survenue : " + ex.getMessage());
        }
        
    }

}
