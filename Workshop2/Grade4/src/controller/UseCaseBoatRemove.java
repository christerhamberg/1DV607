package controller;

import java.util.ArrayList;

import model.Member;
import model.MemberFacadeInterface;
import view.UIInterface;

public class UseCaseBoatRemove extends UseCaseCommon implements UseCaseInterface{
	
	public void runUseCase (UIInterface ui, MemberFacadeInterface memFacade){
		
		ui.displayRemoveBoat();
		
		// 1. DISPLAY HEADING
		
		ui.displaySingleMemberHeading();		
		
		// 2. Select member 
				
		ArrayList <Member> totalList = memFacade.getAllMembers();
		
		// 3. Show all Members
		
		displayMembers (ui,totalList,false);
				
		// 4. Select Member
		
		int key = selectMember(ui,totalList,false);
        if (key != -1){ 
        	
            if (key != -1){ 

            	int selectedBoat = selectBoat (ui,memFacade,key);
        			
        		if (selectedBoat != -1){
        	        	        	               
        	       	int result = memFacade.removeBoat(key,selectedBoat);
             		ui.displayOpResult (result);	
        	        			
       	        }
       	    	else ui.displayOpResult (ECODE920);
        	        	
        	        	
       	    }
             	        		
        }
     	
	}

}
