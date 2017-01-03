import java.io.*;
import java.lang.*;
import javax.servlet.*;
import java.util.*;
import javax.servlet.http.*;
import javax.swing.*;


/*  Compile error  100
    Wrong Answer   150
    Runtime error  200
      
*/  
public class StopContest extends HttpServlet
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
	
	void givePermission()
	{	
		try
		{
			ProcessBuilder pb=new ProcessBuilder("/usr/share/tomcat7/webapps/SJCE50/permission.sh");
			pb.start();
		}catch(Exception e){}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	{          
		PrintWriter pw=null;
		try 
		{	
			pw=res.getWriter();
			res.setContentType("text|html");
			File f=new File(s+"ContestStartTime");
			f.delete();
			ProcessBuilder pb=new ProcessBuilder("sh", "/usr/share/tomcat7/bin/shutdown.sh");
			pb.start();
			pw.print("<html><body bgcolor=black text=white> <h1> Contest Stopped </h1></body></html>");
			pw.close();
			
		}catch(Exception e){  pw.print(e.toString());}
	}
}





