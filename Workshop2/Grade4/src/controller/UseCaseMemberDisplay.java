package controller;

import java.util.ArrayList;

import model.Member;
import model.MemberFacadeInterface;
import view.UIInterface;

public class UseCaseMemberDisplay extends UseCaseCommon implements UseCaseInterface{
	
	public void runUseCase (UIInterface ui, MemberFacadeInterface memFacade){
		
		ui.displayMember();
		
		// 1. DISPLAY HEADING
		
		ui.displaySingleMemberHeading();		
		
		// 2. Select member 
				
		ArrayList <Member> totalList = memFacade.getAllMembers();
		
		// 3. Show all Members
		
		displayMembers (ui,totalList,false);
				
		// 4. Select Member
		
		int key = selectMember(ui,totalList,false);
		
		if (key != -1){
			
			// 5. Show heading for one user
			
			ui.displaySingleMemberHeading();
    		
			// 6. Show member Data
			
			ui.displaySingleMember(key, memFacade.getMemberName (key), memFacade.getMemberNoOfBoats(key), memFacade.getMemberPersonalNumber (key));

			// 7. Get number of owned boats
		
			int boats = memFacade.getMemberNoOfBoats(key);
		
			for (int loopMe = 1;boats>=loopMe;loopMe++){
			
				ui.displayBoat (key,loopMe,memFacade.getMemberBoatType (key,loopMe),memFacade.getMemberBoatLength (key,loopMe));
			
			}
			
		}
		
	}

}
