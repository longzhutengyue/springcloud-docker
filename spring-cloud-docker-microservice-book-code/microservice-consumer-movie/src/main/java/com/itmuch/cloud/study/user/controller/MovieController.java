package com.itmuch.cloud.study.user.controller;

import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.itmuch.cloud.study.user.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {
  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/")
  public String index() {
    return "我是服务消费者";
  }


  /**
   * restTemplate单个参数
   */
  @GetMapping("/user/{id}")
  public User findById(@PathVariable Long id) {
    return this.restTemplate.getForObject("http://localhost:8011/" + id, User.class);
  }

  /**
   * restTemplate拼接多个参数，调用别的服务
   */
  @GetMapping("/search")
  public String search(@RequestParam("username") String username,
                           @RequestParam("name") String name,
                           @RequestParam("age") Integer age) {
    Map<String, Object> paramMap=new HashMap();
    paramMap.put("name",name);
    paramMap.put("username",username);
    paramMap.put("age",age);
    return this.restTemplate.getForObject("http://localhost:8011/search?name={name}&username={username}&age={age}", String.class, paramMap);
  }

  @PostMapping("/testPost")
  public String testPost(@RequestParam("username") String username,
                       @RequestParam("name") String name,
                       @RequestParam("age") Integer age) {
    Map<String, Object> paramMap=new HashMap();
    paramMap.put("name",name);
    paramMap.put("username",username);
    paramMap.put("age",age);
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.add("api-version", "1.0");
    return this.restTemplate.postForObject("http://localhost:8011/testPostRest?name={name}&username={username}&age={age}",requestHeaders,String.class);
  }







}
