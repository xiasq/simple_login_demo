package com.xiasq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiasq.bean.Admin;
import com.xiasq.mapper.AdminMapper;
import com.xiasq.service.AdminService;

/**
 * @author xiasq
 * @version AdminServiceImpl, v0.1 2018/3/13 21:26
 */

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public Admin login(String username, String password) {
		Admin admin = adminMapper.findByName(username);
		if (admin != null) {
			if (admin.getPassword().equals(password)) {
				return admin;
			}
		}
		return null;
	}

	@Override
	public void register(Admin admin) {
		adminMapper.insert(admin);
	}

	@Override
	public Admin findByName(String name) {
		return adminMapper.findByName(name);
	}

}
