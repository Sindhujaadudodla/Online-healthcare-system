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
            <li class="active"><a href="AddPhysician.jsp">Add Physicians</a></li>
            <li><a href="ViewPhysician.jsp">View Physicians</a></li>
            <li><a href="Logout.jsp">Logout</a></li>
          </ul>
        </div>
        <div class="hbg"><img src="images/header_images.jpg" width="915" height="286" alt="" /></div>
		<%
	String res = request.getParameter("t1");
	if(res != null){
		out.println("<center><font face=verdana color=red>"+res+"</center></font>");
	}%>
       <p align="justify"><font size="3" style="font-family: Comic Sans MS">Abstract-Distributed m-healthcare cloud computing system significantly facilitates efficient patient treatment for medical consultation
by sharing personal health information among healthcare providers. However, it brings about the challenge of keeping both the data
confidentiality and patients’ identity privacy simultaneously. Many existing access control and anonymous authentication schemes
cannot be straightforwardly exploited. To solve the problem, in this paper, a novel authorized accessible privacy model (AAPM) is
established. Patients can authorize physicians by setting an access tree supporting flexible threshold predicates. Then, based on it, by
devising a new technique of attribute-based designated verifier signature, a patient self-controllable multi-level privacy-preserving
cooperative authentication scheme (PSMPA) realizing three levels of security and privacy requirement in distributed m-healthcare
cloud computing system is proposed. The directly authorized physicians, the indirectly authorized physicians and the unauthorized
persons in medical consultation can respectively decipher the personal health information and/or verify patients’ identities by satisfying
the access tree with their own attribute sets. Finally, the formal security proof and simulation results illustrate our scheme can resist
various kinds of attacks and far outperforms the previous ones in terms of computational, communication and storage overhead.</p>
 
 
  </body>
</html>
