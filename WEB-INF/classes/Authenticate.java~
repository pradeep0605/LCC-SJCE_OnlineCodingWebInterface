import java.io.*;
import java.lang.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.*;



public class Authenticate extends HttpServlet
{
	String s="/usr/share/tomcat7/webapps/SJCE50/WEB-INF/classes/";
	
	String readFromFile(String filepath)
	{
		byte barr[]=null;
		try
		{
			FileInputStream in=new FileInputStream(filepath);
			barr=new byte[in.available()];
			in.read(barr);
		}catch(Exception e){}
		return (new String(barr));		
	}
	
	void writeToFile(String filename, String contents)
	{
	  try
	  {
		FileOutputStream fout=new FileOutputStream(filename);
		fout.write(contents.getBytes());
		fout.close();
	   } catch(Exception e){}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	{
		PrintWriter pw;
		try
		{	
			res.setContentType("text|html");
			pw=res.getWriter();
			String uname= req.getParameter("usrname");
			String password=req.getParameter("pswd");
			
			pw.print("<html><title>Login Unsuccessful</title><body bgcolor=black text=white> <h1> <center> Authentication</center> </h1>");
			//pw.print("Uname:" + uname +"<br>" + "pass:" + password + "<br>");
			String users=readFromFile(s+"5haD0w");
			StringTokenizer stkr=new StringTokenizer(users,"\n");
			String temp;
			if(uname!="" && password!="")
				while(stkr.hasMoreElements())
				{
					temp=stkr.nextToken();
					if(temp.indexOf(uname) !=-1 && temp.indexOf(password) != -1)
					{
						String teamname=temp.substring(temp.lastIndexOf(":")+1);
						
						if(new File(s+teamname+"/LoginCount").exists())
						{
							String lcount=readFromFile(s+teamname+"/LoginCount").trim();										
							if(lcount.equals("1"))
							{
								pw.print("<html><body bgcolor=black text=white><h1><center>Another user has Already logged in With this ID"+
								"</h1><br> <a href=Login> Click here</a> to get Back to login Page</center></body></html>");
								pw.close();
								return;
							}
						}
						Cookie username=new Cookie("username",teamname);
						res.addCookie(username);
						File f=new File(s+teamname);
						
						if(!f.exists())
						{
							ProcessBuilder pb=new ProcessBuilder("mkdir",s+teamname);
							Process p=pb.start();
							p.waitFor();
							
							writeToFile(s+teamname+"/points","0");
							writeToFile(s+teamname+"/SubmissionPoints","0");
							writeToFile(s+teamname+"/ProblemsSolved"," \n");
						}
						else
						{
							
						}
						writeToFile(s+teamname+"/LoginCount","1");
						givePermission();
						res.sendRedirect("MainPage?question=1");
						return;
					}
				}
			pw.print("INVALID USERNAME OR PASSOWRD\n");
			pw.print("<br> <h2> <font color=red>LOGIN UNSUCCESSFUL </font>" +  
			"</h2> <a href=Login> Click here</a> to get back to login page </body></html");

			//res.sendRedirect("Login");
			
			pw.close();
		}catch(Exception e)
		{
			try{
			pw=res.getWriter();
			pw.print("<html><body><h1> error:"+e.toString()+ "</h1></body></html>");
			pw.close();}catch(Exception ex){}
		}
	}
	void givePermission()
	{
		try
		{
			ProcessBuilder pb=new ProcessBuilder("/usr/share/tomcat7/webapps/SJCE50/permission.sh");
			pb.start();
		}catch(Exception e){}
	}
}
