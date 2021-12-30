package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.DTO.HomePageDTO;
import com.fivetwoff.hyonlinebe.VO.StatusCodeVO;
import com.fivetwoff.hyonlinebe.VO.SubGoodsVO;
import com.fivetwoff.hyonlinebe.entity.Cart;
import com.fivetwoff.hyonlinebe.entity.Goods;
import com.fivetwoff.hyonlinebe.entity.cascade.GoodsAndCart;
import com.fivetwoff.hyonlinebe.entity.cascade.UserAndCart;
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
    public Map<String, Object> showGoods() {  //goodsList
        List<SubGoodsVO> goodsList = new ArrayList<>();
        List<Goods> goodsList1 = gService.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("info", "Normal Server");
        for (Goods g : goodsList1) {
            try {
                Integer sId = sgService.findByGoods(g.getId()).get(0).getStore_key();
                SubGoodsVO goods = new SubGoodsVO();
                goods.setStoreName(sService.findById(sId).getName());
                goods.setId(g.getId());
                goods.setImg(g.getImg());
                goods.setContext(g.getDescription());
                goods.setPrice(g.getPrice().toString());
                goodsList.add(goods);
            } catch (Exception e) {
                System.out.println(e.toString());
                goodsList.add(null);
                map.put("code", 500);
                map.put("info", e.toString());
                break;
            }
        }
        map.put("goodsList", goodsList);
        return map;
    }

    @PostMapping("")
    public StatusCodeVO addGoods(@RequestBody HomePageDTO homePageDTO, HttpServletResponse response) {
        if (homePageDTO == null || homePageDTO.getUid() == null || homePageDTO.getGid() == null) {
            response.setStatus(404);
            return new StatusCodeVO(404, "传入数据有null");
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
