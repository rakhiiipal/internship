package  authentication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.*;

import com.google.gson.Gson;
import com.google.gson.*;
         
//import com.sun.image.codec.jpeg.JPEGCodec;

@WebServlet("/getsession")  
public class GetSessionServlet extends HttpServlet
{
	private static final long serialVersionUID = -1761346889117186607L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session=request.getSession();
		UserData  user=(UserData)session.getAttribute("otpverifieduser");
		
		System.out.println("SessionId in GetSessionServlet ="+session.getId());
		System.out.println("SessionId in OtpVerifiedUser="+user.getSessionId());
		response.setContentType("text/json");  
        PrintWriter out=response.getWriter();
		
		GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting(); 
        Gson gson = builder.create();
		
		String line=gson.toJson(user);
		
		//line="{\"capcha\" : \""+capcha_str+"\",\"salt_value\":\""+salt_value+"\"}";
		System.out.println(line);
		out.println(line);
	}
}