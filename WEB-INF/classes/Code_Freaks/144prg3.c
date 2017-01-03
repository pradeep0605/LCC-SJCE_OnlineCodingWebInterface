StringBuffer SubmissionStack=new StringBuffer(readFromFile("/usr/share/tomcat7/webapps/SJCE50/SubmissionStack.html"));
					String status="<tr> <td> <center><font size=2>Qno" + QNO +": "+ teamname +  
					": CompileError: <img src=compileerror.png widht=20 height=20></img></font></center></td></tr>";
					SubmissionStack.insert(SubmissionStack.indexOf("<tr>"),status);
					writeToFile("/usr/share/tomcat7/webapps/SJCE50/SubmissionStack.html",SubmissionStack.toString());