package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.DTO.HomePageDTO;
import com.fivetwoff.hyonlinebe.VO.StatusCodeVO;
import com.fivetwoff.hyonlinebe.VO.SubGoodsVO;
import com.fivetwoff.hyonlinebe.entity.cascade.GoodsAndCart;
import com.fivetwoff.hyonlinebe.entity.cascade.UserAndCart;
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
    public Map<String, List<SubGoodsVO>> showGoods() {  //goodsList
        SubGoodsVO sg = new SubGoodsVO();
        sg.setStoreName("Normal Server");
        ArrayList<SubGoodsVO> goodsList = new ArrayList<>();
        List<Goods> goodsList1 = gService.findAll();
        Map<String, List<SubGoodsVO>> map = new HashMap<>();
        for (Goods g : goodsList1) {
            try {
                Integer sId = sgService.findByGoods(g.getId()).get(0).getStore_key();
                SubGoodsVO goods = new SubGoodsVO();
                goods.setGoods(g);
                goods.setStoreName(sService.findById(sId).getName());
                goodsList.add(goods);
            } catch (Exception e) {
                System.out.println(e.toString());
                goodsList.add(null);
                sg.setStoreName(e.toString());
            }
        }
        map.put("goodsList", goodsList);
        map.put("info", List.of(sg));
        return map;
    }

    @PostMapping("")
    public StatusCodeVO addGoods(@RequestBody HomePageDTO homePageDTO, HttpServletResponse response) {
        if (homePageDTO == null) {
            response.setStatus(404);
            return new StatusCodeVO(404, "传入数据为空");
        }
        Integer cId = ucService.findByUser(homePageDTO.getUid()).getCart_key();
        try {
            if (cId == null) {
                Cart c = new Cart();
                c.setId(cService.findAll().get(cService.findAll().size() + 1).getId() + 1);
                c.setTotal_price(0.0);
                if (cService.insert(c)) {
                    UserAndCart uc = new UserAndCart();
                    uc.setUser_key(homePageDTO.getUid());
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
            return new StatusCodeVO(500, e.toString());
        }

        GoodsAndCart gc = new GoodsAndCart();
        gc.setGoods_key(homePageDTO.getGid());
        gc.setCart_key(cId);
        if (gcService.insert(gc)) {
            response.setStatus(200);
            return new StatusCodeVO(200, "Normal Server");
        } else {
            response.setStatus(500);
            return new StatusCodeVO(500, "goods_cart表插入失败");
        }
    }
}
