package com.example.springboot.Service.implementation;

import com.example.springboot.entity.Good;
import com.example.springboot.Mapper.GoodsMapper;
import com.example.springboot.Service.IGoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GoodService implements IGoodService {
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public void saveGood(Good good) {
        if (goodsMapper == null) {
            goodsMapper.insertGood(good);
        }else{
            goodsMapper.updateGood(good);
        }
    }

    @Override
    public Good getGoodById(int id) {
        return goodsMapper.selectGoodById(id);
    }

    @Override
    public List<Good> getAllGoods() {

        return goodsMapper.selectAllGoods();
    }

    @Override
    public List<Good> searchGoodsByName(String name) {
        return goodsMapper.selectGoodsByName("%" + name + "%");
    }

    @Override
    public List<Good> getGoodsByCategory(String category1, String category2) {
        return goodsMapper.selectGoodsByCategory(category1, category2);
    }

    @Override
    public void deleteGoodById(int id) {
        goodsMapper.deleteGoodById(id);
    }
}
