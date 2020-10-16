package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.CreateImage;
/**
 * Servlet implementation class CreateVerifyCodeImageController
 */
@WebServlet(urlPatterns="/servlet/CreateImage")
public class CreateVerifyCodeImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CreateImage createVCodeImage = new CreateImage();
		String vCode = createVCodeImage.createCode();
		HttpSession session = request.getSession();
		session.setAttribute("vcodeImg", vCode);//把建立好的验证码存放在session中
		response.setContentType("img/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expirse", 0);
		BufferedImage image = createVCodeImage.CreateIm(vCode);
		ServletOutputStream out= response.getOutputStream();
		ImageIO.write(image, "JPEG", out);
		out.flush();
		out.close();
		
	}

	
}
