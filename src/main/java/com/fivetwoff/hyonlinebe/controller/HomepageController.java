package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.cascade.GoodsAndCart;
import com.fivetwoff.hyonlinebe.cascade.UserAndCart;
import com.fivetwoff.hyonlinebe.entity.Goods;
import com.fivetwoff.hyonlinebe.service.GoodsService;
import com.fivetwoff.hyonlinebe.service.cascade.GoodsCartService;
import com.fivetwoff.hyonlinebe.service.cascade.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/homepage")
public class HomepageController {
    @Autowired
    private GoodsService gService;
    @Autowired
    private GoodsCartService gcService;
    @Autowired
    private UserCartService ucService;

    @GetMapping("")
    public List<Goods> showGoods(){
        return gService.findAll();
    }

    @PostMapping("")
    public void addGoods(@RequestParam("uId") String uId, @RequestParam("gId") String gId) {
        int cId = ucService.findByUser(Integer.parseInt(uId)).getCart_key();
        GoodsAndCart gc = new GoodsAndCart();
        gc.setGoods_key(Integer.parseInt(gId));
        gc.setCart_key(cId);
        gcService.insert(gc);
    }
}
