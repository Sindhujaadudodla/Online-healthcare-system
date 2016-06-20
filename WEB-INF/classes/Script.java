package com;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Script extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
	response.setContentType("text/html");
	HttpSession session=request.getSession();
	PrintWriter out = response.getWriter();
	String pid=request.getParameter("t1");
	String prescription=request.getParameter("t2");
	String doctor = session.getAttribute("user").toString();
	try{
		String input[]={pid,prescription,doctor};
		String msg=DBConnection.addPrescription(input);
		if(msg.equals("success")){
			RequestDispatcher rd=request.getRequestDispatcher("PhysicianScreen.jsp?t1=Prescription details added");
			rd.forward(request, response);
		}else{
			response.sendRedirect("PhysicianScreen.jsp?t1=Error in adding prescription");
		}
	

	}catch(Exception e){
		e.printStackTrace();
	}
	}

}
