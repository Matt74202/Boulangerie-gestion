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
import java.util.Vector;
import model.Connect;
import model.Produit;

/**
 *
 * @author Matthew
 */
public class UpdateProduit extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getMethod().equalsIgnoreCase("POST")) {
            // Handle POST (update)
            String idStr = request.getParameter("idProduit");
            String prixStr = request.getParameter("prixProduit");
            String redirectUrl = "updateProduit.jsp";

            try {
                int id = Integer.parseInt(idStr);
                double prix = Double.parseDouble(prixStr);
                Produit produit = Produit.getById(null, id); // Fetch product
                produit.update(null, prix); // Update price

                redirectUrl += "?success=1";
            } catch (NumberFormatException e) {
                redirectUrl += "?error=" + URLEncoder.encode("Invalid price or ID format!", "UTF-8");
            } catch (Exception e) {
                redirectUrl += "?error=" + URLEncoder.encode("Update failed: " + e.getMessage(), "UTF-8");
            }

            response.sendRedirect(redirectUrl);
            return;
        }

        // Handle GET (show form with dropdown)
        try {
            Vector<Produit> produits = new Produit().getAllProduit(new Connect().connect());
            request.setAttribute("produits", produits);
            request.getRequestDispatcher("updateProduit.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("updateProduit.jsp?error=" + URLEncoder.encode("Error loading products", "UTF-8"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
