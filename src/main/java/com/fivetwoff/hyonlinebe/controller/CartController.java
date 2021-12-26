package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.cascade.GoodsAndCart;
import com.fivetwoff.hyonlinebe.entity.Goods;
import com.fivetwoff.hyonlinebe.service.GoodsService;
import com.fivetwoff.hyonlinebe.service.cascade.GoodsCartService;
import com.fivetwoff.hyonlinebe.service.cascade.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class CartController {
    @Autowired
    private UserCartService ucService;
    @Autowired
    private GoodsCartService gcService;
    @Autowired
    private GoodsService gService;

    @GetMapping("")
    public List<Goods> getCart(@RequestParam("uId") String uId) {
        int cKey = ucService.findByUser(Integer.parseInt(uId)).getCart_key();
        List<GoodsAndCart> gcList = gcService.findByCart(cKey);
        ArrayList<Goods> goods = new ArrayList<>();
        for(GoodsAndCart x:gcList){
            goods.add(gService.findById(x.getGoods_key()));
        }
        return goods;
    }

    @PostMapping("")
    public void deleteGood(@RequestParam("gId") String gId) {
        gcService.deleteByGoods(Integer.parseInt(gId));
    }
}
