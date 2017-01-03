import java.io.*;
import java.lang.*;
import javax.servlet.*;
import java.util.*;
import javax.servlet.http.*;
import javax.swing.*;


/*  
	Compile error  100
    Wrong Answer   150
    Runtime error  200
    Timeout        200
*/  

public class Test extends HttpServlet
{
	String s="/usr/share/tomcat7/webapps/SJCE50/WEB-INF/classes/";
	String QNO;String teamname="";
	final int CompileErrorPoints=100, WrongAnswerPoints=150, RuntimeErrorPoints=200, TimeoutPoints=200;
	
	void addPoints(int ErrorPoints)
	{
			String points=readFromFile(s+teamname+"/points").trim();
			int n=Integer.parseInt(points);
			writeToFile(s+teamname+"/points", new String("" + (n+ErrorPoints)));
	}	
	
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
	
	void writeToFile(String filename, byte []arr)
	{
	  try
	  {
		FileOutputStream fout=new FileOutputStream(s+filename);
		fout.write(arr);
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
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	{          
		PrintWriter pw;
		try 
		{	
		
			pw=res.getWriter();
			res.setContentType("text|html");
			String desc=req.getParameter("program");
			desc=desc.trim();
			QNO=req.getParameter("QNO");
			int SubmissionCount=Integer.parseInt(readFromFile(s+"problems/SubmissionNumber").trim());
			if(desc.indexOf("fork")!=-1 || desc.indexOf("system")!=-1 || desc.indexOf("fread")!=-1 || desc.indexOf("fwrite")!=-1 )
			{
				pw.printf("<html><body bgcolor=black text=red><center><h1>YOU!!! Do you wanna continue coding or what ?! ?! ?!</h1>"+
				"<br>UNAUTHORIZED USAGE OF FUNCTIONS <br><a href=MainPage>Question Page</a></center></body></html>");
				pw.close();
				return;
			}
			
			Cookie cookies[]=req.getCookies();
			if(cookies[0] != null)
				teamname=cookies[0].getValue();
			if(desc!=null && desc!="")
			{
				String filepath=s+teamname+"/"+SubmissionCount+"prg"+QNO+".c";
				writeToFile(filepath ,desc);
				writeToFile(s+"problems/SubmissionNumber",""+(SubmissionCount+1));
				ProcessBuilder pb=new ProcessBuilder("gcc","-Wall","-lm",filepath,"-o",s+teamname+"/" + teamname + ".out");
				Process p=pb.start();
				//while( !(new File(s+teamname+"/" + teamname + ".out").exists() )) { Thread.currentThread().sleep(50); }
				Thread.currentThread().sleep(500);
				InputStream in=p.getInputStream();
				InputStream err=p.getErrorStream();
				byte barr1[]=new byte[in.available()], barr2[]=new byte[err.available()];
				in.read(barr1); err.read(barr2);
				String compileResults= new String(barr1) + new String(barr2);
				compileResults=compileResults.trim();
				givePermission();
				
				if(compileResults.length()==0)
				{
					pw.print("<html><body bgcolor=black text=white ><center><h1>Compilation : <font color=green> Successful</font>");
					pw.print("&nbsp;<img src=correct.png width=30 height=30></h1>");
					ProcessBuilder pbout=
					new ProcessBuilder(s+"interface.out",s+teamname+ "/" + teamname +".out", s+"problems/prg"+QNO+".input", 
									   s+teamname + "/output");
														
					FileInputStream timeoutin =(new FileInputStream(s+"problems/prg" + QNO +".timeout"));
					byte timeoutbarr[]=new byte[timeoutin.available()];
					timeoutin.read(timeoutbarr);
					(new ExecuteProgram(pbout,pw,Float.parseFloat( new String(timeoutbarr).trim()))).join();
					//pw.print("<br>JOINED!\n");
					pw.close();				
				}
				else //COMPILE ERRROROROR !!!
				{
					pw.print("<html><body bgcolor=black text=white> " +
					"<h1><center>Compilation :<font color=red> ERROR </font>");
					pw.print("<img src=compileerror.png widht=50 height=50></center></h1></body></html>");
					addPoints(CompileErrorPoints);

					updateSubmissionStack("CompileError","compileerror.png");
					
					pw.close();
				}
			}
			else
			{	
				pw.print("<html><body><center><h1>Gimme some input !!!</center></body></html>");
				pw.close();
			}
		}catch(Exception e)  
		{	try{
			pw=res.getWriter();
			pw.print("<html><body><h1> error:"+e.toString()+ "</h1></body></html>");
			pw.close();}catch(Exception ex){}
		}
	}
	
	void updateSubmissionStack(String stat, String imgfilename)
	{
		StringBuffer SubmissionStack=new StringBuffer(readFromFile("/usr/share/tomcat7/webapps/SJCE50/SubmissionStack.html"));
		String submissionNumber = readFromFile(s+"problems/SubmissionNumber").trim();
		String status="<tr> <td> <font size=2 color=gold>SubNo: " + submissionNumber +": Qno " + QNO +": <font color=#7AE3F8>"+ teamname +  
		"</font> :<font color=red> "+stat+"</font>: <img src="+ imgfilename+" widht=20 height=20></img></font></td></tr>";
		SubmissionStack.insert(SubmissionStack.indexOf("<tr>"),status);
		writeToFile("/usr/share/tomcat7/webapps/SJCE50/SubmissionStack.html",SubmissionStack.toString());
	}
	
	class ExecuteProgram extends Thread
	{
		ProcessBuilder pb;
		PrintWriter pw;
		Process p;
		int timeout;
		ExecuteProgram(ProcessBuilder pbs, PrintWriter pws,float timeouts)
		{
			try
			{
				pb=pbs; pw=pws;
				timeout=(int)( timeouts*1000) + 200;
				p=pb.start();
				//pw.print("Time out = " + timeout);
				start();
			}catch(Exception e){ pw.print(e.toString());}
		}
		
		public void run()
		{
			try
			{
				sleep(timeout);
				if(isBadkidiya()) //TIME OUT ERROROR !
				{
					p.destroy();
					ProcessBuilder killer=new ProcessBuilder("killall","-9",teamname+".out");
					killer.start();
					pw.print("<br> <h1> Time out &nbsp; <img src=timeout.png width=70 height = 70></img></h1></body></html>");
					updateSubmissionStack("TimeOut","timeout.png");
					addPoints(TimeoutPoints);
				}
				else
				{
					InputStream err=p.getErrorStream();
					
					
					if(p.exitValue() !=0 ) // RUNTIME EROROROR !!!!!
					{
						pw.print("<br> <h1> Runtime Error : Abnomral Termination"+ 
						"<img src=runtimeerror.png width=70 height = 70></img></h1></body></html>");
						addPoints(RuntimeErrorPoints);
						updateSubmissionStack("RuntimeError","runtimeerror.png");
					}
					if( err.available()>0 ) // RUNTIME ERROR
					{
						byte barr[]=new byte [ err.available()];
						err.read(barr);
						pw.print("<br> <h1> Runtime Error :" + new String(barr).trim() +
						" &nbsp; <img src=runtimeerror.png width=70 height = 70></img></h1></body></html>");
						addPoints(RuntimeErrorPoints);
						updateSubmissionStack("RuntimeError","runtimeerror.png");
												
					}
					else
					{
						String original=readFromFile(s+"/problems/prg" +QNO +".output");
						String generated=readFromFile(s+teamname+"/output");
						original=original.trim();
						generated=generated.trim();
						//pw.print("Original: " + original + "<br>Generated:" + generated);
						if(generated.equals(original)) // PROGRAM ACCEPTED !!!
						{	
							pw.print("<br><h1> Status : <font color=green> Accepted ! </font>" +
							" <img src=correct.png width=50 height=50></img> </h1></body></html>");
							long ContestStartTime=Long.parseLong(readFromFile(s+"ContestStartTime").trim());
							long CurrentTime=System.currentTimeMillis();
							ContestStartTime=ContestStartTime/(1000);
							CurrentTime=CurrentTime/(1000);
							
							addPoints(((int)(CurrentTime-ContestStartTime)));
							updateSubmissionStack("Accepted","correct.png");
							String ProblemsSolved= readFromFile(s+ teamname + "/ProblemsSolved");
							if( ProblemsSolved.indexOf(QNO+ " ") == -1)
							{
								ProblemsSolved=ProblemsSolved+ QNO + " \n";
								writeToFile(s+ teamname + "/ProblemsSolved",ProblemsSolved);
								int SubmissionPoints=Integer.parseInt(readFromFile(s+ teamname + "/SubmissionPoints").trim());
								SubmissionPoints+=Integer.parseInt(readFromFile(s+"problems/prg" +QNO + ".SubmissionPoints").trim());
								writeToFile(s+ teamname + "/SubmissionPoints",""+SubmissionPoints);
							}
						}
						else // WRONG ANSWER   ! ! ! ! 
						{
							pw.print("<br><h1> <font color=red> Wrong Answer : <img src=wrong.png></img> </font> </h1></body></html>");
							addPoints(WrongAnswerPoints);
							updateSubmissionStack("WrongAnswer","wrong.png");
						}
					}
				}
			}catch(Exception e){ pw.print(e.toString());}
		}
		boolean isBadkidiya() 
		{
			try 
			{
				p.exitValue();
				return false;
			} catch (Exception e) 
			{		return true;    }
		}
	}
}





