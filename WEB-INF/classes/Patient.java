package com;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class Patient extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
	response.setContentType("text/html");
	HttpSession session=request.getSession();
	PrintWriter out = response.getWriter();
	String pid=request.getParameter("t1");
	try{
		String msg=DBConnection.patientLogin(pid);
		boolean flag=false;
		if(msg.equals("success")){
			session.setAttribute("user",pid);
			RequestDispatcher rd=request.getRequestDispatcher("PatientScreen.jsp?t1=Welcome "+pid);
			rd.forward(request, response);
		}else{
			response.sendRedirect("Patient.jsp?t1=Invalid Patient ID");
		}
	

	}catch(Exception e){
		e.printStackTrace();
	}
	}

}
