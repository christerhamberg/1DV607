package searchfilter;

import java.util.ArrayList;

import model.Boat;

public class SearchStringValidatorExtractor {
	

	public boolean validateSearchString (String searchString){
		
		ArrayList <String> resultString = validateSearchStringSplit (searchString);
				
		if (resultString == null) return false;
		else return true;
		
	}

	
	@SuppressWarnings("unused")
	public ArrayList <String> validateSearchStringSplit (String searchString){
		
		ArrayList <String> resultString = new ArrayList <String> ();
		
		String [] splitString = searchString.split(" ");
	
		boolean previousAndOr = false;
		boolean containsAndOr = false;
		
		for (int loopMe=0;splitString.length>loopMe;loopMe++){

			boolean validationOk = false;
			String str = null;
			
			String  patternString = splitString[loopMe];
			
			// Check for empty strings i.e. input was a whitespace
			if (patternString.length()>0){
			
				// validate pattern names
				
				if (patternString.compareToIgnoreCase("AND")==0){

					if (previousAndOr == true) return null;       // PATTERN xxxx AND OR, AND AND is not supported
				
				    containsAndOr = true;  // at the end check that we have odd number of parameters
				    previousAndOr = true;
				    validationOk = true;
				    str = "AND";
				    
				}

				if (patternString.compareToIgnoreCase("OR")==0){
				
					if (previousAndOr == true) return null;       // PATTERN xxxx OR AND, OR OR is not supported
				
				    containsAndOr = true;  // at the end check that we have odd number of parameters
				    previousAndOr = true;
				    validationOk = true;
				    str = "OR";
				    
				}
				
				// name=xxxxx or name=xxxxx*
				if (patternString.startsWith("name=") == true){
					
					if (validateName(patternString) == false) return null;

					previousAndOr = false;
				    validationOk = true;
				    str = patternString;				    
				    
				}
		
				// validate pattern age
				if (patternString.startsWith("age=") == true || patternString.startsWith("age>") == true || patternString.startsWith("age<") == true){
			
					
					if (validateAge(patternString) == false) return null;


					previousAndOr = false;
				    validationOk = true;
				    //str = isAge(patternString);				    


					
					// THIS PATTERN IS NOT IMPLEMENTED 
					// BY DEFAULT RETURN false
					return null;
			
				}
		
				// validate pattern bornmonth
				if (patternString.startsWith("bornmonth=") == true){
			
					if (validateBornMonth(patternString) == false) return null;
				
					previousAndOr = false;
				    validationOk = true;			
				    str = patternString;				    

				}
		
				// validate pattern boattyp
				if (patternString.startsWith("boattype=") == true){
			
					if (validateBoatType(patternString) == false) return null;
			
					previousAndOr = false;
				    validationOk = true;
				    str = patternString;				    

				}		
	
			}
			
			if (validationOk == false) return null;
			
			resultString.add(str);
		
		}
		
		// FAULT CASES
		if (resultString == null) return null;
		if (resultString.isEmpty() == true) return null;
		if (containsAndOr == true && ((resultString.size() & 0x01) == 0x00)) return null;
		
		// SUCCESSFUL
		return resultString;
		
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
					int value = Integer.parseInt(searchString.substring(10, searchString.length()));
					
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
