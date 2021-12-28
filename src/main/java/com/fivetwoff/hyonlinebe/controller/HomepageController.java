package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.DTO.HomePageDTO;
import com.fivetwoff.hyonlinebe.DTO.StatusCodeVO;
import com.fivetwoff.hyonlinebe.DTO.SubGoods;
import com.fivetwoff.hyonlinebe.cascade.GoodsAndCart;
import com.fivetwoff.hyonlinebe.cascade.UserAndCart;
import com.fivetwoff.hyonlinebe.entity.Cart;
import com.fivetwoff.hyonlinebe.entity.Goods;
import com.fivetwoff.hyonlinebe.service.CartService;
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
    @Autowired
    private CartService cService;

    @GetMapping("")
    public Map<String, List<SubGoods>> showGoods() {  //goodsList
        ArrayList<SubGoods> goodsList = new ArrayList<>();
        List<Goods> goodsList1 = gService.findAll();
        Map<String, List<SubGoods>> map = new HashMap<>();
        for (Goods g : goodsList1) {
            try {
                Integer sId = sgService.findByGoods(g.getId()).get(0).getStore_key();
                SubGoods goods = new SubGoods();
                goods.setGoods(g);
                goods.setStoreName(sService.findById(sId).getName());
                goodsList.add(goods);
            } catch (Exception e) {
                System.out.println(e.toString());
                goodsList.add(null);
            }
        }
        map.put("goodsList", goodsList);
        return map;
    }

    @PostMapping("")
    public StatusCodeVO addGoods(@RequestBody HomePageDTO homePageDTO, HttpServletResponse response) {
        if (homePageDTO == null) {
            response.setStatus(404);
            return new StatusCodeVO(404);
        }
        Integer cId = ucService.findByUser(Integer.parseInt(homePageDTO.getUid())).getCart_key();
        try {
            if (cId == null) {
                Cart c = new Cart();
                c.setId(cService.findAll().get(cService.findAll().size() + 1).getId() + 1);
                c.setTotal_price(0.0);
                if (cService.insert(c)) {
                    UserAndCart uc = new UserAndCart();
                    uc.setUser_key(Integer.parseInt(homePageDTO.getUid()));
                    uc.setCart_key(c.getId());
                    if (!ucService.insert(uc)) {
                        throw new Exception("user_cart表插入失败");
                    }
                } else {
                    throw new Exception("cart表插入失败");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            response.setStatus(500);
            return new StatusCodeVO(500);
        }

        GoodsAndCart gc = new GoodsAndCart();
        gc.setGoods_key(Integer.parseInt(homePageDTO.getGid()));
        gc.setCart_key(cId);
        if (gcService.insert(gc)) {
            response.setStatus(200);
            return new StatusCodeVO(200);
        } else {
            response.setStatus(500);
            return new StatusCodeVO(500);
        }
    }
}
