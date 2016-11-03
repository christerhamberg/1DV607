package searchfilter;

import model.Boat;

public class SearchStringValidatorExtractor {
	
	public boolean validateSearchString (String searchString){
		
		// validate pattern names
		
		// name=xxxxx or name=xxxxx*
		if (searchString.startsWith("name=") == true){
			
			if (validateName(searchString) == false) return false;

		}
		
		// validate pattern age
		if (searchString.startsWith("age=") == true || searchString.startsWith("age>") == true || searchString.startsWith("age<") == true){
			
			if (validateAge(searchString) == false) return false;
			
			// THIS PATTERN IS NOT IMPLEMENTED 
			// BY DEFAULT RETURN false
			return false;
			
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
		
		
		// PATTERN IS NOT IMPLEMENTED
		return false;
		
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
			if (possibleTypes[loopMe].compareToIgnoreCase(boatType) == 0) return true;
		}
		
		return false;
		
	}
	
	protected String isName (String searchString){
		
		if (searchString.startsWith("name=") == true){
			
			if (searchString.endsWith("*") == true) return searchString.substring(5, searchString.length()-1);				
			else if (searchString.contains("*") == false) return searchString.substring(5, searchString.length());
			
		}
		
		return null;
		
	}
	
	protected String isBoatType (String searchString){
		
		if (searchString.startsWith("boattype=") == true){

		   return searchString.substring(9, searchString.length());
		
	    }

		return null;
		
    }
	
	protected String isBornMonth (String searchString){
		
		if (searchString.startsWith("bornmonth=") == true){

		   return searchString.substring(10, searchString.length());
		
	    }

		return null;
		
    }
	
}
