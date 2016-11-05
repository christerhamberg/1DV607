package searchfilter;

import java.util.ArrayList;

import model.Member;

public class SearchFilterAND implements SearchFilterInterface {
	
	private ArrayList<Member> leftParameter;
	
	public SearchFilterAND (ArrayList<Member> members){
		leftParameter = members;
	}
	
	public ArrayList<Member> filterResult(ArrayList<Member> rightParameter, String searchString) {
		
		ArrayList<Member> result = new ArrayList <Member> ();
		
		// left is empty
		if (leftParameter == null) return null;
		if (leftParameter.isEmpty()==true) return null;
		
		// right is empty
		if (rightParameter == null) return null;
		if (rightParameter.isEmpty()==true) return null;
		
		
		for (int loopMe=0;rightParameter.size()>loopMe;loopMe++){
			
			Member mb = rightParameter.get(loopMe);		
			if (leftParameter.contains(mb) == true) result.add (mb);
			
		}
		
		return result;
			
	}

}
