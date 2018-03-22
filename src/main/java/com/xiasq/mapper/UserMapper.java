package com.xiasq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xiasq.bean.User;

/**
 * @author xiasq
 * @version UserMapper, v0.1 2018/3/13 15:35
 */
@Repository
public interface UserMapper {

	List<User> list(@Param("startLimit") int startLimit, @Param("endLimit") int endLimit);

	User findByUserId(int userId);

	void deleteByUserId(int userId);

	void update(User user);

	void insert(User user);

	User findByUsername(String username);

	User findByMobile(String mobile);

	User findByIdcard(String idcard);

	int count();

	void updateImagePath(@Param("id") int userId,@Param("filepath") String filepath);
}