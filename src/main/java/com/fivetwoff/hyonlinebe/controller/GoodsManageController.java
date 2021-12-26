package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.cascade.StoreAndGoods;
import com.fivetwoff.hyonlinebe.entity.Goods;
import com.fivetwoff.hyonlinebe.service.GoodsService;
import com.fivetwoff.hyonlinebe.service.cascade.StoreGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/store")
public class GoodsManageController {
    @Autowired
    private StoreGoodsService sgService;
    @Autowired
    private GoodsService gService;

    @GetMapping("")
    public List<Goods> showStore(@RequestParam("sId") String sId) {
        List<StoreAndGoods> sgList = sgService.findByStore(Integer.parseInt(sId));
        ArrayList<Goods> goods = new ArrayList<>();
        for(StoreAndGoods sg:sgList){
            goods.add(gService.findById(sg.getGoods_key()));
        }
        return goods;
    }

    @PostMapping("")
    public void addGoods(@RequestParam("sId") String sId, @RequestBody Goods good) {
        StoreAndGoods sg = new StoreAndGoods();
        sg.setStore_key(Integer.parseInt(sId));
        sg.setGoods_key(good.getId());
        sgService.insert(sg);
        gService.insert(good);
    }

    @PostMapping("")
    public void deleteGoods(@RequestParam("gId") String gId) {
        sgService.deleteByGoods(Integer.parseInt(gId));
        gService.deleteById(Integer.parseInt(gId));
    }

    @PostMapping("")
    public void updateGoods(@RequestBody Goods good) {
        gService.update(good);
    }
}
