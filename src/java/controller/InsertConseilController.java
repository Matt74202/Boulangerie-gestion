package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import model.Connect;
import model.Produit;
import model.Conseil;

public class InsertConseilController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Connect co=new Connect();
            Connection connex = co.connect();

            String erreur = request.getParameter("erreur");
            String succes = request.getParameter("succes");
            Produit produit=new Produit();
            List<Produit> produits = produit.getAllProduit(connex);

            RequestDispatcher dispat = request.getRequestDispatcher("/InsertConseil.jsp");
            request.setAttribute("produits", produits);
            
            request.setAttribute("erreur", erreur);
            request.setAttribute("succes", succes);
            dispat.forward(request, response);
            
            connex.close();

        } catch (Exception ex) {
            ex.printStackTrace(out);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            
            Connect co=new Connect();
            Connection connex = co.connect();

            int idProduit= Integer.parseInt(request.getParameter("produit"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateUtil= format.parse(request.getParameter("date"));
            java.sql.Date date= new java.sql.Date(dateUtil.getTime());
            Conseil conseil= new Conseil();
            conseil.InsertConseil(idProduit,date,connex);
            String message= "conseil insere avec succes !";
            response.sendRedirect("./InsertConseilController?succes=" + message);
            out.print(idProduit);
            out.print(date);
        } catch (Exception ex) {
            String message= "Erreur lors de l'insertion du conseil";
            ex.printStackTrace(out);
        } 
    }

}
