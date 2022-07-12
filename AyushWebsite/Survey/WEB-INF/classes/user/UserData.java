package   user;

public class  UserData
{
	private  String  sessionId;
	public   String  getSessionId()
	{
		return sessionId;
	}
	public  void  setSessionId(String sessionId)
	{
		this.sessionId=sessionId;
	}
	
	private  String  mobileNo;
	public  String  getMobileNo()
	{
		return mobileNo;
	}
	public  void  setMobileNo(String mobileNo)
	{
		this.mobileNo=mobileNo;
	}
	
	private  String  otp;
	public  String  getOtp()
	{
		return otp;
	}
	public  void   setOtp(String otp)
	{
		this.otp=otp;
	}
}