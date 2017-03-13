package com.wugw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 针对get方法的参数传递，只测试String和简单的list
 */
@RestController
public class GetController {

    @RequestMapping(value = "/user")
    public String getUser(@RequestParam("id") String id) {
        return "got >>> " + id;
    }

    @RequestMapping(value = "/users")
    public List<String> getUsers(@RequestParam("ids") List<String> ids) {
        return ids.stream().map(u -> "got >>> " + u).collect(Collectors.toList());
    }
}
