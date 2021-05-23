package entity;

import hannotation.Column;
import hannotation.Entity;
import hannotation.Id;
import service.CategoryService;

import java.sql.Date;

@Entity(tableName = "categories")
public class Category {
    @Id(autoIncrement = true)
    @Column(columnName = "id", columnType = "INT")
    private int id;
    @Column(columnName = "categoryName", columnType = "VARCHAR(250)")
    private String categoryName;
    @Column(columnName = "createdAt", columnType = "DATE")
    private Date createdAt;
    @Column(columnName = "updateAt", columnType = "DATE")
    private Date updatedAt;
    @Column(columnName = "status", columnType = "INT")
    private int Status;

//    public enum StatusCategory {
//        SELLING(1), STOPSELLING(2), DELETE(0);
//        private int value;
//
//        StatusCategory(int i) {
//            this.value = i;
//        }
//
//        public int getValue() {
//            return value;
//        }

    private CategoryService categoryService = new CategoryService();
    public String getNameFromId(int id) {
        String name = categoryService.findById(id).getCategoryName();
        return name;
    }
    public Category() {
    }

    public Category(int id, String categoryName, Date createdAt, Date updatedAt, int status) {
        this.id = id;
        this.categoryName = categoryName;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
