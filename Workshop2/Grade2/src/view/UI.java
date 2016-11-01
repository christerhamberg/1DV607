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
	 
	public void showMainMenu (){
		
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

		if (selection.compareToIgnoreCase("q") == 0) return -1;
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
		
		System.out.print ("\nPlease enter Member's Social Security ID (date of birth) : ");
		return getKbSelection();
		
	}
	
	public String getBoatLength (){
		
		System.out.print ("\nPlease enter Length of the boat : ");
		return getKbSelection();
		
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
	
	public void displayMemberObject (int id, String name, int socSecId, int noOfBoats){
		
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
	
	public void displayVerboseObject (String name,int socSecId, int id){
		
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


	
	
	
	

}
