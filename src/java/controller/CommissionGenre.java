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
import java.util.Vector;
import model.Commission;
import model.Connect;
import model.Genre;

/**
 *
 * @author Matthew
 */
public class CommissionGenre extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            Connect co=new Connect();
            Connection connex= co.connect();

            Vector<Genre> genres= Genre.getAll(connex);
            
            Vector<Commission> commissions= Commission.getAll(connex);
            int idGenre=0;
            Date date1= null;
            Date date2= null;

            if (request.getParameter("genre")!=null){
                idGenre= Integer.parseInt(request.getParameter("genre"));
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (request.getParameter("date1") != null && !request.getParameter("date1").isEmpty()) {
                date1 = convertToDate(request.getParameter("date1"), dateFormat);
            }
            if (request.getParameter("date2") != null && !request.getParameter("date2").isEmpty()) {
                date2 = convertToDate(request.getParameter("date2"), dateFormat);
            }
            commissions= Commission.searchGenre(connex, idGenre, date1, date2);


            RequestDispatcher dispat = request.getRequestDispatcher("/CommissionGenre.jsp");
            request.setAttribute("genres", genres);
            request.setAttribute("commissions", commissions);
            dispat.forward(request, response);

            connex.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur est survenue : " + ex.getMessage());
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
