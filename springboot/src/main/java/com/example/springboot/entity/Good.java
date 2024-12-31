package com.example.springboot.entity;

import lombok.Data;

@Data
public class Good {
    private int id;
    private String name;
    private int price;      // 单位分，避免精度问题
    private String description;
    private String image;
    private String category;
    private String platform;
    private String url;
}
