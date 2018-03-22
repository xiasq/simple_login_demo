package com.xiasq.service;

import com.xiasq.bean.Admin;

/**
 * @author xiasq
 * @version AdminService, v0.1 2018/3/13 21:25
 */
public interface AdminService {
    Admin login(String username, String password);

    void register(Admin admin);

    Admin findByName(String name);
}
