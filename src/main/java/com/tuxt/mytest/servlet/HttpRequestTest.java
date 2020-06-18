package com.tuxt.mytest.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HttpRequestTest
 */
@WebServlet(name="HttpRequestTest",urlPatterns={"/HttpRequestTest"})
public class HttpRequestTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public HttpRequestTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("这是一个get请求");
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		System.out.println("name:"+name+",age:"+age);
		response.setCharacterEncoding("utf-8");
		response.getWriter().print("我收到了你的get请求");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("这是一个post请求");
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		System.out.println("name:"+name+",age:"+age);
		response.setCharacterEncoding("utf-8");
		response.getWriter().print("我收到了你的post请求");
	}

}
