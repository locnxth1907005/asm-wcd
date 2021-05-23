<%@ page import="entity.Food" %>
<%@ page import="entity.Category" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/23/2021
  Time: 9:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Food food = (Food) request.getAttribute("currentFood");
    String validateString = (String) request.getAttribute("validateString");
    ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
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
        <form action="/food/edit" method="post">

            <div class="form-group">
                <label>Tên món</label>
                <input type="text" class="form-control" name="foodName" value="<%=food.getFoodName()%>">
            </div>
            <div class="form-group">
                <label for="categoryId">Danh mục</label>
                <select class="form-control" id="categoryId" name="categoryId">
                    <%for (Category category:categories) { %>
                    <option value=<%=category.getId()%>><%=category.getCategoryName()%></option>
                    <%}%>
                </select>
            </div>
            <div class="form-group">
                <label>Mô tả</label>
                <input type="text" class="form-control" name="description" value="<%=food.getDescription()%>" >

            </div>
            <div class="form-group">
                <button type="button" id="upload_widget" class="btn btn-primary">Sửa ảnh
                </button>
                <input hidden type="text" name="thumbnailz" id="picture" value="<%=food.getThumbnail()%>">
                <div class="thumbnails"></div>
            </div>
            <div class="form-group">
                <label>Ngày sửa</label>
                <input type="date" class="form-control" name="updateAt" value="<%=food.getUpdatedAt()%>" >
            </div>
            <div class="form-group">
                <label>Giá</label>
                <input type="text" class="form-control" name="price" value="<%=food.getPrice()%>" >
            </div>
            <div class="form-group">
                <label>Trạng thái</label>
                <select class="form-control" id="status" name="status">
                    <option value="1">SELLING</option>
                    <option value="2">STOPSELLING</option>
                </select>
            </div>
            <input hidden type="text" name="id" value="<%=food.getId()%>">
            <button type="submit" class="btn btn-primary">Submit</button>
            <button type="reset" class="btn btn-primary">Reset</button>
        </form>
    </div>
</div>

</body>
</html>
<script src="https://upload-widget.cloudinary.com/global/all.js" type="text/javascript"></script>

<script type="text/javascript">
    var myWidget = cloudinary.createUploadWidget(
        {
            cloudName: 'badboiz',
            uploadPreset: 'locdeptrai',
            multiple: false,
            form: '#product_form',
            fieldName: 'thumbnail',
            thumbnails: '.thumbnails'
        }, function (error, result) {
            if (!error && result && result.event === "success") {
                $('#picture').val(result.info.url);
            }
        }
    );
    document.getElementById("upload_widget").addEventListener("click", function(){
        myWidget.open();
    }, false);
    // xử lý js trên dynamic content.

</script>
