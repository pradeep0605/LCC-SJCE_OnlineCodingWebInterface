import java.io.*;
import java.lang.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.*;



public class MainPage extends HttpServlet
{

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
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	{
		PrintWriter pw;
		try
		{	
			String s="/usr/share/tomcat7/webapps/SJCE50/WEB-INF/classes/";
			pw=res.getWriter();
			res.setContentType("text|html");
			int n=Integer.parseInt(readFromFile(s+"problems/NumberOfProblems").trim());
			Cookie cookies[]=req.getCookies();
			if(cookies==null)
			{
				pw.print("<html><title>Login please </title><body bgcolor=black text=white> <center> <h1> You're not logged in !! </h1><br>");
				pw.print("<a href=Login>Click here</a> <b> to login </b></body></html>");
				pw.close();
				return ;
			}
			String questionNumber=req.getParameter("question");
			if(questionNumber==null)
				questionNumber="1";
			if(Integer.parseInt(questionNumber) > n)
			{
				pw.print("<html><title>error</title><body bgcolor=black text=white><h2><center>Invalid Access to question " 
				+ questionNumber + "</h2><br> <a href=MainPage?question=1>click here</a> to go to home page </center></body></html>");
				pw.close();
				return;
			}
			String Contents=readFromFile(s+ "problems/prg" + questionNumber + ".html");
			pw.print(Contents);
			//res.sendRedirect("problems/test.html");
			pw.close();
		}catch(Exception e)
		{
			try{
			pw=res.getWriter();
			pw.print("<html><body><h1> error:"+e.toString()+ "</h1></body></html>");
			pw.close();}catch(Exception ex){}
		}
	}
}

