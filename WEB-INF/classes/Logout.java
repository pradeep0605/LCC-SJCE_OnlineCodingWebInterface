import java.io.*;
import java.lang.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.*;



public class Logout extends HttpServlet
{

	String s="/usr/share/tomcat7/webapps/SJCE50/WEB-INF/classes/";
	void writeToFile(String filename, String contents)
	{
	  try
	  {
		FileOutputStream fout=new FileOutputStream(filename);
		fout.write(contents.getBytes());
		fout.close();
	   } catch(Exception e){}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	{
		PrintWriter pw;
		try
		{	
			String teamname="";			
			res.setContentType("text|html");
			pw=res.getWriter();
			Cookie cookies[]=req.getCookies();
			if(cookies != null)
			if(cookies[0].getValue() !=null)
				teamname=cookies[0].getValue().trim();		
			for(int i=0;i<cookies.length;i++)
			{
				cookies[i].setMaxAge(0);
				res.addCookie(cookies[i]);
			}

			
			writeToFile(s+teamname+"/LoginCount","0");
			res.sendRedirect("Login");
		}catch(Exception e)
		{
			try{
			pw=res.getWriter();
			pw.print("<html><body><h1> error:"+e.toString()+ "</h1></body></html>");
			pw.close();}catch(Exception ex){}
		}
	}
}
