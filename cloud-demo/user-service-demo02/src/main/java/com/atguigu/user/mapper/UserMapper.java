package com.atguigu.user.mapper;

import com.atguigu.user.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Mr jie
 * @create 2022-54-26-20:54
 */
public interface UserMapper {
    @Select("select * from tb_user where id = #{id}")
    User findById(@Param("id") Long id);
}
