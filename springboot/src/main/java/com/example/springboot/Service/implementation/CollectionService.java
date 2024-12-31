package com.example.springboot.Service.implementation;

import com.example.springboot.Mapper.CollectionMapper;
import com.example.springboot.Service.ICollectionService;
import com.example.springboot.entity.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CollectionService implements ICollectionService {
    @Autowired
    CollectionMapper collectionMapper;

    @Override
    public void addCollection(Collection collection){
        collectionMapper.insertCollection(collection);
    }

    @Override
    public void deleteCollectionById(int id) {
        collectionMapper.deleteCollectionById(id);
    }

    @Override
    public List<Collection> getAllCollections() {
        return collectionMapper.selectAllCollections();
    }

    @Override
    public List<Collection> searchCollectionsByGoodName(String goodName) {
        return collectionMapper.selectCollectionByGoodName();
    }


    @Override
    public void setPriceCheckTime(int id, String startTime, String checkTime){

    }
}
