package com.lanyou.springboothibernatepaging.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fanzk
 * @version 1.8
 * @date 2020/12/14 17:56
 */
@WebServlet(name = "HibernateServlet")
public class HibernateServlet extends HttpServlet {
	private static final long serialVersionUID = -2061496464591117339L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //返回结果到页面
		request.getRequestDispatcher("hibernateStudent.jsp").forward(request,response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
}
