package com.wugw.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 测试path请求
 */
@RestController
public class RestTemplatePathDemo {

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/test/path")
    public void test() {
        hello();
        getWithPathVariable();
        postWithPathVariable();
    }

    /**
     * 简单测试
     */
    private void hello() {
        String url = "http://127.0.0.1:8005/test";
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
    }

    /**
     * 通过PathVariable传递参数/get方法
     */
    private void getWithPathVariable() {
        String url = "http://127.0.0.1:8005/path/{name}";
        String result = restTemplate.getForObject(url, String.class, "zhangsan");
        System.out.println(result);
    }

    /**
     * 通过PathVariable传递参数/post方法
     */
    private void postWithPathVariable() {
        String url = "http://127.0.0.1:8005/path/{name}";
        String result = restTemplate.postForObject(url, null, String.class, "zhangsan");
        System.out.println(result);
    }

}
