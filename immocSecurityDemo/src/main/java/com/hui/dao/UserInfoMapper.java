package com.hui.dao;

import com.hui.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 22:52 2019\3\11 0011
 */
@Mapper
public interface  UserInfoMapper {

    @Select({"select * from user_info where id = #{id}"})
    public UserInfo findById(int id);

}
