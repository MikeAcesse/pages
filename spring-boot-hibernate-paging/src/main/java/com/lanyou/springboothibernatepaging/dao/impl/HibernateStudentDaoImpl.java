package com.lanyou.springboothibernatepaging.dao.impl;

import com.lanyou.springboothibernatepaging.Constant;
import com.lanyou.springboothibernatepaging.HibernateSessionFactory;
import com.lanyou.springboothibernatepaging.dao.StudentDao;
import com.lanyou.springboothibernatepaging.model.Pager;
import com.lanyou.springboothibernatepaging.model.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fanzk
 * @version 1.8
 * @date 2020/12/14 14:51
 */
public class HibernateStudentDaoImpl implements StudentDao {
	@Override
	public Pager<Student> findStudent(Student searchModel, int pageNum, int pageSize) {

		Pager<Student> result = null;
		//存放查询参数
		Map<String,Object> paramMap = new HashMap<String,Object>();
        String stuName = searchModel.getStuName();
        int gender = searchModel.getGender();

        StringBuilder hql = new StringBuilder("from Student where 1=1");
        StringBuilder counthql = new StringBuilder("select count(id) from Student where 1=1");

        if(stuName != null && !stuName.equals("")){
        	hql.append("and stuName like :stuName ");
        	counthql.append(" and stuName like :stuName");
        	paramMap.put("stuName","%"+stuName+"%");
        }

        if(gender == Constant.GENDER_FEMALE || gender == Constant.GENDER_MALE){
        	hql.append(" and gender = :gender ");
        	counthql.append(" and gender = :gender ");
        	paramMap.put("gender",gender);

        }
        //起始索引
		int fromIndex = pageSize * (pageNum - 1 );
        //存放所有查询出的学生对象
		List<Student> studentList = new ArrayList<Student>();
		Session session = null ;
		try{
           session = HibernateSessionFactory.getSession();
           //获取query对象
			Query hqlQuery = session.createQuery(hql.toString());
			Query countHqlQuery = session.createQuery(counthql.toString());
			//设置查询参数
			setQueryParams(hqlQuery,paramMap);
			setQueryParams(countHqlQuery,paramMap);

			//从第几条记录开始查询
			hqlQuery.setFirstResult(fromIndex);
			//一共查询多少条记录
			hqlQuery.setMaxResults(pageSize);

			//获取查询的结果
			studentList = hqlQuery.list();
			//获取总计条数
			List<?> countResult = countHqlQuery.list();
			int totalRecord = ((Number)countResult.get(0)).intValue();

			//获取总页数
			int totalPage = totalRecord /pageSize;
			if(totalRecord % pageSize != 0){
				totalPage++;
			}

			//组装pager对象
			result = new Pager<Student>(pageSize,pageNum,totalRecord,totalPage,studentList);

		}catch (Exception e){
			throw new RuntimeException("查询所有数据异常！",e);
		}finally {
			if(session !=null){
				HibernateSessionFactory.closeSession();
			}
		}
		return null;
	}

	/**
	 * 设置查询的参数
	 * @param query
	 * @param paramMap
	 * @return
	 */
	private Query setQueryParams(Query query, Map<String,Object> paramMap){
       if(paramMap != null && !paramMap.isEmpty()){
       	  for(Map.Entry<String,Object> param : paramMap.entrySet()){
       	  	   query.setParameter(param.getKey(),param.getValue());
          }
       }
       return query;
	}
}