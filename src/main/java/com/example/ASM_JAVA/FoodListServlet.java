package com.example.ASM_JAVA;

import service.FoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FoodListServlet",urlPatterns = "/food/list")
public class FoodListServlet extends HttpServlet {
    private FoodService foodService = new FoodService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listFoodServlet",foodService.getList());
        req.setAttribute("count",foodService.getCount());
        req.getRequestDispatcher("/food/list.jsp").forward(req,resp);
    }
}
