package service;

import entity.Category;
import entity.Food;
import repository.GenericRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FoodService {
    private GenericRepository<Food> genericRepository;
    public FoodService() {

        this.genericRepository = new GenericRepository<>(Food.class);
    }
    java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
    public boolean create(Food obj) {

        if(!genericRepository.save(obj)) {
            return true;

        }

        return false;
    }
    public int getCount(){

        return genericRepository.getCount();
    }

    public Food findById(int id){

        return genericRepository.findById(id);
    }
    public ArrayList<Food> getList() {

        return genericRepository.findAll();
    }
    public boolean edit (Food food , int id) {

        if (genericRepository.findById(id) != null) {

            if (genericRepository.update(id, food)) {

                return genericRepository.update(id, food);
            }

            return false;
        }
        return false;
    }
    public boolean delete(int id){

            if (genericRepository.findById(id) != null) {
                Food food = genericRepository.findById(id);
                food.setStatus(0);
                genericRepository.update(id, food);
                return true;
            }
            return false;
    }
}
