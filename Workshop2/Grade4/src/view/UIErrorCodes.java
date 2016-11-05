package view;

import java.util.Hashtable;

public class UIErrorCodes implements UIErrorCodesInterface{
	
	
	private int ECODE100 = 100;  // Can not convert date of birth
	private int ECODE101 = 101;  // Can not allocate SystemId
	private int ECODE102 = 102;  // Failed to store new Member to file 
	private int ECODE103 = 103;  // Can not convert length of the boat 
	private int ECODE104 = 104;  // Failed to store boat 
	private int ECODE105 = 105;  // Failed to update boat 
	private int ECODE106 = 106;  // Incorrect Social Security Number 

	
	private int ECODE900 = 900;  // Faulty Member Selection
	private int ECODE920 = 920;  // Faulty boat selection
	private int ECODE921 = 921;  // No boats assigned to member

	
    // User Name or Password	
	private int ECODE1000 = 1000; // Incorrect Username or Password
	
	private int ECODE9000 = 9000; // Social Security ID YEAR incorrect
	private int ECODE9001 = 9001; // Social Security ID MONTH incorrect
	private int ECODE9002 = 9002; // Social Security ID DAY incorrect
	private int ECODE9003 = 9003; // Social Security ID incorrect length
	private int ECODE9004 = 9004; // Social Security ID incorrect input
	

	
	
	Hashtable <Integer,String> ecodes = new Hashtable <Integer,String> (); 

	UIErrorCodes (){
		setupAllKnownErrorCodes ();
	}
	
	private void setupAllKnownErrorCodes(){
				
		ecodes.put(ECODE100, "Can not convert date of birth");
		ecodes.put(ECODE101, "Can not allocate SystemId");
		ecodes.put(ECODE102, "Failed to store new Member to file");
		ecodes.put(ECODE103, "Can not convert length of the boat");
		ecodes.put(ECODE104, "Failed to store boat");
		ecodes.put(ECODE105, "Failed to update boat ");
		ecodes.put(ECODE106, "Incorrect Social Security Number");

		ecodes.put(ECODE900, "Faulty Member Selection");
		ecodes.put(ECODE920, "Faulty boat selection");
		ecodes.put(ECODE921, "No boats assigned to member");
		
		ecodes.put(ECODE1000, "Incorrect Username or Password");

		ecodes.put(ECODE9000, "Social Security ID YEAR incorrect");
		ecodes.put(ECODE9001, "Social Security ID MONTH incorrect");
		ecodes.put(ECODE9002, "Social Security ID DAY incorrect");
		ecodes.put(ECODE9003, "Social Security ID incorrect length");
		ecodes.put(ECODE9004, "Social Security ID incorrect input");
		
	}
	
	public String getErrorText (int ecode){
		
		if (ecodes.containsKey(ecode) == true) return ecodes.get(ecode);
		else return "Oh dear!!! I'm missing mapping for Error code " + ecode;
		
	}
	

}
