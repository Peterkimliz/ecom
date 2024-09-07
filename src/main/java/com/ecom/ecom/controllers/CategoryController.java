package com.ecom.ecom.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {


    public ResponseEntity<List<String>> getAllCategories(){
        List iList=new ArrayList<>();
        iList.add("iList");
        return new ResponseEntity<List<String>>(iList,HttpStatus.OK);
    
    }
    
}
