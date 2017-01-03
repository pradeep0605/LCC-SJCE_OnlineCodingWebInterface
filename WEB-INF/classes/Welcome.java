import java.io.*;
import java.lang.*;
import javax.servlet.*;
import java.util.*;
import javax.servlet.http.*;
import javax.swing.*;
import java.net.*;

public class Welcome extends HttpServlet
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
			ProcessBuilder pb=new ProcessBuilder("ifconfig","-a");
			Process p=pb.start();
			InputStream in=p.getInputStream();
			while(in.available()<=0);
			byte arr[]=new byte[in.available()];
			in.read(arr);
			String ServerIPAddress=new String(arr);
			while(ServerIPAddress.indexOf("inet addr:")!=-1)
			{
				ServerIPAddress=ServerIPAddress.substring(ServerIPAddress.indexOf("inet addr:")+10);
				if(ServerIPAddress.substring(0,ServerIPAddress.indexOf(" ")).trim().equals("127.0.0.1"))
					continue;
				else
				{
					ServerIPAddress=ServerIPAddress.substring(0,ServerIPAddress.indexOf(" ")).trim();
					break;					
				}				  
			}
			
			String Content="<html><body bgcolor=black text=white> <center><h1> Weclome to LCC 'C' Coding Contest</h1>"+
			"<h1>Department Of Computer Science and Engineering <br><br> Sri Jayachamarajendra College of Engineering, Mysore "+
			"</h1> <font color=gold> "+
			"<h2> Celebrating 50 years of Excellence</h2></font></center> <h2> Instructions </h2> (Real all of then carefully)" +
			"<ul> <li> When admin says \"Contest has Started\" login using this link "+
			"<a href=http://" + ServerIPAddress +":8080/SJCE50/Login target=\"_blank\"> click here</a> or url "+
			"http://" + ServerIPAddress +":8080/SJCE50/Login </li> <br> <li> After you login you will see set of question with links to each"+
			" of them. </li>"+
			"<br><li> Read the information given in the Question carefully. Such as Time limit, Input-output constraints, Input-output limit"+ 				"etc. Certain Number of starts are allocated for each question. And by solving them your team would rewarded those many stars."+
			"</li><br><li> Compile using gcc compiler. Use <font color=green><b>gcc -Wall yourfilename.c</b></font>"+
			" and make sure there are no Errors or Warnings. </li>"+
			"<br><li> Once you've coded your program, go to the respective question's page and click on <b>Submit Program</b> Button."+
			" Copy paste your code and click on <b>Send for Evaluation Button </b>.</li>"+ 
			"<br><li> Depending upon your program you will get <font color=red> Compile Error <img src=compileerror.png width=20 height=20>"+
			", Runtime Error <img src=runtimeerror.png width=20 height=20>, Time out <img src=timeout.png width=20 height=20>,"+
			"Wrong Answer <img src=wrong.png width=20 height=20></font> or <font color=green> Accepted ! "+
			"<img src=correct.png width=20 height=20></font> result.</li>"+
			"<br><li> For each team a value called <b>POINTS</b> will be kept. Higher the points lower the rank. Certain Points are added for "+
			" each of the above results. Compile error would add <font color=red>100</font> points, Wrong Answer would add "+
			"<font color=red>150</font> points, Runtime error and timeout would add <font color=red>200</font> points and "+
			"for an accepted program a certain units of value depending upon the time elapsed from start of the contenst will be added. "+
			"For multiple submissions of the same program (seeking more optimizations) would again add to the Points. Remember Higher the"+
			" points Lower the rank. </li><br>"+
			"<li> In deciding the Winner the Server sorts the teams according to the number of <b><i>Stars</i></b> first and then with "+
			"the points. The team with more stars and less points would WIN. i.e. if two teams gain same number of stars then, "+
			"the points are used to break the tie. These things can be seen in <b><i> Leader Board.</i></b></li><br>"+
			"<li> Whenever any Team submits code, the result will be updated in the <b><i> Submission Stack</i></b>.</li><br>"+		
			"<li> Though the Winner is almost selected by the Server, the decision by the LCC core on the Winner will be final. "+
			"Certain ascepts such as <b>\"Team which was leading most of the time\"</b> and <b>\"code efficiency\"</b> should"+
			" also be considered.</li><br>"+
			"<li> Any team using cell phones, internet, pendrive, sharing code or trying to harm the server would be immediately"+
			" disqualified.</li>"+

			"</body></html>";
			pw.print(Content);
			pw.close();
			
		}catch(Exception e){  pw.print(e.toString());}
	}
}





