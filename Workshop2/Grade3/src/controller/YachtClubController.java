package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import model.Boat;
import model.Member;
import model.MemberFacadeInterface;
import model.authentication.Authentication;
import searchfilter.SearchFilterFacade;
import searchfilter.SearchStringValidatorExtractor;
import view.UIInterface;

public class YachtClubController {
	
	private int ECODE900 = 900;  // Faulty Member Selection
	private int ECODE920 = 920;  // Faulty boat selection
	private int ECODE921 = 921;  // No boats assigned to member
	
	private boolean loggedin = false;

	public void startProg (UIInterface ui, MemberFacadeInterface memFacade){
			
		int value = 0;
		
		while (value != Character.getNumericValue('q')){
			
			if (loggedin == false) ui.showMainMenuNotLoggedIn ();
			else ui.showMainMenuLoggedIn ();

			value = ui.selectMenu();
						
			if (ui.isLogin(value) == true) useCaseLogin (ui, memFacade);
			// must be logged in 
			if (loggedin == true){
			    if (ui.isCreateMember(value) == true) useCaseCreateNewMember (ui, memFacade);
			    else if (ui.isRemoveMember(value) == true) useCaseRemoveMember (ui, memFacade);
			    else if (ui.isUpdateMember(value) == true) useCaseUpdateMember (ui, memFacade);
			    else if (ui.isCreateBoat(value) == true) useCaseCreateBoat (ui, memFacade);
			    else if (ui.isRemoveBoat(value) == true) useCaseRemoveBoat (ui, memFacade);
			    else if (ui.isUpdateBoat(value) == true) useCaseUpdateBoat (ui, memFacade);
			}
		    if (ui.isDisplayMember(value) == true) useCaseDisplaySingleMember (ui, memFacade); 			
			if (ui.isComapctList(value) == true) useCaseDisplayCompactList (ui, memFacade);
			if (ui.isVerboseList(value) == true) useCaseDisplayVerboseList (ui, memFacade);
			if (ui.isSearch(value) == true) useCaseSearch (ui, memFacade);
			if (ui.isQuit(value) == true) ;

		}
		
		ui.exitUI ();
		
	}
	
	private void useCaseCreateNewMember (UIInterface ui, MemberFacadeInterface memFacade){
		
		// 1. Get the New Members Name
	
		String name = ui.getMemberName ();
		
		// 2. Get the New Members Social Security ID (date of birth is used in this case)
				
		String socSecId = ui.getSocialSecId ();
		
		// 3. Store the new Member
		
		int result = memFacade.storeNewMember (name,socSecId);
		ui.displayOpResult (result);
		
	}
	
	
	private void useCaseRemoveMember (UIInterface ui, MemberFacadeInterface memFacade){			
			
		// 1. List all members
			
		useCaseDisplayCompactList (ui,memFacade);
			
		// 2. Display Select Member (Remove)
			
		ui.displayMemberMenuRemove();
			
		// 3. Select member 
			
		int key = ui.selectMenu();
		
		if (key != Character.getNumericValue('q')){ 
	        	
			// 4. Check if the key is valid
	        	
			if (memFacade.isMemberKeyValid(key) == true){
	        		
				// 5. Remove the member
	        		
        		int result = memFacade.removeMember(key);
        		ui.displayOpResult (result);	
			}
	        	
			else ui.displayOpResult (ECODE900);
	               
		}
	        
		else ui.displayOpExit ();  // exit was selected
		
	}
	
	private void useCaseUpdateMember (UIInterface ui, MemberFacadeInterface memFacade){
		
		// 1. List all members
		
		useCaseDisplayCompactList (ui,memFacade);
		
		// 2. Display Select Member (Update)
		
		ui.displayMemberMenuUpdate();
		
		// 3. Select member 
		
		int key = ui.selectMenu();
        if (key != Character.getNumericValue('q')){ 
        	
        	// 4. Check if the key is valid
        	
        	if (memFacade.isMemberKeyValid(key) == true){
        		
            	// 5. Update member's Data (Name)
        		String name = ui.getMemberName ();

            	// 6. Update member's Data (Personal Id)
        		String socSecId = ui.getSocialSecId ();

            	// 7. Update Member Object and update the file
        		
        		int result = memFacade.updateMember(key, name, socSecId);
        		ui.displayOpResult (result);	
        		
        	}
        	else ui.displayOpResult (ECODE900);
        
        }
        else ui.displayOpExit ();  // exit was selected
		
	}
	
	private void useCaseDisplaySingleMember (UIInterface ui, MemberFacadeInterface memFacade){
		
		// 1. List all members
		
		useCaseDisplayCompactList (ui,memFacade);
		
		// 2. Display Select Member (Update)
		
		ui.displayMemberMenuDisplayFilter();
		
		// 3. Select member 
		
		int key = ui.selectMenu();

		if (key != Character.getNumericValue('q')){ 
        	
        	// ADD FILTER OPTION
        	if (key == Character.getNumericValue('f')){
                		
        		// Display search options
        		String searchString = ui.getFilterString();
        		        		
        		if (searchString == null) key = -1;
        		else if (searchString.compareToIgnoreCase("q") == 0) key = -1;
        		else {
        			        	        			
        			SearchFilterFacade validateString = new  SearchFilterFacade();
        			if (validateString.validateSearchString (searchString) == false) ui.displayIncorrectSearchString (searchString);

        			else {
        				SearchFilterFacade searchMain = new SearchFilterFacade();
        				
        				ArrayList <Member> searchResult = searchMain.filterResult (memFacade, searchString);
        			        				
            			if (searchResult == null) key = -1;
            			else if (searchResult.isEmpty() == true) key = -1;
            			else {
            				for (int loopMe=0;searchResult.size()>loopMe;loopMe++){
            				
            					Member mb = searchResult.get(loopMe);
            				
            			   		int id = mb.getId();
            			   		String name = mb.getName();
            			   		long socialId = mb.getPersonalNumber(); 
            			   		int noOfBoats = mb.getNoOfBoats();
            				
            			   		ui.displayMemberObject(id,name,socialId,noOfBoats);
            				
               				}
            				
            				ui.displayMemberMenuDisplay();
            				key = ui.selectMenu();

            				if (key == Character.getNumericValue('q')) key = -1;
            				else {
            					boolean validated = false;
            					for (int loopMe=0;searchResult.size()>loopMe;loopMe++){
            						Member mb = searchResult.get(loopMe);
                				
            			   			int id = mb.getId();
            			   		
            			   			if (key == id){
            			   				validated = true;
            			   				break;
            			   			}
            				
            					}
            					            				
            					if (validated == false) key = -1;
            					
            				}
            			}

        			}
        		}

          	}
        	
            if (key != -1){ 

        
            	// 4. Check if the key is valid
        	
            	if (memFacade.isMemberKeyValid(key) == true){
        		
        			// 5. Display Data for the Member
        		
        			ui.displaySingleMemberHeading();
        		
    				ui.displayMemberObject(memFacade.getMemberId (key), memFacade.getMemberName (key),  memFacade.getMemberPersonalNumber (key), memFacade.getMemberNoOfBoats(key));

        			// 6. Get number of owned boats
        		
        			int boats = memFacade.getMemberNoOfBoats(key);
        		
        			for (int loopMe = 1;boats>=loopMe;loopMe++){
        			
    					ui.displayVerboseObjectBoat (key,loopMe,memFacade.getMemberBoatType (key,loopMe),memFacade.getMemberBoatLength (key,loopMe));
        			
        			}
        		        		
        		}
        		else ui.displayOpResult (ECODE900);
            }
        
        }
        else ui.displayOpExit ();  // exit was selected
		
	}

	
	
	
	
	private void useCaseDisplayCompactList (UIInterface ui, MemberFacadeInterface memFacade){
		
		// 1. Get all hashKeys
		
		Set <Integer> hashKeys = memFacade.getHashKeysToAllMembers ();
		Iterator<Integer> iter = hashKeys.iterator();
		
		// 2. Display Heading
		
		ui.displayCompactObjectHeading();
		
		// 3. Display all Objects
		
		while (iter.hasNext() == true){
			
			int key = iter.next();
			
			ui.displayCompactObject(memFacade.getMemberName (key), memFacade.getMemberId (key), memFacade.getMemberNoOfBoats (key));
			
		}
		
	}
	
	private void useCaseDisplayVerboseList (UIInterface ui, MemberFacadeInterface memFacade){
		
		// 1. Get all hashKeys
		
		Set <Integer> hashKeys = memFacade.getHashKeysToAllMembers ();
		Iterator<Integer> iter = hashKeys.iterator();
		
		// 2. Display Heading
		
		ui.displayVerboseObjectHeading();
		
		// 3. Display all Objects
		
		while (iter.hasNext() == true){
			
			int key = iter.next();
			
			int noOfBoats = memFacade.getMemberNoOfBoats (key);
			
			ui.displayVerboseObject(memFacade.getMemberName (key), memFacade.getMemberPersonalNumber (key), key);

			for (int loopMe = 1; noOfBoats>=loopMe;loopMe++){
				
				ui.displayVerboseObjectBoat (key,loopMe,memFacade.getMemberBoatType (key,loopMe),memFacade.getMemberBoatLength (key,loopMe));
				
			}
			
		}
		
	}
	
	
	private void useCaseCreateBoat (UIInterface ui, MemberFacadeInterface memFacade){
		
		// 1. List all members
		
		useCaseDisplayCompactList (ui,memFacade);
		
		// 2. Display Select Member to whom the boat is to be registered
		
		ui.displayMemberMenuCreateBoat();
		
		// 3. Select member 
		
		int key = ui.selectMenu();
        if (key != Character.getNumericValue('q')){ 
        	
        	// 4. Check if the key is valid
        	
        	if (memFacade.isMemberKeyValid(key) == true){
        		
        		// 5. Get all valid boat types
        		
        		String [] types = memFacade.getValidBoatTypes ();
        		
        		// 6. Display select Boat Type menu heading
        		
        		ui.displayMemberMenuBoatHeading();
        		
        		// 7. Display boat type
        		for (int loopMe = 0; types.length>loopMe;loopMe++) ui.displayMemberMenuBoat((loopMe+1), types[loopMe]);

        		ui.displayMemberMenuBoatFooter();

        		// 8. select boat type
        		
        		int value = ui.selectMenu();
        		
        		if (value >=1 && value <= types.length){
        			
                	// 9. Get boat length
            		String length = ui.getBoatLength ();
            		
                	// 10. Store New Boat
            		
            		int result = memFacade.storeBoat(key, types[value-1], length);
            		ui.displayOpResult (result);	
        			
        		}
            	else ui.displayOpResult (ECODE920);
        		
        	}
        	else ui.displayOpResult (ECODE900);
        
        }
        else ui.displayOpExit ();  // exit was selected
		
	}
	
	private void useCaseRemoveBoat (UIInterface ui, MemberFacadeInterface memFacade){
		
		// 1. List all members
		
		useCaseDisplayVerboseList (ui,memFacade);
		
		// 2. Display Select Member owning the boat to be updated
		
		ui.displayMemberMenuRemoveBoat();
		
		// 3. Select member 
		
		int key = ui.selectMenu();
        if (key != Character.getNumericValue('q')){ 
        	
        	// 4. Check if the key is valid
        	
        	if (memFacade.isMemberKeyValid(key) == true){
        		
        		int boats = memFacade.getMemberNoOfBoats(key);

        		if (boats>0){
        			
        			// 5. Display the boats for the member
        			for (int loopMe=1;boats>=loopMe;loopMe++){
        				
        				ui.displayVerboseObjectBoat (loopMe,memFacade.getMemberBoatType (key,loopMe),memFacade.getMemberBoatLength (key,loopMe));
        				
        			}
        			
        			
        			// 6. Request the boat to be selected
        			ui.displayBoatMenuRemoveBoat();
        			
        			
        			// 7. Get the selected boat
        			int boat = ui.selectMenu();
        	        if (boat != Character.getNumericValue('q')){
        	        	
        	        	// 8. Check that it is valid
        	        	
        	        	if (boat>=1 && boat <= boats){
        	               
        	        		int result = memFacade.removeBoat(key,boat);
        	        		ui.displayOpResult (result);	
        	        			
        	        	}
        	        	else ui.displayOpResult (ECODE920);
        	        	
        	        	
        	        }
                	else ui.displayOpResult (ECODE900);

        			
        		}
        		else ui.displayOpResult (ECODE921);
        		
        	}
        	else ui.displayOpResult (ECODE900);
        
        }
        else ui.displayOpExit ();  // exit was selected
		
		
	}
	
	private void useCaseUpdateBoat (UIInterface ui, MemberFacadeInterface memFacade){
		
		// 1. List all members
		
		useCaseDisplayVerboseList (ui,memFacade);
		
		// 2. Display Select Member owning the boat to be updated
		
		ui.displayMemberMenuUpdateBoat();
		
		// 3. Select member 
		
		int key = ui.selectMenu();
        if (key != Character.getNumericValue('q')){ 
        	
        	// 4. Check if the key is valid
        	
        	if (memFacade.isMemberKeyValid(key) == true){
        		
        		int boats = memFacade.getMemberNoOfBoats(key);

        		if (boats>0){
        			
        			// 5. Display the boats for the member
        			for (int loopMe=1;boats>=loopMe;loopMe++){
        				
        				ui.displayVerboseObjectBoat (loopMe,memFacade.getMemberBoatType (key,loopMe),memFacade.getMemberBoatLength (key,loopMe));
        				
        			}
        			
        			
        			// 6. Request the boat to be selected
        			ui.displayBoatMenuUpdateBoat();
        			
        			
        			// 7. Get the selected boat
        			int boat = ui.selectMenu();
        	        if (boat != Character.getNumericValue('q')){
        	        	
        	        	// 8. Check that it is valid
        	        	
        	        	if (boat>=1 && boat <= boats){
        	        		
        	        		ui.displayMemberMenuBoatHeading();

        	        		
        	        		String [] types = memFacade.getValidBoatTypes ();

        	        		
        	        		// 9. Display boat type
        	        		for (int loopMe = 0; types.length>loopMe;loopMe++) ui.displayMemberMenuBoat((loopMe+1), types[loopMe]);

        	        		ui.displayMemberMenuBoatFooter();

        	        		// 10. select boat type
        	        		
        	        		int value = ui.selectMenu();
        	        		
        	        		if (value >=1 && value <= types.length){
        	        			
        	                	// 11. Get boat length
        	            		String length = ui.getBoatLength ();
        	            		
        	                	// 12. Store New Boat
        	            		
        	            		int result = memFacade.updateBoat(key,boat, types[value-1], length);
        	            		ui.displayOpResult (result);	
        	        			
        	        		}
        	            	else ui.displayOpResult (ECODE920);
        	        		
        	        		
        	        	}
        	        	else ui.displayOpResult (ECODE920);
        	        	
        	        	
        	        }
                	else ui.displayOpResult (ECODE900);

        			
        		}
        		else ui.displayOpResult (ECODE921);
        		
        	}
        	else ui.displayOpResult (ECODE900);
        
        }
        else ui.displayOpExit ();  // exit was selected
		
	}
	
	private void useCaseLogin(UIInterface ui, MemberFacadeInterface memFacade){
		
		// request userid
		String userId = ui.getUserId ();

		// request password
		String userPassword = ui.getUserPassword ();
		
		// validate password
		
		Authentication auth = Authentication.getInstance(); 
		
		boolean result = auth.validateUser(memFacade,userId,userPassword);
		
		if (result == true) loggedin = true;
	    
	}
	
	private void useCaseSearch(UIInterface ui, MemberFacadeInterface memFacade){
		
		String searchString = ui.getSearchString ();
		
		if (searchString != null){
			
			SearchFilterFacade validateString = new  SearchFilterFacade();
			if (validateString.validateSearchString (searchString) == false) ui.displayIncorrectSearchString (searchString);
			
			SearchFilterFacade searchMain = new SearchFilterFacade();
			
			ArrayList <Member> searchResult = searchMain.filterResult (memFacade, searchString);
			
			// LIST RESULT
			
    		ui.searchResultStart ();
    		ui.displaySearchHeading ();

    		if (searchResult != null){
    			if (searchResult.isEmpty() == false){
    			   for (int loopMe=0;searchResult.size()>loopMe;loopMe++){
    				
    				   Member mb = searchResult.get(loopMe);
    				
    				   int id = mb.getId();
    				   String name = mb.getName();
    				   long socialId = mb.getPersonalNumber(); 
    				   int noOfBoats = mb.getNoOfBoats();
    				
    				   ui.displayMemberObject(id,name,socialId,noOfBoats);
    				
       			   }
    				   
    			}
    		}

		}
		
	}
	
	
	
	
	
	
	// Allowed patterns
	// name=xxxxxx* (SEARCH ON startsWith)
	// age>yyy | age=yyy | age<yy SEARCH ON ageOver, ageEqual, ageUnder
	// bornmonth=xx SEARCH on a born a specific month
	// boattype=yyyy SEARCH on a specific boat type
	

	
}
