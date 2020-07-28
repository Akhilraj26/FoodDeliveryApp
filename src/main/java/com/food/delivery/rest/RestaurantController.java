package com.food.delivery.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.food.delivery.domain.Menu;
import com.food.delivery.domain.MenuRepository;
import com.food.delivery.domain.Restaurant;
import com.food.delivery.domain.RestaurantRepository;
import com.google.gson.Gson;

import javassist.expr.NewArray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository cr;

    @Autowired
    private MenuRepository mr;

    @RequestMapping("/")
    public @ResponseBody List<String> getRestaurants() {
    	System.out.println("list of restaurents");
    	
        List<Restaurant> restaurants = cr.findAll();
        List<String> stringList = new ArrayList<String>();
        for(Restaurant i: restaurants) {
        	stringList.add(i.toString());
        }
        
        return stringList;
    }

    @RequestMapping("/{id}")
    public @ResponseBody String findRestaurantById(@PathVariable("id") Long id) {
    	System.out.println("list of restaurents ID");
        return cr.findOne(id).toString();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<Restaurant> restaurants) {
        cr.save(restaurants);
    }

    @DeleteMapping("/")
    public void deleteAll() {
        cr.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        cr.delete(id);
    }

    @RequestMapping("/{id}/menus/")
    public List<Menu> getMenus(@PathVariable("id") Long id) {
        Restaurant rest = cr.findOne(id);
        if (rest != null)
            return mr.findByRestaurant_Id(id);
        return new LinkedList<Menu>();
    }

    @PostMapping("/{id}/menus/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMenus(@PathVariable("id") Long id, @RequestBody List<Menu> menus) {
        Restaurant rest = cr.findOne(id);
        if (rest == null)
            return ;

        for(Menu m : menus)
            m.setRestaurant(rest);
        mr.save(menus);
    }

    @DeleteMapping("/{id}/menus/")
    public void deleteMenus(@PathVariable("id") Long id) {
        Restaurant rest = cr.findOne(id);
        if (rest == null)
            return ;
		/*mr.deleteByRestaurant_Id(rest.getId()); */
    }

}
