package com;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class AccessControl extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
	response.setContentType("text/html");
	HttpSession session=request.getSession();
	PrintWriter out = response.getWriter();
	String pid=session.getAttribute("user").toString();
	String doctor=request.getParameter("t1");
	String qualification=request.getParameter("t2");
	String speciality=request.getParameter("t3");
	String hospital=request.getParameter("t4");
	try{
		String path = getServletContext().getRealPath("/")+"WEB-INF/enc";
		String input[]={pid,doctor,qualification,speciality,hospital};
		String msg=DBConnection.accessControl(input,path);
		if(msg.equals("success")){
			RequestDispatcher rd=request.getRequestDispatcher("AccessControl.jsp?t1=Access control details added successfully");
			rd.forward(request, response);
		}
		else{
			response.sendRedirect("AccessControl.jsp?t1=Error in adding access control");
		}
	

	}catch(Exception e){
		e.printStackTrace();
	}
	}

}
