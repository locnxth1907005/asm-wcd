package helper;

import entity.Category;
import entity.Food;
import hannotation.Column;
import hannotation.Entity;
import hannotation.Id;
import org.reflections.Reflections;
import service.CategoryService;
import service.FoodService;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Set;

public class HMigration {

    public static void main(String[] args) throws SQLException {
        // quét trong package entity.
        Reflections reflections = new Reflections("entity");
        // tìm ra tất cả các class có annotation là @Entity.
        Set<Class<?>> allClasses =
                reflections.getTypesAnnotatedWith(Entity.class);
        for (Class<?> c :
                allClasses) {
            registerClass(c);
            if(c.getSimpleName().equals("Food")){
                FoodService foodService = new FoodService();

                Food food1 = new Food();
                food1.setFoodName("Bún Mọc");
                food1.setDescription("Bún mọc nhà làm");
                food1.setCategoryId(1);
                food1.setThumbnail("https://res.cloudinary.com/badboiz/image/upload/v1621777355/sz39fyaoelrrfvks8g7s.jpg");
                food1.setPrice(30000);
                food1.setStatus(1);
                food1.setCreatedAt(Date.valueOf("2021-05-23"));
                foodService.create(food1);

                Food food2 = new Food();
                food2.setFoodName("Bún Chả");
                food2.setDescription("Bún chả nhà làm");
                food2.setCategoryId(1);
                food2.setThumbnail("https://res.cloudinary.com/badboiz/image/upload/v1621777337/uxhiiaowpgthxflkefol.jpg");
                food2.setPrice(35000);
                food2.setStatus(1);
                food2.setCreatedAt(Date.valueOf("2021-05-23"));
                foodService.create(food2);

                Food food3 = new Food();
                food3.setFoodName("Bún Cá");
                food3.setDescription("Bún cá nhà làm");
                food3.setCategoryId(1);
                food3.setThumbnail("https://res.cloudinary.com/badboiz/image/upload/v1621777326/xassznkzembvxqvnoauz.jpg");
                food3.setPrice(25000);
                food3.setStatus(1);
                food3.setCreatedAt(Date.valueOf("2021-05-23"));
                foodService.create(food3);

                Food food4 = new Food();
                food4.setFoodName("Bún Bò Huế");
                food4.setDescription("Bún Bò Huế nhà làm");
                food4.setCategoryId(1);
                food4.setThumbnail("https://res.cloudinary.com/badboiz/image/upload/v1621777316/eg9xb0apnq27g6rrmpqe.jpg");
                food4.setPrice(40000);
                food4.setStatus(1);
                food4.setCreatedAt(Date.valueOf("2021-05-23"));
                foodService.create(food4);

                Food food5 = new Food();
                food5.setFoodName("Bún đậu mắm tôm");
                food5.setDescription("Bún đậu mắm tôm");
                food5.setCategoryId(1);
                food5.setThumbnail("https://res.cloudinary.com/badboiz/image/upload/v1621777347/usnynzj3mv4igaedtz8f.jpg");
                food5.setPrice(40000);
                food5.setStatus(1);
                food5.setCreatedAt(Date.valueOf("2021-05-23"));
                foodService.create(food5);

                Food food6 = new Food();
                food6.setFoodName("Bún Thang");
                food6.setDescription("Bún thang");
                food6.setCategoryId(1);
                food6.setThumbnail("https://res.cloudinary.com/badboiz/image/upload/v1621777726/jz5n2a9yzjg1plxjof71.jpg");
                food6.setPrice(35000);
                food6.setStatus(1);
                food6.setCreatedAt(Date.valueOf("2021-05-23"));
                foodService.create(food6);


                System.out.printf("Tạo món thành công.");
            }

            if(c.getSimpleName().equals("Category")){
                CategoryService categoryService = new CategoryService();

                Category category1 = new Category();
                category1.setCategoryName("Món nướng");
                category1.setCreatedAt(Date.valueOf("2021-05-23"));
                category1.setUpdatedAt(Date.valueOf("2021-05-23"));
                categoryService.create(category1);
                Category category2 = new Category();
                category2.setCategoryName("Món Luộc");
                category2.setCreatedAt(Date.valueOf("2021-05-23"));
                category2.setUpdatedAt(Date.valueOf("2021-05-23"));
                categoryService.create(category2);
                Category category3 = new Category();
                category3.setCategoryName("Món Chay");
                category3.setCreatedAt(Date.valueOf("2021-05-23"));
                category3.setUpdatedAt(Date.valueOf("2021-05-23"));
                categoryService.create(category3);
                Category category4 = new Category();
                category4.setCategoryName("Đồ uống");
                category4.setCreatedAt(Date.valueOf("2021-05-23"));
                category4.setUpdatedAt(Date.valueOf("2021-05-23"));
                categoryService.create(category4);
                System.out.printf("Thêm loại món ăn thành công.");
            }
        }
    }
    public static void registerClass(Class clazz) throws SQLException {

        try {
            // kiểm tra class có được đánh dấu là @Entity hay không?
            if (!clazz.isAnnotationPresent(Entity.class)) {
                return;
            }
            // Lấy ra giá trị của annotation entity.
            Entity entityInfor = (Entity) clazz.getAnnotation(Entity.class);
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(SQLConstant.CREATE_TABLE);
            strQuery.append(SQLConstant.SPACE);
            strQuery.append(entityInfor.tableName());
            strQuery.append(SQLConstant.SPACE);
            strQuery.append(SQLConstant.OPEN_PARENTHESES);
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                // check xem trường có phải là column không.
                if (!field.isAnnotationPresent(Column.class)) {
                    continue;
                }
                // lấy thông tin column để check tên trường, kiểu giá trị của trường.
                Column columnInfor = (Column) field.getAnnotation(Column.class);
                strQuery.append(columnInfor.columnName());
                strQuery.append(SQLConstant.SPACE);
                strQuery.append(columnInfor.columnType());
                // check xem trường có phải là id không.
                if (field.isAnnotationPresent(Id.class)) {
                    // lấy thông tin id.
                    Id idInfor = (Id) field.getAnnotation(Id.class);
                    strQuery.append(SQLConstant.SPACE);
                    strQuery.append(SQLConstant.PRIMARY_KEY);
                    if (idInfor.autoIncrement()) {
                        strQuery.append(SQLConstant.SPACE);
                        strQuery.append(SQLConstant.AUTO_INCREMENT);
                    }
                }
                strQuery.append(SQLConstant.COMMON);
                strQuery.append(SQLConstant.SPACE);
            }
            strQuery.setLength(strQuery.length() - 2);
            strQuery.append(SQLConstant.CLOSE_PARENTHESES);
            ConnectionHelper.getConnection().createStatement().execute(strQuery.toString());
            System.out.printf("Tạo bảng %s thành công.\n", entityInfor.tableName());

        } catch (java.sql.SQLSyntaxErrorException sqlSyntaxErrorException) {
            // sqlSyntaxErrorException.printStackTrace();
            System.err.printf("Có lỗi xảy ra trong quá trình tạo bảng. Error %s.\n", sqlSyntaxErrorException.getMessage());
        }
    }
}
