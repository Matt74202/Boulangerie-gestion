/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.util.Vector;
import model.Connect;
import model.PrixProduit;
import model.Produit;

/**
 *
 * @author Matthew
 */
public class HistoriqueController extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Handle GET (show form with products)
    try {
        Vector<Produit> produits = new Produit().getAllProduit(new Connect().connect());
        request.setAttribute("produits", produits);
        request.getRequestDispatcher("Historique.jsp").forward(request, response);
    } catch (Exception e) {
        response.sendRedirect("Historique.jsp?error=" + URLEncoder.encode("Error loading products", "UTF-8"));
    }
}
    

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Handle POST (search)
    String idProduitStr = request.getParameter("idProduit");
    String dateStr = request.getParameter("date");
    int idProduit = (idProduitStr == null || idProduitStr.isEmpty()) ? 0 : Integer.parseInt(idProduitStr);
    Date date = (dateStr == null || dateStr.isEmpty()) ? null : Date.valueOf(dateStr);
    Vector<PrixProduit> results = new Vector<>();

    try {
        Vector<Produit> produits = new Produit().getAllProduit(new Connect().connect());
        request.setAttribute("produits", produits);
        results = PrixProduit.search(null, idProduit, date);
        request.setAttribute("results", results);
        request.getRequestDispatcher("Historique.jsp").forward(request, response);
    } catch (Exception e) {
        response.sendRedirect("Historique.jsp?error=" + URLEncoder.encode("Search failed: " + e.getMessage(), "UTF-8"));
    }
}

}

