package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import bswabe.BswabePub;
import bswabe.BswabeCph;
import java.io.File;
import org.jfree.ui.RefineryUtilities;
public class DBConnection{
    private static Connection con;
	
public static Connection getCon()throws Exception {
   try{
	   Class.forName("com.mysql.jdbc.Driver");
	   con = DriverManager.getConnection("jdbc:mysql://localhost/psmpa","root","root");
    }catch(Exception e){
		e.printStackTrace();
	}
	return con;
}


public static String addPhysician(String[] input)throws Exception{
    String msg="no";
    boolean flag=false;
    con = getCon();
	Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select username from adddoctor where username='"+input[0]+"'");
    if(rs.next()){
        flag=true;
        msg = "Username already exist";
    }else{
    PreparedStatement stat=con.prepareStatement("insert into adddoctor values(?,?,?,?,?,?,?,?)");
    stat.setString(1,input[0]);
    stat.setString(2,input[1]);
    stat.setString(3,input[2]);
    stat.setString(4,input[3]);
    stat.setString(5,input[4].trim());
	stat.setString(6,input[5].trim());
	stat.setString(7,input[6].trim());
	stat.setString(8,input[7].trim());
	int i=stat.executeUpdate();
    if(i > 0){
        msg = "success";
	}
    }
    return msg;
}
public static String createProfile(String[] input)throws Exception{
    String msg="no";
    int pid = 0;
    con = getCon();
	Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select count(*) from patientprofile");
    if(rs.next()){
        pid = pid + rs.getInt(1);
    }
	pid = pid + 1;
	java.util.Date d1 = new java.util.Date(input[1].trim());
	java.sql.Date d2 = new java.sql.Date(d1.getTime());
    PreparedStatement stat=con.prepareStatement("insert into patientprofile values(?,?,?,?,?,?,?,?,?)");
	stat.setString(1,"PID-"+pid);
	stat.setString(2,input[0]);
    stat.setDate(3,d2);
    stat.setString(4,input[2]);
    stat.setString(5,input[3]);
    stat.setString(6,input[4].trim());
	stat.setString(7,input[5].trim());
	stat.setString(8,input[6].trim());
	stat.setString(9,input[7]);
	int i=stat.executeUpdate();
    if(i > 0){
        msg = "success,"+"PID-"+pid;
	}
    
    return msg;
}

public static String addPrescription(String[] input)throws Exception{
    String msg="no";
    con = getCon();
	java.util.Date d1 = new java.util.Date();
	java.sql.Timestamp d2 = new java.sql.Timestamp(d1.getTime());
    PreparedStatement stat=con.prepareStatement("insert into prescription values(?,?,?,?)");
    stat.setString(1,input[0]);
    stat.setString(2,input[1]);
    stat.setString(3,input[2]);
    stat.setTimestamp(4,d2);
	int i=stat.executeUpdate();
    if(i > 0){
        msg = "success";
	}
    
    return msg;
}

public static String accessControl(String[] input,String path)throws Exception{
    String msg="no";
    con = getCon();
	PreparedStatement stat=con.prepareStatement("delete from accesspolicy where patient_id=?");
    stat.setString(1,input[0]);
    stat.executeUpdate();
	stat=con.prepareStatement("insert into accesspolicy values(?,?,?,?,?)");
    stat.setString(1,input[0]);
    stat.setString(2,input[1]);
    stat.setString(3,input[2]);
    stat.setString(4,input[3]);
    stat.setString(5,input[4]);
	int i=stat.executeUpdate();
    if(i > 0){
		System.gc();
		long stime = System.currentTimeMillis();
		long total = Runtime.getRuntime().totalMemory();
		String att[] = attributes();
		ABE.generateKey(att);
		String policy = input[1]+" "+input[2]+" "+input[3]+" "+input[4]+" 100 4of5";
		byte enc[] = ABE.encrypt(policy,ABE.public_key);
		FileOutputStream out = new FileOutputStream(path+"/"+input[0]+".txt");
		out.write(enc,0,enc.length);
		out.flush();
		out.close();
		long etime = System.currentTimeMillis();
		long avail = Runtime.getRuntime().freeMemory();
		long memory_use = total - avail;
		memory_use = memory_use/1000;
		long tot = etime - stime;
		Chart chart1 = new Chart("Computation & Storage Chart",tot,memory_use);
		chart1.pack();
		RefineryUtilities.centerFrameOnScreen(chart1);
		chart1.setVisible(true);
        msg = "success";
	}
    return msg;
}
public static String login(String input[])throws Exception{
    String msg="invalid login";
    con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select usertype from adddoctor where username='"+input[0]+"' and password='"+input[1]+"'");
    if(rs.next()){
        msg = "success";
    }
    System.out.println(msg);
    return msg;
}
public static String[] getAccess(String path,String user)throws Exception{
	String att[] = attributes(user);
	ABE.generateKey(att);
	File file = new File(path);
	File list[] = file.listFiles();
	StringBuilder sb = new StringBuilder();
	for(int i=0;i<list.length;i++){
		FileInputStream fin = new FileInputStream(list[i]);
		byte b[] = new byte[fin.available()];
		fin.read(b,0,b.length);
		fin.close();
		boolean flag = ABE.decrypt(ABE.public_key,ABE.private_key,b);
		if(flag){
			String name = list[i].getName();
			name = name.substring(0,name.lastIndexOf("."));
			sb.append(name+",");
		}
	}
	if(sb.length() > 0)
		sb.deleteCharAt(sb.length()-1);
	return sb.toString().split(",");
}

public static String[] attributes()throws Exception{
    ArrayList<String> list =  new ArrayList<String>();
    con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select username,qualification,speciality,hospital_id from adddoctor");
    while(rs.next()){
		String user = rs.getString(1);
		String qua = rs.getString(2);
		String spe = rs.getString(3);
		String hospital = rs.getString(4);
		if(!list.contains(user))
			list.add(user);
		if(!list.contains(qua))
			list.add(qua);
		if(!list.contains(spe))
			list.add(spe);
		if(!list.contains(hospital))
			list.add(hospital);
    }
    rs.close();stmt.close();con.close();
	String att[] = new String[list.size()];
	for(int i=0;i<list.size();i++){
		att[i] = list.get(i);
	}
    return att;
}
public static String[] attributes(String usr)throws Exception{
    ArrayList<String> list =  new ArrayList<String>();
    con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select username,qualification,speciality,hospital_id from adddoctor where username='"+usr+"'");
    while(rs.next()){
		String user = rs.getString(1);
		String qua = rs.getString(2);
		String spe = rs.getString(3);
		String hospital = rs.getString(4);
		if(!list.contains(user))
			list.add(user);
		if(!list.contains(qua))
			list.add(qua);
		if(!list.contains(spe))
			list.add(spe);
		if(!list.contains(hospital))
			list.add(hospital);
    }
    rs.close();stmt.close();con.close();
	String att[] = new String[list.size()];
	for(int i=0;i<list.size();i++){
		att[i] = list.get(i);
	}
    return att;
}
public static String patientLogin(String pid)throws Exception{
    String msg="invalid login";
    con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select patient_id from patientprofile where patient_id='"+pid+"'");
    if(rs.next()){
        msg = "success";
    }
    return msg;
}
public static String getProblem(String pid)throws Exception{
    String msg="none";
    con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select problem from patientprofile where patient_id='"+pid+"'");
    if(rs.next()){
        msg = rs.getString(1);
    }
    return msg;
}
public static String getProblemDesc(String pid)throws Exception{
    String msg="none";
    con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select problem_desc from patientprofile where patient_id='"+pid+"'");
    if(rs.next()){
        msg = rs.getString(1);
    }
    return msg;
}
public static boolean exists(String pid)throws Exception{
    boolean flag = false;
    con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select patient_id from prescription where patient_id='"+pid+"'");
    if(rs.next()){
        flag = true;
    }
    return flag;
}
}