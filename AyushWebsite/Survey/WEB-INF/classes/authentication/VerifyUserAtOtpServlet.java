package  authentication;

import  user.UserData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.Math;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
//import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.*;


import  user.*;
  
@WebServlet("/verifyuseratotp")  
public class  VerifyUserAtOtpServlet extends HttpServlet 
{  
    private static final long serialVersionUID = 1L;  
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                            throws ServletException, IOException { 
        /*    For getting Parameter         */
		System.out.println("-------------------------------------------------------------");
		String mobileNo="";
		String otp="";
		String sessionId="";
		
		HttpSession session=request.getSession(true);
		System.out.println("Session id in SendOtpServlet ="+session.getId());
		
		StringBuilder sb = new StringBuilder();
        System.out.println("In side  VerifyUserAtOtpServlet.java ...........");
        BufferedReader reader = request.getReader();
		String line="";
        try
		{
            while ((line = reader.readLine()) != null) {
            sb.append(line).append('\n');
        }
        } finally {
        reader.close();
        }
	    try
	    {
	    
		String json_string=sb.toString();
	    System.out.println("Client Side JSON String in VerifyUserAtOtpServlet.java ="+json_string);
		
		
		JsonParser  parser=new  JsonParser();
		JsonElement  jsonElement=parser.parse(json_string);
		JsonObject   rootElement=jsonElement.getAsJsonObject();
		
	    mobileNo= rootElement.get("mobileNo").getAsString();
		otp=rootElement.get("otp").getAsString();
		sessionId=rootElement.get("sessionId").getAsString();
		}
		catch(Exception ex)
	    {
			System.out.println(ex);
		}

		/*---------------------------------- */
        
        response.setContentType("text/json");  
        PrintWriter out=response.getWriter();
		
				
		GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting(); 
        Gson gson = builder.create();
		/*------------------------------------------------------------------------*/
		//int max=999999;
		//int min=111111;
		//int b = (int)(Math.random()*(max-min+1)+min);
		//String otp_str=Integer.toString(b);
        //System.out.println("Otp="+otp_str);
		line="";
		UserData user=(UserData)session.getAttribute("user");
		if(user.getSessionId().compareTo(sessionId)==0 && user.getOtp().compareTo(otp)==0 && user.getMobileNo().compareTo(mobileNo)==0)
		{
			line="{\"result\" : \"success\"}";
			session.setAttribute("otpverifieduser",user);
		}
		else
		{
			line="{\"result\" : \"failed\"}";
		}
		// Sending sms to user and accept return value as boolean
		/*------------------------------------------------------------------------ */
		
		System.out.println(line);
		out.println(line);
    }  
}  