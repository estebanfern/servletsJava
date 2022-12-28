package com.roshka.bootcamp;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.SQLException;

//@WebServlet("/consulta")
public class Consulta extends HttpServlet{
    Connection connection;
    public void init(){
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
    public void doGet(HttpServletRequest req, HttpServletResponse res){}


}
