package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.DTO.HomePageDTO;
import com.fivetwoff.hyonlinebe.VO.StatusCodeVO;
import com.fivetwoff.hyonlinebe.VO.SubGoodsVO;
import com.fivetwoff.hyonlinebe.entity.Goods;
import com.fivetwoff.hyonlinebe.entity.cascade.GoodsAndCart;
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
        System.out.println(homePageDTO);
        if (homePageDTO == null || homePageDTO.getUid() == null || homePageDTO.getGid() == null) {
            response.setStatus(404);
            return new StatusCodeVO(404, "传入数据有null");
        }
        try {
            Integer cId = ucService.findByUser(homePageDTO.getUid()).getCart_key();
            Integer gId = homePageDTO.getGid();
            GoodsAndCart gc = new GoodsAndCart();
            gc.setCart_key(cId);
            gc.setGoods_key(gId);
            if (!gcService.insert(gc)) {
                throw new Exception("goods_cart表插入失败");
            }
            return new StatusCodeVO(200, "Normal Server");
        } catch (Exception e) {
            System.out.println(e.toString());
            return new StatusCodeVO(500, e.toString());
        }
    }
}
