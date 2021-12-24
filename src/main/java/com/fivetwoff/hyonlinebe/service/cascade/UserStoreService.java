package com.fivetwoff.hyonlinebe.service.cascade;

import com.fivetwoff.hyonlinebe.cascade.UserAndStore;
import com.fivetwoff.hyonlinebe.mapper.StoreMapper;
import com.fivetwoff.hyonlinebe.mapper.UserMapper;
import com.fivetwoff.hyonlinebe.mapper.cascade.UserStoreMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author VHBin
 * @date 2021/12/22 - 11:45
 */

@Service
@Slf4j
public class UserStoreService {
    @Autowired
    private UserStoreMapper userStore;
    @Autowired
    private UserMapper user;
    @Autowired
    private StoreMapper store;

    public List<UserAndStore> findByUser(Integer id) {
        return userStore.findByUser(id);
    }

    public List<UserAndStore> findByStore(Integer id) {
        return userStore.findByStore(id);
    }

    public boolean deleteByUser(Integer id) {
        int i = 0;
        try {
            i = userStore.deleteByUser(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("删除了" + i + "条信息");
        return true;
    }

    public boolean deleteByStore(Integer id) {
        int i = 0;
        try {
            i = userStore.deleteByStore(id);
        } catch (Exception ex) {
            log.error(ex.toString());
            return false;
        }
        log.info("删除了" + i + "条信息");
        return true;
    }

    public boolean insert(UserAndStore userAndStore) {
        Integer userKey = userAndStore.getMaster_key();
        Integer storeKey = userAndStore.getStore_key();
        if (user.findById(userKey) == null || store.findById(storeKey) == null) {
            log.error("user表和store表内没有对应主键");
            return false;
        } else {
            try {
                userStore.insert(userAndStore);
            } catch (Exception ex) {
                log.error(ex.toString());
                return false;
            }
        }
        return true;
    }
}
