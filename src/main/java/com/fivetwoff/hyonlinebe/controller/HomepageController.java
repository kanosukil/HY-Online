package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.DTO.SubGoods;
import com.fivetwoff.hyonlinebe.cascade.GoodsAndCart;
import com.fivetwoff.hyonlinebe.entity.Goods;
import com.fivetwoff.hyonlinebe.service.GoodsService;
import com.fivetwoff.hyonlinebe.service.StoreService;
import com.fivetwoff.hyonlinebe.service.cascade.GoodsCartService;
import com.fivetwoff.hyonlinebe.service.cascade.StoreGoodsService;
import com.fivetwoff.hyonlinebe.service.cascade.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/homepage")
public class HomepageController {
    @Autowired
    private GoodsService gService;
    @Autowired
    private GoodsCartService gcService;
    @Autowired
    private UserCartService ucService;
    @Autowired
    private StoreGoodsService sgService;
    @Autowired
    private StoreService sService;

    @GetMapping("")
    public Map<String, List<SubGoods>> showGoods(){  //goodsList
        ArrayList<SubGoods> goodsList = new ArrayList<>();
        List<Goods> goodsList1 = gService.findAll();
        Map<String, List<SubGoods>> map = new HashMap<>();
        for(Goods g:goodsList1){
            Integer sId = sgService.findByGoods(g.getId()).get(0).getStore_key();
            SubGoods goods = new SubGoods();
            goods.setGoods(g);
            goods.setStoreName(sService.findById(sId).getName());
            goodsList.add(goods);
        }
        map.put("goodsList", goodsList);
        return map;
    }

    @PostMapping("")
    public void addGoods(@RequestParam("uId") String uId, @RequestParam("gId") String gId, HttpServletResponse response) {
        int cId = ucService.findByUser(Integer.parseInt(uId)).getCart_key();
        GoodsAndCart gc = new GoodsAndCart();
        gc.setGoods_key(Integer.parseInt(gId));
        gc.setCart_key(cId);
        if(gcService.insert(gc)){
            response.setStatus(200);
        }else {
            response.setStatus(404);
        }
    }
}
