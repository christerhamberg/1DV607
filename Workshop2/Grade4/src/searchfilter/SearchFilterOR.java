package searchfilter;

import java.util.ArrayList;

import model.Member;

public class SearchFilterOR implements SearchFilterInterface {

	private ArrayList<Member> leftParameter;
	
	public SearchFilterOR (ArrayList<Member> members){
		leftParameter = members;
	}
	
	public ArrayList<Member> filterResult(ArrayList<Member> rightParameter, String searchString) {
		
		ArrayList<Member> result = new ArrayList <Member> ();
		
		// left is empty

		if (leftParameter != null && leftParameter.isEmpty() == false){
			if (leftParameter.isEmpty() == false) result = leftParameter;
			else {
				if (rightParameter == null) return leftParameter;
				if (rightParameter.isEmpty()==true) return leftParameter;
			}
		}
		else {

			// left is null or empty
			
			if (rightParameter == null) return null;
			if (rightParameter.isEmpty()==true) return null;		
			
			return rightParameter;
			
		}
		
		// JOIN THE TWO
		
        if (rightParameter != null) {
        	if (rightParameter.isEmpty() == false){
			    for (int loopMe=0;rightParameter.size()>loopMe;loopMe++){
			
			    	Member mb = rightParameter.get(loopMe);
		
				    if (leftParameter.contains(mb) == false) result.add (mb);
			
			    }
		     } 
        } 

        return result;
			
	}
	
}
