package  authentication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.RequestDispatcher;
import user.UserData;


import org.apache.commons.codec.digest.DigestUtils;
//import org.json.JSONObject;

import com.google.gson.Gson;


  
@WebServlet("/redirectto")  
public class RedirectToServlet extends HttpServlet 
{  
    private static final long serialVersionUID = 1L;  
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
    { 
		try{
        /*    For getting Parameter         */
		System.out.println("-------------------------RedirectToServlet has been invocked-------------------------");
		
		HttpSession session=request.getSession();
		
		UserData  user1=(UserData)session.getAttribute("user");
		UserData  user2=(UserData)session.getAttribute("otpverifieduser");
		
		//if(user == null ) {System.out.println("User object is not found in session"); user = new User();}
		//System.out.println("Module Name"+user.getDesignationId());
		//System.out.println("Session Id in RedirectToServlet ="+session.getId());
		
		/* OPD Operatior Designation id is 4  */
		if( user2.getSessionId().compareTo(session.getId())==0 && user1.getMobileNo().compareTo(user2.getMobileNo())==0 )
		{
			RequestDispatcher rd=request.getRequestDispatcher("/surveyform");
			rd.forward(request,response);
		}
		else 
		{
			System.out.println("Inside login module.................");
			RequestDispatcher rd=request.getRequestDispatcher("/loginmodule");
			rd.forward(request,response);
		}
		
		
		}
		catch(Exception ex){ System.out.println(ex);}
       
    }  
}  