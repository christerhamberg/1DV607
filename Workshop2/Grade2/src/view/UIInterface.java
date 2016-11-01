package view;

public interface UIInterface {
	
	// show main menu
	public void showMainMenu ();
	
	// show select member menu ();
	public void displayMemberMenuUpdate ();
	public void displayMemberMenuRemove ();
	public void displayMemberMenuDisplay ();

	public void displayMemberMenuCreateBoat ();
	
	// show select boat menu
	public void displayMemberMenuBoatHeading ();
	public void displayMemberMenuBoat (int id, String boatType);
	public void displayMemberMenuBoatFooter ();
	public void displayMemberMenuUpdateBoat ();
	public void displayBoatMenuUpdateBoat ();
	public void displayMemberMenuRemoveBoat ();
	public void displayBoatMenuRemoveBoat ();


	// exit message and clean up
	public void exitUI ();
		
	// show result
	public void displayOpResult (int result);
	public void displayOpExit ();
	
	// show compact Object
	public void displayCompactObjectHeading ();
	public void displayCompactObject (String name,int id, int boats);
	
	// show verbose Object
	public void displayVerboseObjectHeading ();
	public void displayVerboseObject (String name,int socSecId, int id);
	public void displayVerboseObjectBoat (int id1,int id2, String type, double length);
	public void displayVerboseObjectBoat (int id1, String type, double length);

	// show one member heading
	public void displaySingleMemberHeading();
	public void displayMemberObject (int id, String name, int socSecId, int noOfBoats);
	
	// select option from menu
	public int selectMenu ();
	
	// get data
	String getMemberName ();
	String getSocialSecId ();	
	String getBoatLength ();


}
