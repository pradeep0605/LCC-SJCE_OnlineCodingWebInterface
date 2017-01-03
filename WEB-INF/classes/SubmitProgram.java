import java.io.*;
import java.lang.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.*;



public class SubmitProgram extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	{
		String s="/usr/share/tomcat7/webapps/SJCE50/WEB-INF/classes/";
		PrintWriter pw;
		try
		{	
			res.setContentType("text|html");
			pw=res.getWriter();
			String QNO=req.getParameter("QNO");
			pw.print("<html><title> Submit Program </title> <body bgcolor=black text=white>");
			pw.print("<center> <h1> Submit your code for program \"" + QNO +"\" </h1> <br> Copy paste the program code in the below text area");
			pw.print("</center> <form name=\"form1\"action=\"Test\" method=post>"+
			"<input type=hidden name=QNO value=" + QNO + ">" +
			"<div align=right><input type=submit value=\"Send For Evaluation\" style=\"height:50px; width=500px; font-size:200%\" /></div>" + 
			" <textarea name=\"program\" rows=50 cols=130 style=\"color:blue; background-color:black; font-size:150%\"></textarea>"+
			"</form></body></html>");
			
			pw.close();
		}catch(Exception e){
		   try{
			pw=res.getWriter();
			pw.print("<html><body><h1> error:"+e.toString()+ "</h1></body></html>");
			pw.close();}catch(Exception ex){}
		}
	}
}
