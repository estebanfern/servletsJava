package com.roshka.bootcamp;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/clientes")
public class Clientes extends Consulta{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            Statement stmt = connection.createStatement();
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cliente;");
            res.setContentType("text/html");
            out.println("<html>");
            out.println("<body>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr><th>ID</th><th>Nombre</th><th>Apellido</th><th>CI</th><th>Telefono</th></tr>");
            out.println("<thead>");
            while (rs.next()) {
                out.println("<tr>" + "<th>" + rs.getString(1) + "</th>"
                        + "<th>" +rs.getString(2) + "</th>"
                        + "<th>" +rs.getString(3) + "</th>"
                        + "<th>" +rs.getString(4) + "</th>"
                        + "<th>" +rs.getString(5) + "</th>" + "</tr>");
            }
            out.println("<table>");
            out.println("<body>");
            out.println("<html>");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
