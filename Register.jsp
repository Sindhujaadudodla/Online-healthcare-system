<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<script language="javascript">
	function validate(formObj)
	{
	if(formObj.t1.value.length==0)
	{
	alert("Please Enter patient name");
	formObj.t1.focus();
	return false;
	}
	if(formObj.t2.value.length==0)
	{
	alert("Please Enter birth date");
	formObj.t2.focus();
	return false;
	}
	if(formObj.t5.value.length==0)
	{
	alert("Please Enter Contact No");
	formObj.t5.focus();
	return false;
	}
	if(formObj.t6.value.length==0)
	{
	alert("Please Enter Address");
	formObj.t6.focus();
	return false;
	}
	if(formObj.t7.value.length==0)
	{
	alert("Please Enter problem");
	formObj.t7.focus();
	return false;
	}
	if(formObj.t8.value.length==0)
	{
	alert("Please Enter problem description");
	formObj.t8.focus();
	return false;
	}
	formObj.actionUpdateData.value="update";
	return true;
	}
	</script>
	 <script language="javascript" type="text/javascript" src="datetimepicker.js">
</script>
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
            <li class="active"><a href="index.jsp">Home</a></li>
            <li><a href="Admin.jsp">Administrator</a></li>
            <li><a href="Physician.jsp">Physicians Login</a></li>
            <li><a href="Patient.jsp">Patient Login</a></li>
            <li><a href="Register.jsp">Patient Profile</a></li>
          </ul>
        </div>
        <div class="hbg"><img src="images/header_images.jpg" width="915" height="286" alt="" /></div>
      				<center>
<form name="f1" method="post" action="Register" onsubmit="return validate(this);"><br/>
   <h2><b>Patient Profile Screen</b></h2>
   
	<%
	String res = request.getParameter("t1");
	if(res != null){
		out.println("<center><font face=verdana color=red>"+res+"</center></font>");
	}%>
						
						<table align="center" width="40" >
			 <tr><td><b>Patient&nbsp;Name</b></td><td><input type="text" name="t1" style="font-family: Comic Sans MS" size="20"/></td></tr>
         
		  <tr><td><b>Birth Date</b></td><td><input name="t2" type="Text" id="demo1" maxlength="25" size="20" class="c2" ><a href="javascript:NewCal('demo1','ddmmmyyyy',true,24)"><img src="cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
	  		<span class="descriptions"></span></td></tr>

		    <tr><td><b>Age</b></td><td><select name="t3">
			<%for(int i=1;i<=200;i++){%>
			<option value="<%=i%>"><%=i%></option>
			<%}%>
			</select>
			</td></tr>

			  <tr><td><b>Gender</b></td><td><select name="t4">
			<option value="Male">Male</option>
			<option value="Female">Female</option>
			</select>
			</td></tr>

			 <tr><td><b>Contact&nbsp;No</b></td><td><input type="text" name="t5" style="font-family: Comic Sans MS" size="20"/></td></tr>

			  <tr><td><b>Address</b></td><td><input type="text" name="t6" style="font-family: Comic Sans MS" size="50"/></td></tr>
				
				<tr><td><b>Problem</b></td><td><input type="text" name="t7" style="font-family: Comic Sans MS" size="80"/></td></tr>

			<tr><td><b>Problem&nbsp:Description</b></td><td><textarea name="t8" cols="60" rows="8"></textarea></td></tr>


			<tr><td></td><td><input type="submit" value="Create Profile"></td>
			</table>
				</div>	
					
				</div>
				
					
	</body>
</html>