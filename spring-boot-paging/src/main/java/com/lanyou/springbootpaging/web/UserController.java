package com.lanyou.springbootpaging.web;

import com.lanyou.springbootpaging.model.User;
import com.lanyou.springbootpaging.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Iterator;

/**
 * @author fanzk
 * @version 1.8
 * @date 2020/12/8 15:29
 */
@Controller
public class UserController {
	@Resource
	UserService userService;

	@RequestMapping("/")
	public String index(){
		return "redirect:/list";
	}

	@RequestMapping("/list")
    public String list(Model model, @RequestParam(value="pageNum",defaultValue = "0") int pageNum,@RequestParam(value = "pageSize",defaultValue = "3") int pageSize){
		System.out.println("=====================================");
		Page<User> users = userService.getUserList(pageNum,pageSize);
		System.out.println("总页数"+users.getTotalPages());
		System.out.println("当前页是："+pageNum);
		System.out.println("分页数据：");
		Iterator<User> u = users.iterator();
		while (u.hasNext()){
			System.out.println(u.next().toString());
		}
		model.addAttribute("users",users);
		return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
          return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(User user){
       userService.save(user);
       return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model,Long id){
		User user = userService.findUserById(id);
		model.addAttribute("user",user);
		return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(User user){
		userService.edit(user);
		return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id){
		userService.delete(id);
		return "redirect:/list";
    }


	@RequestMapping("/list1")
	public String list1(Model model, @RequestParam(value="pageNum",defaultValue = "0") int pageNum,@RequestParam(value = "pageSize",defaultValue = "3") int pageSize){
		System.out.println("=====================================");
		Page<User> users = userService.getUserList(pageNum,pageSize);
		System.out.println("总页数"+users.getTotalPages());
		System.out.println("当前页是："+pageNum);
		System.out.println("分页数据：");
		Iterator<User> u = users.iterator();
		while (u.hasNext()){
			System.out.println(u.next().toString());
		}
		model.addAttribute("users",users);
		return "user/list1";
	}

}
