package controller;

import java.util.ArrayList;

import model.Member;
import model.MemberFacadeInterface;
import view.UIInterface;

public class UseCaseMemberDisplayVerboseList extends UseCaseCommon implements UseCaseInterface{

	public void runUseCase(UIInterface ui, MemberFacadeInterface memFacade) {
		
		ui.displayMemberComapactList();
		
		// 1. DISPLAY HEADING
		
		ui.displaySingleMemberHeading();		
		
		// 2. Select member 
				
		ArrayList <Member> totalList = memFacade.getAllMembers();
		
		// 3. Show all Members
		
		displayMembers (ui,totalList,true);
		
	}
	
}
