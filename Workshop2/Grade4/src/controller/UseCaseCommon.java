package controller;

import java.util.ArrayList;

import model.Member;
import model.MemberFacadeInterface;
import searchfilter.SearchFilterFacade;
import view.UIInterface;

public class UseCaseCommon {
	
	protected int ECODE900 = 900;  // Faulty Member Selection
	protected int ECODE920 = 920;  // Faulty boat selection
	protected int ECODE921 = 921;  // No boats assigned to member
	
	protected void displayMembers(UIInterface ui, ArrayList <Member> searchResult, boolean showBoats){
		
		if (searchResult != null){
			if (searchResult.isEmpty() == false){
			   for (int loopMe=0;searchResult.size()>loopMe;loopMe++){
				
				   Member mb = searchResult.get(loopMe);
				
				   int id = mb.getId();
				   String name = mb.getName();
				   long socialId = mb.getPersonalNumber(); 
				   int noOfBoats = mb.getNoOfBoats();
				
				   ui.displaySingleMember(id,name,noOfBoats,socialId);
				
				   if (showBoats == true){
					   int boats = mb.getNoOfBoats();
					   
					   if (boats>0){
						   
						   for (int loopBoats=1;boats>=loopBoats;loopBoats++){
								
							   ui.displayBoat(mb.getId(),loopBoats, mb.getMemberBoatType(loopBoats), mb.getMemberBoatLength(loopBoats));;
							   							
							}
						   
					   }
						   
				   }
   			   }	   
			}
		}
		
	}

	
	protected int selectMember (UIInterface ui, ArrayList <Member> listOfMembers, boolean onlyFilterOrQuit){

		if (onlyFilterOrQuit == true) ui.displaySelectFilterOrQuit();
		else ui.displaySelectMemberOrFilterOrQuit ();
		return getKeySelection (ui,listOfMembers,onlyFilterOrQuit);
		
	}

	
	private int getKeySelection(UIInterface ui, ArrayList <Member> listOfMembers,boolean onlyFilterOrQuit){
		
		int key = ui.selectMenu();

		if (key != Character.getNumericValue('q')){ 
        	
        	// ADD FILTER OPTION
        	if (key == Character.getNumericValue('f')){
        	
        		ArrayList <Member> filteredResult = filterList (ui, listOfMembers);
        		
        		if (filteredResult != null){
        			if (filteredResult.isEmpty()==false){

                		displayMembers (ui, filteredResult,false);

                		// SELECT FROM THE FILTERED LIST

                		return selectMember (ui, filteredResult,onlyFilterOrQuit);
                		
        				//return selectMemberFromList (ui, filteredResult);

        			}
        			else return -1;
        		}
        		else return -1;
        		
        	}
        	else {
        	
        		if (key != -1) {
        			for (int loopMe=0;listOfMembers.size()>loopMe;loopMe++){
        				
        				Member mb = listOfMembers.get(loopMe);
        				if (mb.getId() == key) return key;
        				
        			}
        		}
        		
        	}
        	
		}
		
		return -1;
		
	}
	
	

	protected ArrayList <Member> filterList (UIInterface ui,ArrayList <Member> listOfMembers){

		String searchString = ui.getFilterString();

		if (searchString == null) return null;
		else if (searchString.compareToIgnoreCase("q") == 0) return null;
		else {
		
			SearchFilterFacade validateString = new  SearchFilterFacade();
			if (validateString.validateSearchString (searchString) == false){
				ui.displayIncorrectSearchString (searchString);
				return null;
			}
			else {
	
				// Get the filtered List
				
				return listOfMembers = validateString.filterResultNew (listOfMembers, searchString);
				
				
			}
			
		}
		
		
	}
	
	
	
	protected int selectBoat (UIInterface ui, MemberFacadeInterface memFacade, int key){
		
		int boats = memFacade.getMemberNoOfBoats(key);

		if (boats>0){
		
			// 5. Display the boats for the member
			for (int loopMe=1;boats>=loopMe;loopMe++){
			
				ui.displayBoat (loopMe,memFacade.getMemberBoatType (key,loopMe),memFacade.getMemberBoatLength (key,loopMe));
			
			}
		

			// 6. Request the boat to be selected
			ui.selectBoat();



		
			// 7. Get the selected boat
			int boat = ui.selectMenu();
        	if (boat != Character.getNumericValue('q')){
        	
        		// 8. Check that it is valid
        	
        		if (boat>=1 && boat <= boats){
        		
        			// VALID BOAT SELECTED
        			return boat;		        			
        		
        		}
        	
        	}
		}

		// invalid boat or 'q' selected
		return -1;
		
	}
	
	protected String selectBoattype (UIInterface ui, MemberFacadeInterface memFacade){
		
		
		String [] types = memFacade.getValidBoatTypes ();

		// 9. Display boat type
		for (int loopMe = 0; types.length>loopMe;loopMe++) ui.displayBoatSelectionMenu((loopMe+1), types[loopMe]);

		// 10. select boat type

		ui.displaySelectBoatType();

		
		int value = ui.selectMenu();
	
		if (value >=1 && value <= types.length) return types[value-1];
		
		return null;

	}

	
	

}
