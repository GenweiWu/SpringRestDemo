package com.wugw.controller;

import org.springframework.web.bind.annotation.*;

/**
 * 通过url传递的
 */
@RestController
public class PathController {

    @RequestMapping("/test")
    public String test() {
        return "Hello world!";
    }

    /**
     * get方法-String/post方法-String
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/path/{name}", method = {RequestMethod.GET, RequestMethod.POST})
    public String pathWithGet(@PathVariable("name") String name) {
        return "Hello " + name;
    }
}
