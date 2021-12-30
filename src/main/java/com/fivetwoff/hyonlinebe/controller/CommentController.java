package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.DTO.CommentDTO;
import com.fivetwoff.hyonlinebe.VO.StatusCodeVO;
import com.fivetwoff.hyonlinebe.entity.Comment;
import com.fivetwoff.hyonlinebe.entity.cascade.GoodsAndComment;
import com.fivetwoff.hyonlinebe.entity.cascade.UserAndComment;
import com.fivetwoff.hyonlinebe.service.CommentService;
import com.fivetwoff.hyonlinebe.service.GoodsService;
import com.fivetwoff.hyonlinebe.service.cascade.GoodsCommentService;
import com.fivetwoff.hyonlinebe.service.cascade.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService cService;
    @Autowired
    private UserCommentService ucService;
    @Autowired
    private GoodsCommentService gcService;
    @Autowired
    private GoodsService gService;

    @GetMapping("")
    public Map<String, Object> showComment(@RequestParam("gId") String gId) {
        System.out.println(gId);
        Map<String, Object> map = new HashMap<>();
        List<Comment> comments = new ArrayList<>();
        map.put("code", 200);
        map.put("info", "Normal Server");
        try {
            List<GoodsAndComment> gcList = gcService.findByGoods(Integer.parseInt(gId));
            for (GoodsAndComment gc : gcList) {
                comments.add(cService.findById(gc.getComment_key()));
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            map.put("code", 500);
            map.put("info", ex.toString());
            comments = null;
        }
        map.put("comments", comments);
        return map;
    }

    @PostMapping("")
    public StatusCodeVO addComment(@RequestBody CommentDTO commentDTO,
                                   HttpServletResponse response) {
        System.out.println(commentDTO);
        if (commentDTO == null || commentDTO.getUid() == null ||
                commentDTO.getGid() == null || commentDTO.getComment() == null) {
            response.setStatus(404);
            return new StatusCodeVO(404, "传入数据有null");
        }
        Integer gId = commentDTO.getGid();
        String comment = commentDTO.getComment().trim();
        Integer uId = commentDTO.getUid();
        System.out.println(gId + "\n" + comment + "\n" + uId);
        if (gService.findById(gId) == null) {
            response.setStatus(404);
            System.out.println("未找到对应商品");
            return new StatusCodeVO(404, "未找到对应商品");
        }

        Comment comment1 = new Comment();
        comment1.setId(cService.findAll().get(cService.findAll().size() - 1).getId() + 1);
        comment1.setContent(comment);
        try {
            if (cService.insert(comment1)) {
                GoodsAndComment gc = new GoodsAndComment();
                gc.setGoods_key(gId);
                gc.setComment_key(comment1.getId());
                if (gcService.insert(gc)) {
                    UserAndComment uComment = new UserAndComment();
                    uComment.setUser_key(uId);
                    uComment.setComment_key(comment1.getId());
                    if (!ucService.insert(uComment)) {
                        throw new Exception("user_comment表插入失败");
                    }
                } else {
                    throw new Exception("goods_comment表插入失败");
                }
            } else {
                throw new Exception("comment表插入失败");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            response.setStatus(500);
            return new StatusCodeVO(500, ex.toString());
        }
        response.setStatus(200);
        return new StatusCodeVO(200, "Normal Server");
    }
}
