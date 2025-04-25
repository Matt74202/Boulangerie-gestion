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
import model.Produit;

/**
 *
 * @author Matthew
 */
public class InsertProduit extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getMethod().equalsIgnoreCase("POST")) {
            String nom = request.getParameter("nomProduit");
            String prixStr = request.getParameter("prixProduit");
            String redirectUrl = "addProduit.jsp";

            try {
                double prix = Double.parseDouble(prixStr);
                Produit produit = new Produit();
                produit.setNom(nom);
                produit.setPrixVente(prix);
                produit.insert(null); // Insert into DB

                // Success: Add success flag to URL
                redirectUrl += "?success=1";
            } catch (NumberFormatException e) {
                redirectUrl += "?error=" + URLEncoder.encode("Invalid price format!", "UTF-8");
            } catch (Exception e) {
                redirectUrl += "?error=" + URLEncoder.encode("Insert failed: " + e.getMessage(), "UTF-8");
            }

            response.sendRedirect(redirectUrl);
            return;
        }

        // For GET requests, just show the form
        response.sendRedirect("addProduit.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
