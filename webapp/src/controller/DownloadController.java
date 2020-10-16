package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DownloadDao;
import model.vo.Download;

/**
 * Servlet implementation class DownloadController
 */
@WebServlet("/Download.do")
public class DownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		DownloadDao dao = new DownloadDao();
		Download download=dao.get(Integer.parseInt(id));
		
		
		String path=request.getServletContext().getRealPath("/WEB-INF/"+download.getPath());
		
		String fileName = path.substring(path.lastIndexOf("\\")+1);
		
		response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(fileName,"utf-8"));
		
		InputStream in = new FileInputStream(path);
		
		int len=0;
		byte[] buffer = new byte[1024];
		
		ServletOutputStream out=response.getOutputStream();
		while((len=in.read(buffer)) !=-1) {
			out.write(buffer,0,len);
		}
		in.close();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
