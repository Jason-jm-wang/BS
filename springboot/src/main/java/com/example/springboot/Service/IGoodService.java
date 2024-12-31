package com.example.springboot.Service;

import com.example.springboot.entity.Good;

import java.util.List;

public interface IGoodService {

    /**
     * 添加或更新商品信息
     *
     * @param good 商品实体
     */
    void saveGood(Good good);

    /**
     * 根据商品 ID 获取商品信息
     *
     * @param id 商品 ID
     * @return 商品实体
     */
    Good getGoodById(int id);

    /**
     * 获取所有商品信息
     *
     * @return 商品列表
     */
    List<Good> getAllGoods();

    /**
     * 根据名称模糊搜索商品
     *
     * @param name 商品名称
     * @return 商品列表
     */
    List<Good> searchGoodsByName(String name);

    /**
     * 根据分类查询商品
     *
     * @param category1 分类名称1 category2 分类名称2
     * @return 商品列表
     */
    List<Good> getGoodsByCategory(String category1, String category2);

    /**
     * 删除商品
     *
     * @param id 商品 ID
     */
    void deleteGoodById(int id);
}

