package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.DTO.SubGoods;
import com.fivetwoff.hyonlinebe.cascade.GoodsAndCart;
import com.fivetwoff.hyonlinebe.entity.Goods;
import com.fivetwoff.hyonlinebe.service.GoodsService;
import com.fivetwoff.hyonlinebe.service.StoreService;
import com.fivetwoff.hyonlinebe.service.cascade.GoodsCartService;
import com.fivetwoff.hyonlinebe.service.cascade.UserCartService;
import com.fivetwoff.hyonlinebe.service.cascade.UserStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
public class CartController {
    @Autowired
    private UserCartService ucService;
    @Autowired
    private GoodsCartService gcService;
    @Autowired
    private GoodsService gService;
    @Autowired
    private UserStoreService usService;
    @Autowired
    private StoreService sService;

    @GetMapping("/cart")
    public Map<String, Object> getCart(@RequestParam("uId") String uId) { //CartGoodsList
        List<Number> list = new ArrayList<>();
        int cId = ucService.findByUser(Integer.parseInt(uId)).getCart_key();
        List<GoodsAndCart> gcList = gcService.findByCart(cId);
        Integer sId = usService.findByUser(Integer.parseInt(uId)).get(0).getStore_key();
        String storeName = sService.findById(sId).getName();
        ArrayList<SubGoods> goods = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for(GoodsAndCart x:gcList){
            SubGoods sg = new SubGoods();
            sg.setStoreName(storeName);
            sg.setGoods(gService.findById(x.getGoods_key()));
            goods.add(sg);
        }
        map.put("userId", uId);
        map.put("list", goods);
        return map;
    }

    @PostMapping("")
    public void deleteGood(@RequestParam("gId") String gId, HttpServletResponse response) {
        if(!gcService.deleteByGoods(Integer.parseInt(gId))){
            response.setStatus(404);
        }else {
            response.setStatus(200);
        }
    }

    @PostMapping("")
    public void pay(@RequestParam("uId") String uId, HttpServletResponse response) {
        Integer cId = ucService.findByUser(Integer.parseInt(uId)).getCart_key();
        if(gcService.deleteByCart(cId)){
            response.setStatus(200);
        }else {
            response.setStatus(404);
        }
    }
}
