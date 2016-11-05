package controller;

import java.util.ArrayList;

import model.Member;
import model.MemberFacadeInterface;
import searchfilter.SearchFilterFacade;
import view.UIInterface;

public class UseCaseSearch extends UseCaseCommon implements UseCaseInterface{

	
	// Allowed patterns
	// name=xxxxxx* (SEARCH ON startsWith)
	// age>yyy | age=yyy | age<yy SEARCH ON ageOver, ageEqual, ageUnder NOT IMPLEMENTED
	// bornmonth=xx SEARCH on a born a specific month
	// boattype=yyyy SEARCH on a specific boat type
	
	
	public void runUseCase(UIInterface ui, MemberFacadeInterface memFacade){
		
		String searchString = ui.getSearchString ();
		
		if (searchString != null){
			
			SearchFilterFacade validateString = new  SearchFilterFacade();
			if (validateString.validateSearchString (searchString) == false) ui.displayIncorrectSearchString (searchString);
			
			SearchFilterFacade searchMain = new SearchFilterFacade();
			
			ArrayList <Member> searchResult = searchMain.filterResult (memFacade, searchString);
			
			// LIST RESULT
			
    		ui.searchResultStart ();
    		ui.displaySingleMemberHeading ();
    		displayMembers (ui,searchResult,true);
    		ui.searchResultEnd ();

		}
		
	}
	
	
}
