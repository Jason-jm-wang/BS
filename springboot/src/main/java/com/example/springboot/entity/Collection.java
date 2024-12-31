package com.example.springboot.entity;

import lombok.Data;

@Data
public class Collection {
    private int id;
    private int goodid;
    private String goodname;
    private String price;
    private String platform;
    private String starttime;
    private String checktime;
}
