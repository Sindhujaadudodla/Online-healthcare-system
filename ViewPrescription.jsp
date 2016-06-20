<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.DBConnection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="main">
  <div class="main_resize">
    <div class="header">
      <div class="logo">
        <h1><span><center>PSMPA: Patient Self-Controllable
and Multi-Level Privacy-Preserving
Cooperative</center><center>Authentication in Distributed
m-Healthcare Cloud Computing System</center> </span><small></small></h1>
      </div>
    </div>
    <div class="content">
      <div class="content_bg">
        <div class="menu_nav">
       <ul>
            <li class="active"><a href="AccessControl.jsp">Create Access Control</a></li>
            <li><a href="ViewPrescription.jsp">View Prescription</a></li>
            <li><a href="Logout.jsp">Logout</a></li>
          </ul>
        </div>
        <div class="hbg"><img src="images/header_images.jpg" width="915" height="286" alt="" /></div>
      				<center>

   <h2><b>View Patient Details Screen</b></h2>
   
	<%
	String res = request.getParameter("t1");
	if(res != null){
		out.println("<center><font face=verdana color=red>"+res+"</center></font>");
	}%>
			<table border="1" align="center" width="100%">
			<tr><th><font size="3" color="black">Patient ID</th>
			<th><font size="3" color="black">Problem</th>
			<th><font size="3" color="black">Problem Description</th>
			<th><font size="3" color="black">Prescription</th><th><font size="3" color="black">Doctor Name</th>
			<th><font size="3" color="black">Date And Time</th>
			<tr>
	<%
	Connection con = DBConnection.getCon();
	String user = session.getAttribute("user").toString();
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("select * from  prescription where patient_id='"+user+"'");
	while(rs.next()){%>
	<tr><td><font size="3" color="black"><%=rs.getString(1)%></td>
	<td><font size="3" color="black"><%=DBConnection.getProblem(rs.getString(1))%></td>
	<td><font size="3" color="black"><%=DBConnection.getProblemDesc(rs.getString(1))%></td>
	<td><font size="3" color="black"><%=rs.getString(2)%></td>
	<td><font size="3" color="black"><%=rs.getString(3)%></td>
	<td><font size="3" color="black"><%=rs.getString(4)%></td>
	
	<%}%>
	</tr>
	</table>
	</body>
</html>