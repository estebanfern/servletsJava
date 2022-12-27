package com.roshka.bootcamp;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/consulta2")
public class Consulta2 extends Consulta{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res){
        try{
            Statement stmt = connection.createStatement();
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            ResultSet rs = stmt
                    .executeQuery("SELECT cliente.nombre, SUM(ROUND(producto.precio*factura_detalle.cantidad)) gasto FROM cliente INNER JOIN factura ON cliente.id=factura.cliente_id " +
                            "INNER JOIN factura_detalle ON factura.id = factura_detalle.factura_id INNER JOIN producto ON producto.id= factura_detalle.producto_id " +
                            "GROUP BY (cliente.nombre)  ORDER BY (gasto) DESC;");
            out.println("<html>");
            out.println("<body>");
            while (rs.next()){
                String nombre = rs.getString(1);
                Double gasto = rs.getDouble(2);
                out.println("<p>NOMBRE = " + nombre + "</p>");
                out.println("<p>GAST0 = " + gasto + "</p>");
                out.println("<br>");
            }
            out.println("<html>");
            out.println("<body>");
            rs.close();
            stmt.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

}
