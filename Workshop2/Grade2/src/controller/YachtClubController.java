package controller;

import java.util.Iterator;
import java.util.Set;

import model.MemberFacadeInterface;
import view.UIInterface;

public class YachtClubController {
	
	private int ECODE900 = 900;  // Faulty Member Selection
	private int ECODE920 = 920;  // Faulty boat selection
	private int ECODE921 = 921;  // No boats assigned to member

	public void startProg (UIInterface ui, MemberFacadeInterface cache){
			
		int value = 0;
		
		while (value != -1){
			
			ui.showMainMenu ();

			value = ui.selectMenu();
						
			if (value == 1) useCaseCreateNewMember (ui, cache);
			else if (value == 2) useCaseRemoveMember (ui, cache);
			else if (value == 3) useCaseUpdateMember (ui, cache);
			else if (value == 4) useCaseDisplaySingleMember (ui, cache); 
			else if (value == 5) useCaseDisplayCompactList (ui, cache);
			else if (value == 6) useCaseDisplayVerboseList (ui, cache);
			else if (value == 7) useCaseCreateBoat (ui, cache);
			else if (value == 8) useCaseRemoveBoat (ui, cache);
			else if (value == 9) useCaseUpdateBoat (ui, cache);

		}
		
		ui.exitUI ();
		
	}
	
	private void useCaseCreateNewMember (UIInterface ui, MemberFacadeInterface cache){
		
		// 1. Get the New Members Name
	
		String name = ui.getMemberName ();
		
		// 2. Get the New Members Social Security ID (date of birth is used in this case)
				
		String socSecId = ui.getSocialSecId ();
		
		// 3. Store the new Member
		
		int result = cache.storeNewMember (name,socSecId);
		ui.displayOpResult (result);
		
	}
	
	
	private void useCaseRemoveMember (UIInterface ui, MemberFacadeInterface cache){			
			
		// 1. List all members
			
		useCaseDisplayCompactList (ui,cache);
			
		// 2. Display Select Member (Remove)
			
		ui.displayMemberMenuRemove();
			
		// 3. Select member 
			
		int key = ui.selectMenu();
	        
		if (key != -1){ 
	        	
			// 4. Check if the key is valid
	        	
			if (cache.isMemberKeyValid(key) == true){
	        		
				// 5. Remove the member
	        		
        		int result = cache.removeMember(key);
        		ui.displayOpResult (result);	
			}
	        	
			else ui.displayOpResult (ECODE900);
	               
		}
	        
		else ui.displayOpExit ();  // exit was selected
		
	}
	
	private void useCaseUpdateMember (UIInterface ui, MemberFacadeInterface cache){
		
		// 1. List all members
		
		useCaseDisplayCompactList (ui,cache);
		
		// 2. Display Select Member (Update)
		
		ui.displayMemberMenuUpdate();
		
		// 3. Select member 
		
		int key = ui.selectMenu();
        if (key != -1){ 
        	
        	// 4. Check if the key is valid
        	
        	if (cache.isMemberKeyValid(key) == true){
        		
            	// 5. Update member's Data (Name)
        		String name = ui.getMemberName ();

            	// 6. Update member's Data (Personal Id)
        		String socSecId = ui.getSocialSecId ();

            	// 7. Update Member Object and update the file
        		
        		int result = cache.updateMember(key, name, socSecId);
        		ui.displayOpResult (result);	
        		
        	}
        	else ui.displayOpResult (ECODE900);
        
        }
        else ui.displayOpExit ();  // exit was selected
		
	}
	
	private void useCaseDisplaySingleMember (UIInterface ui, MemberFacadeInterface cache){
		
		// 1. List all members
		
		useCaseDisplayCompactList (ui,cache);
		
		// 2. Display Select Member (Update)
		
		ui.displayMemberMenuDisplay();
		
		// 3. Select member 
		
		int key = ui.selectMenu();
        if (key != -1){ 
        	
        	// 4. Check if the key is valid
        	
        	if (cache.isMemberKeyValid(key) == true){
        		
        		// 5. Display Data for the Member
        		
        		ui.displaySingleMemberHeading();
        		
    			ui.displayMemberObject(cache.getMemberId (key), cache.getMemberName (key),  cache.getMemberPersonalNumber (key), cache.getMemberNoOfBoats(key));

        		// 6. Get number of owned boats
        		
        		int boats = cache.getMemberNoOfBoats(key);
        		
        		for (int loopMe = 1;boats>=loopMe;loopMe++){
        			
    				ui.displayVerboseObjectBoat (loopMe,cache.getMemberBoatType (key,loopMe),cache.getMemberBoatLength (key,loopMe));
        			
        		}
        		        		
        	}
        	else ui.displayOpResult (ECODE900);
        
        }
        else ui.displayOpExit ();  // exit was selected
		
	}

	
	
	
	
	private void useCaseDisplayCompactList (UIInterface ui, MemberFacadeInterface cache){
		
		// 1. Get all hashKeys
		
		Set <Integer> hashKeys = cache.getHashKeysToAllMembers ();
		Iterator<Integer> iter = hashKeys.iterator();
		
		// 2. Display Heading
		
		ui.displayCompactObjectHeading();
		
		// 3. Display all Objects
		
		while (iter.hasNext() == true){
			
			int key = iter.next();
			
			ui.displayCompactObject(cache.getMemberName (key), cache.getMemberId (key), cache.getMemberNoOfBoats (key));
			
		}
		
	}
	
	private void useCaseDisplayVerboseList (UIInterface ui, MemberFacadeInterface cache){
		
		// 1. Get all hashKeys
		
		Set <Integer> hashKeys = cache.getHashKeysToAllMembers ();
		Iterator<Integer> iter = hashKeys.iterator();
		
		// 2. Display Heading
		
		ui.displayVerboseObjectHeading();
		
		// 3. Display all Objects
		
		while (iter.hasNext() == true){
			
			int key = iter.next();
			
			int noOfBoats = cache.getMemberNoOfBoats (key);
			
			ui.displayVerboseObject(cache.getMemberName (key), cache.getMemberPersonalNumber (key), key);

			for (int loopMe = 1; noOfBoats>=loopMe;loopMe++){
				
				ui.displayVerboseObjectBoat (key,loopMe,cache.getMemberBoatType (key,loopMe),cache.getMemberBoatLength (key,loopMe));
				
			}
			
		}
		
	}
	
	
	private void useCaseCreateBoat (UIInterface ui, MemberFacadeInterface cache){
		
		// 1. List all members
		
		useCaseDisplayCompactList (ui,cache);
		
		// 2. Display Select Member to whom the boat is to be registered
		
		ui.displayMemberMenuCreateBoat();
		
		// 3. Select member 
		
		int key = ui.selectMenu();
        if (key != -1){ 
        	
        	// 4. Check if the key is valid
        	
        	if (cache.isMemberKeyValid(key) == true){
        		
        		// 5. Get all valid boat types
        		
        		String [] types = cache.getValidBoatTypes ();
        		
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
            		
            		int result = cache.storeBoat(key, types[value-1], length);
            		ui.displayOpResult (result);	
        			
        		}
            	else ui.displayOpResult (ECODE920);
        		
        	}
        	else ui.displayOpResult (ECODE900);
        
        }
        else ui.displayOpExit ();  // exit was selected
		
	}
	
	private void useCaseRemoveBoat (UIInterface ui, MemberFacadeInterface cache){
		
		// 1. List all members
		
		useCaseDisplayVerboseList (ui,cache);
		
		// 2. Display Select Member owning the boat to be updated
		
		ui.displayMemberMenuRemoveBoat();
		
		// 3. Select member 
		
		int key = ui.selectMenu();
        if (key != -1){ 
        	
        	// 4. Check if the key is valid
        	
        	if (cache.isMemberKeyValid(key) == true){
        		
        		int boats = cache.getMemberNoOfBoats(key);

        		if (boats>0){
        			
        			// 5. Display the boats for the member
        			for (int loopMe=1;boats>=loopMe;loopMe++){
        				
        				ui.displayVerboseObjectBoat (loopMe,cache.getMemberBoatType (key,loopMe),cache.getMemberBoatLength (key,loopMe));
        				
        			}
        			
        			
        			// 6. Request the boat to be selected
        			ui.displayBoatMenuRemoveBoat();
        			
        			
        			// 7. Get the selected boat
        			int boat = ui.selectMenu();
        	        if (boat != -1){
        	        	
        	        	// 8. Check that it is valid
        	        	
        	        	if (boat>=1 && boat <= boats){
        	               
        	        		int result = cache.removeBoat(key,boat);
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
	
	private void useCaseUpdateBoat (UIInterface ui, MemberFacadeInterface cache){
		
		// 1. List all members
		
		useCaseDisplayVerboseList (ui,cache);
		
		// 2. Display Select Member owning the boat to be updated
		
		ui.displayMemberMenuUpdateBoat();
		
		// 3. Select member 
		
		int key = ui.selectMenu();
        if (key != -1){ 
        	
        	// 4. Check if the key is valid
        	
        	if (cache.isMemberKeyValid(key) == true){
        		
        		int boats = cache.getMemberNoOfBoats(key);

        		if (boats>0){
        			
        			// 5. Display the boats for the member
        			for (int loopMe=1;boats>=loopMe;loopMe++){
        				
        				ui.displayVerboseObjectBoat (loopMe,cache.getMemberBoatType (key,loopMe),cache.getMemberBoatLength (key,loopMe));
        				
        			}
        			
        			
        			// 6. Request the boat to be selected
        			ui.displayBoatMenuUpdateBoat();
        			
        			
        			// 7. Get the selected boat
        			int boat = ui.selectMenu();
        	        if (boat != -1){
        	        	
        	        	// 8. Check that it is valid
        	        	
        	        	if (boat>=1 && boat <= boats){
        	        		
        	        		ui.displayMemberMenuBoatHeading();

        	        		
        	        		String [] types = cache.getValidBoatTypes ();

        	        		
        	        		// 9. Display boat type
        	        		for (int loopMe = 0; types.length>loopMe;loopMe++) ui.displayMemberMenuBoat((loopMe+1), types[loopMe]);

        	        		ui.displayMemberMenuBoatFooter();

        	        		// 10. select boat type
        	        		
        	        		int value = ui.selectMenu();
        	        		
        	        		if (value >=1 && value <= types.length){
        	        			
        	                	// 11. Get boat length
        	            		String length = ui.getBoatLength ();
        	            		
        	                	// 12. Store New Boat
        	            		
        	            		int result = cache.updateBoat(key,boat, types[value-1], length);
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
	
}
