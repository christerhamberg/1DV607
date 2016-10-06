package model;

public class Member {
	
	private String name = "";
	private int personalNumber = 0;
	private int id;
	private int noOfBoats = 0;
	
	public Member (int i, String n, int no){
		
		this.id = i;
		this.name = n;
		this.personalNumber = no;
		
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
    public void setNoOfBoats (int boats){
    	
    	this.noOfBoats = boats;
    	
    }
    
    public int getId (){
    	return this.id;
    }

    public String getName (){
    	return this.name;
    }
    
    public int getPersonalNumber (){
    	return this.personalNumber;
    }
    public int getNoOfBoats (){
    	return this.noOfBoats;
    }
    
    public String writeToFile (){
    	return "" +this.id +";" +this.name +";" +this.personalNumber + "\n";
    }

}



