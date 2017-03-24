package com.wugw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wugw.Message;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 针对post方法的参数传递
 */
@RestController
public class PostController {

    /**
     * 测试参数String
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/book", method = {RequestMethod.POST})
    public String getBook(@RequestParam("id") String id) {
        return "book >>> " + id;
    }

    /**
     * 测试参数List
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/books", method = {RequestMethod.POST})
    public List<String> getBooks(@RequestParam("ids") List<String> ids) {
        return ids.stream().map(u -> "book >>> " + u).collect(Collectors.toList());
    }

    /**
     * 测试参数ObjectNode
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/bookInfo", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ObjectNode> getBookInfo(@RequestParam("ids") List<String> ids) {
        ObjectMapper mapper = new ObjectMapper();

        List<ObjectNode> bookInfo = new ArrayList<>();
        ids.forEach(b -> {
            ObjectNode node = mapper.createObjectNode();
            node.put("id", b);
            node.put("book", String.format("<<%s>>", b));

            bookInfo.add(node);
        });
        return bookInfo;
    }

    @RequestMapping(value = "/message", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public boolean getMessage(@RequestBody Message message) {
        System.out.println(message);

        return true;
    }
}
