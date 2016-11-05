package view;

import java.util.Scanner;

public class UI implements UIInterface{
	
	private Scanner sc ;
	
	private UIErrorCodesInterface errorMapper;

	private int MEMBER_SPACE_NAME = 10;
	private int MEMBER_SPACE_NOOFBOATS = 40;
	private int MEMBER_SPACE_SOCIALSECID = 50;
	
	private int BOAT_SPACE_TYPE = 10;
	private int BOAT_SPACE_LENGTH = 30;
	
	private int ECODE9000 = 9000;    // Social Security ID YEAR incorrect
	private int ECODE9001 = 9001;    // Social Security ID MONTH incorrect
	private int ECODE9002 = 9002;    // Social Security ID DAY incorrect
	private int ECODE9003 = 9003;    // Social Security ID incorrect length
	private int ECODE9004 = 9004;    // Social Security ID incorrect input
	
	
	public UI (){
		errorMapper = new UIErrorCodes();
		sc = new Scanner (System.in);
	}
	
	public void showMainMenuNotLoggedIn (){
		
		System.out.print("\n\n\n\n\nMain Menu");
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
		
		System.out.print("\n\n\n\n\nMain Menu");
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
	
	public void displayCreateMember(){
		System.out.print("\nOption : Create a new Member");
	}
	public void displayRemoveMember(){
		System.out.print("\nOption : Remove a Member");
	}	
	public void displayUpdateMember(){
		System.out.print("\nOption : Update a Member");		
	}
	public void displayMember(){
		System.out.print("\nOption : Display a Member");
	}	
	public void displayMemberComapactList(){
		System.out.print("\nOption : Display Compact List");
	}	
	public void displayMemberVerboseList(){
		System.out.print("\nOption : Display a Verbose List");
	}	
	
	public void displayCreateBoat(){
		System.out.print("\nOption : Create a new boat");
	}	
	public void displayRemoveBoat(){
		System.out.print("\nOption : Remove a boat");
	}	
	public void displayUpdateBoat(){
		System.out.print("\nOption : Update a boat");
	}	
	
	public void displaySelectMemberOrFilterOrQuit (){
		System.out.print("\nSelect Member or 'f' to filter the list or 'q' to quit : ");		
	}
	
	public void displaySelectFilterOrQuit (){
		System.out.print("\nSelect 'f' to filter the list or 'q' to quit : ");			
	}
	
	public void displaySelectBoatType (){
		System.out.print("\nSelect from the following boat type(s) : ");
	}
	
	
	
	public void displayMemberMenuBoat (int id, String boatType){
		System.out.print("\n"+id +" : "+boatType);
	}	
	public void displayMemberMenuBoatFooter (){
		System.out.print("\nSelect boat Type : ");
	}

	public void displaySelectMemberOrFilter (){
		System.out.print("\nSelect Member or 'q' to quit or 'f' to first filter the list : ");
	}

	
	public void exitUI (){
		
		System.out.print("\nThank You for playing with this application, have a nice day!");
		
		sc.close ();
		
	}
	
	public int selectMenu (){
		
		String selection = getKbSelection();

		if (selection.compareToIgnoreCase("q") == 0) return Character.getNumericValue('q');
		else if (selection.compareToIgnoreCase("s") == 0) return Character.getNumericValue('s');
		else if (selection.compareToIgnoreCase("f") == 0) return Character.getNumericValue('f');
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
	
	
	public void displayIncorrectSearchString (String searchString){
		
		System.out.print("\nIncorrect Search String : "+searchString +" please correct and try again ");
		
	}

	public void displayOpResult (int ecode){
		
		if (ecode == 0){
			System.out.print("\nOperation was successfully completed!");
		
		    // ADD a one seconds delay
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		else{
			
			String errorString = errorMapper.getErrorText(ecode);
			
			System.err.print("\nOperation failed! "+errorString);
		}
		
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

	}
	
	public void displaySingleMember (int id, String name, int noOfBoats, long socialSecurtyId){
		
		String writeString = "\n" +id;
		while (MEMBER_SPACE_NAME> writeString.length()) writeString = writeString + " ";			
		writeString = writeString + " " +name;
		while (MEMBER_SPACE_NOOFBOATS> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " " +noOfBoats;		
		while (MEMBER_SPACE_SOCIALSECID> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " " +socialSecurtyId;

		System.out.print(writeString);

	}

	public void displayBoatHeading (){
		
	    String writeString = "\n  Boat ID";
		while (BOAT_SPACE_TYPE> writeString.length()) writeString = writeString + " ";			
		writeString = writeString + " Boat type";
		while (BOAT_SPACE_LENGTH> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " Length";
		
		System.out.print(writeString);

	}
	
	public void displayBoat(int key1, int key2, String boattype, double boatLength){
	
	    String writeString = "\n  "+key1 +"_" +key2;
		while (BOAT_SPACE_TYPE> writeString.length()) writeString = writeString + " ";			
		writeString = writeString + " " +boattype;
		while (BOAT_SPACE_LENGTH> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " "+boatLength;
		
		System.out.print(writeString);
		
	}
	
	public void displayBoat(int key2, String boattype, double boatLength){
		
	    String writeString = "\n  "+key2;
		while (BOAT_SPACE_TYPE> writeString.length()) writeString = writeString + " ";			
		writeString = writeString + " " +boattype;
		while (BOAT_SPACE_LENGTH> writeString.length()) writeString = writeString + " ";
		writeString = writeString + " "+boatLength;
		
		System.out.print(writeString);
		
	}	
	
    private String getKbSelection (){
    	
		while (sc.hasNext() == false);
		
		return sc.nextLine();
				
	}
    

	public void displayBoatSelectionMenu (int id, String boatType){
		System.out.print("\n"+id +" : "+boatType);
	}	



	// CONSIDER OVERRIDE FOR DIFFERENT LANGUAGES
	public void displayError (int ecode){
		
		String errorString = errorMapper.getErrorText(ecode);
		System.err.print("\nHandling error detected : "+errorString);		
	
	}

	public String getFilterString (){
		
		System.out.print ("\nValid filter option(s) name=xxx*, age=<>YYYY, bornmonth=MM, boattype=TYPE, AND, OR");
		filterExamples();
		System.out.print("\nEnter filter pattern : ");
		
		return getKbSelection();
		
	}
	

	public String getSearchString (){
		
		System.out.print ("\nValid search pattern(s) name=xxx*, age=<>YYYY, bornmonth=MM, boattype=TYPE, AND, OR");
		filterExamples();
		System.out.print("\nEnter search pattern : ");
		
		return getKbSelection();
		
	}
	
	public void selectBoat (){
		System.out.print("\nSelect boat : ");
	}
	
	private void filterExamples (){
		
		System.out.print ("\nExample 1 : name=and* finds anders,andreas,...");
		System.out.print ("\nExample 2 : bornmonth=mm, bornmonth=12 finds users born in December");
		System.out.print ("\nExample 3 : boattype=xxxx, boattype=sailboat finds users with sailboats");
		System.out.print ("\nExample 4 : name=jenny or name=jennu and bornmonth=6");
		System.out.print ("\nExample 5 : name=ka or bornmonth=6");
		System.out.print ("\nExample 6 : name=je and bornmonth=6");
		System.out.print ("\nOr 'q' to quit");
		
	}
	
	public void searchResultStart() {
		
		System.out.print ("\n\n\nSearch Result");
		System.out.print ("\n----------------------------------------------------------------------");		

	}

	public void searchResultEnd() {
		
		System.out.print ("\n----------------------------------------------------------------------");		
		System.out.print ("\nSearch Result End\n\n\n");

	}
	
	
	
	private boolean validateSocialSecurityId (String socialSecID){
		
		// Check that the string is of correct length = 12 digits
		if (socialSecID.length() != 12){
			displayError (ECODE9003);
			return false;
		}
		
		// Check that the string is only digits
		try {
		    Long.parseLong(socialSecID);
		}
		catch (NumberFormatException e){
			displayError (ECODE9004);
			return false;
		}
				
		// Check that year is <> Old people should not be on the sea :) Limit is 1900
		int year = Integer.parseInt(socialSecID.substring(0, 4));
		if (year<1900 || year>2099){
			displayError (ECODE9000);
			return false;
		}
		
		// Check that month is <>
		int month = Integer.parseInt(socialSecID.substring(4,6));
		if (month<1 || month>12){
			displayError (ECODE9001);
			return false;
		}
		
		// Check that day is <>
		int day = Integer.parseInt(socialSecID.substring(6,8));
		if (day<1 || day>31){
			displayError (ECODE9002);
			return false;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11){
			if (day>30){
				displayError (ECODE9002);
				return false;
			}
		}
		else if (month == 2){
			if (day>29){
				displayError (ECODE9002);
				return false;
			}
			if (day>28){
				if (year % 4 != 0){
					displayError (ECODE9002);
					return false;         // not a leap year
				}
				else if (year % 400 == 0) ;              // leap year 29 is okay
				else if (year % 100 == 0){
					displayError (ECODE9002);
					return false;  // not a leap year
				}
				else ;                                   // leap year 29 is okay
			}
		}

		return true;
		
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
    
    // DEFAULT FOR ENGLISH CONSIDER OVERRIDE FOR OTHER LANGUAGES
    public boolean isSearch (int selection){
    	if (selection == Character.getNumericValue('s')) return true;
    	else return false;
    }
    
    // DEFAULT FOR ENGLISH CONSIDER OVERRIDE FOR OTHER LANGUAGES
    public boolean isQuit (int selection){
    	if (selection == Character.getNumericValue('q')) return true;
    	else return false;
    }
	
}
