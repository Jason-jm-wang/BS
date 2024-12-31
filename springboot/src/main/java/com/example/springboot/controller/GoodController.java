package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Good;
import com.example.springboot.Service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/good")
public class GoodController {

    public GoodController() {
        System.out.println("GoodController has been initialized!");
    }

    @Autowired
    IGoodService goodService;

    /**
     * 添加或更新商品信息
     */
    @PostMapping("/save")
    public Result save(@RequestBody Good good) {
        goodService.saveGood(good);
        return Result.success();
    }

    /**
     * 根据 ID 获取商品信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable int id) {
        Good good = goodService.getGoodById(id);
        return Result.success(good);
    }

    /**
     * 获取所有商品信息
     */
    @GetMapping("/list")
    public Result list() {
        List<Good> goods = goodService.getAllGoods();
        return Result.success(goods);
    }

    /**
     * 根据名称模糊搜索商品
     */
    @GetMapping("/search")
    public Result searchByName(@RequestParam String name) {
        List<Good> goods = goodService.searchGoodsByName(name);
        return Result.success(goods);
    }

    /**
     * 根据分类获取商品
     */
    @GetMapping("/category")
    public Result getByCategory(@RequestParam String category1, String category2) {
        List<Good> goods = goodService.getGoodsByCategory(category1, category2);
        return Result.success(goods);
    }

    /**
     * 删除商品
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        goodService.deleteGoodById(id);
        return Result.success();
    }
}
