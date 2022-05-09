package com.roshka.bootcamp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/parameter")
public class Parameter extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }


    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");

        String paramName = "nombre";
        String paramValue = request.getParameter(paramName);

        out.write(paramName);
        out.write(" = ");
        out.write(paramValue);
        out.write("n");

        if (paramValue==null) {
            out.write("Parameter " + paramName + " not found");
        }

        out.close();

    }
    
}
