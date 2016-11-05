package controller;

import model.MemberFacadeInterface;
import view.UIInterface;

public class UseCaseMemberCreate implements UseCaseInterface{
	
	public void runUseCase (UIInterface ui, MemberFacadeInterface memFacade){
		
		ui.displayCreateMember();
		
		// 1. Get the New Members Name
	
		String name = ui.getMemberName ();
		
		// 2. Get the New Members Social Security ID (date of birth is used in this case)
				
		String socSecId = ui.getSocialSecId ();
		
		// 3. Store the new Member
		
		int result = memFacade.storeNewMember (name,socSecId);
		ui.displayOpResult (result);
		
	}

}
