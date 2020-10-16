package filter;

//import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//@WebFilter(urlPatterns= {"*.do","*.jsp"})
public class PermissionFilter implements Filter {
 private String notCheckFilter;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		 //TODO Auto-generated method stub
 /*从回话session中获取curentUser的数据，若为空，则表示不存在
  
  有些请求不需要过滤的
  首先获取请求的urI
  */
		
		
		HttpServletRequest request = (HttpServletRequest)req;//得到请求对象
		
		String path =request.getServletPath();
		System.out.println("请求地址url:"+path);
		
		if(notCheckFilter.indexOf(path) == -1) {
			HttpSession session=request.getSession();
			if(session.getAttribute("currentUser")==null) {
				request.setAttribute("info", "没有访问权限");
				request.getRequestDispatcher("/error.jsp").forward(request, resp);
			}else {
				chain.doFilter(req, resp); //已经登录直接放行
			}
		}else {
			chain.doFilter(req, resp);//请求地址不需要过滤，直接放行
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		notCheckFilter= config.getInitParameter("notCheckFilter");

	}

}
