package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.entity.Store;
import com.fivetwoff.hyonlinebe.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class StoreManageController {
    @Autowired
    private StoreService service;

    @GetMapping("")
    public List<Store> showStores() {
        return service.findAll();
    }
}
