import java.io.*;
import java.lang.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.*;



public class Login extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	{
		String s="/usr/share/tomcat7/webapps/SJCE50/WEB-INF/classes/";
		PrintWriter pw;
		try
		{	
			res.setContentType("text|html");
			pw=res.getWriter();
			if(!(new File(s+"ContestStartTime").exists()))
			{
				pw.print("<html><title> Login</title><body bgcolor=black text=white>"+
				" <center> <h1> Contest has not started yet :) ! </h1> </body></html>");
				pw.close();
				return;
			}
			
			Cookie cookies[]=req.getCookies();
			if(cookies != null)
			if(cookies[0].getValue() !=null)
			{
				pw.print("<html><body bgcolor=black text=white> <center> <h1> You're already logged in as \""  
				+ cookies[0].getValue() +"\" </h1> </center> <a href=MainPage>Main Page</a> <br>" + 
				"<a href=Logout>click here<a> to logout</body></html>");
				return;
			}
			
			String imgpath="../webapps/SJCE50/WEB-INF/classes/";
			
			
			pw.print("<html>\n<title> Login </title> <body bgcolor=black>\n");
			pw.print("<center><h1><font color=white> 'C' Coding Contest <br> LCC Dept. of Computer Science and Engineering</font></h1>");
			pw.print("<h2><font color=gold> Celebrating 50 years of Engineering </h2> </font> ");
			pw.print("<h3 style=\"color:grey\"> Sri Jayachamarajendra College of Engineering, Mysore </h3> ");
			pw.print("<br><table> <tr><td> ");
			pw.print("<form action=Authenticate method=post> <font color=grey>");
			pw.print("<pre>User Name: <input type=text name=usrname> </pre>");
			pw.print("<pre>Password : <input type=password name=pswd> </pre>");
			pw.print("<input type=reset value=\"clear\"> <input type=submit name=submit>");
			pw.print("</table></td></tr></form></font></center></body>\n</html>");
			pw.close();
		}catch(Exception e){}
	}
}

