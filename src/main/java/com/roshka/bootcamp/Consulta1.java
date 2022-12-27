package com.roshka.bootcamp;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.*;
@WebServlet("/consulta1")
public class Consulta1 extends HttpServlet{
    Connection connection;
    public void init() {
        connection = ConnectionDb.getConn();
    }

    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            Statement stmt = connection.createStatement();
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            ResultSet rs = stmt
                    .executeQuery("SELECT cliente.id cliente_id, cliente.nombre, cliente.apellido, cliente.nro_cedula, cliente.telefono,  COUNT(cliente.id) cantidad_facturas " +
                            "FROM cliente INNER JOIN factura ON cliente.id=factura.cliente_id " +
                            "GROUP BY (cliente.id)  ORDER BY (cantidad_facturas) DESC; ");
            out.println("<html>");
            out.println("<body>");
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int cantidad = rs.getInt("cantidad_facturas");

                out.println("<p>NOMBRE = " + nombre + "</p>");
                out.println("<p>APELLIDO = " + apellido + "</p>");
                out.println("<p>CANTIDAD FACTURA = " + cantidad + "</p>");
                out.println("<br>");

            }
            out.println("</body>");
            out.println("</html>");
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
