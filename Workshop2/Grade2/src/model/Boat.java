package model;

public class Boat {
	
	private static String[] BOAT_TYPES = {"Sailboat","Motorsailer","Kayak/Canoe","Other"}; 

	private int boatOwner = 0;
	private String boatType = "";
	private double boatLength = 0;
			
	public Boat (int owner, String type, double l){
		
		this.boatOwner = owner;
		this.boatType = type;
		this.boatLength = l;
		
	}
	
	public int getBoatOwner (){
		return this.boatOwner;
	}
	public String getBoatType (){
		return this.boatType;
	}
	public double getBoatLength (){
		return this.boatLength;
	}
	
	public void setBoatOwner (int owner){
		this.boatOwner = owner;
	}
	public void setBoatType (String type){
		this.boatType = type;
	}
	public void setBoatLength (double l){
		this.boatLength = l;
	}
	
	public static String [] boatTypes (){
		return BOAT_TYPES;
	}
	
	public String writeToFile (){
		return "" +this.boatOwner +";" +this.boatType +";" +this.boatLength +"\n";
	}
	

	

	
}
