import java.io.*;
import java.lang.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.*;



public class LeaderBoard extends HttpServlet
{
	String s="/usr/share/tomcat7/webapps/SJCE50/WEB-INF/classes/";
	static long time=0;
	static String LeaderBoard="";
	static int currentUsers=0;
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
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	{
		PrintWriter pw;
		try
		{	
			currentUsers++;
			res.setContentType("text|html");
			pw=res.getWriter();


			if(time==0)
			{
				time=System.currentTimeMillis()/1000;
				generateLeaderBoard(pw);
				pw.print(LeaderBoard);
			}
			else
			{
				long curtime=System.currentTimeMillis()/1000;
				if((curtime-time) >=10 && currentUsers <=3)
				{
					generateLeaderBoard(pw);
					time=System.currentTimeMillis()/1000;	
					givePermission();
				}
					pw.print(LeaderBoard);
			}
			
			pw.print("</table></center></body></html>");
			pw.close();
			currentUsers--;
		}catch(Exception e)
		{
			try{
			pw=res.getWriter();
			pw.print("<html><body><h1> error:"+e.toString()+ "</h1></body></html>");
			pw.close();}catch(Exception ex){}
		}
	}
	
	void generateLeaderBoard(PrintWriter pw)
	{
		try
		{	
			int n=Integer.parseInt(readFromFile(s+"problems/NumberOfProblems").trim());
			String contents="";
			LeaderBoard=new String("");
			LeaderBoard="<html><body bgcolor=black text=white > " +
			"<center><h1> Leader Board </h1> <h2> Leader Board updates will be available for every <font color=gold> 30 </font> seconds </h2>" +
			"<table border=\"1\" cellspacing=5 cellpadding=10> <th> No. </th> <th> Team Name </th>" ;
			
			
			int noques=Integer.parseInt(readFromFile(s+"problems/NumberOfProblems").trim());
			for(int i=0;i<noques;i++)
				 LeaderBoard = LeaderBoard + "<th> Question \"" +  (i+1)  +"\" </th>" ;
			
			LeaderBoard = LeaderBoard + "<th> <img src=star.png width=30 height=30> </th> <th> Total Points </th>" ;
			StringTokenizer st=new StringTokenizer(readFromFile(s+ "5haD0w") , "\n");
			for(int i=0; st.hasMoreElements();i++)
			{
				String CurrentToken=st.nextToken();
				String CurrentTeam = CurrentToken.substring((CurrentToken.lastIndexOf(":") +1)).trim();
				String CurrentTeamSolvedProblems="";
				
				if( new File(s+CurrentTeam + "/ProblemsSolved").exists()) // user might not have logged in so...
					CurrentTeamSolvedProblems= readFromFile(s+CurrentTeam + "/ProblemsSolved");
					
				contents= contents + CurrentTeam+"|";
				
				for(int j=1;j<=n;j++)
				{
					if(CurrentTeamSolvedProblems.indexOf(new String("" + j +" ")) != -1)
						contents=contents + "<font color=green size=5><b> Solved ! </b></font>|";
					else
						contents=contents + "<font color=orange>        </font>|";
				}
				if( new File(s+CurrentTeam + "/SubmissionPoints").exists())
					contents= contents + readFromFile(s+CurrentTeam + "/SubmissionPoints").trim() + "|";				
				else
					contents = contents + "0" + "|";	
				if( new File(s+CurrentTeam + "/points").exists())
					contents= contents + readFromFile(s+CurrentTeam + "/points").trim() +"\n" ;		
				else
					contents = contents + "0" + "\n";
			}
			writeToFile(s+"LeaderBoardTemp",contents);
			
			ProcessBuilder pb=new ProcessBuilder("sort","-s","-n","-t", "|" ,"--key=" + (n+3),s+"LeaderBoardTemp");
			Process p=pb.start();
			InputStream in=p.getInputStream();
			Thread.currentThread().sleep(50);
			while(in.available()==0);
			byte barr[]=new byte[in.available()];
			in.read(barr);
			writeToFile(s+"LeaderBoardTemp2", new String(barr).trim());

			ProcessBuilder pb2=new ProcessBuilder("sort","-s","-n","-r","-t", "|" ,"--key=" + (n+2) + "," + (n+2),s+"LeaderBoardTemp2");
			Process p2=pb2.start();
			InputStream ins=p2.getInputStream();
			Thread.currentThread().sleep(50);
			while(ins.available()==0);
			byte barr2[]=new byte[ins.available()];
			ins.read(barr2);
			String BoardContents=new String(barr2);
			StringTokenizer stk =new StringTokenizer(BoardContents,"\n");
			int i=1;
			while(stk.hasMoreElements())
			{
				String CurrentToken=stk.nextToken();
				CurrentToken=CurrentToken.replace("|","</td><td>");
				LeaderBoard=LeaderBoard + "<tr><td>" + i + "</td><td>" + CurrentToken + "</td></tr>";
				i++;
			}
			
		}catch(Exception e){
			try{
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
