package com.lanyou.springboothibernatepaging.service;

import com.lanyou.springboothibernatepaging.dao.StudentDao;
import com.lanyou.springboothibernatepaging.dao.impl.HibernateStudentDaoImpl;
import com.lanyou.springboothibernatepaging.model.Pager;
import com.lanyou.springboothibernatepaging.model.Student;

/**
 * @author fanzk
 * @version 1.8
 * @date 2020/12/14 16:46
 */
public class HibernateStudentServiceImpl implements StudentService {
	private StudentDao studentDao;

	public HibernateStudentServiceImpl() {
		studentDao = new HibernateStudentDaoImpl();
	}

	@Override
	public Pager<Student> findStudent(Student searchModel, int pageNum, int pageSize) {
		Pager<Student> result = studentDao.findStudent(searchModel,pageNum,pageSize);
		return result;
	}
}
