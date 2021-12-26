package com.fivetwoff.hyonlinebe.controller;

import com.fivetwoff.hyonlinebe.cascade.UserAndComment;
import com.fivetwoff.hyonlinebe.entity.Comment;
import com.fivetwoff.hyonlinebe.mapper.CommentMapper;
import com.fivetwoff.hyonlinebe.service.CommentService;
import com.fivetwoff.hyonlinebe.service.cascade.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class CommentController {
    @Autowired
    private CommentService service;
    @Autowired
    private UserCommentService uService;

    @PostMapping("")
    public void addComment(@RequestParam("id") String id, @RequestParam("comment") String comment, @RequestParam("userId") String userId){
        Comment comment1 = new Comment();
        comment1.setId(Integer.parseInt(id));
        comment1.setContent(comment);
        service.insert(comment1);

        UserAndComment uComment = new UserAndComment();
        uComment.setUser_key(Integer.parseInt(userId));
        uComment.setComment_key(Integer.parseInt(id));
        uService.insert(uComment);
    }
}
