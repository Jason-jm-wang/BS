package com.example.springboot.Mapper;

import com.example.springboot.entity.Good;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
    List<Good> list();

    void save(Good good);

    void insertGood(Good good);

    void updateGood(Good good);

    Good selectGoodById(int id);

    List<Good> selectAllGoods();

    List<Good> selectGoodsByName(String s);

    List<Good> selectGoodsByCategory(String category);

    void deleteGoodById(int id);
}
