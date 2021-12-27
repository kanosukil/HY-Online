package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.cascade.StoreAndGoods;
import com.fivetwoff.hyonlinebe.entity.Goods;
import com.fivetwoff.hyonlinebe.service.GoodsService;
import com.fivetwoff.hyonlinebe.service.StoreService;
import com.fivetwoff.hyonlinebe.service.cascade.StoreGoodsService;
import com.fivetwoff.hyonlinebe.service.cascade.UserStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/store")
public class GoodsManageController {
    @Autowired
    private StoreGoodsService sgService;
    @Autowired
    private GoodsService gService;
    @Autowired
    private UserStoreService usService;
    @Autowired
    private StoreService sService;

    @GetMapping("/show")
    public Map<String, Object> showStore(@RequestParam("uId") String uId) {
        Map<String, Object> map = new HashMap<>();
        List<Goods> goodsList = new ArrayList<>();
        Integer sId = usService.findByUser(Integer.parseInt(uId)).get(0).getStore_key();
        String storeName = sService.findById(sId).getName();
        List<StoreAndGoods> sgList = sgService.findByStore(sId);
        for (StoreAndGoods sg : sgList) {
            goodsList.add(gService.findById(sg.getGoods_key()));
        }
        map.put("userId", uId);
        map.put("storeName", storeName);
        map.put("list", goodsList);
        return map;
    }

    @PostMapping("/add")
    public void addGoods(@RequestParam("uId") String uId, @RequestBody Goods good,
                         HttpServletResponse response) {
        if (gService.insert(good)) {
            response.setStatus(200);
        } else {
            response.setStatus(404);
            return;
        }
        Integer sId = usService.findByUser(Integer.parseInt(uId)).get(0).getStore_key();
        StoreAndGoods sg = new StoreAndGoods();
        sg.setStore_key(sId);
        sg.setGoods_key(good.getId());
        sgService.insert(sg);
    }

    @PostMapping("/delete")
    public void deleteGoods(@RequestParam("gId") String gId, HttpServletResponse response) {
        if (gService.deleteById(Integer.parseInt(gId))) {
            response.setStatus(200);
        } else {
            response.setStatus(404);
            return;
        }
        sgService.deleteByGoods(Integer.parseInt(gId));
    }

    @PostMapping("/update")
    public void updateGoods(@RequestBody Goods good, HttpServletResponse response) {
        if (gService.update(good)) {
            response.setStatus(200);
        } else {
            response.setStatus(404);
        }
    }
}
