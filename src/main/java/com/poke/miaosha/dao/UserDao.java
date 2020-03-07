package com.poke.miaosha.dao;

import com.poke.miaosha.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @InterfaceName UserDao
 * @Description //TODO
 * @Author poke
 * @Date 2020/2/29 7:22 下午
 */
@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")
    User getById(@Param("id") int id);

    @Insert("insert into  user(id, name) values(#{id}, #{name})")
    int insert(User user);
}
