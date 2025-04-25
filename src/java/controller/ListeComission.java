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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import model.Client;
import model.Commission;
import model.Connect;
import model.Genre;
import model.Vendeur;
import model.Vente;

/**
 *
 * @author Matthew
 */
public class ListeComission extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            Connect co=new Connect();
            Connection connex=co.connect();
            Vector<Vendeur> vendeurs= Vendeur.getAll(connex);
            Vector<Commission> commissions= Commission.getAll(connex);
            Date date1= null;
            Date date2= null;
            int idVendeur=0;

            if(request.getParameter("vendeur")!=null){
                idVendeur= Integer.parseInt(request.getParameter("vendeur"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (request.getParameter("date1") != null && !request.getParameter("date1").isEmpty()) {
                date1 = convertToDate(request.getParameter("date1"), dateFormat);
            }
            if (request.getParameter("date2") != null && !request.getParameter("date2").isEmpty()) {
                date2 = convertToDate(request.getParameter("date2"), dateFormat);
            }
                commissions= Commission.search(connex,idVendeur,date1,date2);
            }
                           
            RequestDispatcher dispat = request.getRequestDispatcher("/ListeCommission.jsp");
            request.setAttribute("vendeurs", vendeurs);
            request.setAttribute("commissions",commissions);
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
