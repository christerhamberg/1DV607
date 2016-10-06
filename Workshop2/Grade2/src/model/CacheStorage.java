package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CacheStorage implements CacheStorageInterface{	
	
	int ECODE100 = 100;  // Can not convert date of birth
	int ECODE101 = 101;  // Can not allocate SystemId
	int ECODE102 = 102;  // Failed to store new Member to file 
	int ECODE103 = 103;  // Can not convert length of the boat 
	int ECODE104 = 104;  // Failed to store boat 
	int ECODE105 = 105;  // Failed to update boat 

	private Map <Integer,Member> memberData = new HashMap <Integer,Member> ();
	private Map <String,Boat> boatData = new HashMap <String,Boat> ();
	
	private FileStorage file;
	
	
	public CacheStorage (String filePath){
		
		// initialize and read data if any
		file = new FileStorage (filePath);
		
		// initialize FileStorage
		if (file.initFileStorage() == false) file = null;
		
		// read any existing data
		if (file.rebuildCache(this) == false) file = null;
		
	}
	
	public int storeNewMember (String name, String socSecId){
		
		// Convert socSecId to an integer
		
		int intSocSecId;
		
	    try {
			intSocSecId = Integer.parseInt (socSecId);
		}
		catch (NumberFormatException e){
			return ECODE100; // error 100
		}
			
		
		// request a new ID
		
		int id = file.getNextSystemId ();

		if (id==-1) return ECODE101; // error 101
		
		// Create a new MemberData instance
		
		Member mb = new Member (id,name,intSocSecId);
		
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
		Boat bt = new Boat (id,type,length);
		
		int noOfBoats = memberData.get(id).getNoOfBoats() + 1;
		memberData.get(id).setNoOfBoats(noOfBoats);
				
		String boatKey = "" + id +"_" +noOfBoats;
		boatData.put (boatKey,bt);

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
	
	public int getMemberPersonalNumber (int key){
		
		if (memberData.containsKey(key) == true){
			return memberData.get(key).getPersonalNumber();
		}
		else return -1;	
		
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
		
		// remove any boat if they exist
		int boats = memberData.get(key).getNoOfBoats();
		if (boats>0){
			
			for (int loopMe=1;boats>=loopMe;loopMe++){
				
				String boatKey = "" + key +"_" +loopMe;
				
				if (boatData.containsKey(boatKey) == true){
					
					// remove the boat object 
					boatData.remove(boatKey);
				
				}
					
			}
			
			storeAllBoats ();
			
		}
		
		// remove the member Object
		memberData.remove(key);
		
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
		
		Boat bt = new Boat (key,boattype,doubleLength);
		
		// Store the data in the file structure
		
		if (file.writeNewBoatToFile(bt.writeToFile(), true) == -1) return ECODE104; // error 104
			
		// update number of boats for the user
		
		int noOfBoats = memberData.get(key).getNoOfBoats() + 1;
		memberData.get(key).setNoOfBoats(noOfBoats);
		
		String boatKey = "" + key +"_" +noOfBoats;
		
		// add the MemberData instance to the HashMap
		
		boatData.put (boatKey,bt);
		
		// return result
		
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
		
		String boatKey = "" + key1 +"_" +key2;
		
		if (boatData.containsKey(boatKey) == true){
			
			boatData.get(boatKey).setBoatType(boattype);
			boatData.get(boatKey).setBoatLength(doubleLength);

			return storeAllBoats ();
			
		}
		else return ECODE105;
	    		
	}
	
	public int removeBoat (int key1,int key2){
		
		String boatKey = "" + key1 +"_" +key2;
		
		if (boatData.containsKey(boatKey) == true){
			
			// remove the boat object 
			boatData.remove(boatKey);
			
			// decrease number of owned boats
			
			int noOfBoats = memberData.get(key1).getNoOfBoats();
			memberData.get(key1).setNoOfBoats(noOfBoats-1);

			// rehash boats if needed 
			
			if (key2<noOfBoats){
				
				for (int loopMe=key2;(noOfBoats-1)>=loopMe;loopMe++){
					
					String boatKey1 = "" + key1 +"_" +(loopMe+1);
					String boatKey2 = "" + key1 +"_" +(loopMe);

					boatData.put(boatKey2, boatData.get(boatKey1));
							
				}
				
				boatKey = "" + key1 +"_" +(noOfBoats);

				boatData.remove(boatKey);

			
			}
			
			return storeAllBoats ();
			
		}
		else return ECODE105;
	    		
	}
	
	public String getMemberBoatType (int key, int boat){
		
		String boatKey = "" +key + "_" +boat;
		
		if (boatData.containsKey(boatKey) == true){
			return boatData.get(boatKey).getBoatType();
		}
		else return "-1";	
		
	}
	public double getMemberBoatLength (int key, int boat){
		
		String boatKey = "" +key + "_" +boat;
		
		if (boatData.containsKey(boatKey) == true){
			return boatData.get(boatKey).getBoatLength();
		}
		
		else return -1D;	
		
	}

	private int storeAllBoats (){
		
		Set <String> hashKeys = boatData.keySet();
		Iterator<String> iter = hashKeys.iterator();
		
		// Store all Objects
		
		while (iter.hasNext() == true){
			
			boolean append = false; // overwrite the file
			
			while (iter.hasNext() == true){
			
				String key = iter.next();
				
				if (file.writeNewBoatToFile(boatData.get(key).writeToFile(), append) == -1) return ECODE104; // error 104
				append = true; // following objects append to the new file
				
			}
			
		}
		
		return 0;
		
	}
	
}
