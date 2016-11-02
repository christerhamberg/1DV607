package searchfilter;

import java.util.ArrayList;

import model.Member;

public class SearchFilterName implements SearchFilterInterface {

	@Override
	public ArrayList<Member> filterResult (ArrayList<Member> members, String searchString) {
		
		String subString = searchString.toUpperCase();
		
		ArrayList<Member> result = new ArrayList<Member> ();
		
		for (int loopMe = 0; members.size()>loopMe;loopMe++){
			
			Member mb = members.get(loopMe);
			
			String memberName = mb.getName().toUpperCase();
						
			if (memberName.startsWith(subString) == true) result.add(mb);
			
		}
		
		if (result.isEmpty() == true) return null;
		else return result;

	}

}
