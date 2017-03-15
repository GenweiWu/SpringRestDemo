package com.wugw.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 测试get请求时传递参数的方式
 */
@RestController
public class RestTemplateWithGet {

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/test/get")
    public void test() {
        passStringParam();
        passListParam();
    }

    private void passStringParam() {
        String url = "http://127.0.0.1:8005/user?id=wugw";
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
    }

    private void passListParam() {
        String url = "http://127.0.0.1:8005/users?ids=";

        String[] ids = {"wugw", "zhangsan", "lisi", "dddd"};
        List<String> idList = Arrays.asList(ids);
        String idsStr = idList.stream().collect(Collectors.joining(","));

        url = url + idsStr;
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
    }
}
