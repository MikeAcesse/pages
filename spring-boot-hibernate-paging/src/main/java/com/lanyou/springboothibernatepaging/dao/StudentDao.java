package com.lanyou.springboothibernatepaging.dao;

import com.lanyou.springboothibernatepaging.model.Pager;
import com.lanyou.springboothibernatepaging.model.Student;

/**
 * 根据查询条件，查询学生分页信息
 * @Param searchModel //封装查询条件
 * @Param pageNum //查询第几页
 * @Param pageSize //每页显示多少条记录
 * @return 查询结果
 */
public interface StudentDao {
	public Pager<Student> findStudent(Student searchModel, int pageNum, int pageSize);
}
