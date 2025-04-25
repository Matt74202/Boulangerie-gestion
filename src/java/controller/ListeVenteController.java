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
import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import model.Client;
import model.Connect;
import model.Conseil;
import model.Vente;

/**
 *
 * @author Matthew
 */
public class ListeVenteController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Connect co=new Connect();
            Connection connex=co.connect();
            Vector<Vente> ventes= Vente.getAll(connex);
            Vector<Client> clients=Client.getAll(connex);
             
            int idClient=0;
            Date date= null;

            if(request.getParameter("client")!=null){
                idClient= Integer.parseInt(request.getParameter("client"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if (request.getParameter("date") != null && !request.getParameter("date").isEmpty()) {
                    date = convertToDate(request.getParameter("date"), dateFormat);
                }
                ventes= Vente.search(connex,idClient,date);
            }
            
            for (Vente v: ventes){
                out.println("Produit: "+ v.getProduit().getNom()+"\n");
                out.println("Quantite: "+ v.getQuantite()+"\n");
                out.println("Client: "+ v.getClient().getNom()+"\n");
                out.println("Date: "+ v.getDate()+"\n");
                out.println("\n");
            }


            RequestDispatcher dispat = request.getRequestDispatcher("/ListeClient.jsp");
            request.setAttribute("ventes", ventes);
            request.setAttribute("clients",clients);
            dispat.forward(request, response);

            connex.close();
        }
        catch(Exception e){
            e.printStackTrace(out);
            out.println("lalala");
        }
    }

    private Date convertToDate(String dateString, SimpleDateFormat dateFormat) throws ParseException {
        java.util.Date parsedDate = dateFormat.parse(dateString);
        return new Date(parsedDate.getTime());
    }
    
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
    }





