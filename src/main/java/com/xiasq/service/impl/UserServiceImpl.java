package com.xiasq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiasq.bean.User;
import com.xiasq.mapper.UserMapper;
import com.xiasq.service.UserService;

/**
 * @author xiasq
 * @version UserServiceImpl, v0.1 2018/3/13 20:15
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> list(int startLimit, int endLimit) {
		return userMapper.list(startLimit, endLimit);
	}

	@Override
	public User findByUserId(int userId) {
		return userMapper.findByUserId(userId);
	}

	@Override
	public void deleteByUserId(int userId) {
		userMapper.deleteByUserId(userId);
	}

	@Override
	public void update(User user) {
		userMapper.update(user);
	}

	@Override
	public void insert(User user) {
		userMapper.insert(user);
	}

	@Override
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

	@Override
	public User findByMobile(String mobile) {
		return userMapper.findByMobile(mobile);
	}

	@Override
	public User findByIdcard(String idcard) {
		return userMapper.findByIdcard(idcard);
	}

	@Override
	public int count() {
		return userMapper.count();
	}

	@Override
	public void updateImagePath(int userId, String filepath) {
		userMapper.updateImagePath(userId, filepath);
	}
}
