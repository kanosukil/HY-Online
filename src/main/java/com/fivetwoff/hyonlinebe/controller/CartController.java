package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.DTO.StatusCodeVO;
import com.fivetwoff.hyonlinebe.DTO.SubGoods;
import com.fivetwoff.hyonlinebe.cascade.GoodsAndCart;
import com.fivetwoff.hyonlinebe.cascade.GoodsAndOrder;
import com.fivetwoff.hyonlinebe.cascade.UserAndOrder;
import com.fivetwoff.hyonlinebe.entity.Orders;
import com.fivetwoff.hyonlinebe.service.GoodsService;
import com.fivetwoff.hyonlinebe.service.OrderService;
import com.fivetwoff.hyonlinebe.service.StoreService;
import com.fivetwoff.hyonlinebe.service.cascade.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
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
    @Autowired
    private OrderService oService;
    @Autowired
    private UserOrderService uoService;
    @Autowired
    private GoodsOrderService goService;

    @GetMapping("/get")
    public Map<String, Object> getCart(@RequestParam("uId") String uId) { //CartGoodsList
        ArrayList<SubGoods> goods = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        try {
            int cId = ucService.findByUser(Integer.parseInt(uId)).getCart_key();
            List<GoodsAndCart> gcList = gcService.findByCart(cId);
            Integer sId = usService.findByUser(Integer.parseInt(uId)).get(0).getStore_key();
            String storeName = sService.findById(sId).getName();
            for (GoodsAndCart x : gcList) {
                SubGoods sg = new SubGoods();
                sg.setStoreName(storeName);
                sg.setGoods(gService.findById(x.getGoods_key()));
                goods.add(sg);
            }
            map.put("userId", uId);
            map.put("list", goods);
            return map;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            goods = null;
            map.put("list", goods);
            map.put("userId", uId);
            return map;
        }
    }

    @PostMapping("/delete")
    public StatusCodeVO deleteGood(@RequestParam("gId") String gId, HttpServletResponse response) {
        if (!gcService.deleteByGoods(Integer.parseInt(gId))) {
            response.setStatus(404);
            return new StatusCodeVO(404);
        } else {
            response.setStatus(200);
            return new StatusCodeVO(200);
        }
    }

    @PostMapping("/pay")
    public StatusCodeVO pay(@RequestParam("uId") String uId, HttpServletResponse response) {
        Integer cId = null;
        try {
            cId = ucService.findByUser(Integer.parseInt(uId)).getCart_key();
            List<GoodsAndCart> gc = gcService.findByCart(cId);
            Integer oIdStart = oService.findAll().get(oService.findAll().size() - 1).getId() + 1;
            for (GoodsAndCart goodsAndCart : gc) {
                Integer gKey = goodsAndCart.getGoods_key();
                Orders o = new Orders();
                o.setId(oIdStart);
                o.setNumber(1);
                o.setAddress("default");
                if (oService.insert(o)) {
                    GoodsAndOrder go = new GoodsAndOrder();
                    go.setGoods_key(gKey);
                    go.setOrder_key(oIdStart);
                    if (!goService.insert(go)) {
                        throw new Exception("goods_order表插入失败");
                    }
                    UserAndOrder uo = new UserAndOrder();
                    uo.setCustomer_key(Integer.parseInt(uId));
                    uo.setOrder_key(oIdStart);
                    if (!uoService.insert(uo)) {
                        throw new Exception("user_order表插入是失败");
                    }
                } else {
                    throw new Exception("order表插入失败");
                }
                oIdStart++;
            }
        } catch (Exception ex) {
            response.setStatus(500);
            return new StatusCodeVO(500);
        }

        if (gcService.deleteByCart(cId)) {
            response.setStatus(200);
            return new StatusCodeVO(200);
        } else {
            response.setStatus(404);
            return new StatusCodeVO(404);
        }
    }
}
