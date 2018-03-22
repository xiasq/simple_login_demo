package com.xiasq.mapper;

/**
 * @author xiasq
 * @version BaseMapper, v0.1 2018/3/22 15:17
 */
public interface BaseMapper<T> {
	int delete(int id);

	int insert(T record);

	T selectByPrimaryKey(int id);

	int update(T record);

    int count();
}
