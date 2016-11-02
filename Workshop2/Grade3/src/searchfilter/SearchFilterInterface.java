package searchfilter;

import java.util.ArrayList;

import model.Member;

public interface SearchFilterInterface {
	
	   public ArrayList<Member> filterResult (ArrayList<Member> members, String searchString);

}	
