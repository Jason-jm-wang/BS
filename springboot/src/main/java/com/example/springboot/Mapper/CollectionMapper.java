package com.example.springboot.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.springboot.entity.Collection;

@Mapper
public interface CollectionMapper {

    void insertCollection(Collection collection);

    void deleteCollectionById(int id);

    List<Collection> selectAllCollections();

    List<Collection> selectCollectionByGoodName();
}
