package com.roshka.bootcamp;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/consulta3")
public class Consulta3 extends Consulta{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res){
        try{
            Statement stmt = connection.createStatement();
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            ResultSet rs = stmt
                    .executeQuery("SELECT moneda.nombre, count(moneda.nombre) cantidad_de_facturas FROM moneda INNER JOIN factura ON moneda.id=factura.moneda_id " +
                            "GROUP BY (moneda.nombre) ORDER BY (cantidad_de_facturas) DESC;");
            out.println("<html>");
            out.println("<body>");
            while (rs.next()){
                String nombre = rs.getString(1);
                Integer gasto = rs.getInt(2);
                out.println("<p>NOMBRE = " + nombre + "</p>");
                out.println("<p>CANTIDAD DE FACTURAS = " + gasto + "</p>");
                out.println("<br>");
            }
            out.println("<body>");
            out.println("<html>");
            rs.close();
            stmt.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
