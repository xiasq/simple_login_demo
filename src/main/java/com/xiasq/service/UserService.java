package com.xiasq.service;

import com.xiasq.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiasq
 * @version UserService, v0.1 2018/3/13 20:15
 */
@Service
public interface UserService {

    List<User> list(int startLimit, int endLimit);

    User findByUserId(int userId);

    void deleteByUserId(int userId);

    void update(User user);

    void insert(User user);

    User findByUsername(String username);

    User findByMobile(String mobile);

    User findByIdcard(String idcard);

    int count();

    void updateImagePath(int userId, String filepath);
}
