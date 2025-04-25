/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Vector;

import model.Client;
import model.Connect;
import model.Produit;
import model.Vendeur;
import model.Vente;




/**
 *
 * @author Matthew
 */
public class InsertVenteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            PrintWriter out= response.getWriter();

            Connect co=new Connect();
            Connection conn=co.connect();
            Produit produit  = new Produit();
            Client client= new Client();
            
            Vector<Produit> produits = produit.getAllProduit(conn);
            Vector<Client> clients= client.getAll(conn);
            Vector<Vendeur> vendeur=Vendeur.getAll(conn);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/InsertVente.jsp");
            request.setAttribute("produits", produits);
            request.setAttribute("clients", clients);
            request.setAttribute("vendeurs",vendeur);
            dispatcher.forward(request, response);
        }
        catch(Exception ex){
            out.print("lololo");
            ex.printStackTrace(out);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            PrintWriter out= response.getWriter();
            Connect co=new Connect();
            Connection conn=co.connect();

            int quantite =  Integer.parseInt(request.getParameter("quantite"));
            int idProduit= Integer.parseInt(request.getParameter("produit"));
            int idClient= Integer.parseInt(request.getParameter("client"));
            int idVendeur=Integer.parseInt(request.getParameter("vendeur"));
            SimpleDateFormat format= new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date dateUtil= format.parse(request.getParameter("date"));
            java.sql.Date date= new java.sql.Date(dateUtil.getTime());

            Vente vente = new Vente();
            vente.insertVente(idProduit,quantite,date, idClient,idVendeur,conn);
            
            response.sendRedirect("./InsertVenteServlet");
        }
        catch(Exception ex){
            out.print("lololo");
            ex.printStackTrace(out);
        }
    }



}
