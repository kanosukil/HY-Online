package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.DTO.GoodsDTO;
import com.fivetwoff.hyonlinebe.DTO.GoodsDeleteDTO;
import com.fivetwoff.hyonlinebe.DTO.GoodsUpdateDTO;
import com.fivetwoff.hyonlinebe.DTO.StatusCodeVO;
import com.fivetwoff.hyonlinebe.cascade.StoreAndGoods;
import com.fivetwoff.hyonlinebe.cascade.UserAndStore;
import com.fivetwoff.hyonlinebe.entity.Goods;
import com.fivetwoff.hyonlinebe.service.GoodsService;
import com.fivetwoff.hyonlinebe.service.StoreService;
import com.fivetwoff.hyonlinebe.service.cascade.StoreGoodsService;
import com.fivetwoff.hyonlinebe.service.cascade.UserStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/store")
public class GoodsManageController {
    @Autowired
    private StoreGoodsService sgService;
    @Autowired
    private GoodsService gService;
    @Autowired
    private UserStoreService usService;
    @Autowired
    private StoreService sService;

    @GetMapping("/show")
    public Map<String, Object> showStore(@RequestParam("uId") String uId) {
        Map<String, Object> map = new HashMap<>();
        List<Goods> goodsList = new ArrayList<>();
        String storeName = null;
        try {
            Integer sId = usService.findByUser(Integer.parseInt(uId)).get(0).getStore_key();
            storeName = sService.findById(sId).getName();
            List<StoreAndGoods> sgList = sgService.findByStore(sId);
            for (StoreAndGoods sg : sgList) {
                goodsList.add(gService.findById(sg.getGoods_key()));
            }
            map.put("userId", uId);
            map.put("storeName", storeName);
            map.put("list", goodsList);
            return map;
        } catch (Exception ex) {
            map.put("userId", uId);
            map.put("storeName", storeName);
            map.put("list", goodsList);
            return map;
        }
    }

    @PostMapping("/add")
    public StatusCodeVO addGoods(@RequestBody GoodsDTO goods,
                                 HttpServletResponse response) {
        Integer uid = Integer.parseInt(goods.getUid());
        System.out.println(goods);
        try {
            List<UserAndStore> us = usService.findByUser(uid);
            if (us.size() == 0) {
                throw new Exception("非商店所属者");
            } else {
                Goods goods1 = new Goods();
                goods1.setId(gService.findAll().size() + 1);
                goods1.setName(goods.getGoodsName());
                goods1.setImg("default");
                goods1.setPrice(Double.parseDouble(goods.getPrice()));
                goods1.setDescription(goods.getDescription());
                if (gService.insert(goods1)) {
                    Integer storeKey = us.get(0).getStore_key();
                    Integer goodsKey = goods1.getId();
                    StoreAndGoods sg = new StoreAndGoods();
                    sg.setStore_key(storeKey);
                    sg.setGoods_key(goodsKey);
                    if (!sgService.insert(sg)) {
                        throw new Exception("store_goods表插入异常");
                    }
                } else {
                    throw new Exception("goods表插入异常");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            response.setStatus(500);
            return new StatusCodeVO(500);
        }
        response.setStatus(200);
        return new StatusCodeVO(200);
    }

    @PostMapping("/delete")
    public StatusCodeVO deleteGoods(@RequestBody GoodsDeleteDTO goodsDeleteDTO, HttpServletResponse response) {
        Integer uid = Integer.parseInt(goodsDeleteDTO.getUid());
        Integer gid = Integer.parseInt(goodsDeleteDTO.getGid());
        List<UserAndStore> us = usService.findByUser(uid);
        List<StoreAndGoods> sg = sgService.findByGoods(gid);
        try {
            if (us.size() == 0 || sg.size() == 0) {
                throw new Exception("非商店所属者");
            } else if (us.size() > 1) {
                throw new Exception("商店所属异常");
            } else {
                Integer storeKey = us.get(0).getStore_key();
                if (!sgService.deleteByGoodsAndStore(gid, storeKey)) {
                    throw new Exception("store_goods表删除异常");
                } else {
                    if (!gService.deleteById(gid)) {
                        throw new Exception("goods表删除异常");
                    }
                }
            }
        } catch (Exception ex) {
            response.setStatus(500);
            return new StatusCodeVO(500);
        }
        response.setStatus(200);
        return new StatusCodeVO(200);
    }

    @PostMapping("/update")
    public StatusCodeVO updateGoods(@RequestBody GoodsUpdateDTO good, HttpServletResponse response) {
        Integer uid = Integer.parseInt(good.getUid());
        Integer gid = Integer.parseInt(good.getGid());
        List<UserAndStore> us = usService.findByUser(uid);
        List<StoreAndGoods> sg = sgService.findByGoods(gid);
        try {
            if (us.size() == 0) {
                throw new Exception("非商店所属者");
            } else {
                Goods updateGoods = gService.findById(gid);
                if (updateGoods != null) {
                    updateGoods.setName(good.getUpdate().getGoodsTitle());
                    updateGoods.setDescription(good.getUpdate().getGoodsSubtitle());
                    updateGoods.setPrice(Double.parseDouble(good.getUpdate().getGoodsPrice()));
                    if (!gService.update(updateGoods)) {
                        throw new Exception("goods表更新失败");
                    }
                } else {
                    throw new Exception("商品未找到");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            response.setStatus(500);
            return new StatusCodeVO(500);
        }
        response.setStatus(200);
        return new StatusCodeVO(200);
    }
}
