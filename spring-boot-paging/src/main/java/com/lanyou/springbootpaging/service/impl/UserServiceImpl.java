package com.lanyou.springbootpaging.service.impl;

import com.lanyou.springbootpaging.model.User;
import com.lanyou.springbootpaging.repository.UserRepository;
import com.lanyou.springbootpaging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author fanzk
 * @version 1.8
 * @date 2020/12/8 11:16
 */

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public Page<User> getUserList(int pageNum, int pageSize) {
		Sort sort = new Sort(Sort.Direction.DESC,"id");
		Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
		Page<User> users =userRepository.findAll(pageable);
		return users;
	}

	@Override
	public User findUserById(long id) {
		return userRepository.findById(id);
	}

	@Override
	public void save(User user) {
            userRepository.save(user);
	}

	@Override
	public void edit(User user) {
		userRepository.save(user);

	}

	@Override
	public void delete(long id) {
        userRepository.deleteById(id);
	}
}
