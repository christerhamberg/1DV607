package view;

public interface UIInterface {
	
	// show main menu
	public void showMainMenuNotLoggedIn ();
	public void showMainMenuLoggedIn ();

	// show Options
	public void displayCreateMember();
	public void displayRemoveMember();
	public void displayUpdateMember();
	public void displayMember();
	public void displayMemberComapactList();
	public void displayMemberVerboseList();
	public void displayCreateBoat();
	public void displayRemoveBoat();
	public void displayUpdateBoat();
	
	// show selection Options member
	public void displaySelectFilterOrQuit ();
	public void displaySelectMemberOrFilterOrQuit ();
	
	// show selection Options boats
	public void displaySelectBoatType ();
	public void displayBoatSelectionMenu (int id, String boat);
	
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

	// exit message and clean up
	public void exitUI ();

	// show member and boat data
	public void displaySingleMemberHeading();
    public void displaySingleMember(int id,String name, int noOfBoats, long socialSecurityId);
	public void displayBoatHeading ();
	public void displayBoat (int key1, int key2, String boattype, double boatLength);
	public void displayBoat (int key2, String boattype, double boatLength);


	// errors etc
	public void displayOpResult (int ecode);
	public void displayError (int ecode);
	public void displaySelectMemberOrFilter ();

	public void selectBoat ();
	
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
	String getFilterString ();
	
	public void searchResultStart ();
	public void searchResultEnd ();
	
	

	
}
