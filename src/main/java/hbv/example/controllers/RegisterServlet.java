package hbv.example.controllers;

import hbv.example.dao.Counters;
import hbv.example.dao.Test;
import hbv.example.dao.UserDao;
import hbv.example.model.User;
import redis.clients.jedis.Jedis;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/insertUser")
public class RegisterServlet extends HttpServlet {
    private Jedis jedis;
    Counters counter = new Counters();

    public RegisterServlet(){
        super();
        this.jedis = new Jedis("localhost" , 6379); // replace with your Redis server address
    }

    private static final long serialVersionUID = 1L;



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
        counter.incrementPageViews("Page_called","Registrieren");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int userId = -1;

        User user = new User(firstname, lastname, email,password);
        UserDao userDao = new UserDao();

        if (!userDao.isEmailExists(email)) {
            userId = userDao.addUser(user);
            if (userId > 0) {
                counter.redisCounter("s_reg");
                response.sendRedirect("login.html?registration=success");
            } else {
                response.sendRedirect("register.html?error=1");
            }
        } else {
            response.sendRedirect("register.html?error=emailexists");
        }

        // Increment the page views for "/insertUser"

//        System.out.println("Page views for /insertUser: " + getPageViews("/insertUser"));
    }
}
