package searchfilter;

import java.util.ArrayList;

import model.Member;

public class SearchFilterBoatType implements SearchFilterInterface { 
	
	@Override
	public ArrayList<Member> filterResult (ArrayList<Member> members, String searchString) {
		
		String subString = searchString.toUpperCase();
		
		ArrayList<Member> result = new ArrayList<Member> ();
		
		for (int loopMe = 0; members.size()>loopMe;loopMe++){
			
			Member mb = members.get(loopMe);
			
			// fetch all boats for the member
			
    		int boats = mb.getNoOfBoats();
    		    		
    		for (int loopBoats = 1; boats>= loopBoats; loopBoats++){

    			String boatType = mb.getMemberBoatType(loopBoats);
    			
    			if (boatType.toUpperCase().compareTo(subString) == 0){
    				result.add(mb);
        			break; // end loop one user could have more than one boat of the same type
    			}
    			
    		}
			
		}
		
		if (result.isEmpty() == true) return null;
		else return result;
		

	}

}
