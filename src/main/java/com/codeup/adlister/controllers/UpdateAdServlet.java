package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name ="updateAdServlet", value ="/updateAdServlet")
public class UpdateAdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
    @Override
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long Update= Long.parseLong(request.getParameter("Id"));
        String upTitle=request.getParameter("title");
        String upDescr=request.getParameter("descript");
        DaoFactory.getAdsDao().updateAd(Update,upTitle,upDescr);
        System.out.println(Update);
        response.sendRedirect("/ads");
    }
}