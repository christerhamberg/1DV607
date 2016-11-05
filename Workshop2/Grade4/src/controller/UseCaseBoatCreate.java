package controller;

import java.util.ArrayList;

import model.Member;
import model.MemberFacadeInterface;
import view.UIInterface;

public class UseCaseBoatCreate extends UseCaseCommon implements UseCaseInterface{
	
	public void runUseCase (UIInterface ui, MemberFacadeInterface memFacade){
		
		ui.displayCreateBoat();
		
		// 1. DISPLAY HEADING
		
		ui.displaySingleMemberHeading();		
		
		// 2. Select member 
				
		ArrayList <Member> totalList = memFacade.getAllMembers();
		
		// 3. Show all Members
		
		displayMembers (ui,totalList,false);
				
		// 4. Select Member
		
		int key = selectMember(ui,totalList,false);
        if (key != -1){ 
        		
       	    // 8. select boat type
       		String boatType = selectBoattype(ui,memFacade);
        	
       		if (boatType != null){
       				
               	// 9. Get boat length
           		String length = ui.getBoatLength ();
            		
               	// 10. Store New Boat
            		
           		int result = memFacade.storeBoat(key, boatType, length);
           		ui.displayOpResult (result);	
        			
       		}
           	else ui.displayOpResult (ECODE920);
        		
        }
         
    	
	}

	
}
