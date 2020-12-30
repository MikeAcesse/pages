package com.lanyou.springboothibernatepaging.servlet;

import com.alibaba.fastjson.JSON;
import com.lanyou.springboothibernatepaging.Constant;
import com.lanyou.springboothibernatepaging.model.Pager;
import com.lanyou.springboothibernatepaging.model.Student;
import com.lanyou.springboothibernatepaging.service.HibernateStudentServiceImpl;
import com.lanyou.springboothibernatepaging.service.StudentService;
import com.lanyou.springboothibernatepaging.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @author fanzk
 * @version 1.8
 * @date 2020/12/14 16:53
 */
@WebServlet(name = "HibernateDataServlet")
public class HibernateDataServlet extends HttpServlet {
	private static final long serialVersionUID = 8926314880065657528L;
	private StudentService studentService = new HibernateStudentServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //接收request 里的参数
		String stuName = request.getParameter("stuName"); //学生姓名
		// 获取学生性别
		int gender = Constant.DEFAULT_GENDER;
		String genderStr = request.getParameter("gender");
		if(genderStr !=null && !"".equals(genderStr.trim())){
			gender = Integer.parseInt(genderStr);
		}
		//检验pageNum 参数输入合法性
		String pageNumStr = request.getParameter("pageNum");
		if(pageNumStr !=null && !StringUtil.isNum(pageNumStr)){
             request.setAttribute("errorMsg","参数传输错误");
             request.getRequestDispatcher("jdbcSqlStudent.jsp").forward(request,response);
             return;
		}
		int pageNum = Constant.DEFAULT_PAGE_NUM; //显示第几页数据
		if(pageNumStr !=null && !"".equals(pageNumStr.trim())){
			pageNum = Integer.parseInt(pageNumStr);
		}

		int pageSize = Constant.DEFAULT_PAGE_SIZE; //每页显示多少条记录
		String pageSizeStr = request.getParameter("pageSize");
		if(pageSizeStr !=null && !"".equals(pageSizeStr.trim())){
			pageSize = Integer.parseInt(pageSizeStr.trim());
		}
		// 组装查询条件
		Student searchModel = new Student();
		searchModel.setStuName(stuName);
		searchModel.setGender(gender);

		// 调用service 获取查询结果
		Pager<Student> result = studentService.findStudent(searchModel,pageNum,pageSize);
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader("Expires",0);
		response.setContentType("text/html;charset=utf-8");
		String responseStr = JSON.toJSONString(result);
		Writer writer =response.getWriter();
		writer.write(responseStr);
		writer.flush();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
	}

	public static void main(String[] args) {
		String s = String.format("%05d",123);
		System.out.println(s);
	}
}
