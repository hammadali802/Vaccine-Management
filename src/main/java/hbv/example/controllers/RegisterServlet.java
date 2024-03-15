package hbv.example.controllers;


import hbv.example.dao.UserDao;
import hbv.example.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
//using web annotation to map the url

@WebServlet(name = "RegisterServlet", value = "/insertUser")
public class RegisterServlet extends HttpServlet {
    public RegisterServlet(){
        super();
    }
    private static final long serialVersionUID = 1L;

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////    resp.getWriter().write(req.getSession().getAttribute("userId").toString());
//    }
//

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        if (session == null) {
//            response.sendRedirect("login.html");
//            return;
//        }
          HttpSession session = request.getSession();


        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
//        String dateofbirth = request.getParameter("birthdate");


        String email = request.getParameter("email");
        String password = request.getParameter("password");
//        String city = request.getParameter("city");
//        String postalcode = request.getParameter("postalcode");
        int userId = -1;
        System.out.println(firstname);

        User user = new User(firstname, lastname, email,password);

        UserDao userDao = new UserDao();

        if (!userDao.isEmailExists(email)) {
            userId = userDao.addUser(user);
            if (userId > 0) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.html?registration=success");
                dispatcher.forward(request, response);
//                response.sendRedirect("login.html?registration=success");
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.html?error=1");
                dispatcher.forward(request, response);
//                response.sendRedirect("register.html?error=1");
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.html?error=emailexists");
            dispatcher.forward(request, response);
//            response.sendRedirect("register.html?error=emailexists");
        }


    }

}