package entity;

import hannotation.Column;
import hannotation.Entity;
import hannotation.Id;

import java.sql.Date;
@Entity(tableName = "foods")
public class Food {
    @Id(autoIncrement = true)
    @Column(columnName = "id", columnType = "INT")
    private int id;
    @Column(columnName = "foodName", columnType = "VARCHAR(250)")
    private String foodName;
    @Column(columnName = "categoryId", columnType = "VARCHAR(250)")
    private int categoryId;
    @Column(columnName = "description", columnType = "VARCHAR(250)")
    private String description;
    @Column(columnName = "thumbnail", columnType = "VARCHAR(250)")
    private String thumbnail;
    @Column(columnName = "price",columnType = "DOUBLE")
    private double price;
    @Column(columnName = "createdAt", columnType = "DATE")
    private Date createdAt;
    @Column(columnName = "updateAt", columnType = "DATE")
    private Date updatedAt;
    @Column(columnName = "status", columnType = "INT")
    private int Status;

//    public enum CategoryName{
//        GRILLED(1),BOILED(2),VEGETARIAN(3),DRINK(4);
//        private int value;
//        CategoryName(int i){this.value = i;}
//        public int getValue(){
//            return value;
//        }
//    }

    public Food() {
    }
    public boolean isValid() {
        if (this.getFoodName().length() > 7 && this.getPrice() > 0) {
            return true;
        }

        return false;
    }

    public String caseStatus(int status) {
        switch (status) {
            case 0:
                return "DELETE";
            case 1:
                return "SELLING";
            case 2:
                return "STOP SELLING";
            default:
                return "";
        }
    }
    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", categoryId=" + categoryId +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", price='" + price + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", Status=" + Status +
                '}';
    }

    public Food(int id, String foodName, int categoryId, String description, String thumbnail, double price, Date createdAt, Date updatedAt, int status) {
        this.id = id;
        this.foodName = foodName;
        this.categoryId = categoryId;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        Status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
