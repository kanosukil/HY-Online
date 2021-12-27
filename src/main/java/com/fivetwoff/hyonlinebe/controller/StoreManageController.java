package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.entity.Store;
import com.fivetwoff.hyonlinebe.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class StoreManageController {
    @Autowired
    private StoreService service;

    @GetMapping("")
    public Map<String, List<Store>> showStores() {
        List<Store> stores = service.findAll();
        Map<String, List<Store>> map = new HashMap<>();
        map.put("list", stores);
        return map;
    }
}
