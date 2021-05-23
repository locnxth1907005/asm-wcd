<%@ page import="entity.Food" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Category" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/22/2021
  Time: 2:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Food> foods = (ArrayList<Food>) request.getAttribute("listFoodServlet");
    Category category = new Category();
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div class="">
        <h1 class="text-center">List Foods</h1>
        <button class = "btn-success"style="border: 2px;"><a href="/food/create"style="color:lightskyblue">CREATE FOOD</a></button>
        <table class="table" style="height: 500px;">
            <thead class="table-info" style="background-color: magenta">
            <tr  class="text-center">
                <th scope="col">Mã</th>
                <th scope="col">Tên món ăn</th>
                <th scope="col" style="width: 10%">Danh mục</th>
                <th scope="col">Mô tả</th>
                <th scope="col">Thumbnail</th>
                <th scope="col">Giá</th>
                <th scope="col">Ngày bắt đầu bán</th>
                <th scope="col">Ngày cập nhật</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Chức năng</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (int i = 0; i < foods.size(); i++) {
                    Food food = foods.get(i);
            %>
            <tr class="contentPage">
                <td scope="col" style="text-align: center"><%=food.getId()%>
                </td>
                <td scope="col" style="text-align: center"><%=food.getFoodName()%>
                </td>
                <td scope="col" style="text-align: center"><%=category.getNameFromId(food.getCategoryId()) %>
                </td>
                <td scope="col" style="text-align: center"><%=food.getDescription()%>
                </td>
                <td scope="col" style="text-align: center">
                    <div style="width: 100px; height: 100px; background-repeat: no-repeat; background-position: center; background-size: cover; background-image: url('<%=food.getThumbnail()%>')"></div>
                </td>
                <td scope="col" style="text-align: center"><%=food.getPrice()%> VNĐ
                </td>
                <td scope="col"
                    style="text-align: center"><%= food.getCreatedAt()%>
                </td>
                <td scope="col"
                    style="text-align: center"><%=food.getUpdatedAt()%>
                </td>
                <td scope="col" style="text-align: center"><%=food.caseStatus(food.getStatus())%>
                </td>
                <% if (food.getStatus() == 0) { %>
                <td scope="col" style="text-align: center">
                    <div class="btn-group">


                    </div>
                </td>
                <% } else { %>
                <td scope="col" style="text-align: center">
                    <div class="btn-group">
                        <a href="/food/edit?id=<%=food.getId()%>" class="btn btn-outline-success">Sửa</a>
                        <a href="/food/delete?id=<%=food.getId()%>" class="btn btn-outline-dark">Xóa</a>
                    </div>
                </td>
                <% } %>

            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
