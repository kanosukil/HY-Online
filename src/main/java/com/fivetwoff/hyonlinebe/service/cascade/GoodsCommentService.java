package com.fivetwoff.hyonlinebe.service.cascade;

import com.fivetwoff.hyonlinebe.entity.cascade.GoodsAndComment;
import com.fivetwoff.hyonlinebe.mapper.CommentMapper;
import com.fivetwoff.hyonlinebe.mapper.GoodsMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.GoodsCommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/27 - 09:31
 */

@Slf4j
@Service
public class GoodsCommentService {
    @Autowired
    private GoodsCommentMapper goodsComment;
    @Autowired
    private GoodsMapper goods;
    @Autowired
    private CommentMapper comment;

    public List<GoodsAndComment> findByGoods(Integer id) {
        return goodsComment.findByGoods(id);
    }

    public List<GoodsAndComment> findByComment(Integer id) {
        return goodsComment.findByComment(id);
    }

    public boolean deleteByGoods(Integer id) {
        int i = 0;
        try {
            i = goodsComment.deleteByGoods(id);
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
            i = goodsComment.deleteByComment(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("删除了" + i + "条信息");
        return true;
    }

    public boolean insert(GoodsAndComment goodsAndComment) {
        Integer goodsKey = goodsAndComment.getGoods_key();
        Integer cartKey = goodsAndComment.getComment_key();
        if (goods.findById(goodsKey) == null || comment.findById(cartKey) == null) {
            log.error("goods和comment表内没有对应主键");
            return false;
        } else {
            try {
                goodsComment.insert(goodsAndComment);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        }
        return true;
    }
}
