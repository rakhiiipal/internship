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
  
@WebServlet("/savesurveyformdata")  
public class  SaveSurveyFormDataServlet extends HttpServlet 
{  
    private static final long serialVersionUID = 1L;  
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                            throws ServletException, IOException { 
        /*    For getting Parameter         */
		System.out.println("-------------------------------------------------------------");
		String sessionId="";
		String name="";
		int age=0;
		String gender="";
		String mobileNo="";
		String professional_qualification="";
		String ddc="";
		int  yop=0;
		String registrationNo="";
		String designation="";
		int yoe=0;
		String hphc="";
		String opdradio="";
		String ipdradio="";
		String mmtradio="";
		int noofas=0;
		int participationinipd=0;
		int participationinopd=0;
		String salaryType="";
		int increment=0;
		int grossSalary=0;
		String nonPracticingAllowance="";
		
		String line="";
		
		HttpSession session=request.getSession(true);
		
		System.out.println("Session id in SaveSurveyFormDataServlet ="+session.getId());
		String sessionId_current=session.getId();
		
		StringBuilder sb = new StringBuilder();
        System.out.println("In side   SaveSurveyFormDataServlet.java ...........");
        BufferedReader reader = request.getReader();
		line="";
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
	    System.out.println("Client Side JSON String in SaveSurveyFormDataServlet.java ="+json_string);
		
		
		JsonParser  parser=new  JsonParser();
		JsonElement  jsonElement=parser.parse(json_string);
		JsonObject   rootElement=jsonElement.getAsJsonObject();
		
	    //mobileNo= rootElement.get("mobileNo").getAsString();
		//otp=rootElement.get("otp").getAsString();
		
		sessionId=rootElement.get("sessionId").getAsString();
		name=rootElement.get("name").getAsString();
		age=rootElement.get("age").getAsInt();
		gender=rootElement.get("gender").getAsString();
		mobileNo=rootElement.get("mobileNo").getAsString();
		professional_qualification=rootElement.get("professional_qualification").getAsString();
		ddc=rootElement.get("ddc").getAsString();
		yop=rootElement.get("yop").getAsInt();
		registrationNo=rootElement.get("registrationNo").getAsString();
		designation=rootElement.get("designation").getAsString();
		yoe=rootElement.get("yoe").getAsInt();
		hphc=rootElement.get("hphc").getAsString();
		opdradio=rootElement.get("opdradio").getAsString();
		ipdradio=rootElement.get("ipdradio").getAsString();
		mmtradio=rootElement.get("mmtradio").getAsString();
		noofas=rootElement.get("noofas").getAsInt();
		participationinipd=rootElement.get("participationinipd").getAsInt();
		participationinopd=rootElement.get("participationinopd").getAsInt();
		salaryType=rootElement.get("salaryType").getAsString();
		increment=rootElement.get("increment").getAsInt();
		grossSalary=rootElement.get("grossSalary").getAsInt();
		nonPracticingAllowance=rootElement.get("nonPracticingAllowance").getAsString();
		}
		catch(Exception ex)
	    {
			System.out.println(ex);
		}
        line="";
		/*---------------------------------- */
        try
		{
        response.setContentType("text/json");  
        PrintWriter out=response.getWriter();
		/*--------------------------Database Loading ---------------------------- */
		if( sessionId_current.compareTo(sessionId)==0 ){
		
			Connection con=new ConnectionClass().getConnection();
			String query="insert into survey_data ( name,age,gender,mobileNo,professional_qualification,ddc,yop,registrationNo,designation,yoe,hphc,opdradio, ipdradio,mmtradio,noofas,participationinipd,participationinopd,salaryType,increment,grossSalary,nonPracticingAllowance) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setString(1,name);
			pstmt.setInt(2,age);
			pstmt.setString(3,gender);
			pstmt.setString(4,mobileNo);
			pstmt.setString(5,professional_qualification);
			pstmt.setString(6,ddc);
			pstmt.setInt(7,yop);
			pstmt.setString(8,registrationNo);
			pstmt.setString(9,designation);
			pstmt.setInt(10,yoe);
			pstmt.setString(11,hphc);
			pstmt.setString(12,opdradio);
			pstmt.setString(13,ipdradio);
			pstmt.setString(14,mmtradio);
			pstmt.setInt(15,noofas);
			pstmt.setInt(16,participationinipd);
			pstmt.setInt(17,participationinopd);
			pstmt.setString(18,salaryType);
			pstmt.setInt(19,increment);
			pstmt.setInt(20,grossSalary);
			pstmt.setString(21,nonPracticingAllowance);
			
			int x=pstmt.executeUpdate();
			
			
		GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting(); 
        Gson gson = builder.create();
		/*------------------------------------------------------------------------*/
			if( x==1 )
			{
				line="{\"result\" : \"success\"}";
				
			}
			else
			{
				line="{\"result\" : \"failed\"}";
			}
		}
		else
		{
			line="{\"result\" : \"failed\"}";
		}
		System.out.println(line);
		out.println(line);
		}
		catch(Exception ex){System.out.println(ex);}
    }  
}  