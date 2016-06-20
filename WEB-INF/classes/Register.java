package com;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Register extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	String pname=request.getParameter("t1");
	String bdate=request.getParameter("t2");
	String age=request.getParameter("t3");
	String gender=request.getParameter("t4");
	String contact=request.getParameter("t5");
	String address=request.getParameter("t6");
	String problem=request.getParameter("t7");
	String desc=request.getParameter("t8");
	try{
		String input[]={pname,bdate,age,gender,contact,address,problem,desc};
		String msg=DBConnection.createProfile(input);
		if(!msg.equals("fail")){
			String arr[] = msg.split(",");
			RequestDispatcher rd=request.getRequestDispatcher("Register.jsp?t1=Your Profile created.<br/>Login with your id = "+arr[1]);
			rd.forward(request, response);
		}
		else{
			response.sendRedirect("Register.jsp?t1=Error in creating pfofile");
		}
	

	}catch(Exception e){
		e.printStackTrace();
	}
	}

}
