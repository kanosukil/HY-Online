package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.cascade.GoodsAndComment;
import com.fivetwoff.hyonlinebe.cascade.UserAndComment;
import com.fivetwoff.hyonlinebe.entity.Comment;
import com.fivetwoff.hyonlinebe.mapper.CommentMapper;
import com.fivetwoff.hyonlinebe.service.CommentService;
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

    @GetMapping("")
    public Map<String, List<Comment>> showComment(@RequestParam("gId") String gId){
        Map<String, List<Comment>> map = new HashMap<>();
        List<GoodsAndComment> gcList = gcService.findByGoods(Integer.parseInt(gId));
        List<Comment> comments = new ArrayList<>();
        for(GoodsAndComment gc:gcList){
            comments.add(cService.findById(gc.getComment_key()));
        }
        map.put("comments", comments);
        return map;
    }

    @PostMapping("")
    public void addComment(@RequestParam("id") String gId, @RequestParam("comment") String comment, @RequestParam("userId") String uId,
                           HttpServletResponse response){
        Comment comment1 = new Comment();
        comment1.setId(Integer.parseInt(gId));
        comment1.setContent(comment);
        if(cService.insert(comment1)){
            response.setStatus(200);
        }else {
            response.setStatus(404);
            return;
        }

        UserAndComment uComment = new UserAndComment();
        uComment.setUser_key(Integer.parseInt(uId));
        uComment.setComment_key(Integer.parseInt(gId));
        ucService.insert(uComment);
    }
}
