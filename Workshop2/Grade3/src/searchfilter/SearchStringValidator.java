package searchfilter;

import model.Boat;

public class SearchStringValidator {
	
	public boolean validateSearchString (String searchString){
		
		// validate pattern names
		
		// name=xxxxx or name=xxxxx*
		if (searchString.startsWith("name=") == true){
			
			if (validateName(searchString) == false) return false;

		}
		
		// validate pattern age
		if (searchString.startsWith("age=") == true || searchString.startsWith("age>") == true || searchString.startsWith("age<") == true){
			
			if (validateAge(searchString) == false) return false;
			
		}
		
		// validate pattern bornmonth
		if (searchString.startsWith("bornmonth=") == true){
			
			if (validateBornMonth(searchString) == false) return false;
			
		}
		
		// validate pattern boattyp
		if (searchString.startsWith("boattype=") == true){
			
			if (validateBoatType(searchString) == false) return false;
			
		}		
		
		return true;
		
	}
	
	private boolean validateName (String searchString){
		
		if (searchString.endsWith("*") == true) return true;
		else if (searchString.contains("*") == false) return true;
		else return false;
		
	}
	
	private boolean validateAge (String searchString){
		
		// check that yyy is a digit 0-116 (1900->2016 = max age is 116)
		// this is a hardcoded limit that could be removed in a refactoring :)
		// only need to check year from the system clock and take systemclock - 1900
		
		if (searchString.length()>4 && searchString.length()<8){
			
			try {
				int value = Integer.parseInt(searchString.substring(4, searchString.length()));
				
				if (value<0 || value> 116) return false;
			
			}
			catch (NumberFormatException e){
				return false;
			}
			
			return true;
			
		}
		else return false;
		
	}
	
	private boolean validateBornMonth (String searchString){
		
        if (searchString.length()>=11 && searchString.length()<=12){
			
				try {
					int value = Integer.parseInt(searchString.substring(11, searchString.length()));
					
					if (value<1 || value>12) return false;
				
				}
				catch (NumberFormatException e){
					return false;
				}
				
				return true;
				
			}
			else return false;
		
	}
	
	private boolean validateBoatType (String searchString){
		
		String boatType = searchString.substring(9, searchString.length());
		
		String possibleTypes[] = Boat.boatTypes();
		
		for (int loopMe=0;possibleTypes.length>loopMe;loopMe++){
			if (possibleTypes[loopMe].compareTo(boatType) == 0) return true;
		}
		
		return false;
		
	}
	
}
