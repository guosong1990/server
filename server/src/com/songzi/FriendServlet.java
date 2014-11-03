package com.songzi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.songzi.util.Const;

public class FriendServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String PARAM = "methodtype"; 
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
	        //response.setContentType("text/html");  
			//System.out.println("华为的经纬度"+Const.huawei);
			//System.out.println("酷派的经纬度"+Const.coolPad);
	        PrintWriter out = response.getWriter();  
	        request.setCharacterEncoding("utf-8");  
	          
	        String type = request.getParameter(PARAM);  
	        String IMEI = request.getParameter("IMEI");
	        if(type.equals("getLatLng")){
		        if(IMEI.equals("862784021654690")){
		        	out.print(Const.coolPad);
		        }else if(IMEI.equals("863155022055881")||IMEI.equals("863155022092694")){
		        	out.print(Const.huawei);
		        }else {
					out.print("0.0:0.0");
				}
	        }
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
