package com.itmuch.cloud.study.controller;

import com.itmuch.cloud.study.Dao.UserDao;
import com.itmuch.cloud.study.entity.User;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

  @Autowired
  UserDao userDao;

  @GetMapping("/")
  public String index() {
    return "我是服务提供者";
  }

  @GetMapping("/{id}")
  public User findById(@PathVariable Long id) {
    User findOne = this.userDao.findOne(id);
    return findOne;
  }

  @GetMapping("/search")
  public List<User> search(@RequestParam("username") String username,
                           @RequestParam("name") String name,
                           @RequestParam("age") int age){
    List<User> userList = this.userDao.search(username,name,age);
    return userList;
  }


    /**
     * @RequestParam既可以get，又可以post
     */
    @PostMapping(value = "/testPostRest")
    public List<User> testPostRest(@RequestParam("username") String username,
                               @RequestParam("name") String name,
                               @RequestParam("age") Integer age) {
        List<User> userList = this.userDao.search(username,name,age);
        return userList;
    }


  @RequestMapping(value = "/getUserList", produces = "application/json",method = RequestMethod.POST)
  public @ResponseBody List<User> getUserList(@RequestBody User dto){
     int age=dto.getAge();
     List<User> userList = this.userDao.getUserList(age);

     return  userList;
  }



  /**
   * post請求的三种方式，pojo、map、json
   * 使用postman测试接口，pojo、map、json三种类型都是一样的传json或jason数组
   * {
   *         "id": 1,
   *         "username": "张三",
   *         "name": "总监",
   *         "age": 18,
   *         "balance": null
   * }
   */

  @RequestMapping(value = "/pojo", method = RequestMethod.POST)
  public List<User> postByObject(@RequestBody User dto) {
    return this.userDao.testPostRestTemplate(dto.getAddress(),dto.getName(),dto.getAge());
  }



  @RequestMapping(value = "/map", method = RequestMethod.POST)
  public List<User> postByMap(@RequestBody Map<String, String> map) {
    User dto=new User();
    for(Map.Entry<String, String> it : map.entrySet()){
      if("address".equals(it.getKey())){
        dto.setAddress(it.getValue());
      }
        if("name".equals(it.getKey())){
            dto.setName(it.getValue());
        }
        if("age".equals(it.getKey())){
            dto.setAge(Integer.parseInt(it.getValue()));
        }
    }
    return this.userDao.testPostRestTemplate(dto.getAddress(),dto.getName(),dto.getAge());
  }


  @RequestMapping(value = "/json", method = RequestMethod.POST)
  public String postByJsonObj(@RequestBody JSONObject jsonRequest) {
    Long id=Long.parseLong(jsonRequest.getAsString("id"));
    User findOne = this.userDao.findOne(id);
    return findOne.getName();
  }


}
