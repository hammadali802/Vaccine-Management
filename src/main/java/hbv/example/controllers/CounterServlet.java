package hbv.example.controllers;

import hbv.example.dao.RedisToJson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "CounterServlet", value = "/counter")
public class CounterServlet extends HttpServlet {

    RedisToJson redisToJson ;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONObject countersJson = null;
        try {
            countersJson = new RedisToJson().getAllKeysAndValuesAsJson();
        }catch (Exception e){


        }
//        System.out.println(appointmentsJSON);
//        // Set response content type to JSON



        response.setContentType("text/json");

        PrintWriter out = response.getWriter();
        out.println(countersJson.toString());
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}