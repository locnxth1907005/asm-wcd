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
@WebServlet(name = "FoodUpdateServlet",urlPatterns = "/food/edit")
public class FoodUpdateServlet extends HttpServlet {
    private CategoryService categoryService = new CategoryService();
    private FoodService foodService = new FoodService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("validateString", "Vui lòng điền đầy đủ thông tin!");
        req.setAttribute("currentFood",foodService.findById(Integer.parseInt(req.getParameter("id"))));
        req.setAttribute("categories", categoryService.getList());
        req.getRequestDispatcher("/food/edit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Food food = new Food();
        food.setFoodName(req.getParameter("foodName"));
        food.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
        food.setDescription(req.getParameter("description"));
        food.setPrice(Double.valueOf(req.getParameter("price")));
        food.setUpdatedAt(Date.valueOf(req.getParameter("updateAt")));
        food.setCreatedAt(foodService.findById(Integer.parseInt(req.getParameter("id"))).getCreatedAt());
        food.setThumbnail(req.getParameter("thumbnailz"));
        food.setStatus(Integer.parseInt(req.getParameter("status")));
        if(foodService.edit(food,Integer.parseInt(req.getParameter("id")))){
            req.setAttribute("count",foodService.getCount());
            req.setAttribute("listFoodServlet",foodService.getList());
            req.getRequestDispatcher("/food/list.jsp").forward(req,resp);

        }else{
            req.setAttribute("validateString","The name of the food must be more than 7 characters, the price must be greater than 0 and can't be null");
            req.setAttribute("currentFood",foodService.findById(Integer.parseInt(req.getParameter("id"))));
            req.setAttribute("categories", categoryService.getList());
            req.getRequestDispatcher("/food/edit.jsp").forward(req,resp);
        }


    }
}
