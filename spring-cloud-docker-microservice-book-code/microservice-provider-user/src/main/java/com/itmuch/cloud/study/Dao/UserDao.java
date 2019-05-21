package com.itmuch.cloud.study.Dao;

import com.itmuch.cloud.study.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

  User findOne(Long id);

  List<User> search(@Param("username") String username,
                    @Param("name") String name,
                    @Param("age")int age);


  List<User>  getUserList(int age);

  List<User> testPostRestTemplate(@Param("address") String address,
                                  @Param("name") String name,
                                  @Param("age")int age);
}


