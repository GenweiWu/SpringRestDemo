package com.wugw.rest;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wugw.Message;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 测试post请求时传递参数的方式
 */
@RestController
public class RestTemplateWithPost {
    private RestTemplate restTemplate = new RestTemplate();

    public RestTemplateWithPost() {
        restTemplate.setRequestFactory(new HttpComponentsAsyncClientHttpRequestFactory());
    }

    @RequestMapping("/test/post")
    public void test() {
        passStringParam();
        passListStringParam();
        passListObjectParam();
        passCustomerObject();
    }

    /**
     * 测试传递自定义类
     */
    private void passCustomerObject() {
        /**
         * 1、序列化
         * 2、默认构造函数
         */
        Message message = new Message(8, "you win!");

        String url = "http://127.0.0.1:8005/message";
        boolean result = restTemplate.postForObject(url, message, Boolean.class);
        System.out.println(result);
    }

    private void passStringParam() {
        String url = "http://127.0.0.1:8005/book";

        MultiValueMap<String, Object> dataMap = new LinkedMultiValueMap<>();
        dataMap.add("id", "sanguoyanyi");

        HttpEntity<Object> httpEntity = new HttpEntity<>(dataMap);

        String result = restTemplate.postForObject(url, httpEntity, String.class);
        System.out.println(result);
    }

    private void passListStringParam() {
        String url = "http://127.0.0.1:8005/books";

        MultiValueMap<String, Object> dataMap = new LinkedMultiValueMap<>();
        String[] books = {"sanguoyanyi", "xiyouji"};
        dataMap.add("ids", books);

        HttpEntity<Object> httpEntity = new HttpEntity<>(dataMap);

        String[] result = restTemplate.postForObject(url, httpEntity, String[].class);
        System.out.println(Arrays.asList(result));
    }

    private void passListObjectParam() {
        String url = "http://127.0.0.1:8005/bookInfo";

        MultiValueMap<String, Object> dataMap = new LinkedMultiValueMap<>();
        String[] books = {"sanguoyanyi", "xiyouji"};
        //FIXME:需要转换为字符串
        //dataMap.add("ids", Arrays.asList(books));
        dataMap.add("ids", Arrays.stream(books).collect(Collectors.joining(",")));

        HttpEntity<Object> httpEntity = new HttpEntity<>(dataMap);

        ObjectNode[] result = restTemplate.postForObject(url, httpEntity, ObjectNode[].class);
        List<ObjectNode> objectNodeList = Arrays.asList(result);

        System.out.println(objectNodeList);
    }
}
