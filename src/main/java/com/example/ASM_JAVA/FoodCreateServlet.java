package com.example.ASM_JAVA;

import entity.Food;
import service.CategoryService;
import service.FoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "createFoodServlet",value = "/food/create")
public class FoodCreateServlet extends HttpServlet {
    private FoodService foodService = new FoodService();
    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("validateString", "You must complete in all the information!");
        req.setAttribute("categories", categoryService.getList());
        req.getRequestDispatcher("/food/create.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Food food = new Food();
        food.setFoodName(req.getParameter("foodName"));
        food.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
        food.setDescription(req.getParameter("description"));
        food.setPrice(Double.valueOf(req.getParameter("price")));
        food.setCreatedAt(Date.valueOf(req.getParameter("createAt")));
        food.setUpdatedAt(Date.valueOf(req.getParameter("updateAt")));
        food.setThumbnail(req.getParameter("thumbnailz"));
        food.setStatus(Integer.parseInt(req.getParameter("status")));
        if(foodService.create(food)){
            req.setAttribute("listFoodServlet",foodService.getList());
            req.setAttribute("count",foodService.getCount());
            req.getRequestDispatcher("/food/list.jsp").forward(req,resp);

        }else{
            req.setAttribute("validateString","The name of the food must be more than 7 characters, the price must be greater than 0 and can't be null");
            req.setAttribute("categories", categoryService.getList());
            req.getRequestDispatcher("/food/create.jsp").forward(req,resp);
        }
    }
}
