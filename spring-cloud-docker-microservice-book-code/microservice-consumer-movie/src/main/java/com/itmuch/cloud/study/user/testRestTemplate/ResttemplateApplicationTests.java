package com.itmuch.cloud.study.user.testRestTemplate;


import com.alibaba.fastjson.JSONObject;
import com.itmuch.cloud.study.user.entity.User;
import com.itmuch.cloud.study.user.util.RestTemplateUtil;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class ResttemplateApplicationTests {
    @Test
    public void testJavaObj() {
        User request = new User();
        request.setAge(18);
        request.setName("小芳");
        request.setAddress("广东深圳");
        RestTemplate restTemplate = RestTemplateUtil.getInstance("utf-8");
        String url = "http://localhost:8011/pojo";
        String result = restTemplate.postForObject(url, request, String.class);
        System.out.println(result);
    }

    @Test
    public void testMap() {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("age", 18);
        hashMap.put("name", "小芳");
        hashMap.put("address", "广东深圳");
        RestTemplate restTemplate = RestTemplateUtil.getInstance("utf-8");
        String url = "http://localhost:8011/map";
        String result = restTemplate.postForObject(url, hashMap, String.class);
        System.out.println(result);
    }

    @Test
    public void testJson() {
        JSONObject json = new JSONObject();
        json.put("age", 18);
        json.put("name", "小芳");
        json.put("address", "广东深圳");
        RestTemplate restTemplate = RestTemplateUtil.getInstance("utf-8");
        String url = "http://localhost:8011/json";
        String result = restTemplate.postForObject(url, json, String.class);
        System.out.println(result);
    }
}