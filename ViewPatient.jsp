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
            <li class="active"><a href="ViewPatient.jsp">View Patient</a></li>
			<li class="active"><a href="Simulated.jsp">View Simulated Script </a></li>
            <li><a href="Logout.jsp">Logout</a></li>
          </ul>
        </div>
        <div class="hbg"><img src="images/header_images.jpg" width="915" height="286" alt="" /></div>
      				<center>
<form name="f1" method="post" action="PatientView.jsp" onsubmit="return validate(this);"><br/>
   <h2><b>View Patient(From Access) Screen</b></h2>
   
	<%
	String res = request.getParameter("t1");
	if(res != null){
		out.println("<center><font face=verdana color=red>"+res+"</center></font>");
	}%>
						
						<table align="center" width="40" >
			 <tr><td><b>Patient&nbsp;ID</b></td>
			 <td><select name="t1" >
			 <%
			 String user = session.getAttribute("user").toString().trim();
			 String path = getServletContext().getRealPath("/")+"/WEB-INF/enc";
			 String id[] = DBConnection.getAccess(path,user);
			 for(int i=0;i<id.length;i++){%>
				<option value="<%=id[i]%>"><%=id[i]%></option>
				<%}%>
				</select></td></tr>
		  		   
			<tr><td></td><td><input type="submit" value="View Details"></td>
			</table>
				</div>	
					
				</div>
				
					
	</body>
</html>