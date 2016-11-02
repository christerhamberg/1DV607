package searchfilter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import model.Member;
import model.MemberFacadeInterface;

public class SearchFilterFacade {
		
	public SearchFilterFacade (){
		
	}

	public ArrayList <Member> filterResult (MemberFacadeInterface memFacade, String searchString){
		
		// fecth all members
				
		ArrayList <Member> memberData = new ArrayList <Member> ();
		ArrayList <Member> searchResult = null;

		Set <Integer> hashKeys = memFacade.getHashKeysToAllMembers ();
		Iterator<Integer> iter = hashKeys.iterator();

		// Store all Objects
		
		while (iter.hasNext() == true){
		
			// get the next hashkey
			int key = iter.next();
			
			// get the next MemberObject 
			Member mem = memFacade.getMember (key);
			
			if (mem != null) memberData.add(mem);
									
		}
		
		if (memberData.isEmpty() == false){
			
			// Is this a name= search ?
			
			String nameSearch = isName (searchString);
			
			if (nameSearch != null){
				
				SearchFilterInterface filter = new SearchFilterName ();
				searchResult = filter.filterResult (memberData,nameSearch);
				
			}
			
			nameSearch = isBoatType (searchString);

			if (nameSearch != null){

				SearchFilterInterface filter = new SearchFilterBoatType ();
				searchResult = filter.filterResult (memberData,nameSearch);
				
			}
			
			nameSearch = isBornMonth (searchString);

			if (nameSearch != null){

				SearchFilterInterface filter = new SearchFilterBornMonth ();
				searchResult = filter.filterResult (memberData,nameSearch);
				
			}
			
		}
		
		return searchResult;
		
	}
	
	private String isName (String searchString){
		
		if (searchString.startsWith("name=") == true){
			
			if (searchString.endsWith("*") == true) return searchString.substring(5, searchString.length()-1);				
			else if (searchString.contains("*") == false) return searchString.substring(5, searchString.length());
			
		}
		
		return null;
		
	}
	
	private String isBoatType (String searchString){
		
		if (searchString.startsWith("boattype=") == true){

		   return searchString.substring(9, searchString.length());
		
	    }

		return null;
		
    }
	
	private String isBornMonth (String searchString){
		
		if (searchString.startsWith("bornmonth=") == true){

		   return searchString.substring(10, searchString.length());
		
	    }

		return null;
		
    }
		
}
