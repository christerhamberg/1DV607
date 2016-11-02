package searchfilter;

import java.util.ArrayList;

import model.Member;

public class SearchFilterBornMonth implements SearchFilterInterface {

	@Override
	public ArrayList<Member> filterResult (ArrayList<Member> members, String searchString) {
		
		int month = Integer.parseInt (searchString);
		
		ArrayList<Member> result = new ArrayList<Member> ();
		
		for (int loopMe = 0; members.size()>loopMe;loopMe++){
			
			Member mb = members.get(loopMe);
			
			int memberMonth = mb.getBornMonth();
						
			if (memberMonth == month) result.add(mb);
			
		}
		
		if (result.isEmpty() == true) return null;
		else return result;

	}

}
