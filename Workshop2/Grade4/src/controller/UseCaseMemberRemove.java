package controller;

import java.util.ArrayList;

import model.Member;
import model.MemberFacadeInterface;
import view.UIInterface;

public class UseCaseMemberRemove extends UseCaseCommon implements UseCaseInterface{
	
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
		if (key != Character.getNumericValue('q')){ 
        	        	
            if (key != -1){ 
	    
            	// 5. Remove the member
	        		
        		int result = memFacade.removeMember(key);
        	    ui.displayOpResult (result);	
			}
	        				
        }
   		
	}


}
