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
  
@WebServlet("/sendotp")  
public class  SendOtpServlet extends HttpServlet 
{  
    private static final long serialVersionUID = 1L;  
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                            throws ServletException, IOException { 
        /*    For getting Parameter         */
		System.out.println("-------------------------------------------------------------");
		String mobileNo="";
		
		HttpSession session=request.getSession(true);
		System.out.println("Session id in SendOtpServlet ="+session.getId());
		
		StringBuilder sb = new StringBuilder();
        System.out.println("In side  SendOtpServlet.java ...........");
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
	    System.out.println("Client Side JSON String in SendOtpServlet.java ="+json_string);
		
		
		JsonParser  parser=new  JsonParser();
		JsonElement  jsonElement=parser.parse(json_string);
		JsonObject   rootElement=jsonElement.getAsJsonObject();
		
	    mobileNo= rootElement.get("mobileNo").getAsString();
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
		int max=999999;
		int min=111111;
		int b = (int)(Math.random()*(max-min+1)+min);
		String otp_str=Integer.toString(b);
        System.out.println("Otp="+otp_str);
		
		
		// Sending sms to user and accept return value as boolean
		/*------------------------------------------------------------------------ */
		
		if( true ){
			UserData user=new UserData();
			String session_id=session.getId();
			user.setSessionId(session_id);
			user.setMobileNo(mobileNo);
			user.setOtp(otp_str);
			session.setAttribute("user",user);
			System.out.println("------------code for authentication end");
			String user_str=gson.toJson(user);
			System.out.println(user_str);
			out.println(user_str);
		}
    }  
}  