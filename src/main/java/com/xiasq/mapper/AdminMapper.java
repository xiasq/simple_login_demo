package com.xiasq.mapper;

import com.xiasq.bean.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xiasq
 * @version AdminMapper, v0.1 2018/3/13 15:35
 */

@Repository
public interface AdminMapper {
    Admin findByName(@Param("name") String name);

    void insert(Admin admin);
}
