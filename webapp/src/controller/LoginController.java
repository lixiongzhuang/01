package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import model.dao.UserDao;
import model.vo.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(urlPatterns="/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	 //请求参数编码格式为utf-8
		request.setCharacterEncoding("utf-8");
		//1.控制表单的各元素的name属性值获取各请求参数
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String vcode = request.getParameter("userCode");
		//获取HttpSession对象
		HttpSession session= request.getSession();
		//取出CreateVerifyCodeImageController中存放的验证码字符串
		String saveVcode=(String)session.getAttribute("vcodeImg");
		String forwardPath="";
		//比较输入的验证码与随机生成的验证吗是否一致
		if(!vcode.equalsIgnoreCase(saveVcode)) {
			request.setAttribute("info", "验证码错误");
			forwardPath="/error.jsp";
			
		}else {
			UserDao userDao = new UserDao();
			User user=userDao.get(userName);
			System.out.println(userName);
			if(user==null) {
				request.setAttribute("info", "您输入的用户不存在！");
				forwardPath="/error.jsp";
			}else {//用户名存在
				if(!user.getPassword().equals(password)) {//密码错误
					//User currentUser = userDao.get(userName, password);
					request.setAttribute("info", "您输入的密码不正确!");
					forwardPath="/error.jsp";
				}
				else {
					session.setAttribute("currentUser", user);
					request.setAttribute("userName",userName);
					forwardPath="/main.jsp";
				}
			}			
		}
		
		RequestDispatcher rd= request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
