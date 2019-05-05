package com.hui.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 23:06 2019\3\11 0011
 */
@Mapper
public interface UserMsgMapper{

    @Insert({"insert into user_msg (username) VALUES (#{username})"})
    public void insertData(@PathVariable("username") String username);
}
