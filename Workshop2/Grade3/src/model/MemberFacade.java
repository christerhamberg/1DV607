package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MemberFacade implements MemberFacadeInterface{	
	
	private int ECODE100 = 100;  // Can not convert date of birth
	private int ECODE101 = 101;  // Can not allocate SystemId
	private int ECODE102 = 102;  // Failed to store new Member to file 
	private int ECODE103 = 103;  // Can not convert length of the boat 
	private int ECODE104 = 104;  // Failed to store boat 
	private int ECODE105 = 105;  // Failed to update boat 
	//private int ECODE106 = 106;  // Incorrect Social Security Number 

	private Map <Integer,Member> memberData = new HashMap <Integer,Member> ();
	
	private FileStorage file;
	
	
	public MemberFacade (String filePath){
		
		// initialize and read data if any
	    file = FileStorage.getInstance();
				
		file.setFilePath(filePath);
		
		// initialize FileStorage
		if (file.initFileStorage() == false) file = null;
		
		// read any existing data
		if (file.rebuildCache(this) == false) file = null;
		
	}
	
	public int storeNewMember (String name, String socSecId){
		
		// Convert socSecId to an integer
		
		long longSocSecId;
		
	    try {
	    	longSocSecId = Long.parseLong (socSecId);
			
			// ADD VALIDATION OF SOCIAL SECURITY NUMBER HERE
			
		}
		catch (NumberFormatException e){
			return ECODE100; // error 100
		}
			
		
		// request a new ID
		
		int id = file.getNextSystemId ();

		if (id==-1) return ECODE101; // error 101
		
		// Create a new MemberData instance
		
		Member mb = new Member (id,name,longSocSecId);
		
		// Store the data in the file structure
		
		if (file.writeNewMemberToFile(mb.writeToFile(), true) == -1) return ECODE102; // error 102
			
		// add the MemberData instance to the HashMap
		
		memberData.put (id,mb);
		
		// return result
		
		return 0;
		
	}
	
	public void initMemeber (int id, String name, int intSocSecId){
		Member mb = new Member (id,name,intSocSecId);
		memberData.put (id,mb);
		
		
	}
	
	public void initBoat (int id, String type, double length){
		
	    Member memberObject = memberData.get(id);

	    memberObject.initNewBoat (type, length);
		
    }
	
	
	
	
	// add the MemberData instance to the HashMap
	
	
	public Set <Integer> getHashKeysToAllMembers (){
			
		return memberData.keySet();
		
	}
	
	
	public String getMemberName (int key){
		
		if (memberData.containsKey(key) == true){
			return memberData.get(key).getName();
		}
		else return "-1";
		
	
	}
	
	public int getMemberId (int key){
		
		if (memberData.containsKey(key) == true){
			return memberData.get(key).getId();
		}
		else return -1;	
		
	}
	
	public int getMemberNoOfBoats (int key){
		
		if (memberData.containsKey(key) == true){
			return memberData.get(key).getNoOfBoats();
		}
		else return -1;	
		
	}
	
	public long getMemberPersonalNumber (int key){
		
		if (memberData.containsKey(key) == true){
			return memberData.get(key).getPersonalNumber();
		}
		else return -1L;	
		
	}
	
	public boolean isMemberKeyValid (int key){
		return memberData.containsKey(key);
	}
	
	public int updateMember (int key, String name, String socSecId){
		
		int intSocSecId;
		
	    try {
			intSocSecId = Integer.parseInt (socSecId);
		}
		catch (NumberFormatException e){
			return ECODE100; // error 100
		}
		
		memberData.get(key).setName(name);
		memberData.get(key).setPersonalNumber(intSocSecId);

		// store all data
		return storeAllMembers();
		
	}
	
	public int removeMember (int key){
		
	    Member memberObject = memberData.get(key);

	    memberObject.removeAllBoats ();
	    		
		// remove the member Object
		memberData.remove(key);
		
		storeAllBoats ();
		
		// store all data
		return storeAllMembers();
		
	}
	
	private int storeAllMembers (){
		
		Set <Integer> hashKeys = memberData.keySet();
		Iterator<Integer> iter = hashKeys.iterator();
		
		// Store all Objects
		
		while (iter.hasNext() == true){
			
			boolean append = false; // overwrite the file
			
			while (iter.hasNext() == true){
			
				int key = iter.next();
				
				if (file.writeNewMemberToFile(memberData.get(key).writeToFile(), append) == -1) return ECODE102; // error 102
				append = true; // following objects append to the new file
				
			}
			
		}
		
		return 0;
		
	}
	
	
	// BOATS
	
	public String [] getValidBoatTypes (){
		return Boat.boatTypes();
	}
	
	public int storeBoat (int key, String boattype, String length){
		
		// Convert the length to a double
		double doubleLength;
		
	    try {
	    	doubleLength = Double.parseDouble (length);
		}
		catch (NumberFormatException e){
			return ECODE103; // error 103
		}
	    
		// Create a new Boat instance
	    
	    Member memberObject = memberData.get(key);
	    
	    boolean result = memberObject.storeNewBoat (file,boattype,doubleLength);
	    
	    if (result == false) return ECODE104;
		
		// Return SUCCESSFUL
	    
	    return 0;
	   
	}
	
	
	public int updateBoat (int key1,int key2, String boattype, String length){
		
		// Convert the length to a double
		double doubleLength;
		
	    try {
	    	doubleLength = Double.parseDouble (length);
		}
		catch (NumberFormatException e){
			return ECODE103; // error 103
		}
		
	    
	    Member memberObject = memberData.get(key1);
	    
	    boolean result = memberObject.updateBoatData (key2,boattype,doubleLength);
	    		
        if (result == false) return ECODE105;
		
		return storeAllBoats ();
	    		
	}
	
	public int removeBoat (int key1,int key2){
		
        Member memberObject = memberData.get(key1);
	    
	    boolean result = memberObject.removeBoat (key2);
	    		
        if (result == false) return ECODE105;
        
		return storeAllBoats ();
			    		
	}
	
	public String getMemberBoatType (int key1, int key2){
		
        Member memberObject = memberData.get(key1);
	    return memberObject.getMemberBoatType (key2);
	    		
	}
	public double getMemberBoatLength (int key1, int key2){
		
        Member memberObject = memberData.get(key1);
	    return memberObject.getMemberBoatLength (key2);
		
	}

	
	
	private int storeAllBoats (){
				
		Set <Integer> hashKeys = memberData.keySet();
		Iterator<Integer> iter = hashKeys.iterator();
		
		// Store all Objects
		
		boolean append = false; // overwrite the file
		
		while (iter.hasNext() == true){
			
			int key = iter.next();
				
		    Member memberObject = memberData.get(key);
		    
		    if (memberObject.getNoOfBoats()>0){
		    		       
		       if (memberObject.storeAllBoats(file,append) == -1) return ECODE104; // error 104
	           append = true; // following objects append to the new file
			
		   }
		}
		
		return 0;
		
	}

	
    public Member getMember (int key){
    	
    	if (memberData.containsKey(key)) return memberData.get(key);
    	else return null;
    	
    }
	
}
