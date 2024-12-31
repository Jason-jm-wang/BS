package com.example.springboot.Service;

import com.example.springboot.entity.Collection;

import java.util.List;

public interface ICollectionService {

    /**
     * 添加收藏记录
     *
     * @param collection 收藏实体
     */
    void addCollection(Collection collection);

    /**
     * 根据 ID 删除收藏记录
     *
     * @param id 收藏记录 ID
     */
    void deleteCollectionById(int id);

    /**
     * 获取所有收藏记录
     *
     * @return 收藏记录列表
     */
    List<Collection> getAllCollections();

    /**
     * 根据商品名称模糊查询收藏记录
     *
     * @param goodName 商品名称
     * @return 收藏记录列表
     */
    List<Collection> searchCollectionsByGoodName(String goodName);

    /**
     * 设置商品查价时间
     *
     * @param id        收藏记录 ID
     * @param startTime 查价开始时间
     * @param checkTime 查价检查时间
     */
    void setPriceCheckTime(int id, String startTime, String checkTime);

}
