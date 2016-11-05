package controller;

import java.util.ArrayList;

import model.Member;
import model.MemberFacadeInterface;
import view.UIInterface;

public class UseCaseMemberUpdate extends UseCaseCommon implements UseCaseInterface{

	public void runUseCase (UIInterface ui, MemberFacadeInterface memFacade){
		
		ui.displayUpdateMember();
		
		// 1. DISPLAY HEADING
		
		ui.displaySingleMemberHeading();		
		
		// 2. Select member 
				
		ArrayList <Member> totalList = memFacade.getAllMembers();
		
		// 3. Show all Members
		
		displayMembers (ui,totalList,false);
				
		// 4. Select Member
		
		int key = selectMember(ui,totalList,false);
		
	    if (key != -1){ 
        	    		
        	// 5. Update member's Data (Name)
    		String name = ui.getMemberName ();

        	// 6. Update member's Data (Personal Id)
    		String socSecId = ui.getSocialSecId ();

        	// 7. Update Member Object and update the file
    		int result = memFacade.updateMember(key, name, socSecId);
    		ui.displayOpResult (result);	
    		
       	}
		
			
	}
}
