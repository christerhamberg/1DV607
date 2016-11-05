package controller;

import model.MemberFacadeInterface;
import model.authentication.Authentication;
import view.UIInterface;

public class YachtClubController {
	
	private int ECODE1000 = 1000; // Incorrect Username or Password 
	
	private boolean loggedin = false;

	
	// COULD BE CHANGED TO AN OBSERVER PATTERN AS WELL
	private static UIInterface sessionUi = null;
	public static void hanlingErrorDetected (int code){

		if (sessionUi != null) sessionUi.displayError (code);
			
	}
	
	public void startProg (UIInterface ui, MemberFacadeInterface memFacade){
			
		sessionUi = ui;
		
		int value = 0;
		
		while (value != Character.getNumericValue('q')){
			
			if (loggedin == false) ui.showMainMenuNotLoggedIn ();
			else ui.showMainMenuLoggedIn ();

			value = ui.selectMenu();
						
			if (ui.isLogin(value) == true){
				if (loggedin==false) useCaseLogin (ui, memFacade);
			}
			// must be logged in 
			if (loggedin == true){
			    if (ui.isCreateMember(value) == true){
			    	
			    	UseCaseInterface createMember = new UseCaseMemberCreate();
			    	createMember.runUseCase(ui, memFacade);

			    }
			    else if (ui.isRemoveMember(value) == true){
			    	
			    	UseCaseInterface removeMember = new UseCaseMemberRemove();
			    	removeMember.runUseCase(ui, memFacade);
			    	
			    }
			    else if (ui.isUpdateMember(value) == true){
			    	
			    	UseCaseInterface updateMember = new UseCaseMemberUpdate();
			    	updateMember.runUseCase(ui, memFacade);
			    	
			    }
			    else if (ui.isCreateBoat(value) == true){
			    	
			    	UseCaseInterface createBoat = new UseCaseBoatCreate();
			    	createBoat.runUseCase(ui, memFacade);
			    	
			    }
			    else if (ui.isRemoveBoat(value) == true){
			    	
			    	UseCaseInterface removeBoat = new UseCaseBoatRemove();
			    	removeBoat.runUseCase(ui, memFacade);
			    	
			    }
			    else if (ui.isUpdateBoat(value) == true){
			    	
			    	UseCaseInterface updateBoat = new UseCaseBoatUpdate();
			    	updateBoat.runUseCase(ui, memFacade);
			    	
			    }
			}
		    if (ui.isDisplayMember(value) == true){
		    	
		    	UseCaseInterface displayMember = new UseCaseMemberDisplay();
		    	displayMember.runUseCase(ui, memFacade);
		    	
		    }
			if (ui.isComapctList(value) == true){
				
		    	UseCaseInterface displayMemberCompactList = new UseCaseMemberDisplayCompactList();
		    	displayMemberCompactList.runUseCase(ui, memFacade);
		    	
			}
			if (ui.isVerboseList(value) == true){
				
		    	UseCaseInterface displayMemberVerboseList = new UseCaseMemberDisplayVerboseList();
		    	displayMemberVerboseList.runUseCase(ui, memFacade);
		    	
			}
			if (ui.isSearch(value) == true){
				
		    	UseCaseInterface search = new UseCaseSearch();
		    	search.runUseCase(ui, memFacade);

			}
			if (ui.isQuit(value) == true) ;

		}
		
		ui.exitUI ();
		
	}
	
	private void useCaseLogin(UIInterface ui, MemberFacadeInterface memFacade){
		
		// request userid
		String userId = ui.getUserId ();

		// request password
		String userPassword = ui.getUserPassword ();
		
		// validate password
		
		Authentication auth = Authentication.getInstance(); 
		
		boolean result = auth.validateUser(memFacade,userId,userPassword);
		
		if (result == true) loggedin = true;
		else hanlingErrorDetected (ECODE1000);
	    
	}
		
}
