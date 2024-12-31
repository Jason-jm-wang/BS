package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class BaseRequest {
    private int PageNumber = 1;
    private int PageSize = 10;
}
