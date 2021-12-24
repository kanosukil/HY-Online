package com.fivetwoff.hyonlinebe.service.cascade;

import com.fivetwoff.hyonlinebe.cascade.UserAndComment;
import com.fivetwoff.hyonlinebe.mapper.CommentMapper;
import com.fivetwoff.hyonlinebe.mapper.UserMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.UserCommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/22 - 11:20
 */

@Slf4j
@Service
public class UserCommentService {
    @Autowired
    private UserCommentMapper userComment;
    @Autowired
    private UserMapper user;
    @Autowired
    private CommentMapper comment;

    public List<UserAndComment> findByUser(Integer id) {
        return userComment.findByUser(id);
    }

    public List<UserAndComment> findByComment(Integer id) {
        return userComment.findByComment(id);
    }

    public boolean deleteByUser(Integer id) {
        int i = 0;
        try {
            i = userComment.deleteByUser(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("删除了" + i + "条信息");
        return true;
    }

    public boolean deleteByComment(Integer id) {
        int i = 0;
        try {
            i = userComment.deleteByComment(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("删除了" + i + "条信息");
        return true;
    }

    public boolean insert(UserAndComment userAndComment) {
        Integer userKey = userAndComment.getUser_key();
        Integer commentKey = userAndComment.getComment_key();
        if (user.findById(userKey) == null || comment.findById(commentKey) == null) {
            log.error("user表和comment表内没有对应主键");
            return false;
        } else {
            try {
                userComment.insert(userAndComment);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        }
        return true;
    }
}
