package com.lanyou.springbootpaging.service;

import com.lanyou.springbootpaging.model.User;
import org.springframework.data.domain.Page;

/**
 * @author fanzk
 * @version 1.8
 * @date 2020/12/8 11:11
 */
public interface UserService {
	Page<User> getUserList(int pageNum,int pageSize);
	User findUserById(long id);
	void save(User user);
	void edit(User user);
	void delete(long id);
}
