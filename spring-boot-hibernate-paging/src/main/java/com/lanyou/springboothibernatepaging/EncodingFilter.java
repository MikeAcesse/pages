package com.lanyou.springboothibernatepaging;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author fanzk
 * @version 1.8
 * @date 2020/12/14 15:25
 */
public class EncodingFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		chain.doFilter(request,response);
	}

	@Override
	public void destroy() {

	}
}
