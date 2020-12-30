package com.lanyou.springbootpaging.repository;

import com.lanyou.springbootpaging.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fanzk
 * @version 1.8
 * @date 2020/12/8 11:06
 */
public interface UserRepository extends JpaRepository<User,Long> {
	User findById(long id);
	void deleteById(long id);
}
