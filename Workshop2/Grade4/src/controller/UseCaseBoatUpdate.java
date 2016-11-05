package controller;

import java.util.ArrayList;

import model.Member;
import model.MemberFacadeInterface;
import view.UIInterface;

public class UseCaseBoatUpdate extends UseCaseCommon implements UseCaseInterface{

	public void runUseCase (UIInterface ui, MemberFacadeInterface memFacade){
		
		ui.displayUpdateBoat();
		
		// 1. DISPLAY HEADING
		
		ui.displaySingleMemberHeading();		
		
		// 2. Select member 
				
		ArrayList <Member> totalList = memFacade.getAllMembers();
		
		// 3. Show all Members
		
		displayMembers (ui,totalList,false);
				
		// 4. Select Member
		
		int key = selectMember(ui,totalList,false);
        if (key != -1){ 
        	 		
       		int selectedBoat = selectBoat (ui,memFacade,key);
        			
       		if (selectedBoat != -1){
        				
	   			// 10. select boat type
	        		
	   			String boattype = selectBoattype (ui,memFacade);
	        		
	   			if (boattype != null){
	        			
	           		// 11. Get boat length
	       			String length = ui.getBoatLength ();
	            		
	           		// 12. Store New Boat
	            		
	       			int result = memFacade.updateBoat(key,selectedBoat, boattype, length);
	       			ui.displayOpResult (result);	
	        			
	   			}
          		else ui.displayOpResult (ECODE920);
        				
   			}
        			
   		}
        
		
	}

	
}
