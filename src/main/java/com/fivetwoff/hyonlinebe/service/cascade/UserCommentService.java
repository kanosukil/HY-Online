package com.fivetwoff.hyonlinebe.service.cascade;

import com.fivetwoff.hyonlinebe.cascade.UserAndComment;
import com.fivetwoff.hyonlinebe.mapper.cascade.UserCommentMapper;
import com.fivetwoff.hyonlinebe.service.CommentService;
import com.fivetwoff.hyonlinebe.service.UserService;
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
    private UserService user;
    @Autowired
    private CommentService comment;

    public List<UserAndComment> findByUser(Integer id) {
        return userComment.findByUser(id);
    }

    public List<UserAndComment> findByComment(Integer id) {
        return userComment.findByComment(id);
    }

    public boolean deleteByUser(Integer id) {
        try {
            userComment.deleteByUser(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        return true;
    }

    public boolean deleteByComment(Integer id) {
        try {
            userComment.deleteByComment(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
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
