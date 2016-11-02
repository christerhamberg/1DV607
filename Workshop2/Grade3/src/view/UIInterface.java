package view;

public interface UIInterface {
	
	// show main menu
	public void showMainMenuNotLoggedIn ();
	public void showMainMenuLoggedIn ();
	
	// menu options
    public boolean isLogin (int selection);
    public boolean isCreateMember (int selection);
    public boolean isRemoveMember (int selection);   
    public boolean isUpdateMember (int selection);
    public boolean isDisplayMember (int selection);   
    public boolean isComapctList (int selection);    
    public boolean isVerboseList (int selection);    
    public boolean isCreateBoat (int selection);    
    public boolean isRemoveBoat (int selection);    
    public boolean isUpdateBoat (int selection);    
    public boolean isSearch (int selection);
    public boolean isQuit (int selection);
    
    // searchString 
    public void displayIncorrectSearchString (String searchString);

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
	public void displayVerboseObject (String name,long socSecId, int id);
	public void displayVerboseObjectBoat (int id1,int id2, String type, double length);
	public void displayVerboseObjectBoat (int id1, String type, double length);

	// show one member heading
	public void displaySingleMemberHeading();
	public void displayMemberObject (int id, String name, long socSecId, int noOfBoats);
	
	// select option from menu
	public int selectMenu ();
	
	// get data
	String getMemberName ();
	String getSocialSecId ();	
	String getBoatLength ();

	// get authentication data
	String getUserId ();
	String getUserPassword ();
	String getSearchString ();
	
	// Search results
	public void searchResultStart ();
	public void searchResultEnd ();
	

}
