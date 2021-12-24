package com.fivetwoff.hyonlinebe.service;

import com.fivetwoff.hyonlinebe.entity.Comment;
import com.fivetwoff.hyonlinebe.mapper.CommentMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.UserCommentMapper;
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
    private UserCommentMapper userComment;

    public List<Comment> findAll() {
        return comment.findAll();
    }

    public Comment findById(Integer id) {
        return comment.findById(id);
    }

    public boolean insert(Comment commentInsert) {
        if (comment.findById(commentInsert.getId()) != null) {
            log.error("id重复");
            return false;
        }
        try {
            comment.insert(commentInsert);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }

    public boolean deleteById(Integer id) {
        int[] i = new int[2];
        int j = 0;
        try {
            j = 1;
            i[0] = userComment.deleteByComment(id);
            j = 2;
            i[1] = comment.deleteById(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            if (j == 1) {
                log.error("use_comment表删除异常");
            } else {
                log.error("comment表删除异常");
            }
            return false;
        }
        log.info("删除" + i[1] + "条信息");
        return true;
    }

    public boolean update(Comment commentUpdate) {
        int i = 0;
        try {
            i = comment.updateByPrimaryKey(commentUpdate);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("更新了" + i + "条信息");
        return true;
    }
}
