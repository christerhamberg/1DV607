package model.authentication;

import model.MemberFacadeInterface;

public class Authentication {
	
	private static Authentication ownInstance = null;
	
	private String MY_SECRET = "secret";
	
	public static Authentication getInstance (){
		if (ownInstance == null) ownInstance = new Authentication ();
		return ownInstance;
	}
	
	public boolean validateUser(MemberFacadeInterface memFacade, String userId, String password){
		
		// convert userId to int
		
		int id = -1;
		
		try {
		    id = Integer.parseInt(userId);
		}
		catch (NumberFormatException e){
			return false;
		}
		
		// NOTE! A SUPER USER IS NEEDED TO SETUP THE FIRST MEMBER ETC.
		
		if (id == 0){
			if (password.compareTo("super") == 0) return true;
			else return false;
		}
		
		
		// check if it is a valid userId
		
		if (memFacade.isMemberKeyValid(id) == false) return false;
		
		// check if password is valid
		
		String pwd = MY_SECRET + id;
		
		if (password.compareTo(pwd) != 0) return false;
		
		// PASSWORD is OKAY
		return true;
		
	}

}
