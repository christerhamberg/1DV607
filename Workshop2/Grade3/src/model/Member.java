package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Member {
	
	private String name = "";
	private long personalNumber = 0;
	private int id;
	private int dateOfBirth = 0;
	private int bornMonth = 0;
	
	// STORAGE FOR THE BOATS OF THE MEMBER
	private Map <String,Boat> boatData = new HashMap <String,Boat> ();

	public Member (int i, String n, long no){
		
		this.id = i;
		this.name = n;
		this.personalNumber = no;
		
		String dateOfBirth = "" +personalNumber;
		this.dateOfBirth = Integer.parseInt(dateOfBirth.substring(0, 8));
		this.bornMonth = Integer.parseInt(dateOfBirth.substring(4, 6));
	
	}
	
	public void setId (int i){
		
		this.id = i;
		
	}
    public void setName (String n){
		
    	this.name = n;
    	
	}
    public void setPersonalNumber (int pno){
	
    	this.personalNumber = pno;
    	
    }
    
    public int getId (){
    	return this.id;
    }

    public String getName (){
    	return this.name;
    }
    
    public long getPersonalNumber (){
    	return this.personalNumber;
    }
    public int getNoOfBoats (){
    	return boatData.size();
    }
    
    public String writeToFile (){
    	return "" +this.id +";" +this.name +";" +this.personalNumber + "\n";
    }

    // BOAT STUFF
    
    boolean initNewBoat (String boatType, double boatLength){
    	
    	int memberKey = this.getId();
    	
		String boatKey = "" + memberKey +"_" +(boatData.size()+1);
		
		// validate boatKey
		if (boatData.containsKey(boatKey) == true) return false;
    	
		// create a boat object
    	Boat bt = new Boat (memberKey,boatType,boatLength);
    	
    	// update internal boat object list
    	boatData.put(boatKey, bt);

    	return true;
    	
    }
    
    boolean storeNewBoat (FileStorage file, String boatType, double boatLength){
    	
    	int memberKey = this.getId();
    	
		String boatKey = "" + memberKey +"_" +(boatData.size()+1);
		
		// validate boatKey
		if (boatData.containsKey(boatKey) == true) return false;
    	
		// create a boat object
    	Boat bt = new Boat (memberKey,boatType,boatLength);
    	
    	// update internal boat object list
    	boatData.put(boatKey, bt);
    	
    	// store the boat to file
		file.writeNewBoatToFile (bt.writeToFile(),true);
    	
    	return true;
    	
    }
    
    public boolean updateBoatData (int key, String boatType, double boatLength){
    	
    	int memberKey = this.getId();
    	
		String boatKey = "" + memberKey +"_" +key;
		
		// Validate that the boat Object exists
		if (boatData.containsKey(boatKey) == false) return false;
		
		// Get the boat Object
		Boat bt = boatData.get(boatKey);
		
		// Update the boat Object 
		
		bt.setBoatType (boatType);
		bt.setBoatLength(boatLength);
		
		return true;
    	
    }
    
    public boolean removeAllBoats (){
    	
    	boatData.clear();
		
		return true;
    	
    }
    
    public boolean removeBoat (int key){
    	
      	int memberKey = this.getId();
    	
    	String boatKey = "" + memberKey +"_" +key;
    		
    	// Validate that the boat Object exists
    	if (boatData.containsKey(boatKey) == false) return false;
		
		int noOfBoats = boatData.size();
		
		if (key<noOfBoats){
						
		   for (int loopMe=key;noOfBoats>=loopMe;loopMe++){
							
			   String boatKey1 = "" + memberKey +"_" +(loopMe+1);
			   String boatKey2 = "" + memberKey +"_" +(loopMe);			   
			   boatData.put(boatKey2, boatData.get(boatKey1));
									
		   }
		   
				
		}

	    boatData.remove(memberKey +"_" +(noOfBoats));

		return true;

    }
    
    public String getMemberBoatType (int key){
    	
      	int memberKey = this.getId();
    	
    	String boatKey = "" + memberKey +"_" +key;
    	    		
    	// Validate that the boat Object exists
    	if (boatData.containsKey(boatKey) == false) return null;
    	else{
    		
    		Boat bt = boatData.get(boatKey);
    		return bt.getBoatType ();
    		
    	}
    	
    }
    
    public double getMemberBoatLength (int key){
    	
      	int memberKey = this.getId();
    	
    	String boatKey = "" + memberKey +"_" +key;
    		
    	// Validate that the boat Object exists
    	if (boatData.containsKey(boatKey) == false) return -1;
    	else{
    		
    		Boat bt = boatData.get(boatKey);
    		return bt.getBoatLength ();
    		
    	}
    	
    }
    
    public int storeAllBoats (FileStorage file, boolean append){
    	
		Set <String> hashKeys = boatData.keySet();
		Iterator<String> iter = hashKeys.iterator();
				
	    while (iter.hasNext() == true){
	    	
			  Boat bt = boatData.get(iter.next());
			  			  
			  file.writeNewBoatToFile (bt.writeToFile(),append);
			  append = true; // only first boat will possiby clear the file
				
		}

	    // RETURN SUCCESSFUL
	    return 0;
	    
    }
    
    public int storeBoatToFile (FileStorage file, int key){
    	
      	int memberKey = this.getId();
    	
    	String boatKey = "" + memberKey +"_" +key;
    		
    	// Validate that the boat Object exists
    	if (boatData.containsKey(boatKey) == false) return -1;
    	else{
    		
    		Boat bt = boatData.get(boatKey);
			
    		// ALWAYS APPEND
    		return file.writeNewBoatToFile (bt.writeToFile(),true);  
			  
    	}
    	
    }
    
    public int getDateOfBirth (){
    	return dateOfBirth;
    }
    
    public int getBornMonth (){
    	return bornMonth;
    }

    
}



