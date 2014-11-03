package com.songzi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.PUBLIC_MEMBER;








import com.songzi.dao.RelationDAO;
import com.songzi.dao.UserDAO;
import com.songzi.model.Relation;
import com.songzi.model.User;
import com.songzi.util.Const;
import com.songzi.util.JsonUtil;

public class MyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String PARAM = "methodtype"; 
	public User user;
	public UserDAO userDAO;
	public RelationDAO relationDAO;
	public List<User> users = new ArrayList<User>();
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
		 
			response.setCharacterEncoding("UTF-8");  
			response.setContentType("application/json; charset=utf-8");  
			System.out.println("连接服务器>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	        PrintWriter out = response.getWriter();  
	        request.setCharacterEncoding("utf-8");  
	          
	        String type = request.getParameter(PARAM); 
	        String IMEI = request.getParameter("IMEI");
	        String username = request.getParameter("username");
	        String telphone = request.getParameter("telphone");
	        String password = request.getParameter("password");
	        String myusername = request.getParameter("myusername");
	        //System.out.println(IMEI);
	        String myLocation = request.getParameter("myLocation");
	        //上传用户为位置
	        if(type.equals("upMyLocation")){
	        	userDAO = new UserDAO();
	        	user = (User) userDAO.findByUsername(username);
	        	if(user!=null){
	        		user.setLatlngLately(myLocation);
	        	}
	        	userDAO.update(user);
	        	System.out.println("得到最新："+myLocation);
	        }
	        //用户注册
	        if(type.equals("regUser")){
	        	user = new User();
	        	userDAO = new UserDAO();
	        	user.setPassword(password);
	        	user.setUsername(username);
	        	user.setTelpone(telphone);
	        	userDAO.save(user);
	        	out.print("success");
	        }
	        //用户登录
	      if (type.equals("logUser")) {
			userDAO = new UserDAO();
			user = userDAO.loginCheck(username, password);
        	System.out.println(username);
        	System.out.println(password);
        	if(user != null){
        		out.print("success");
            	user.setState(1);//表示在线
            	userDAO.update(user);
        	}else {
				out.print("fail");
				System.out.println("fail");
			}
	      }
	      //获取用户的好友列表
	      if(type.equals("myFrends")){
	    	  userDAO = new UserDAO();
	    	  relationDAO = new RelationDAO();
	    	  user = (User) userDAO.findByUsername(myusername);
	    	  int myid =  user.getId();
	    	  List<Relation> relations = relationDAO.findByMyid(myid);
	    	  //System.out.println(relations.size()+":"+myid);
	    	  users.clear();
	    	  for (Iterator iterator = relations.iterator(); iterator.hasNext();) {
				Relation relation = (Relation) iterator.next();
				user = userDAO.findById(relation.getFriendid());
				System.out.println(user.getId());
				users.add(user);
			}
	    	  String jsonString = JsonUtil.listToJson(users);
	    	  out.write(jsonString);
	    	  users.clear();
	    	 //System.out.println(jsonString);
	      }
	       
	      //用户退出
	      if(type.equals("exit")){
	    	  userDAO = new UserDAO();
	    	  user = (User) userDAO.findByUsername(myusername);
	    	  user.setState(0);
	    	  userDAO.update(user);
	    	  System.out.println("用户退出");
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
