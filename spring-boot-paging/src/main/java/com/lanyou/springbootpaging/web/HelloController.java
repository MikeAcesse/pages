package com.lanyou.springbootpaging.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;

/**
 * @author fanzk
 * @version 1.8
 * @date 2020/12/8 11:23
 */
@Controller
public class HelloController {
	@RequestMapping("/hello")
	public String hello(Model model, @RequestParam(value = "name",required = false,defaultValue = "world") String name){
		model.addAttribute("name",name);
		return "hello";

	}

	@RequestMapping("/success")
	public String success(Map<String,Object> map){
         map.put("hello","<span style=\"color:red;\">hello!</span>");
         map.put("users", Arrays.asList("张三","李四","王五"));
         return "success";
	}
}
