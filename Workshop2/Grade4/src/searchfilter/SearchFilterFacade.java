package searchfilter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import model.Member;
import model.MemberFacadeInterface;

public class SearchFilterFacade extends SearchStringValidatorExtractor{
		
	public SearchFilterFacade (){
		
	}

	public ArrayList <Member> filterResultNew (ArrayList <Member> members, String searchString){
		
		// fecth all members
				
		ArrayList <Member> memberData = members;
		ArrayList <Member> searchResult = null;

			
			// GET THE DATA TO SEARCH ON 
			
			ArrayList <String> searchOptions = validateSearchStringSplit (searchString);
			
			if (searchOptions != null){
				if (searchOptions.isEmpty() == false){
					
					if (searchOptions.size()==1){
						
						searchResult = filterOneOption (memberData,searchOptions.get(0));
						
					}
					else {
					
						boolean firstSearch = true;
						
						int index=0;
						while (searchOptions.size()>index){
							
							if (firstSearch == true){

								// Check how to join the searches
								ArrayList <Member> filter1 = filterOneOption (memberData,searchOptions.get(index++));
								String whatToDo = searchOptions.get(index++);
								ArrayList <Member> filter2 = filterOneOption (memberData,searchOptions.get(index++));								
							
								searchResult = joinFilterSearch(filter1,filter2, whatToDo);

						    	firstSearch = false;
						    	
							}
							else {

								// Check how to join the searches
								String whatToDo = searchOptions.get(index++);
								
								ArrayList <Member> filter2 = filterOneOption (searchResult,searchOptions.get(index++));
							       
						    	searchResult = joinFilterSearch(searchResult,filter2, whatToDo);							
								
							}							
							
							
					}
										
				}
			}
			
		}
		
		return searchResult;
		
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
			
			// GET THE DATA TO SEARCH ON 
			
			ArrayList <String> searchOptions = validateSearchStringSplit (searchString);
			
			if (searchOptions != null){
				if (searchOptions.isEmpty() == false){
					
					if (searchOptions.size()==1){
						
						searchResult = filterOneOption (memberData,searchOptions.get(0));
						
					}
					else {
					
						boolean firstSearch = true;
						
						int index=0;
						while (searchOptions.size()>index){
							
							if (firstSearch == true){

								// Check how to join the searches
								ArrayList <Member> filter1 = filterOneOption (memberData,searchOptions.get(index++));
								String whatToDo = searchOptions.get(index++);
								ArrayList <Member> filter2 = filterOneOption (memberData,searchOptions.get(index++));								
							
								searchResult = joinFilterSearch(filter1,filter2, whatToDo);

						    	firstSearch = false;
						    	
							}
							else {

								// Check how to join the searches
								String whatToDo = searchOptions.get(index++);
								
								ArrayList <Member> filter2 = filterOneOption (searchResult,searchOptions.get(index++));
							       
						    	searchResult = joinFilterSearch(searchResult,filter2, whatToDo);							
								
							}							
							
						}
							
					}
										
				}
			}
			
		}
		
		return searchResult;
		
	}
	
	ArrayList <Member> filterOneOption (ArrayList <Member> memberData, String searchString ){
		
		ArrayList <Member> searchResult = null;
		
		if (memberData == null) return null;
		if (memberData.isEmpty()==true) return null;
		
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
	
		return searchResult;		
		
	}
	
	ArrayList <Member> joinFilterSearch (ArrayList <Member> dataSet1, ArrayList <Member> dataSet2, String whatToDo){

		SearchFilterInterface joinFilter=null;
		    
		if (whatToDo.compareToIgnoreCase("OR") == 0) joinFilter = new SearchFilterOR (dataSet1);
		if (whatToDo.compareToIgnoreCase("AND") == 0) joinFilter = new SearchFilterAND (dataSet1);							    	
		    
	    return joinFilter.filterResult(dataSet2, null);
		
	}



		
		
		
	

		
}
