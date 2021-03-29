package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;
import com.mysql.cj.xdevapi.Schema;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProfileEditorServlet", value = "/edit-profile")
public class EditProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/edit-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String currentPassword = request.getParameter("currentPassword");
        String updatedPassword = request.getParameter("updatedPassword");
        User user = (User) request.getSession().getAttribute("user");
        long userId = user.getId();
        System.out.println(username + email + updatedPassword + userId);
//        boolean validAttempt = Password.check(currentPassword, user.getPassword());
        DaoFactory.getUsersDao().updateProfile(username, email, updatedPassword, userId);
        response.sendRedirect("/profile");
    }
}


