package com.fivetwoff.hyonlinebe.service;

import com.fivetwoff.hyonlinebe.entity.Comment;
import com.fivetwoff.hyonlinebe.mapper.CommentMapper;
import com.fivetwoff.hyonlinebe.service.cascade.UserCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/23 - 21:57
 */

@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentMapper comment;
    @Autowired
    private UserCommentService userComment;

    public List<Comment> findAll() {
        return comment.findAll();
    }

    public Comment findById(Integer id) {
        return comment.findById(id);
    }

    public boolean insert(Comment commentInsert) {
        try {
            comment.insert(commentInsert);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }

    public boolean deleteById(Integer id) {
        if (userComment.deleteByComment(id)) {
            try {
                comment.deleteById(id);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        } else {
            log.error("user_comment表删除comment主键异常");
            return false;
        }
        return true;
    }

    public boolean update(Comment commentUpdate) {
        try {
            comment.updateByPrimaryKey(commentUpdate);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }
}
