package view;

import java.util.Scanner;

public class UI implements UIInterface{
	
	private Scanner sc ;
	
	private int MEMBER_SPACE_NAME = 10;
	private int MEMBER_SPACE_NOOFBOATS = 40;
	private int MEMBER_SPACE_SOCIALSECID = 50;
	
	private int BOAT_SPACE_TYPE = 10;
	private int BOAT_SPACE_LENGTH = 30;
	
	public UI (){
		sc = new Scanner (System.in);
	}
	
	public void showMainMenuNotLoggedIn (){
		
		System.out.print("\nMain Menu");
		System.out.print("\n--------------------------------------------------");
		System.out.print("\n0. Login");
		System.out.print("\n4. Display Member");
		System.out.print("\n5. Display Compact List");
		System.out.print("\n6. Display Verbose List");
		System.out.print("\n--------------------------------------------------");
		System.out.print("\ns. Search");
		System.out.print("\n--------------------------------------------------");
		System.out.print("\nq. Quit");
		System.out.print("\nSelect Option : ");
			
	}
	 
	public void showMainMenuLoggedIn (){
		
		System.out.print("\nMain Menu");
		System.out.print("\n--------------------------------------------------");
		System.out.print("\n1. Create Member");
		System.out.print("\n2. Remove Member");
		System.out.print("\n3. Update Member");
		System.out.print("\n4. Display Member");
		System.out.print("\n5. Display Compact List");
		System.out.print("\n6. Display Verbose List");
		System.out.print("\n--------------------------------------------------");
		System.out.print("\n7. Create boat");
		System.out.print("\n8. Remove boat");
		System.out.print("\n9. Update boat");
		System.out.print("\n--------------------------------------------------");
		System.out.print("\ns. Search");
		System.out.print("\n--------------------------------------------------");
		System.out.print("\nq. Quit");
		System.out.print("\nSelect Option : ");
			
	}
	
	public void displayMemberMenuUpdate (){
		System.out.print("\nSelect Member to update or 'q' to quit : ");
	}
	
	public void displayMemberMenuRemove (){
		System.out.print("\nSelect Member to remove or 'q' to quit : ");
	}
	
	public void displayMemberMenuDisplay (){
		System.out.print("\nSelect Member to display or 'q' to quit : ");
	}
	
	public void displayMemberMenuCreateBoat (){
		System.out.print("\nSelect Member whom to register a new boat : ");
	}
	public void displayMemberMenuBoatHeading (){
		System.out.print("\nSelect from the following boat type(s) : ");
	}
	public void displayMemberMenuBoat (int id, String boatType){
		System.out.print("\n"+id +" : "+boatType);
	}	
	public void displayMemberMenuBoatFooter (){
		System.out.print("\nSelect boat Type : ");
	}
	
	public void exitUI (){
		
		System.out.print("\nThank You for playing with this application, have a nice day!");
		
		sc.close ();
		
	}
	
	public int selectMenu (){
		
		String selection = getKbSelection();

		if (selection.compareToIgnoreCase("q") == 0) return Character.getNumericValue('q');
		else if (selection.compareToIgnoreCase("s") == 0) return Character.getNumericValue('s');
		else{
			try {
				return Integer.parseInt (selection);
			}
			catch (NumberFormatException e){
				return 0;
			}
			
		}
		
	}
	
	
	public String getMemberName (){
		
		System.out.print ("\nPlease enter Member's Name : ");
		return getKbSelection();
		
	}
	
	
	public String getSocialSecId (){
		
		System.out.print ("\nPlease enter Member's Social Security ID (format YYYYmmDDnnnn) : ");
		
		String socialSecID = getKbSelection();
		
		if (validateSocialSecurityId (socialSecID) == true){
			return socialSecID;
		}
		else {
			System.out.print ("\nFaulty Social Security ID : " +socialSecID + " please try again");
		    return getSocialSecId();
		}
		
	}
	
	private boolean validateSocialSecurityId (String socialSecID){
		
		// Check that the string is of correct length = 12 digits
		if (socialSecID.length() != 12) return false;
		
		// Check that the string is only digits
		try {
		    Long.parseLong(socialSecID);
		}
		catch (NumberFormatException e){
			return false;
		}
				
		// Check that year is <> Old people should not be on the sea :) Limit is 1900
		int year = Integer.parseInt(socialSecID.substring(0, 4));
		if (year<1900 || year>2099) return false;
		
		// Check that month is <>
		int month = Integer.parseInt(socialSecID.substring(4,6));
		if (month<1 || month>12) return false;
		
		// Check that day is <>
		int day = Integer.parseInt(socialSecID.substring(6,8));
		if (day<1 || day>31) return false;
		if (month == 4 || month == 6 || month == 9 || month == 11){
			if (day>30) return false;
		}
		else if (month == 2){
			if (day>29) return false;
			if (day>28){
				if (year % 4 != 0) return false;         // not a leap year
				else if (year % 400 == 0) ;              // leap year 29 is okay
				else if (year % 100 == 0) return false;  // not a leap year
				else ;                                   // leap year 29 is okay
			}
		}

		return true;
		
	}
	
	
	public String getBoatLength (){
		
		System.out.print ("\nPlease enter Length of the boat : ");
		return getKbSelection();
		
	}
	
	public String getUserId (){
		
		System.out.print ("\nPlease enter User ID       : ");
		return getKbSelection();
		
	}
	
	public String getUserPassword (){
		
		System.out.print ("\nPlease enter User Password : ");
		return getKbSelection();
		
	}
	
	public String getSearchString (){
		
		System.out.print ("\nValid search options name=xxx*, age=<>YYYY, bornmonth=MM, boattype=TYPE");
		System.out.print ("\nExample 1 : name=and* finds anders,andreas,...");
		System.out.print ("\nExample 2 : bornmonth=mm, bornmonth=12 finds users born in December");
		System.out.print ("\nExample 3 : boattype=xxxx, boattype=sailboat finds users with sailboats");

		System.out.print("\nEnter search pattern : ");
		
		return getKbSelection();
		
	}
	
	public void displayIncorrectSearchString (String searchString){
		
		System.out.print("\nIncorrect Search String : "+searchString +" please correct and try again ");
		
	}

	public void displayOpResult (int ecode){
		
		if (ecode == 0) System.out.print("\nOperation was successfully completed!");
		else System.out.print("\nOperation failed! Cryptic error code is : "+ecode);
		
	}
	
	public void displayOpExit (){
		
		System.out.print("\nYou selected to exit the operation");
		
	}
	

	
	public void displaySingleMemberHeading (){
		String writeString = "\nID";
		while (MEMBER_SPACE_NAME> writeString.length()) writeString = writeString + " ";			
		writeString = writeString + " NAME";
		while (MEMBER_SPACE_NOOFBOATS> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " BOATS";
		
		while (MEMBER_SPACE_SOCIALSECID> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " SOCIAL SECURITY ID";
		
		System.out.print(writeString);
		
	    writeString = "\n  Boat ID";
		while (BOAT_SPACE_TYPE> writeString.length()) writeString = writeString + " ";			
		writeString = writeString + " Boat type";
		while (BOAT_SPACE_LENGTH> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " Length";
		
		System.out.print(writeString);
	}
	
	public void displayCompactObjectHeading (){
		System.out.print("\nCompact List of all users");
		
		String writeString = "\nID";
		while (MEMBER_SPACE_NAME> writeString.length()) writeString = writeString + " ";			
		writeString = writeString + " NAME";
		while (MEMBER_SPACE_NOOFBOATS> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " NUMBER OF BOATS";
		
		System.out.print (writeString);
		
	}
	
	public void displayMemberObject (int id, String name, long socSecId, int noOfBoats){
		
		String writeString = "\n" +id;
		while (MEMBER_SPACE_NAME> writeString.length()) writeString = writeString + " ";			
		writeString = writeString + " " + name;
		while (MEMBER_SPACE_NOOFBOATS> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " " + noOfBoats;
		
		while (MEMBER_SPACE_SOCIALSECID> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " " + socSecId;
		
		System.out.print(writeString);
		
	}

	
	public void displayCompactObject (String name, int id, int boats){
		
		String writeString = "\n" +id;
		while (MEMBER_SPACE_NAME> writeString.length()) writeString = writeString + " ";			
		writeString = writeString + " " + name;
		while (MEMBER_SPACE_NOOFBOATS> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " " + boats;
		
		System.out.print(writeString);
	
	}

	public void displayVerboseObjectHeading (){
		System.out.print("\nVerbose List of all users");
		
		String writeString = "\nID";
		while (MEMBER_SPACE_NAME> writeString.length()) writeString = writeString + " ";			
		writeString = writeString + " NAME";
		while (MEMBER_SPACE_NOOFBOATS> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " SOCIAL SECURITY ID";
		
		System.out.print(writeString);
		
	    writeString = "\n  Boat ID";
		while (BOAT_SPACE_TYPE> writeString.length()) writeString = writeString + " ";			
		writeString = writeString + " Boat type";
		while (BOAT_SPACE_LENGTH> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " Length";
		
		System.out.print(writeString);

	}
	
	public void displayVerboseObject (String name,long socSecId, int id){
		
		String writeString = "\n" +id;
		while (MEMBER_SPACE_NAME> writeString.length()) writeString = writeString + " ";			
		writeString = writeString + " " + name;
		while (MEMBER_SPACE_NOOFBOATS> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " " + socSecId;

		System.out.print(writeString);

	}
	
	public void displayVerboseObjectBoat (int boatId1, int boatId2, String boatType, double boatLength){

		String writeString = "\n  " +boatId1 +"_" +boatId2;
		while (BOAT_SPACE_TYPE> writeString.length()) writeString = writeString + " ";			
		writeString = writeString + " " + boatType;
		while (BOAT_SPACE_LENGTH> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " " + boatLength;

		System.out.print(writeString);
		
	}
	public void displayVerboseObjectBoat (int boatId, String boatType, double boatLength){
		
		String writeString = "\n" +boatId;
		while (BOAT_SPACE_TYPE> writeString.length()) writeString = writeString + " ";			
		writeString = writeString + " " + boatType;
		while (BOAT_SPACE_LENGTH> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " " + boatLength;

		System.out.print(writeString);
		
	}
	
	public void displayMemberMenuUpdateBoat (){
		
		System.out.print("\nSelect the member owning the boat to be update or 'q' to quit : ");
		
	}
	public void displayBoatMenuUpdateBoat (){
		
		System.out.print("\nSelect the boat to be update or 'q' to quit : ");
		
	}
		
	public void displayMemberMenuRemoveBoat (){
		
		System.out.print("\nSelect boat to be removed or 'q' to quit : ");
		
	}
	
	public void displayBoatMenuRemoveBoat (){
		
		System.out.print("\nSelect the boat to be removed or 'q' to quit : ");
		
	}
	
    private String getKbSelection (){
    	
		while (sc.hasNext() == false);
		
		return sc.nextLine();
				
	}
    
    public boolean isLogin (int selection){
    	if (selection == 0) return true;
    	else return false;
    }
    
    public boolean isCreateMember (int selection){
    	if (selection == 1) return true;
    	else return false;
    }

    public boolean isRemoveMember (int selection){
    	if (selection == 2) return true;
    	else return false;
    }
    
    public boolean isUpdateMember (int selection){
    	if (selection == 3) return true;
    	else return false;
    }

    public boolean isDisplayMember (int selection){
    	if (selection == 4) return true;
    	else return false;
    }
    
    public boolean isComapctList (int selection){
    	if (selection == 5) return true;
    	else return false;
    }
    
    public boolean isVerboseList (int selection){
    	if (selection == 6) return true;
    	else return false;
    }
    
    public boolean isCreateBoat (int selection){
    	if (selection == 7) return true;
    	else return false;
    }
    
    public boolean isRemoveBoat (int selection){
    	if (selection == 8) return true;
    	else return false;
    }
    
    public boolean isUpdateBoat (int selection){
    	if (selection == 9) return true;
    	else return false;
    }
    
    public boolean isSearch (int selection){
    	if (selection == Character.getNumericValue('s')) return true;
    	else return false;
    }
    
    public boolean isQuit (int selection){
    	if (selection == Character.getNumericValue('q')) return true;
    	else return false;
    }

	@Override
	public void searchResultStart() {
		
		System.out.print ("\n\n\nSearch Result");
		System.out.print ("\n--------------------------------------");

	}

	@Override
	public void searchResultEnd() {
		
		System.out.print ("\n--------------------------------------\n\n\n");		
		System.out.print ("\nSearch Result End");

	}

	
	
	

}
